package com.itboy.dj.examtool.injector.modules;

import com.itboy.dj.examtool.injector.PerActivity;
import com.itboy.dj.examtool.modules.regist.SetPwdPresenter;
import com.itboy.dj.examtool.modules.regist.SetPwdPresenterCompl;
import com.itboy.dj.examtool.modules.regist.SettingPwdActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/3/31.
 */
@Module
public class SetPwdModule {

    private final SettingPwdActivity mView;

    public SetPwdModule(SettingPwdActivity view) {
        this.mView = view;
    }

    @PerActivity
    @Provides
    public SetPwdPresenter providePresenter() {
        return new SetPwdPresenterCompl(mView);
    }
}
