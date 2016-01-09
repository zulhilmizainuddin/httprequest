package com.github.zulhilmizainuddin.httprequest;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.InflaterInputStream;

public final class DeflateStreamConverter implements StreamConverter {
    @Override
    public String convert(InputStream inputStream) throws IOException {
        if (inputStream == null)
            return null;

        StringBuilder builder = new StringBuilder();

        try {
            InputStream deflateInputStream = new InflaterInputStream(inputStream);
            BufferedReader reader = new BufferedReader(new InputStreamReader(deflateInputStream, "UTF-8"));

            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
        }
        finally {
            inputStream.close();
        }

        return builder.toString();
    }
}
