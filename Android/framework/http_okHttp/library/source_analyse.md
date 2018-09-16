### 源码分析  
### RealCall#enqueue
OkHttpClient#newCall  -->    
RealCall#enqueue  -->  
Dispatcher#enqueue   ->  
```
synchronized (this) {
  if (executed) throw new IllegalStateException("Already Executed");
  executed = true;
}
captureCallStackTrace();
eventListener.callStart(this);
client.dispatcher().enqueue(new AsyncCall(responseCallback));   //  往下执行  A1处  
```
### ♬ A1：RealCall.AsyncCall#execute   
```
@Override 
protected void execute() {
    boolean signalledCallback = false;
    try {
      Response response = getResponseWithInterceptorChain();  // 往下执行 A2处  
      if (retryAndFollowUpInterceptor.isCanceled()) {
        signalledCallback = true;
        responseCallback.onFailure(RealCall.this, new IOException("Canceled"));
      } else {
        signalledCallback = true;
        responseCallback.onResponse(RealCall.this, response);
      }
    } catch (IOException e) {
      if (signalledCallback) {
        // Do not signal the callback twice!
        Platform.get().log(INFO, "Callback failure for " + toLoggableString(), e);
      } else {
        eventListener.callFailed(RealCall.this, e);
        responseCallback.onFailure(RealCall.this, e);
      }
    } finally {
      client.dispatcher().finished(this);
    }
  }
}
```
### ♬ A2:  RealCall#getResponseWithInterceptorChain  
```
Response getResponseWithInterceptorChain() throws IOException {
  // Build a full stack of interceptors.
  List<Interceptor> interceptors = new ArrayList<>();
  //  这里可以看出，我们自定义的Interceptor会被优先执行
  interceptors.addAll(client.interceptors());
  interceptors.add(retryAndFollowUpInterceptor);  //  失败重试/重定向  
  interceptors.add(new BridgeInterceptor(client.cookieJar()));  //  请求/响应转换；  负责把用户构造的请求转换为发送给服务器的请求，把服务器返回的响应转换为对用户友好的响应    
  interceptors.add(new CacheInterceptor(client.internalCache()));  //  负责读取缓存以及更新缓存  
  interceptors.add(new ConnectInterceptor(client));  //  负责与服务器建立连接  
  if (!forWebSocket) {
    interceptors.addAll(client.networkInterceptors());
  }
  interceptors.add(new CallServerInterceptor(forWebSocket));  //  负责从服务器读取响应的数据  

  Interceptor.Chain chain = new RealInterceptorChain(interceptors, null, null, null, 0,
      originalRequest, this, eventListener, client.connectTimeoutMillis(),
      client.readTimeoutMillis(), client.writeTimeoutMillis());

  return chain.proceed(originalRequest);
}
```
### RetryAndFollowUpInterceptor#intercept
```
@Override 
public Response intercept(Chain chain) throws IOException {
  Request request = chain.request();
  RealInterceptorChain realChain = (RealInterceptorChain) chain;
  Call call = realChain.call();
  EventListener eventListener = realChain.eventListener();

  
   //1. 构建一个StreamAllocation对象，StreamAllocation相当于是个管理类，维护了Connections、Streams和Calls之间的管理，该类初始化一个Socket连接对象，获取输入/输出流对象。
  StreamAllocation streamAllocation = new StreamAllocation(client.connectionPool(),
      createAddress(request.url()), call, eventListener, callStackTrace);
  this.streamAllocation = streamAllocation;


  //  重定向次数
  int followUpCount = 0;
  Response priorResponse = null;
  while (true) {
    if (canceled) {
      streamAllocation.release();
      throw new IOException("Canceled");
    }

    Response response;
    boolean releaseConnection = true;
    try {
      //  2. 继续执行下一个Interceptor，即BridgeInterceptor
      response = realChain.proceed(request, streamAllocation, null, null);
      releaseConnection = false;
    } catch (RouteException e) {
      //  3. 抛出异常，则检测连接是否还可以继续。
      // The attempt to connect via a route failed. The request will not have been sent.
      if (!recover(e.getLastConnectException(), streamAllocation, false, request)) {   // 参见 ♬ B1 处
        throw e.getLastConnectException();
      }
      releaseConnection = false;
      continue;
    } catch (IOException e) {
      //  和服务端建立连接失败
      // An attempt to communicate with a server failed. The request may have been sent.
      boolean requestSendStarted = !(e instanceof ConnectionShutdownException);
      if (!recover(e, streamAllocation, requestSendStarted, request)) throw e;
      releaseConnection = false;
      continue;
    } finally {
      //  检测到其他未知异常，则释放连接和资源
      // We're throwing an unchecked exception. Release any resources.
      if (releaseConnection) {
        streamAllocation.streamFailed(null);
        streamAllocation.release();
      }
    }

    //  构建响应体，这个响应体的body为空。
    // Attach the prior response if it exists. Such responses never have a body.
    if (priorResponse != null) {
      response = response.newBuilder()
          .priorResponse(priorResponse.newBuilder()
                  .body(null)
                  .build())
          .build();
    }

    //  4。根据响应码处理请求，返回Request不为空时则进行重定向处理。 参见 B2 处
    Request followUp = followUpRequest(response, streamAllocation.route());

    if (followUp == null) {
      if (!forWebSocket) {
        streamAllocation.release();
      }
      return response;
    }

    closeQuietly(response.body());

    //  重定向的次数不能超过20次
    if (++followUpCount > MAX_FOLLOW_UPS) {
      streamAllocation.release();
      throw new ProtocolException("Too many follow-up requests: " + followUpCount);
    }

    if (followUp.body() instanceof UnrepeatableRequestBody) {
      streamAllocation.release();
      throw new HttpRetryException("Cannot retry streamed HTTP body", response.code());
    }

    if (!sameConnection(response, followUp.url())) {
      streamAllocation.release();
      streamAllocation = new StreamAllocation(client.connectionPool(),
          createAddress(followUp.url()), call, eventListener, callStackTrace);
      this.streamAllocation = streamAllocation;
    } else if (streamAllocation.codec() != null) {
      throw new IllegalStateException("Closing the body of " + response
          + " didn't close its backing stream. Bad interceptor?");
    }

    //  把重定向的请求赋值给request,再次进入循环执行
    request = followUp;
    priorResponse = response;
  }
}

```
### ♬ B1：RetryAndFollowUpInterceptor#recover  
```
private boolean recover(IOException e, StreamAllocation streamAllocation,
    boolean requestSendStarted, Request userRequest) {
  streamAllocation.streamFailed(e);

  //  客户端不允许重试    
  // The application layer has forbidden retries.
  if (!client.retryOnConnectionFailure()) return false;

  //  无法再次发送请求体  
  // We can't send the request body again.
  if (requestSendStarted && userRequest.body() instanceof UnrepeatableRequestBody) return false;

  //  发生致命的异常
  // This exception is fatal.
  if (!isRecoverable(e, requestSendStarted)) return false;

  //  没有更多的路由转发地址  
  // No more routes to attempt.
  if (!streamAllocation.hasMoreRoutes()) return false;

  //  为连接失败，做恢复，使用相同的路由转发器，创建一个新的 连接  
  // For failure recovery, use the same route selector with a new connection.
  return true;
}
```
### ♬ B2：RetryAndFollowUpInterceptor#followUpRequest  
```
private Request followUpRequest(Response userResponse, Route route) throws IOException {
  if (userResponse == null) throw new IllegalStateException();
  int responseCode = userResponse.code();

  final String method = userResponse.request().method();
  switch (responseCode) {
  
    //  407，代理认证
    case HTTP_PROXY_AUTH:
      Proxy selectedProxy = route != null
          ? route.proxy()
          : client.proxy();
      if (selectedProxy.type() != Proxy.Type.HTTP) {
        throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
      }
      return client.proxyAuthenticator().authenticate(route, userResponse);

    //  401，未经认证
    case HTTP_UNAUTHORIZED:
      return client.authenticator().authenticate(route, userResponse);

    //  307，308
    case HTTP_PERM_REDIRECT:
    case HTTP_TEMP_REDIRECT:
      // "If the 307 or 308 status code is received in response to a request other than GET
      // or HEAD, the user agent MUST NOT automatically redirect the request"
      if (!method.equals("GET") && !method.equals("HEAD")) {
        return null;
      }
    //  300，301，302，303
     // fall-through
    case HTTP_MULT_CHOICE:
    case HTTP_MOVED_PERM:
    case HTTP_MOVED_TEMP:
    case HTTP_SEE_OTHER:
      // Does the client allow redirects?
      //  客户端在配置中是否允许重定向
      if (!client.followRedirects()) return null;


      //  header 中Location字段，解析到重定向后的url  
      String location = userResponse.header("Location");
      if (location == null) return null;
      HttpUrl url = userResponse.request().url().resolve(location);

      //  url为null，不允许重定向
      // Don't follow redirects to unsupported protocols.
      if (url == null) return null;

      //  查询是否存在http与https之间的重定向，默认不在http--https之间重定向
      // If configured, don't follow redirects between SSL and non-SSL.
      boolean sameScheme = url.scheme().equals(userResponse.request().url().scheme());
      if (!sameScheme && !client.followSslRedirects()) return null;

      // Most redirects don't include a request body.
      Request.Builder requestBuilder = userResponse.request().newBuilder();
      //  根据请求方法，判断重定向时，是否携带原来的请求
      if (HttpMethod.permitsRequestBody(method)) {
        final boolean maintainBody = HttpMethod.redirectsWithBody(method);
        if (HttpMethod.redirectsToGet(method)) {
          requestBuilder.method("GET", null);
        } else {
          RequestBody requestBody = maintainBody ? userResponse.request().body() : null;
          requestBuilder.method(method, requestBody);
        }
        //  如果不转发原来的请求体，那么移除相关的 header  
        if (!maintainBody) {
          requestBuilder.removeHeader("Transfer-Encoding");
          requestBuilder.removeHeader("Content-Length");
          requestBuilder.removeHeader("Content-Type");
        }
      }

      //  跨越主机时，需要移除 Authorization
      // When redirecting across hosts, drop all authentication headers. This
      // is potentially annoying to the application layer since they have no
      // way to retain them.
      if (!sameConnection(userResponse, url)) {
        requestBuilder.removeHeader("Authorization");
      }

      return requestBuilder.url(url).build();

    //  408，超时
    case HTTP_CLIENT_TIMEOUT:
      // 408's are rare in practice, but some servers like HAProxy use this response code. The
      // spec says that we may repeat the request without modifications. Modern browsers also
      // repeat the request (even non-idempotent ones.)
      if (!client.retryOnConnectionFailure()) {
        // The application layer has directed us not to retry the request.
        return null;
      }

      if (userResponse.request().body() instanceof UnrepeatableRequestBody) {
        return null;
      }

      if (userResponse.priorResponse() != null
          && userResponse.priorResponse().code() == HTTP_CLIENT_TIMEOUT) {
        // We attempted to retry and got another timeout. Give up.
        return null;
      }

      if (retryAfter(userResponse, 0) > 0) {
        return null;
      }

      return userResponse.request();

    case HTTP_UNAVAILABLE:
      if (userResponse.priorResponse() != null
          && userResponse.priorResponse().code() == HTTP_UNAVAILABLE) {
        // We attempted to retry and got another timeout. Give up.
        return null;
      }

      if (retryAfter(userResponse, Integer.MAX_VALUE) == 0) {
        // specifically received an instruction to retry without delay
        return userResponse.request();
      }

      return null;

    default:
      return null;
  }
}

```
### BridgeInterceptor#intercept  
负责把用户构造的请求转换为发送给服务器的请求，把服务器返回的响应转换为对用户友好的响应。  
```
@Override 
public Response intercept(Chain chain) throws IOException {
  Request userRequest = chain.request();
  Request.Builder requestBuilder = userRequest.newBuilder();

  RequestBody body = userRequest.body();
  if (body != null) {
  
    //  进行Header的包装
    MediaType contentType = body.contentType();
    if (contentType != null) {
      requestBuilder.header("Content-Type", contentType.toString());
    }

    long contentLength = body.contentLength();
    if (contentLength != -1) {
      requestBuilder.header("Content-Length", Long.toString(contentLength));
      requestBuilder.removeHeader("Transfer-Encoding");
    } else {
      requestBuilder.header("Transfer-Encoding", "chunked");
      requestBuilder.removeHeader("Content-Length");
    }
  }

  if (userRequest.header("Host") == null) {
    requestBuilder.header("Host", hostHeader(userRequest.url(), false));
  }

  if (userRequest.header("Connection") == null) {
    requestBuilder.header("Connection", "Keep-Alive");
  }

  //  这里有个坑：如果你在请求的时候主动添加了"Accept-Encoding: gzip" ，transparentGzip=false，那你就要自己解压，如果你没有解压，或导致response.string()乱码。
  // If we add an "Accept-Encoding: gzip" header field we're responsible for also decompressing
  // the transfer stream.
  boolean transparentGzip = false;
  if (userRequest.header("Accept-Encoding") == null && userRequest.header("Range") == null) {
    transparentGzip = true;
    requestBuilder.header("Accept-Encoding", "gzip");
  }

  //  创建OkhttpClient配置的cookieJar
  List<Cookie> cookies = cookieJar.loadForRequest(userRequest.url());
  if (!cookies.isEmpty()) {
    requestBuilder.header("Cookie", cookieHeader(cookies));
  }

  if (userRequest.header("User-Agent") == null) {
    requestBuilder.header("User-Agent", Version.userAgent());
  }

  Response networkResponse = chain.proceed(requestBuilder.build());

  //  解析服务器返回的Header，如果没有cookie，则不进行解析
  HttpHeaders.receiveHeaders(cookieJar, userRequest.url(), networkResponse.headers());

  Response.Builder responseBuilder = networkResponse.newBuilder()
      .request(userRequest);

  //  判断服务器是否支持gzip压缩，如果支持，则将压缩提交给Okio库来处理
  if (transparentGzip
      && "gzip".equalsIgnoreCase(networkResponse.header("Content-Encoding"))
      && HttpHeaders.hasBody(networkResponse)) {
    GzipSource responseBody = new GzipSource(networkResponse.body().source());
    Headers strippedHeaders = networkResponse.headers().newBuilder()
        .removeAll("Content-Encoding")
        .removeAll("Content-Length")
        .build();
    responseBuilder.headers(strippedHeaders);
    String contentType = networkResponse.header("Content-Type");
    responseBuilder.body(new RealResponseBody(contentType, -1L, Okio.buffer(responseBody)));
  }

  return responseBuilder.build();
}
```
### 关于 gzip  
开发者没有添加Accept-Encoding时，自动添加Accept-Encoding: gzip  
自动添加Accept-Encoding，会对request，response进行自动解压  
手动添加Accept-Encoding，不负责解压缩  
自动解压时移除Content-Length，所以上层Java代码想要contentLength时为-1  
自动解压时移除 Content-Encoding  
自动解压时，如果是分块传输编码，Transfer-Encoding: chunked不受影响。  
### CacheInterceptor#intercept  
```
@Override 
public Response intercept(Chain chain) throws IOException {

  //  读取候选缓存
  Response cacheCandidate = cache != null
      ? cache.get(chain.request())
      : null;

  long now = System.currentTimeMillis();

  //  创建缓存策略，强制缓存、对比缓存等
  CacheStrategy strategy = new CacheStrategy.Factory(now, chain.request(), cacheCandidate).get();
  Request networkRequest = strategy.networkRequest;
  Response cacheResponse = strategy.cacheResponse;

  if (cache != null) {
    cache.trackResponse(strategy);
  }

  if (cacheCandidate != null && cacheResponse == null) {
    closeQuietly(cacheCandidate.body()); // The cache candidate wasn't applicable. Close it.
  }

  //  根据策略，不使用网络，又没有缓存的直接报错，并返回错误码504
  // If we're forbidden from using the network and the cache is insufficient, fail.
  if (networkRequest == null && cacheResponse == null) {
    return new Response.Builder()
        .request(chain.request())
        .protocol(Protocol.HTTP_1_1)
        .code(504)
        .message("Unsatisfiable Request (only-if-cached)")
        .body(Util.EMPTY_RESPONSE)
        .sentRequestAtMillis(-1L)
        .receivedResponseAtMillis(System.currentTimeMillis())
        .build();
  }

  //  根据策略，不使用网络，有缓存的直接返回。
  // If we don't need the network, we're done.
  if (networkRequest == null) {
    return cacheResponse.newBuilder()
        .cacheResponse(stripBody(cacheResponse))
        .build();
  }

  Response networkResponse = null;
  try {
    //  前面两个都没有返回，继续执行下一个Interceptor，即ConnectInterceptor
    networkResponse = chain.proceed(networkRequest);
  } finally {
    //  如果发生IO异常，则释放掉缓存
    // If we're crashing on I/O or otherwise, don't leak the cache body.
    if (networkResponse == null && cacheCandidate != null) {
      closeQuietly(cacheCandidate.body());
    }
  }

  //  接收到网络结果，如果响应code式304，则使用缓存，返回缓存结果
  // If we have a cache response too, then we're doing a conditional get.
  if (cacheResponse != null) {
    if (networkResponse.code() == HTTP_NOT_MODIFIED) {
      Response response = cacheResponse.newBuilder()
          .headers(combine(cacheResponse.headers(), networkResponse.headers()))
          .sentRequestAtMillis(networkResponse.sentRequestAtMillis())
          .receivedResponseAtMillis(networkResponse.receivedResponseAtMillis())
          .cacheResponse(stripBody(cacheResponse))
          .networkResponse(stripBody(networkResponse))
          .build();
      networkResponse.body().close();

      // Update the cache after combining headers but before stripping the
      // Content-Encoding header (as performed by initContentStream()).
      cache.trackConditionalCacheHit();
      cache.update(cacheResponse, response);
      return response;
    } else {
      closeQuietly(cacheResponse.body());
    }
  }

  //  读取网络结果
  Response response = networkResponse.newBuilder()
      .cacheResponse(stripBody(cacheResponse))
      .networkResponse(stripBody(networkResponse))
      .build();

  //  对数据进行缓存
  if (cache != null) {
    if (HttpHeaders.hasBody(response) && CacheStrategy.isCacheable(response, networkRequest)) {
      // Offer this request to the cache.
      CacheRequest cacheRequest = cache.put(response);
      return cacheWritingResponse(cacheRequest, response);
    }

    if (HttpMethod.invalidatesCache(networkRequest.method())) {
      try {
        cache.remove(networkRequest);
      } catch (IOException ignored) {
        // The cache cannot be written.
      }
    }
  }

  //  返回网络读取的结果
  return response;
}
```
### StreamAllocation#findConnection  
```

private RealConnection findConnection(int connectTimeout, int readTimeout, int writeTimeout,
    int pingIntervalMillis, boolean connectionRetryEnabled) throws IOException {
  boolean foundPooledConnection = false;
  RealConnection result = null;
  Route selectedRoute = null;
  Connection releasedConnection;
  Socket toClose;
  synchronized (connectionPool) {
    if (released) throw new IllegalStateException("released");
    if (codec != null) throw new IllegalStateException("codec != null");
    if (canceled) throw new IOException("Canceled");

    //  查看是否有完好的连接
    // Attempt to use an already-allocated connection. We need to be careful here because our
    // already-allocated connection may have been restricted from creating new streams.
    releasedConnection = this.connection;
    toClose = releaseIfNoNewStreams();
    if (this.connection != null) {
      // We had an already-allocated connection and it's good.
      result = this.connection;
      releasedConnection = null;
    }
    if (!reportedAcquired) {
      // If the connection was never reported acquired, don't report it as released!
      releasedConnection = null;
    }

    if (result == null) {
      //  连接池中是否用可用的连接，有则使用
      // Attempt to get a connection from the pool.
      Internal.instance.get(connectionPool, address, this, null);
      if (connection != null) {
        foundPooledConnection = true;
        result = connection;
      } else {
        selectedRoute = route;
      }
    }
  }
  closeQuietly(toClose);

  if (releasedConnection != null) {
    eventListener.connectionReleased(call, releasedConnection);
  }
  if (foundPooledConnection) {
    eventListener.connectionAcquired(call, result);
  }
  if (result != null) {
    // If we found an already-allocated or pooled connection, we're done.
    return result;
  }

  // If we need a route selection, make one. This is a blocking operation.
  boolean newRouteSelection = false;
  if (selectedRoute == null && (routeSelection == null || !routeSelection.hasNext())) {
    newRouteSelection = true;
    routeSelection = routeSelector.next();
  }

  //  如果没有可用连接，则自己创建一个
  synchronized (connectionPool) {
    if (canceled) throw new IOException("Canceled");

    if (newRouteSelection) {
      // Now that we have a set of IP addresses, make another attempt at getting a connection from
      // the pool. This could match due to connection coalescing.
      List<Route> routes = routeSelection.getAll();
      for (int i = 0, size = routes.size(); i < size; i++) {
        Route route = routes.get(i);
        Internal.instance.get(connectionPool, address, this, route);
        if (connection != null) {
          foundPooledConnection = true;
          result = connection;
          this.route = route;
          break;
        }
      }
    }

    if (!foundPooledConnection) {
      if (selectedRoute == null) {
        selectedRoute = routeSelection.next();
      }

      // Create a connection and assign it to this allocation immediately. This makes it possible
      // for an asynchronous cancel() to interrupt the handshake we're about to do.
      route = selectedRoute;
      refusedStreamCount = 0;
      result = new RealConnection(connectionPool, selectedRoute);
      acquire(result, false);
    }
  }

  // If we found a pooled connection on the 2nd time around, we're done.
  if (foundPooledConnection) {
    eventListener.connectionAcquired(call, result);
    return result;
  }

  //  开始TCP以及TLS握手操作
  // Do TCP + TLS handshakes. This is a blocking operation.
  result.connect(connectTimeout, readTimeout, writeTimeout, pingIntervalMillis,
      connectionRetryEnabled, call, eventListener);
  routeDatabase().connected(result.route());

  //   将新创建的连接，放在连接池中
  Socket socket = null;
  synchronized (connectionPool) {
    reportedAcquired = true;

    // Pool the connection.
    Internal.instance.put(connectionPool, result);

    // If another multiplexed connection to the same address was created concurrently, then
    // release this connection and acquire that one.
    if (result.isMultiplexed()) {
      socket = Internal.instance.deduplicate(connectionPool, address, this);
      result = connection;
    }
  }
  closeQuietly(socket);

  eventListener.connectionAcquired(call, result);
  return result;
}
```
### RealConnection#connect  
```
public void connect(int connectTimeout, int readTimeout, int writeTimeout,
    int pingIntervalMillis, boolean connectionRetryEnabled, Call call,
    EventListener eventListener) {
  if (protocol != null) throw new IllegalStateException("already connected");

  //  线路选择
  RouteException routeException = null;
  List<ConnectionSpec> connectionSpecs = route.address().connectionSpecs();
  ConnectionSpecSelector connectionSpecSelector = new ConnectionSpecSelector(connectionSpecs);

  if (route.address().sslSocketFactory() == null) {
    if (!connectionSpecs.contains(ConnectionSpec.CLEARTEXT)) {
      throw new RouteException(new UnknownServiceException(
          "CLEARTEXT communication not enabled for client"));
    }
    String host = route.address().url().host();
    if (!Platform.get().isCleartextTrafficPermitted(host)) {
      throw new RouteException(new UnknownServiceException(
          "CLEARTEXT communication to " + host + " not permitted by network security policy"));
    }
  }

  //   开始连接
  while (true) {
    try {
      //  如果是通道模式，则建立通道连接
      if (route.requiresTunnel()) {
        connectTunnel(connectTimeout, readTimeout, writeTimeout, call, eventListener);
        if (rawSocket == null) {
          // We were unable to connect the tunnel but properly closed down our resources.
          break;
        }
      } else {
      
        //  否则进行Socket连接，一般都是属于这种情况
        connectSocket(connectTimeout, readTimeout, call, eventListener);
      }
      //  建立https连接
      establishProtocol(connectionSpecSelector, pingIntervalMillis, call, eventListener);
      eventListener.connectEnd(call, route.socketAddress(), route.proxy(), protocol);
      break;
    } catch (IOException e) {
      closeQuietly(socket);
      closeQuietly(rawSocket);
      socket = null;
      rawSocket = null;
      source = null;
      sink = null;
      handshake = null;
      protocol = null;
      http2Connection = null;

      eventListener.connectFailed(call, route.socketAddress(), route.proxy(), null, e);

      if (routeException == null) {
        routeException = new RouteException(e);
      } else {
        routeException.addConnectException(e);
      }

      if (!connectionRetryEnabled || !connectionSpecSelector.connectionFailed(e)) {
        throw routeException;
      }
    }
  }

  if (route.requiresTunnel() && rawSocket == null) {
    ProtocolException exception = new ProtocolException("Too many tunnel connections attempted: "
        + MAX_TUNNEL_ATTEMPTS);
    throw new RouteException(exception);
  }

  if (http2Connection != null) {
    synchronized (connectionPool) {
      allocationLimit = http2Connection.maxConcurrentStreams();
    }
  }
}
```

### 内置拦截器  
RetryAndFollowUpInterceptor：负责失败重试以及重定向。  
BridgeInterceptor：负责把用户构造的请求转换为发送给服务器的请求，把服务器返回的响应转换为对用户友好的响应。  
CacheInterceptor：负责读取缓存以及更新缓存。  
ConnectInterceptor：负责与服务器建立连接。  
CallServerInterceptor：负责从服务器读取响应的数据。   
### 参考  
https://juejin.im/post/5a704ed05188255a8817f4c9  
