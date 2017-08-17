package com.itboy.dj.examtool.modules.ftpage;

import com.itboy.dj.examtool.modules.base.IBasePresenter;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/3/31.
 */

public interface FtPagePresenter extends IBasePresenter {

    void bannerGetNews(Map<String, RequestBody> map, String type, String token);

    //8个item
    void LocalItem();
    //热门资讯
    void hotGetNews(Map<String, RequestBody> map, String type, String token);

}
