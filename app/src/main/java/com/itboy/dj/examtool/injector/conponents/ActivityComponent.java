package com.itboy.dj.examtool.injector.conponents;

import android.app.Activity;

import com.itboy.dj.examtool.injector.PerActivity;
import com.itboy.dj.examtool.injector.modules.ActivityModule;

import dagger.Component;

/**
 * Created by long on 2016/8/19.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();
}
