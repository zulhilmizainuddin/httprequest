package com.github.zulhilmizainuddin.httpurlconnectionwrapper;


import android.text.TextUtils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public abstract class Http {
    protected final HttpURLConnection connection;

    private final HttpParameter parameter;

    public Http(String url) throws IOException {
        URL requestUrl = new URL(url);
        connection = (HttpURLConnection) requestUrl.openConnection();

        parameter = new HttpParameter();
    }

    public Http setRequestHeaders(Map<String, String> requestHeaders) {
        if (requestHeaders != null) {
            parameter.setRequestHeaders(requestHeaders);

            for (Map.Entry<String, String> header: parameter.getRequestHeaders().entrySet()) {
                connection.setRequestProperty(header.getKey(), header.getValue());
            }
        }

        return this;
    }

    public Http setCookies(List<HttpCookie> cookies) {
        if (cookies != null) {
            parameter.setCookies(cookies);

            connection.setRequestProperty("Cookie", TextUtils.join(";", parameter.getCookies()));
        }

        return this;
    }

    public Http setConnectionTimeout(int connectionTimeout) {
        parameter.setConnectionTimeout(connectionTimeout);

        connection.setConnectTimeout(parameter.getConnectionTimeout());

        return this;
    }

    public Http setReadTimeout(int readTimeout) {
        parameter.setReadTimeout(readTimeout);

        connection.setReadTimeout(parameter.getReadTimeout());

        return this;
    }

    public Http setRequestBody(Map<String, String> requestBody) throws IOException {
        if (requestBody != null) {
            parameter.setRequestBody(requestBody);

            connection.setDoOutput(true);

            StringBuilder requestBodyBuilder = new StringBuilder();
            for (Map.Entry<String, String> data: parameter.getRequestBody().entrySet()) {
                requestBodyBuilder.append(String.format("%s=%s&", data.getKey(), data.getValue()));
            }

            requestBodyBuilder.deleteCharAt(requestBodyBuilder.length() - 1);

            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(requestBodyBuilder.toString());

            outputStream.flush();
            outputStream.close();
        }

        return this;
    }

    public Http setFollowRedirects(boolean followRedirects) {
        parameter.setFollowRedirects(followRedirects);

        connection.setInstanceFollowRedirects(parameter.getFollowRedirects());

        return this;
    }

    public abstract int execute() throws IOException;
}
