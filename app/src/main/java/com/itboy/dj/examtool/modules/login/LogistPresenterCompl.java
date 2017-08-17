package com.itboy.dj.examtool.modules.login;

import android.app.Activity;

import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.RxSubscribe;
import com.itboy.dj.examtool.api.bean.LoginBean;

import org.json.JSONException;

/**
 * Created by Administrator on 2017/3/30.
 */

public class LogistPresenterCompl implements LoginPresenter {

    private LoginDataView loginDataView;


    public LogistPresenterCompl(LoginDataView loginDataView) {
        this.loginDataView = loginDataView;
    }


    @Override
    public void getData(boolean isRefresh) {

    }

    //登录信息
    @Override
    public void getMoreData() {

    }


    @Override
    public void login(String mobile, String pwd, final Activity context) {
        RetrofitService.login(mobile, pwd)
                .compose(loginDataView.<LoginBean>bindToLife())
                .subscribe(new RxSubscribe<LoginBean>(context) {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    protected void _onNext(LoginBean loginBean) {
                        //   Log.d("LogistPresenterCompl", "loginBean.getData():" + loginBean.getData());
                        try {
                            loginDataView.loadData(loginBean);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    protected void _onError(String message) {

                    }


                });
    }


}
