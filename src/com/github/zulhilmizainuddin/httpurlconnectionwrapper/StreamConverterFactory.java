package com.github.zulhilmizainuddin.httpurlconnectionwrapper;


import java.util.HashMap;
import java.util.Map;

public final class StreamConverterFactory {
    private static final Map<String, StreamConverter> encodingTypeMap = new HashMap<>();

    static {
        encodingTypeMap.put("", new StringStreamConverter());
        encodingTypeMap.put("gzip", new GzipStreamConverter());
    }

    public static StreamConverter getConverter(String encodingType) {
        if (encodingType == null)
            encodingType = "";

        return encodingTypeMap.get(encodingType.toLowerCase());
    }
}
