package com.example.imageapplication;

import android.net.Uri;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import java.util.ArrayList;

public class InternetSetting {
    private final static String GIPHY_API_BASE_URL="https://api.giphy.com";
    private final static String GIPHY_METHOD="/v1/gifs/search";
    private final static String GIPHY_API_KEY="VpnipUbZGsUKLHfViqxkbklE6giIpG35";

    private final static String GIPHY_API_PARAMETER_QUERY="q";
    private final static String GIPHY_API_PARAMETER_LIMIT="limit";
    private final static String GIPHY_API_PARAMETER_OFFSET="offset";
    private final static String GIPHY_API_PARAMETER_RATING="rating";
    private final static String GIPHY_API_PARAMETER_LANG="lang";



    public static URL createURL(String query,String limit,String offset,String rating,String language){
        Uri uri= Uri.parse(GIPHY_API_BASE_URL+GIPHY_METHOD).buildUpon()
                .appendQueryParameter("api_key",GIPHY_API_KEY)
                .appendQueryParameter(GIPHY_API_PARAMETER_QUERY,query)
                .appendQueryParameter(GIPHY_API_PARAMETER_LIMIT,limit)
                .appendQueryParameter(GIPHY_API_PARAMETER_OFFSET,offset)
                .appendQueryParameter(GIPHY_API_PARAMETER_RATING,rating)
                .appendQueryParameter(GIPHY_API_PARAMETER_LANG,language)
                .build();
        URL url=null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            Log.e(FirstActivity.LOG_TAG,"Проблема с URL строкой");
        }
        return url;
    };

    public static String responseStringForJson(URL url)  {
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

    public static ArrayList<Information> valueJson(String responseString){
        ArrayList<Information> informationArrayList = new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(responseString);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            Information information;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectForArray = jsonArray.getJSONObject(i);
                String s=jsonObjectForArray.getString("title");
                String title= s.split(" GIF")[0];
                String type= jsonObjectForArray.getString("type");
                String username=jsonObjectForArray.getString("username");
                String import_datetime=jsonObjectForArray.getString("import_datetime");
                JSONObject jsonObjectImages =jsonObjectForArray.getJSONObject("images");
                JSONObject jsonObjectFixed_Width_Small_Still=jsonObjectImages.getJSONObject("fixed_width_small_still");
                String url=jsonObjectFixed_Width_Small_Still.getString("url");
                information= new Information(url,type,username,title,import_datetime);
                informationArrayList.add(information);
            }
        } catch (JSONException e) {
            Log.e(FirstActivity.LOG_TAG,"Cтрока ответа response не в формате json");
        }
        return informationArrayList;
    }
}
