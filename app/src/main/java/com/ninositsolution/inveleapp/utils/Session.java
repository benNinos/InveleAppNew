package com.ninositsolution.inveleapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Created by Parthasarathy D on 1/17/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class Session {

    private static final String TAG = "Session";

    private String CategoryPosition = "CategoryPosition";

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private static final String device_id = "device_id";
    private static final String user_id = "user_id";
    private static final String user_first_name = "user_first_name";
    private static final String user_last_name = "user_last_name";
    private static final String user_email = "user_email";
    private static final String user_phone = "user_phone";
    private static final String user_dob = "user_dob";
    private static final String user_gender = "user_gender";
    private static final String user_uid = "user_uid";
    private static final String user_photo = "user_photo";
    private static final String is_email_registered = "is_email_registered";
    private static final String image_path = "image_path";
    private static final String is_logged = "is_logged";
    private static final String clearSession = "clearSession";

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
        Log.i(TAG, "setDevice_id -> "+values);
    }

    public static String getUserId(Context context)
    {
        return context.getSharedPreferences("Session", Context.MODE_PRIVATE).getString(user_id, "");
    }

    public static void setUserId(String value, Context context)
    {
        context.getSharedPreferences("Session", Context.MODE_PRIVATE).edit().putString(user_id, value).apply();
        Log.i(TAG, "setUserId -> "+value);
    }

    public static String getUserFirstName(Context context)
    {
        return context.getSharedPreferences("Session", Context.MODE_PRIVATE).getString(user_first_name, "");
    }

    public static void setUserFirstName(String value, Context context)
    {
        context.getSharedPreferences("Session", Context.MODE_PRIVATE).edit().putString(user_first_name, value).apply();
        Log.i(TAG, "setUserFirstName -> "+value);
    }

    public static String getUserLastName(Context context)
    {
        return context.getSharedPreferences("Session", Context.MODE_PRIVATE).getString(user_last_name, "");
    }

    public static void setUserLastName(String value, Context context)
    {
        context.getSharedPreferences("Session", Context.MODE_PRIVATE).edit().putString(user_last_name, value).apply();
        Log.i(TAG, "setUserLastName -> "+value);
    }

    public static String getUserEmail(Context context)
    {
        return context.getSharedPreferences("Session", Context.MODE_PRIVATE).getString(user_email, "");
    }

    public static void setUserEmail(String value, Context context)
    {
        context.getSharedPreferences("Session", Context.MODE_PRIVATE).edit().putString(user_email, value).apply();
        Log.i(TAG, "setUserEmail -> "+value);
    }

    public static String getUserPhone(Context context)
    {
        return context.getSharedPreferences("Session", Context.MODE_PRIVATE).getString(user_phone, "");
    }

    public static void setUserPhone(String value, Context context)
    {
        context.getSharedPreferences("Session", Context.MODE_PRIVATE).edit().putString(user_phone, value).apply();
        Log.i(TAG, "setUserPhone -> "+value);
    }

    public static String getUserDob(Context context)
    {
        return context.getSharedPreferences("Session", Context.MODE_PRIVATE).getString(user_dob, "");
    }

    public static void setUserDob(String value, Context context)
    {
        context.getSharedPreferences("Session", Context.MODE_PRIVATE).edit().putString(user_dob, value).apply();
        Log.i(TAG, "setUserDob -> "+value);
    }

    public static String getUserGender(Context context)
    {
        return context.getSharedPreferences("Session", Context.MODE_PRIVATE).getString(user_gender, "");
    }

    public static void setUserGender(String value, Context context)
    {
        context.getSharedPreferences("Session", Context.MODE_PRIVATE).edit().putString(user_gender, value).apply();
        Log.i(TAG, "setUserGender -> "+value);
    }

    public static String getUserUid(Context context)
    {
        return context.getSharedPreferences("Session", Context.MODE_PRIVATE).getString(user_uid, "");
    }

    public static void setUserUid(String value, Context context)
    {
        context.getSharedPreferences("Session", Context.MODE_PRIVATE).edit().putString(user_uid, value).apply();
        Log.i(TAG, "setUserUid -> "+value);
    }

    public static String getUserPhoto(Context context)
    {
        return context.getSharedPreferences("Session", Context.MODE_PRIVATE).getString(user_photo, "");
    }

    public static void setUserPhoto(String value, Context context)
    {
        context.getSharedPreferences("Session", Context.MODE_PRIVATE).edit().putString(user_photo, value).apply();
        Log.i(TAG, "setUserPhoto -> "+value);
    }

    public static Boolean getIsEmailRegistered(Context context)
    {
        return context.getSharedPreferences("Session", Context.MODE_PRIVATE).getBoolean(is_email_registered, false);
    }

    public static void setIsEmailRegistered(Boolean value, Context context)
    {
        context.getSharedPreferences("Session", Context.MODE_PRIVATE).edit().putBoolean(is_email_registered, value).apply();
        Log.i(TAG, "setIsEmailRegistered -> "+value);
    }

    public static String getImagePath(Context context)
    {
        return context.getSharedPreferences("Session", Context.MODE_PRIVATE).getString(image_path, null);
    }

    public static void setImagePath(String value, Context context)
    {
        context.getSharedPreferences("Session", Context.MODE_PRIVATE).edit().putString(image_path, value).apply();
        Log.i(TAG, "setImagePath -> "+value);
    }

    public static Boolean getIsLogged(Context context)
    {
        return context.getSharedPreferences("Session", Context.MODE_PRIVATE).getBoolean(is_logged, false);
    }

    public static void setIsLogged(Boolean value, Context context)
    {
        context.getSharedPreferences("Session", Context.MODE_PRIVATE).edit().putBoolean(is_logged, value).apply();
        Log.i(TAG, "setIsLogged -> "+value);
    }

    public static void clear(Context context)
    {
        setUserDob("", context);
        setUserEmail("", context);
        setUserFirstName("", context);
        setUserLastName("", context);
        setUserGender("", context);
        setUserPhone("", context);
        setUserPhoto("", context);
        setUserUid("", context);

        setIsLogged(false, context);
    }


}
