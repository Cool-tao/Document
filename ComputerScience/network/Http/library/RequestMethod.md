### GET与 POST 的区别

◆ GET 请求  
用于获取消息实体；  
参数在长链接之后 的 ? 之后，用key=value形式并用&符号分割；  
如果要传中文参数，必须要用URLEncoding  和 URLDecoding 进行交互；  
不能添加body；    
◆ POST 请求  
用来传输消息实体；  
参数可以放在长链接之后，和GET请求一样；    
参数可以放在并body，参数的编码形式，要和后端统一，最好要用UTF-8；  
◆ PUT 请求  
PUT 方法用来传输文件；  
就像 FTP 协议的文件上传一样，要求在请求报文的主体中包含文件内容，然后保存到请求 URI 指定的位置。  
但是，鉴于 HTTP/1.1 的 PUT 方法自身不带验证机制，任何人都可以上传文件 , 存在安全性问题，因此一般的 Web 网站不使用该方法。  
若配合 Web 应用程序的验证机制，或架构设计采用 REST（REpresentationalState Transfer，表征状态转移）标准的同类 Web 网站，就可能会开放使用 PUT 方法。  
◆ HEAD 请求  
用于获得报文首部，不返回报文主体部分。用于确认URI 的有效性及资源更新的日期时间等，例如：返回index.html有关的响应首部。  
◆ DELETE 请求  
DELETE 方法用来删除文件，是与 PUT 相反的方法。 DELETE 方法按请求 URI 删除指定的资源；  
但是， HTTP/1.1 的 DELETE 方法本身和 PUT 方法一样不带验证机制，所以一般的 Web 网站也不使用 DELETE 方法。  
当配合 Web 应用程序的验证机制，或遵守 REST 标准时还是有可能会开放使用的。  
◆ OPTIONS 请求  
OPTIONS 方法用来查询针对请求 URI 指定的资源支持的方法。  
响应体例如：  
```
HTTP/1.1 200 OK
Allow: GET, POST, HEAD, OPTIONS
```
◆ TRACE 请求  
客户端通过 TRACE 方法可以查询发送出去的请求是怎样被加工修改 / 篡改的。  
这是因为，请求想要连接到源目标服务器可能会通过代理中转， TRACE 方法就是用来确认连接过程中发生的一系列操作。    
但是， TRACE 方法本来就不怎么常用，再加上它容易引发 XST（Cross-Site Tracing，跨站追踪）攻击，通常就更不会用到了。  
◆ CONNECT 请求    
CONNECT 方法要求在与代理服务器通信时建立隧道，实现用隧道协议进行 TCP 通信。  
主要使用 SSL（Secure Sockets Layer，安全套接层）和 TLS（Transport Layer Security，传输层安全）协议把通信内容加密后经网络隧道传输。  
