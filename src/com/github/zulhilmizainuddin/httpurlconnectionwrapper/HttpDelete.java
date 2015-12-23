package com.github.zulhilmizainuddin.httpurlconnectionwrapper;


import java.io.IOException;
import java.net.HttpCookie;
import java.util.List;
import java.util.Map;

public final class HttpDelete extends Http {

    public HttpDelete(String url) throws IOException {
        super(url);
        connection.setRequestMethod(HttpVerb.DELETE);
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
    public Http setRequestBody(String requestBody) {
        throw new UnsupportedOperationException("Not supported for HTTP DELETE");
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
