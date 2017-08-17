package com.itboy.dj.examtool.modules.login;

import android.app.Activity;

import com.itboy.dj.examtool.modules.base.IBasePresenter;

/**
 * Created by Administrator on 2017/3/29.
 */

public interface LoginPresenter extends IBasePresenter {


    @Override
    void getData(boolean isRefresh);


    @Override
    void getMoreData();


    void login(String mobile, String pwd, Activity context);




}
