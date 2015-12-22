package com.github.zulhilmizainuddin.httpurlconnectionwrapper;


import java.io.IOException;
import java.net.HttpCookie;
import java.util.List;
import java.util.Map;

public final class HttpGet extends Http implements HttpBuilder {

    public HttpGet(String url) throws IOException {
        super(url);
        connection.setRequestMethod(HttpVerb.GET);
    }

    @Override
    public Http setRequestHeaders(Map<String, String> requestHeaders) {
        parameter.setRequestHeaders(requestHeaders);
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
    public Http setRequestBody(Map<String, String> requestBody) {
        throw new UnsupportedOperationException("Not supported for HTTP GET");
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

            responseCode = connection.getResponseCode();

            retrieveRedirectUrl();
            retrieveResponseCookies();
            retrieveResponseBody();
        }
        finally {
            connection.disconnect();
        }

        return responseCode;
    }
}
