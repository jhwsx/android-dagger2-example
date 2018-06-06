package com.mindorks.example.android_dagger2_example.di.module;

import android.content.Context;

import com.mindorks.example.android_dagger2_example.data.DataManager;
import com.mindorks.example.android_dagger2_example.data.db.DbHelper;
import com.mindorks.example.android_dagger2_example.data.prefs.SharedPrefsHelpr;
import com.mindorks.example.android_dagger2_example.di.interfaces.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author wzc
 * @date 2018/6/6
 */
@Module(includes = {DbHelperModule.class, SharedPrefsHelperModule.class})
public class DataManagerModule {
    @Singleton
    @Provides
    public DataManager dataManager(@ApplicationContext Context context, DbHelper dbHelper, SharedPrefsHelpr sharedPrefsHelpr) {
        return new DataManager(context, dbHelper, sharedPrefsHelpr);
    }
}
