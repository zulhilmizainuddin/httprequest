package com.github.zulhilmizainuddin.httprequest;


import java.util.HashMap;
import java.util.Map;

final class StreamConverterFactory {
    private static final Map<String, Class<? extends StreamConverter>> encodingTypeMap = new HashMap<>();

    static {
        encodingTypeMap.put("", StringStreamConverter.class);
        encodingTypeMap.put("gzip", GzipStreamConverter.class);
        encodingTypeMap.put("deflate", DeflateStreamConverter.class);
    }

    public static StreamConverter getConverter(String encodingType) {
        if (encodingType == null)
            encodingType = "";

        StreamConverter streamConverter = null;

        try {
            streamConverter = encodingTypeMap.get(encodingType.toLowerCase()).newInstance();
        }
        catch (InstantiationException ex) {}
        catch (IllegalAccessException ex) {}

        return streamConverter;
    }
}
