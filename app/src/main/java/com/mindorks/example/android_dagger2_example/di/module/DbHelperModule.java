package com.mindorks.example.android_dagger2_example.di.module;

import android.content.Context;

import com.mindorks.example.android_dagger2_example.data.db.DbHelper;
import com.mindorks.example.android_dagger2_example.di.interfaces.ApplicationContext;
import com.mindorks.example.android_dagger2_example.di.interfaces.DatabaseInfo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author wzc
 * @date 2018/6/6
 */
@Module(includes = {ContextModule.class})
public class DbHelperModule {

    @DatabaseInfo
    @Provides
    public String name() {
        return "wzc.db";
    }
    @Provides
    public String fakename() {
        return "fakename.db";
    }
    @DatabaseInfo
    @Provides
    public Integer version() {
        return 2;
    }

    @Singleton
    @Provides
    public DbHelper dbHelper(@ApplicationContext Context context, @DatabaseInfo String name, @DatabaseInfo Integer version) {
        return new DbHelper(context, name, version);
    }
}
