package com.mindorks.example.android_dagger2_example.data.prefs;

import android.content.SharedPreferences;

import javax.inject.Singleton;

/**
 * @author wzc
 * @date 2018/6/5
 */
@Singleton
public class SharedPrefsHelpr {
    public static String PREF_KEY_FLAG = "com.mindorks.example.android_dagger2_example.flag";

    private SharedPreferences mSharedPreferences;

    public SharedPrefsHelpr(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }

    public String get(String key, String defaultValue) {
        return mSharedPreferences.getString(key, defaultValue);
    }

    public void put(String key, String value) {
        mSharedPreferences.edit().putString(key, value).apply();
    }

    public boolean get(String key, boolean defaultValue) {
        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    public void put(String key, boolean value) {
        mSharedPreferences.edit().putBoolean(key, value).apply();
    }

    public void clear() {
        mSharedPreferences.edit().clear().apply();
    }
}
