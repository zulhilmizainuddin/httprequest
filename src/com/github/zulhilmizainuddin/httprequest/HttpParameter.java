package com.github.zulhilmizainuddin.httprequest;


import java.net.HttpCookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class HttpParameter {
    private final Map<String, String> requestHeaders = new HashMap<>();
    private List<HttpCookie> cookies;
    private int connectionTimeout;
    private int readTimeout;
    private String requestBody;
    private boolean followRedirects;

    public Map<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeader(String key, String value) {
        requestHeaders.put(key, value);
    }

    public void setRequestHeaders(Map<String, String> requestHeaders) {
        this.requestHeaders.putAll(requestHeaders);
    }

    public List<HttpCookie> getCookies() {
        return cookies;
    }

    public void setCookies(List<HttpCookie> cookies) {
        this.cookies = cookies;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public boolean getFollowRedirects() {
        return followRedirects;
    }

    public void setFollowRedirects(boolean followRedirects) {
        this.followRedirects = followRedirects;
    }
}
