package com.itboy.dj.examtool.injector.conponents;

import com.itboy.dj.examtool.injector.PerActivity;
import com.itboy.dj.examtool.injector.modules.SetPwdModule;
import com.itboy.dj.examtool.modules.regist.SettingPwdActivity;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/31.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = SetPwdModule.class)
public interface SetPwdComponent {
    void inject(SettingPwdActivity activity);
}
