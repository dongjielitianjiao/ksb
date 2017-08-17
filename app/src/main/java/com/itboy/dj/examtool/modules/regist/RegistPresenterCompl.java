package com.itboy.dj.examtool.modules.regist;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.RxSubscribe;
import com.itboy.dj.examtool.modules.login.LoginDataView;

import org.json.JSONException;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/3/29.
 */

public class RegistPresenterCompl implements RegistPresenter {
    private final LoginDataView registDataView; //实现了IBaseView，处理网络错误

    public RegistPresenterCompl(LoginDataView registDataView) {
        this.registDataView = registDataView;
    }


    @Override
    public void getData(boolean isRefresh) {


    }

    @Override
    public void getMoreData() {


    }

    //获取验证码
    @Override
    public void getCode(String mobile, String type,String token, final Context context) {
        RetrofitService.getCode(mobile, type,token).subscribe(new RxSubscribe<JsonObject>(context) {
            @Override
            protected void _onNext(JsonObject jsonObject) {
                try {
                    registDataView.loadData(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void _onError(String message) {

            }


        });


    }

    //输入完成下一步
    @Override
    public void regist(String mobile, String code, String inviteCode) {

        RetrofitService.nextStep(mobile, code, inviteCode)
                .compose(registDataView.<JsonObject>bindToLife())
                .subscribe(new Subscriber<JsonObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        Log.d("RegistPresenterCompl", jsonObject.toString());
                        registDataView.loadMoreData(jsonObject);
                    }
                });

    }
}
