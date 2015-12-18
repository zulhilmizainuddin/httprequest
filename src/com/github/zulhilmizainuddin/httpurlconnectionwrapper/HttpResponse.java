package com.github.zulhilmizainuddin.httpurlconnectionwrapper;


import java.net.CookieManager;

public final class HttpResponse {
    private final CookieManager cookieManager = new CookieManager();
    private String redirectUrl = "";

    public CookieManager getCookieManager() {
        return cookieManager;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
