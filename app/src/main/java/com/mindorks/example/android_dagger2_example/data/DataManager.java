package com.mindorks.example.android_dagger2_example.data;

import android.content.Context;

import com.mindorks.example.android_dagger2_example.data.db.DbHelper;
import com.mindorks.example.android_dagger2_example.data.model.User;
import com.mindorks.example.android_dagger2_example.data.prefs.SharedPrefsHelpr;
import com.mindorks.example.android_dagger2_example.di.interfaces.ApplicationContext;

/**
 * @author wzc
 * @date 2018/6/5
 */
public class DataManager {
    private Context mContext;
    private DbHelper mDbHelper;
    private SharedPrefsHelpr mSharedPrefsHelpr;

    public DataManager(@ApplicationContext Context context, DbHelper dbHelper, SharedPrefsHelpr sharedPrefsHelpr) {
        mContext = context;
        mDbHelper = dbHelper;
        mSharedPrefsHelpr = sharedPrefsHelpr;
    }

    public Long createUser(User user) {
       return mDbHelper.insertUser(user);
    }

    public User getUser(Long userId) {
        return mDbHelper.getUser(userId);
    }

    public void setLoginFlag(boolean flag) {
        mSharedPrefsHelpr.put(SharedPrefsHelpr.PREF_KEY_FLAG, flag);
    }

    public boolean getLoginFlag() {
        return mSharedPrefsHelpr.get(SharedPrefsHelpr.PREF_KEY_FLAG, false);
    }

    public void deleteAll(Long userId) {
        mDbHelper.deleteUser(userId);
        mSharedPrefsHelpr.clear();
    }

}
