package com.mindorks.example.android_dagger2_example.di.module;

import com.mindorks.example.android_dagger2_example.di.interfaces.MainActivityScope;
import com.mindorks.example.android_dagger2_example.ui.MainActivity;

import dagger.Module;
import dagger.Provides;

/**
 * @author wzc
 * @date 2018/6/6
 */
@Module
public class MainActivityModule {
    public MainActivityModule(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    private final MainActivity mMainActivity;
    @MainActivityScope
    @Provides
    public MainActivity mainActivity() {
        return mMainActivity;
    }
}
