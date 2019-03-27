package com.example.json;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Récupère les données d'un fichier json
 */
public class HttpHandler {
    private static final String sNameClass = HttpHandler.class.getSimpleName();

    public HttpHandler(){

    }

    public String callHttp(String requestUrl){
        String response = null;
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read the response
            InputStream is = new BufferedInputStream(connection.getInputStream());
            response = convertStreamToString(is);
            is.close();
        }catch (Exception e){
            Log.e(sNameClass, e.getMessage());
        }
        return response;
    }

    public String convertStreamToString(InputStream is){
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;

        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return sb.toString();
    }
}
