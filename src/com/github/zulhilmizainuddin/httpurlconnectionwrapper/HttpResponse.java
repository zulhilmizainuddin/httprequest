package com.github.zulhilmizainuddin.httpurlconnectionwrapper;


import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class HttpResponse {
    private final Map<String, List<String>> headers = new HashMap<>();
    private final List<HttpCookie> cookies = new ArrayList<>();
    private String redirectUrl = "";
    private String body = "";

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public List<HttpCookie> getCookies() {
        return cookies;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
