package com.susy.clientsocket;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    static final String PREFERENCES = "PROJECT_SOCKET_LAB";


    public static void setDataObject(Context context, String data) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(data, "data_object");
        editor.commit();
    }

    public static String getDataObject(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES, context.MODE_PRIVATE);
        return preferences.getString("data_object", "not_data");
    }

    public static void setIpAddr(Context context, String data) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("ip_addr",data);
        editor.commit();
    }

    public static String getIpAddr(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES, context.MODE_PRIVATE);
        return preferences.getString("ip_addr", null);
    }

    public static void setPort(Context context, String data) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("port",data);
        editor.commit();
    }

    public static String getPort(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES, context.MODE_PRIVATE);
        return preferences.getString("port", null);
    }


}
