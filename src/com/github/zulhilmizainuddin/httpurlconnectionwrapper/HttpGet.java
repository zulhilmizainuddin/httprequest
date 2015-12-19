package com.github.zulhilmizainuddin.httpurlconnectionwrapper;


import java.io.IOException;

public final class HttpGet extends Http {

    public HttpGet(String url) throws IOException {
        super(url);
        connection.setRequestMethod(HttpVerb.GET);
    }

    @Override
    public int execute() throws IOException {
        int responseCode = -1;

        try {
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
