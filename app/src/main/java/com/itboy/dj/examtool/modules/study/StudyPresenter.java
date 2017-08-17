package com.itboy.dj.examtool.modules.study;

import com.itboy.dj.examtool.modules.base.IBasePresenter;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/4/7.
 */

public interface StudyPresenter extends IBasePresenter {
    void  videoListUnCommand(Map<String, RequestBody> map, String token);


    void  videoListCommand(Map<String, RequestBody> map, String token);

}
