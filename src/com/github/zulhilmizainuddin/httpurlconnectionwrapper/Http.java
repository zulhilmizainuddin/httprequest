package com.github.zulhilmizainuddin.httpurlconnectionwrapper;


import android.text.TextUtils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public abstract class Http {
    protected final HttpURLConnection connection;

    private final HttpParameter parameter = new HttpParameter();
    private final HttpResponse response = new HttpResponse();

    public Http(String url) throws IOException {
        URL requestUrl = new URL(url);
        connection = (HttpURLConnection) requestUrl.openConnection();
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

    protected void retrieveResponseCookies() {
        List<String> cookiesHeader = connection.getHeaderFields().get("Set-Cookie");
        if (cookiesHeader != null) {
            for (String cookie : cookiesHeader) {
               response.getCookieManager().getCookieStore().add(null, HttpCookie.parse(cookie).get(0));
            }
        }
    }

    public CookieStore getResponseCookies() {
        return response.getCookieManager().getCookieStore();
    }

    protected void retrieveRedirectUrl() {
        List<String> locationHeader = connection.getHeaderFields().get("Location");
        if (locationHeader != null)
        {
            response.setRedirectUrl(locationHeader.get(0));
        }
    }

    public String getRedirectUrl() {
        return response.getRedirectUrl();
    }

    protected void retrieveResponseBody() throws IOException {
        StreamConverter streamConverter =
                StreamConverterFactory.getConverter(connection.getContentEncoding());

        try {
            InputStream inputStream = connection.getInputStream();
            if (inputStream != null) {
                response.setResponseBody(streamConverter.convert(inputStream));
            }
        }
        catch (IOException inputStreamException) {
            try {
                InputStream errorStream = connection.getErrorStream();
                if (errorStream != null) {
                    response.setResponseBody(streamConverter.convert(errorStream));
                }
            }
            catch (IOException errorStreamException) {
                throw errorStreamException;
            }
        }
    }

    public String getResponseBody() {
        return response.getResponseBody();
    }

    public abstract int execute() throws IOException;
}
