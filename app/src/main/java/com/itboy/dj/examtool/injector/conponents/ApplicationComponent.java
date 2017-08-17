package com.itboy.dj.examtool.injector.conponents;

import android.content.Context;

import com.itboy.dj.examtool.injector.modules.ApplicationModule;
import com.itboy.dj.examtool.local.DaoSession;
import com.itboy.dj.examtool.rxbus.RxBus;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by long on 2016/8/19.
 * Application Component
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Context getContext();

    RxBus getRxBus();

    DaoSession getDaoSession();
}
