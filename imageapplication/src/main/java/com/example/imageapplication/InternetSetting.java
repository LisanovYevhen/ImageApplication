package com.example.imageapplication;

import android.net.Uri;

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
            e.printStackTrace();
        }
        return url;

    };
}
