package com.github.zulhilmizainuddin.httpurlconnectionwrapper;


import java.util.HashMap;
import java.util.Map;

public final class StreamConverterFactory {
    private static final Map<String, Class<?>> encodingTypeMap = new HashMap<>();

    static {
        encodingTypeMap.put("", StringStreamConverter.class);
        encodingTypeMap.put("gzip", GzipStreamConverter.class);
    }

    public static StreamConverter getConverter(String encodingType) {
        if (encodingType == null)
            encodingType = "";

        StreamConverter streamConverter = null;

        try {
            streamConverter =
                    (StreamConverter)encodingTypeMap.get(encodingType.toLowerCase())
                                                    .newInstance();
        }
        catch (InstantiationException | IllegalAccessException ex) {}

        return streamConverter;
    }
}
