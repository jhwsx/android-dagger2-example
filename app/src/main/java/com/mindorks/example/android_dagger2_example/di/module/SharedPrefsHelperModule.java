package com.mindorks.example.android_dagger2_example.di.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.mindorks.example.android_dagger2_example.data.prefs.SharedPrefsHelpr;
import com.mindorks.example.android_dagger2_example.di.interfaces.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author wzc
 * @date 2018/6/6
 */
@Module(includes = ContextModule.class)
public class SharedPrefsHelperModule {
    @Provides
    public SharedPreferences sharedPreferences(@ApplicationContext Context context) {
        return context.getSharedPreferences("wzc_sp", Context.MODE_PRIVATE);
    }

    @Singleton
    @Provides
    public SharedPrefsHelpr sharedPrefsHelper(SharedPreferences sharedPreferences) {
        return new SharedPrefsHelpr(sharedPreferences);
    }
}
