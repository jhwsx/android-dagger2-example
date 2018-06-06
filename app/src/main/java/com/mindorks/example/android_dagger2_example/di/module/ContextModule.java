package com.mindorks.example.android_dagger2_example.di.module;

import android.content.Context;

import com.mindorks.example.android_dagger2_example.di.interfaces.ApplicationContext;

import dagger.Module;
import dagger.Provides;

/**
 * @author wzc
 * @date 2018/6/6
 */
@Module
public class ContextModule {
    private Context mContext;

    public ContextModule(Context context) {
        mContext = context;
    }
    @Provides
    @ApplicationContext
    public Context getContext() {
        return mContext.getApplicationContext();
    }
}
