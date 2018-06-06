package com.mindorks.example.android_dagger2_example.di.component;

import com.mindorks.example.android_dagger2_example.di.interfaces.MainActivityScope;
import com.mindorks.example.android_dagger2_example.di.module.MainActivityModule;
import com.mindorks.example.android_dagger2_example.ui.MainActivity;

import dagger.Component;

/**
 * @author wzc
 * @date 2018/6/6
 */
@MainActivityScope
@Component(modules = {MainActivityModule.class}, dependencies = {DataManagerComponent.class})
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
