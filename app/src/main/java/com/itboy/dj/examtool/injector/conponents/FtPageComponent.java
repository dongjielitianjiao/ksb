package com.itboy.dj.examtool.injector.conponents;

import com.itboy.dj.examtool.injector.PerFragment;
import com.itboy.dj.examtool.injector.modules.FtModule;
import com.itboy.dj.examtool.modules.ftpage.FtPageFragment;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/31.
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = FtModule.class)
public interface FtPageComponent{
    void inject(FtPageFragment fragment);
}
