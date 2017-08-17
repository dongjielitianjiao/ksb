package com.itboy.dj.examtool.modules.regist;

import android.content.Context;

import com.itboy.dj.examtool.modules.base.IBasePresenter;

/**
 * Created by Administrator on 2017/3/30.
 */

public interface RegistPresenter extends IBasePresenter {


    void getCode(String mobile,String type,String token, Context context);

    void  regist(String mobile,String code,String inviteCode);
}
