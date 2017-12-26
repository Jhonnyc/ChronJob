package com.yonikal.chronjob;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;

import java.util.Set;


public class PreferencesUtils {
    public static int PREFERENCE_NOT_EXIST = 99;
    public static boolean PREFERENCE_NOT_EXIST_BOOLEAN = true;

    public static void savePreferences(Context activity, String key, int value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int loadPreferences(Context activity, String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        return sharedPreferences.getInt(key, PREFERENCE_NOT_EXIST);
    }

    public static int loadPreferences(Context activity, String key, int defaultValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        return sharedPreferences.getInt(key, defaultValue);
    }

    public static void savePreferences(Activity activity, String key, boolean value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void addToStringArray(Context context, String key, String data) {
        String value;
        String values[] = getStringArray(context, key);
        if (values != null) {
            value = "";
            for (String s : values) {
                value += s + ",";
            }
            value += data;
        } else {
            value = data;
        }
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(key, value).apply();
    }

    public static String[] getStringArray(Context context, String key) {
        String[] values = null;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String value = prefs.getString(key, null);
        if (value != null) {
            values = value.split(",");
        }
        return values;
    }


    public static void resetKey(Context context, String key) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.remove(key).apply();
    }

    public static boolean loadPreferencesBoolean(Context activity, String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        return sharedPreferences.getBoolean(key, PREFERENCE_NOT_EXIST_BOOLEAN);
    }

    public static void savePreferences(Activity activity, String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static Set<String> loadPreferencesStringSet(Activity activity, String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        return sharedPreferences.getStringSet(key, null);
    }

    public static String loadPreferencesString(Context ctx, String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        return sharedPreferences.getString(key, null);
    }

    public static String loadPreferencesString(Context ctx, String key, String defaultString) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        return sharedPreferences.getString(key, defaultString);
    }

    public static boolean loadPreferencesBoolean(Context ctx, String key, boolean defualt) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        return sharedPreferences.getBoolean(key, defualt);
    }

    public static long loadPreferencesLong(Context ctx, String key, long defaultValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        return sharedPreferences.getLong(key, defaultValue);
    }

    public static void deletePreferences(Context mContext, String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }

    public static void savePreferences(Context mContext, String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void savePreferences(Context mContext, String key, boolean value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void savePreferences(Context mContext, String key, long value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }
}