package com.itboy.dj.examtool.injector.conponents;

import com.itboy.dj.examtool.injector.PerActivity;
import com.itboy.dj.examtool.injector.modules.LoginModule;
import com.itboy.dj.examtool.modules.login.LoginActivity;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/30.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = LoginModule.class)
public interface LoginComponent {
    void inject(LoginActivity activity);
}
