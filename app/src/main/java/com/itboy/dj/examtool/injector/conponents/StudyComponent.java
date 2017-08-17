package com.itboy.dj.examtool.injector.conponents;

import com.itboy.dj.examtool.injector.PerFragment;
import com.itboy.dj.examtool.injector.modules.StudyModule;
import com.itboy.dj.examtool.modules.study.StudyFragment;

import dagger.Component;

/**
 * Created by Administrator on 2017/4/7.
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = StudyModule.class)
public interface StudyComponent {
    void inject(StudyFragment fragment);
}
