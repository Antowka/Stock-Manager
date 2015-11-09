package ru.antowka.stock.utils;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by Anton Nik on 09.11.15.
 */
public class Json {

    /**
     * Parse JSON from URL
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static JSONArray readJsonFromUrl(String url) throws IOException {

        JSONArray json = null;

        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

            StringBuilder sb = new StringBuilder();
            int cp;

            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }

            json = new JSONArray(sb.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;
    }
}
