package com.itboy.dj.examtool.injector.modules;

import com.itboy.dj.examtool.injector.PerActivity;
import com.itboy.dj.examtool.modules.login.LoginActivity;
import com.itboy.dj.examtool.modules.login.LoginPresenter;
import com.itboy.dj.examtool.modules.login.LogistPresenterCompl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/3/30.
 */
@Module
public class LoginModule {
    private LoginActivity loginActivity;


    public LoginModule(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }



    @PerActivity
    @Provides
    public LoginPresenter providePresenter() {
        return new LogistPresenterCompl(loginActivity);
    }
}
