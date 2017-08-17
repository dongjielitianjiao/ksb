package com.itboy.dj.examtool.injector.conponents;

import com.itboy.dj.examtool.injector.PerActivity;
import com.itboy.dj.examtool.injector.modules.RegistModule;
import com.itboy.dj.examtool.modules.regist.RegistActivity;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/29.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = RegistModule.class)
public interface RegistComponent {
    void inject(RegistActivity activity);
}
