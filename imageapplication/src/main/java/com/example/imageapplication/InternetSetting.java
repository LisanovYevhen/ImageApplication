package com.example.imageapplication;

import android.net.Uri;
import android.util.JsonReader;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class InternetSetting {
    private final static String GIPHY_API_BASE_URL="https://api.giphy.com";
    private final static String GIPHY_METHOD="/v1/gifs/search";
    private final static String GIPHY_API_KEY="VpnipUbZGsUKLHfViqxkbklE6giIpG35";

    private final static String GIPHY_API_PARAMETER_QUERY="q";
    private final static String GIPHY_API_PARAMETER_LIMIT="limit";
    private final static String GIPHY_API_PARAMETER_OFFSET="offset";
    private final static String GIPHY_API_PARAMETER_RATING="rating";
    private final static String GIPHY_API_PARAMETER_LANG="lang";



    public static URL createURL(/*String query,String limit,String offset,String rating,String language*/){
        Uri uri= Uri.parse(GIPHY_API_BASE_URL+GIPHY_METHOD).buildUpon()
                .appendQueryParameter("api_key",GIPHY_API_KEY)
                .appendQueryParameter(GIPHY_API_PARAMETER_QUERY,"china")
                .appendQueryParameter(GIPHY_API_PARAMETER_LIMIT,"5")
                .appendQueryParameter(GIPHY_API_PARAMETER_OFFSET,"0")
                .appendQueryParameter(GIPHY_API_PARAMETER_RATING,"g")
                .appendQueryParameter(GIPHY_API_PARAMETER_LANG,"en")
                .build();
        URL url=null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            Log.e(FirstActivity.LOG_TAG,"Проблема с URL строкой");
        }
        return url;
    };

    public static String responseJson(URL url)  {
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream= httpURLConnection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream),45000);
            StringBuilder stringBuilder = new StringBuilder(45000);

            String line= new String();

            while ((line=bufferedReader.readLine())!=null){
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            Log.e(FirstActivity.LOG_TAG,"Не удалось открыть httpURLConnection или проблема с BufferedReader");
        }
        finally {
            httpURLConnection.disconnect();
            Log.d(FirstActivity.LOG_TAG,"Отключено");
        }
        return null;
    }
}
