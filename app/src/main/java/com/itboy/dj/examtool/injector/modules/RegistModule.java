package com.itboy.dj.examtool.injector.modules;

import com.itboy.dj.examtool.injector.PerActivity;
import com.itboy.dj.examtool.modules.regist.RegistActivity;
import com.itboy.dj.examtool.modules.regist.RegistPresenter;
import com.itboy.dj.examtool.modules.regist.RegistPresenterCompl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/3/29.
 */
@Module
public class RegistModule {

    private final RegistActivity mView;

    public RegistModule(RegistActivity view) {
        this.mView = view;
    }

    @PerActivity
    @Provides
    public RegistPresenter providePresenter() {
        return new RegistPresenterCompl(mView);
    }

}
