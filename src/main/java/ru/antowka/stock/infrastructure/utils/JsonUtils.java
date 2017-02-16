package ru.antowka.stock.infrastructure.utils;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Operation with JSON
 */
public class JsonUtils {

    /**
     * Parse JSON from URL
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static JsonNode readJsonFromUrl(String url) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = null;

        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

            StringBuilder sb = new StringBuilder();
            int cp;

            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }

            json = mapper.readValue(sb.toString(), JsonNode.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }
}
