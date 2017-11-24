package org.alex.okhttp;


import org.alex.util.LogTrack;
import org.alex.util.ObjUtil;
import org.jetbrains.annotations.Contract;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * 作者：Alex
 * 时间：2017/4/10 11:20
 * 简述：
 */
@SuppressWarnings({"StringConcatenationInsideStringBufferAppend", "ConstantConditions"})
public class HttpLogInterceptor implements Interceptor {
    private static final Charset CHARSET = Charset.forName("UTF-8");
    private Type type = Type.I;
    private static HttpLogInterceptor logInterceptor;

    public enum Type {
        W, E, I
    }

    private HttpLogInterceptor() {
    }

    public static HttpLogInterceptor getInstance() {
        if (logInterceptor == null) {
            synchronized (HttpLogInterceptor.class) {
                logInterceptor = (logInterceptor == null) ? new HttpLogInterceptor() : logInterceptor;
            }
        }
        return logInterceptor;
    }

    public HttpLogInterceptor setType(Type type) {
        this.type = type == null ? Type.I : type;
        return this;
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        boolean cannotPrintLog = false;
        if (cannotPrintLog) {
            return chain.proceed(request);
        }
        //请求日志拦截
        log4Request(request);
        //执行请求，计算请求时间
        long startNs = System.nanoTime();
        Response response;
        try {
            response = chain.proceed(request);
        } catch (Exception e) {
            LogTrack.i("<-- HTTP FAILED: " + e);
            throw e;
        }
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);
        //响应日志拦截
        return log4Response(request, response, tookMs);
    }

    @SuppressWarnings({"ConstantConditions", "StringConcatenationInsideStringBufferAppend"})
    private void log4Request(Request request) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        RequestBody requestBody = request.body();

        String contentType = "";
        if (requestBody.contentType() != null) {
            contentType = requestBody.contentType().type() + "" + requestBody.contentType().subtype();
        }
        try {
            if (ObjUtil.isNotEmpty(contentType)) {
                stringBuilder.append("contentType = " + contentType + "\n");
            }
            stringBuilder.append(request.method() + "  " + getFormatBody(request.url().toString(), false));
            Headers headers = request.headers();
            int count = (headers == null) ? 0 : headers.size();
            stringBuilder.append(count > 0 ? "\n请求头:" : "");
            for (int i = 0; i < count; i++) {
                String name = headers.name(i);
                stringBuilder.append("\n" + name + "=" + headers.value(i));
            }

            if (requestBody != null) {
                boolean multiplePart = isMultiplePart(requestBody.contentType());
                //LogTrack.e(requestBody.contentType()+"  "+multiplePart);
                stringBuilder.append(bodyToString(request, isMultiplePart(requestBody.contentType())));
            }

        } catch (Exception e) {
            LogTrack.e(e);
        }

        printLog("打印请求参数: \n" + stringBuilder.toString());
    }

    private Response log4Response(Request request, Response response, long tookMs) {
        StringBuilder stringBuilder = new StringBuilder();
        Response.Builder builder = response.newBuilder();
        Response cloneResponse = builder.build();
        ResponseBody responseBody = cloneResponse.body();

        try {
            stringBuilder.append(cloneResponse.request().method() + ' ' + cloneResponse.request().url() + " (" + tookMs + "ms）");

            Headers headers = cloneResponse.headers();
            for (int i = 0, count = headers.size(); i < count; i++) {
                String name = headers.name(i);
                if (!"User-Agent".equalsIgnoreCase(name) && !"Accept-Language".equalsIgnoreCase(name) && !"Server".equalsIgnoreCase(name) && !"Date".equalsIgnoreCase(name) && !"Content-Type".equalsIgnoreCase(name) && !"Transfer-Encoding".equalsIgnoreCase(name) && !"Content-Length".equalsIgnoreCase(name)) {
                    stringBuilder.append("\n" + name + "=" + headers.value(i));
                }
            }
            String body = responseBody.string();
            stringBuilder.append("\nbody:" + body);
            responseBody = ResponseBody.create(responseBody.contentType(), body);
            printLog("打印返回数据: \n" + stringBuilder.toString());

            return response.newBuilder().body(responseBody).build();

        } catch (Exception e) {
            LogTrack.e(e);
        }

        printLog("打印返回数据: \n" + stringBuilder.toString());
        return response;
    }

    private void printLog(String result) {
        if (Type.E == type) {
            LogTrack.e(result);
        } else if (Type.I == type) {
            LogTrack.i(result);
        } else if (Type.W == type) {
            LogTrack.w(result);
        }
    }

    private StringBuilder bodyToString(Request request, boolean hasMultiplePart) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            Charset charset = CHARSET;
            MediaType contentType = copy.body().contentType();
            if (contentType != null) {
                charset = contentType.charset(CHARSET);
            }
            String readString = buffer.readString(charset);
            String formatBody = getFormatBody(readString, hasMultiplePart);
            if (formatBody != null && formatBody.length() > 0) {
                stringBuilder.append("\n请求体:\n" + formatBody);
            }
        } catch (Exception e) {
            LogTrack.e(e);
        }
        return stringBuilder;
    }

    private String getFormatBody(String body, boolean hasMultiplePart) {
        if (body == null || body.length() <= 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < body.length(); i++) {
            if (hasMultiplePart && !isUsualASCII(body.charAt(i))) {
                break;
            }
            stringBuilder.append((body.charAt(i) == '&' || body.charAt(i) == '?') ? '\n' : body.charAt(i));
        }
        return stringBuilder.toString();
    }

    @SuppressWarnings("SameParameterValue")
    @Contract("null, null -> true; null, !null -> false; !null, null -> false")
    private static boolean contains(Object words, Object letter) {
        return words == null && letter == null || !(words == null && letter != null) && !(words != null && letter == null) && words.toString().contains(letter.toString());
    }

    @Contract(pure = true)
    private boolean isUsualASCII(char ch) {
        return ((int) ch) >= 0 && ((int) ch) <= 126 || '\n' == ch || '\r' == ch;
    }

    @Contract("null -> false")
    private static boolean isMultiplePart(MediaType mediaType) {
        return mediaType != null && contains(mediaType.type(), "multipart");
    }
}
