package com.github.zulhilmizainuddin.httprequest;


import android.text.TextUtils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public abstract class Http {
    protected final HttpURLConnection connection;

    protected final HttpParameter parameter = new HttpParameter();
    private final HttpResponse response = new HttpResponse();

    protected Http(String url) throws IOException {
        URL requestUrl = new URL(url);
        connection = (HttpURLConnection) requestUrl.openConnection();
    }

    protected void setRequestHeaders() {
        if (parameter.getRequestHeaders() != null) {
            for (Map.Entry<String, String> header: parameter.getRequestHeaders().entrySet()) {
                connection.setRequestProperty(header.getKey(), header.getValue());
            }
        }
    }

    protected void setCookies() {
        if (parameter.getCookies() != null) {
            connection.setRequestProperty("Cookie", TextUtils.join(";", parameter.getCookies()));
        }
    }

    protected void setConnectionTimeout() {
        if (parameter.getConnectionTimeout() != 0) {
            connection.setConnectTimeout(parameter.getConnectionTimeout());
        }
    }

    protected void setReadTimeout() {
        if (parameter.getReadTimeout() != 0) {
            connection.setReadTimeout(parameter.getReadTimeout());
        }
    }

    protected void setRequestBody() throws IOException {
        if (parameter.getRequestBody() != null) {
            connection.setDoOutput(true);

            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(parameter.getRequestBody());
            outputStream.flush();
            outputStream.close();
        }
    }

    protected void setFollowRedirects() {
        if (!parameter.getFollowRedirects()) {
            connection.setInstanceFollowRedirects(parameter.getFollowRedirects());
        }
    }

    protected void retrieveResponseHeaders() {
        Map<String, List<String>> headers = connection.getHeaderFields();
        if (headers != null) {
            response.getHeaders().putAll(headers);
        }
    }

    public Map<String, List<String>> getResponseHeaders() {
        return response.getHeaders();
    }

    protected void retrieveResponseCookies() {
        List<String> cookiesHeader = connection.getHeaderFields().get("Set-Cookie");
        if (cookiesHeader != null) {
            for (String cookie : cookiesHeader) {
                response.getCookies().add(HttpCookie.parse(cookie).get(0));
            }
        }
    }

    public List<HttpCookie> getResponseCookies() {
        return response.getCookies();
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

        if (streamConverter == null)
            throw new IOException("Failed to get stream converter");

        try {
            InputStream inputStream = connection.getInputStream();
            if (inputStream != null) {
                response.setBody(streamConverter.convert(inputStream));
            }
        }
        catch (IOException inputStreamException) {
            try {
                InputStream errorStream = connection.getErrorStream();
                if (errorStream != null) {
                    response.setBody(streamConverter.convert(errorStream));
                }
            }
            catch (IOException errorStreamException) {
                throw errorStreamException;
            }
        }
    }

    public String getResponseBody() {
        return response.getBody();
    }

    public abstract Http setRequestHeader(String key, String value);
    public abstract Http setRequestHeaders(Map<String, String> requestHeaders);
    public abstract Http setCookie(HttpCookie httpCookie);
    public abstract Http setCookies(List<HttpCookie> cookies);
    public abstract Http setConnectionTimeout(int connectionTimeout);
    public abstract Http setReadTimeout(int readTimeout);
    public abstract Http setRequestBody(String requestBody);
    public abstract Http setFollowRedirects(boolean followRedirects);

    public abstract int execute() throws IOException;
}
