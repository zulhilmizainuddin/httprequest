package com.github.zulhilmizainuddin.httpurlconnectionwrapper;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public final class HttpGet extends Http {

    public HttpGet(String url) throws IOException {
        super(url);
    }

    @Override
    public int execute() throws IOException {
        int responseCode = -1;

        try {
            connection.setRequestMethod(HttpVerb.GET);

            responseCode = connection.getResponseCode();
        }
        finally {
            connection.disconnect();
        }

        return responseCode;
    }
}
