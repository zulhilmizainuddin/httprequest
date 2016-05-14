package com.github.zulhilmizainuddin.httprequest;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

final class StringStreamConverter implements StreamConverter {
    @Override
    public String convert(InputStream inputStream) throws IOException {
        if (inputStream == null)
            return null;

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();

        try {
            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
        } finally {
            inputStream.close();
        }
        return builder.toString();
    }
}
