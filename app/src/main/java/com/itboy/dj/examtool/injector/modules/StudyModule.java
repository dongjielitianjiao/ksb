package com.itboy.dj.examtool.injector.modules;

import com.itboy.dj.examtool.injector.PerFragment;
import com.itboy.dj.examtool.modules.study.StudyFragment;
import com.itboy.dj.examtool.modules.study.StudyPresenter;
import com.itboy.dj.examtool.modules.study.StudyPresenterCompl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/4/7.
 */
@Module
public class StudyModule {
    private StudyFragment studyFragment;
    public StudyModule(StudyFragment studyFragment) {
        this.studyFragment = studyFragment;
    }

    @PerFragment
    @Provides
    public StudyPresenter providePresenter() {
        return new StudyPresenterCompl(studyFragment);
    }
}
