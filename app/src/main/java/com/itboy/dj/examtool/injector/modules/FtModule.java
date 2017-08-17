package com.itboy.dj.examtool.injector.modules;

import com.itboy.dj.examtool.injector.PerFragment;
import com.itboy.dj.examtool.modules.ftpage.FtPageFragment;
import com.itboy.dj.examtool.modules.ftpage.FtPagePresenter;
import com.itboy.dj.examtool.modules.ftpage.FtPagePresenterCompl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/3/31.
 */
@Module
public class FtModule {
private FtPageFragment ftPageFragment;

    public FtModule(FtPageFragment ftPageFragment) {
        this.ftPageFragment = ftPageFragment;
    }

    @PerFragment
    @Provides
    public FtPagePresenter providePresenter() {
        return new FtPagePresenterCompl(ftPageFragment);
    }


}
