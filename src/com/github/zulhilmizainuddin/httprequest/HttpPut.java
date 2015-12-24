package com.github.zulhilmizainuddin.httprequest;


import java.io.IOException;
import java.net.HttpCookie;
import java.util.List;
import java.util.Map;

public final class HttpPut extends Http {

    public HttpPut(String url) throws IOException {
        super(url);
        connection.setRequestMethod(HttpVerb.PUT);
    }

    @Override
    public Http setHeader(String key, String value) {
        parameter.setRequestHeader(key, value);
        return this;
    }

    @Override
    public Http setHeaders(Map<String, String> headers) {
        parameter.setRequestHeaders(headers);
        return this;
    }

    @Override
    public Http setCookie(HttpCookie cookie) {
        parameter.setCookie(cookie);
        return this;
    }

    @Override
    public Http setCookies(List<HttpCookie> cookies) {
        parameter.setCookies(cookies);
        return this;
    }

    @Override
    public Http setConnectionTimeout(int connectionTimeout) {
        parameter.setConnectionTimeout(connectionTimeout);
        return this;
    }

    @Override
    public Http setReadTimeout(int readTimeout) {
        parameter.setReadTimeout(readTimeout);
        return this;
    }

    @Override
    public Http setBody(String body) {
        parameter.setRequestBody(body);
        return this;
    }

    @Override
    public Http setFollowRedirects(boolean followRedirects) {
        parameter.setFollowRedirects(followRedirects);
        return this;
    }

    @Override
    public int execute() throws IOException {
        int responseCode = -1;

        try {
            setRequestHeaders();
            setCookies();
            setConnectionTimeout();
            setReadTimeout();
            setFollowRedirects();
            setRequestBody();

            responseCode = connection.getResponseCode();

            retrieveResponseHeaders();
            retrieveResponseCookies();
            retrieveRedirectUrl();
            retrieveResponseBody();
        }
        finally {
            connection.disconnect();
        }

        return responseCode;
    }
}
