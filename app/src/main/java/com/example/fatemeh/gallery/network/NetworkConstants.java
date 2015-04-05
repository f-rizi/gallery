package com.example.fatemeh.gallery.network;

/**
 * Created by fatemeh on 31/03/15.
 */

public class NetworkConstants {
    public static final String CLIENT_ID = "044838a303bc73c";

    public static final String AUTHORIZATION = "Authorization";

    // section/sort/window/page/show viral
    public static final String GALLERY_URL = "https://api.imgur.com/3/gallery/%s/%s/%s/%d?showViral=%b";

    public static String getAuthorizationHeader() {
        return "Client-ID " + CLIENT_ID;
    }

}
