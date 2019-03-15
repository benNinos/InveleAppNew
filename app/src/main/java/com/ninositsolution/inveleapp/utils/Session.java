package com.ninositsolution.inveleapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Parthasarathy D on 1/17/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class Session {

    private String CategoryPosition = "CategoryPosition";

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private static final String device_id = "device_id";
    private static final String user_id = "user_id";
    private static final String user_name = "user_name";
    private static final String user_email = "user_email";
    private static final String user_phone = "user_phone";
    private static final String user_uid = "user_uid";
    private static final String user_photo = "user_photo";

    public Session(Context context)
    {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = preferences.edit();
    }

    public int getCategoryPosition() {
       return preferences.getInt(CategoryPosition, 1);
    }

    public void setCategoryPosition(int categoryPosition) {
        editor.putInt(CategoryPosition, categoryPosition).apply();
    }

    public static String getDevice_id(Context context) {
        return context.getSharedPreferences("Session", Context.MODE_PRIVATE).getString(device_id, "0");
    }

    public static void setDevice_id(String values, Context context) {
        context.getSharedPreferences("Session", Context.MODE_PRIVATE).edit().putString(device_id, values).apply();
    }

    public static String getUserId(Context context)
    {
        return context.getSharedPreferences("Session", Context.MODE_PRIVATE).getString(user_id, "");
    }

    public static void setUserId(String value, Context context)
    {
        context.getSharedPreferences("Session", Context.MODE_PRIVATE).edit().putString(user_id, value).apply();
    }

    public static String getUserName(Context context)
    {
        return context.getSharedPreferences("Session", Context.MODE_PRIVATE).getString(user_name, "");
    }

    public static void setUsername(String value, Context context)
    {
        context.getSharedPreferences("Session", Context.MODE_PRIVATE).edit().putString(user_name, value).apply();
    }

    public static String getUserEmail(Context context)
    {
        return context.getSharedPreferences("Session", Context.MODE_PRIVATE).getString(user_email, "");
    }

    public static void setUserEmail(String value, Context context)
    {
        context.getSharedPreferences("Session", Context.MODE_PRIVATE).edit().putString(user_email, value).apply();
    }

    public static String getUserPhone(Context context)
    {
        return context.getSharedPreferences("Session", Context.MODE_PRIVATE).getString(user_phone, "");
    }

    public static void setUserPhone(String value, Context context)
    {
        context.getSharedPreferences("Session", Context.MODE_PRIVATE).edit().putString(user_phone, value).apply();
    }

    public static String getUserUid(Context context)
    {
        return context.getSharedPreferences("Session", Context.MODE_PRIVATE).getString(user_id, "");
    }

    public static void setUserUid(String value, Context context)
    {
        context.getSharedPreferences("Session", Context.MODE_PRIVATE).edit().putString(user_id, value).apply();
    }

    public static String getUserPhoto(Context context)
    {
        return context.getSharedPreferences("Session", Context.MODE_PRIVATE).getString(user_photo, "");
    }

    public static void setUserPhoto(String value, Context context)
    {
        context.getSharedPreferences("Session", Context.MODE_PRIVATE).edit().putString(user_photo, value).apply();
    }
}
