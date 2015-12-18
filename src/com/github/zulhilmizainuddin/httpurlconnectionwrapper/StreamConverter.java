package com.github.zulhilmizainuddin.httpurlconnectionwrapper;


import java.io.IOException;
import java.io.InputStream;

public interface StreamConverter {
    String convert(InputStream inputStream) throws IOException;
}
