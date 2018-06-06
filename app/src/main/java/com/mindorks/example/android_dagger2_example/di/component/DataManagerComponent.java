package com.mindorks.example.android_dagger2_example.di.component;

import com.mindorks.example.android_dagger2_example.data.DataManager;
import com.mindorks.example.android_dagger2_example.di.module.DataManagerModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author wzc
 * @date 2018/6/6
 */
@Singleton
@Component(modules = {DataManagerModule.class})
public interface DataManagerComponent {
    DataManager getDataManager();
}
