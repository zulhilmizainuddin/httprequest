package com.github.zulhilmizainuddin.httpurlconnectionwrapper;


import java.net.HttpCookie;
import java.util.List;
import java.util.Map;

public interface HttpBuilder {
    Http setRequestHeaders(Map<String, String> requestHeaders);
    Http setCookies(List<HttpCookie> cookies);
    Http setConnectionTimeout(int connectionTimeout);
    Http setReadTimeout(int readTimeout);
    Http setRequestBody(String requestBody);
    Http setFollowRedirects(boolean followRedirects);
}
