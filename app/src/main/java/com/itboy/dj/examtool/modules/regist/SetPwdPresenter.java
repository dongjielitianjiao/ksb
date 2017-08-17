package com.itboy.dj.examtool.modules.regist;

import android.content.Context;

import com.itboy.dj.examtool.modules.base.IBasePresenter;

/**
 * Created by Administrator on 2017/3/30.
 */

public interface SetPwdPresenter extends IBasePresenter {


    void  setPwd(String mobile, String pwd, Context context);
}
