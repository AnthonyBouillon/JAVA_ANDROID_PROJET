package com.example.json;

import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Retrieves data from an API
 */
public class HttpHandler {

    // URL to get contacts JSON
    private static String mUrl = "https://api.androidhive.info/contacts/";

    // Simple name : Name class | getName : folder/folder/class name
    private static final String sNameClass = HttpHandler.class.getSimpleName();

    // Constructor
    public HttpHandler(){

    }

    /**
     *
     * @return data of l'api in json
     */
    public String callHttp(){
        String response = null;
        try {
            // Takes as parameter the url of the API
            URL url = new URL(mUrl);

            // Goes to the API url and opens the connection to the HTTP server
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Specifies how the data should be retrieved
            connection.setRequestMethod("GET");

            // Read input streams (bytes)
            InputStream is = new BufferedInputStream(connection.getInputStream());

            // Convert input streams (bytes) to a character string
            response = convertStreamToString(is);

            // Close the reading
            is.close();
        }catch (Exception e){
            Log.e(sNameClass, e.getMessage());
        }

        // Returns json data
        return response;
    }

    /**
     * Convet input streams to string
     * @param is
     * @return
     */
    public String convertStreamToString(InputStream is){

        // Reading the InputStream
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        // Allows you to add other elements to the recovered data
        StringBuilder stringBuilder = new StringBuilder();

        String line;
        try {

            // readLine() : JSONObject type => assign to variable String
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append('\n');
            }
        } catch (Exception e) {
            e.getMessage();
        }

        return stringBuilder.toString();
    }
}
