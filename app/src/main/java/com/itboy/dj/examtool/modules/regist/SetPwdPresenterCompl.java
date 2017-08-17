package com.itboy.dj.examtool.modules.regist;

import android.content.Context;

import com.google.gson.JsonObject;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.RxSubscribe;
import com.itboy.dj.examtool.modules.login.LoginDataView;

import org.json.JSONException;

/**
 * Created by Administrator on 2017/3/30.
 */

public class SetPwdPresenterCompl implements SetPwdPresenter {
    private final LoginDataView setPwdDataView; //实现了IBaseView，处理网络错误

    public SetPwdPresenterCompl(LoginDataView setPwdDataView) {
        this.setPwdDataView = setPwdDataView;
    }


    @Override
    public void getData(boolean isRefresh) {

    }

    @Override
    public void getMoreData() {

    }


    @Override
    public void setPwd(String mobile, String pwd, final Context context) {

        RetrofitService.setPwd(mobile, pwd)
                .compose(setPwdDataView.<JsonObject>bindToLife())
                .subscribe(new RxSubscribe<JsonObject>(context) {

                    @Override
                    protected void _onNext(JsonObject jsonObject) {
                        try {
                            setPwdDataView.loadData(jsonObject);
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
