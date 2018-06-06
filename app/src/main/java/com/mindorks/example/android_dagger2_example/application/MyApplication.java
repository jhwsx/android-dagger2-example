package com.mindorks.example.android_dagger2_example.application;

import android.app.Application;
import android.content.Context;

import com.mindorks.example.android_dagger2_example.di.component.DaggerDataManagerComponent;
import com.mindorks.example.android_dagger2_example.di.component.DataManagerComponent;
import com.mindorks.example.android_dagger2_example.di.module.ContextModule;

/**
 * @author wzc
 * @date 2018/6/6
 */
public class MyApplication extends Application {

    private DataManagerComponent mDataManagerApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mDataManagerApplicationComponent = DaggerDataManagerComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public DataManagerComponent getDataManagerApplicationComponent() {
        return mDataManagerApplicationComponent;
    }

    public static MyApplication get(Context context){
        return (MyApplication) context.getApplicationContext();
    }
}
