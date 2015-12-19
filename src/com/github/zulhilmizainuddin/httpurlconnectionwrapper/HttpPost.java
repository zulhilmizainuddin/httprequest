package com.github.zulhilmizainuddin.httpurlconnectionwrapper;


import java.io.IOException;

public final class HttpPost extends Http {

    public HttpPost(String url) throws IOException {
        super(url);
        connection.setRequestMethod(HttpVerb.POST);
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
