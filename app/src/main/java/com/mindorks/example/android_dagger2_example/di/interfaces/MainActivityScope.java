package com.mindorks.example.android_dagger2_example.di.interfaces;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author wzc
 * @date 2018/6/6
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface MainActivityScope {
}
