package com.itboy.dj.examtool.modules.study;

import com.itboy.dj.examtool.api.bean.StudyCommandBean;
import com.itboy.dj.examtool.api.bean.StudyUnCommandBean;
import com.itboy.dj.examtool.modules.base.IBaseView;

import java.util.List;

/**
 * Created by Administrator on 2017/4/7.
 */

public interface StudyDataView extends IBaseView {
    //未推荐课程
    void loadVideoListUnCom(List<StudyUnCommandBean.DataBean.RowsBean> studyBeans);

    //推荐课程
    void loadVideoListCom(List<StudyCommandBean.DataBean> studyBeans);

}
