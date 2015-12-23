package com.github.zulhilmizainuddin.httpurlconnectionwrapper;


import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;

public final class HttpResponse {
    private final List<HttpCookie> cookies = new ArrayList<>();
    private String redirectUrl = "";
    private String responseBody = "";

    public List<HttpCookie> getCookies() {
        return cookies;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }
}
