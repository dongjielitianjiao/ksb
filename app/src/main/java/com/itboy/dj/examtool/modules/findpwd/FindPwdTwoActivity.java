package com.itboy.dj.examtool.modules.findpwd;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.google.gson.JsonObject;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.RxSubscribe;
import com.itboy.dj.examtool.api.bean.FindPwdOk;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.modules.login.LoginActivity;
import com.itboy.dj.examtool.rxbus.RxBus;
import com.itboy.dj.examtool.utils.LActivityManager;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;
import com.itboy.dj.examtool.widget.LToast;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import carbon.widget.Button;

public class FindPwdTwoActivity extends BaseActivity {

    @BindView(R.id.fpwd_set_pwd)
    EditText fpwdSetPwd;
    @BindView(R.id.findpwd_sure_pwd)
    EditText findpwdSurePwd;
    @BindView(R.id.findpwd_set_pwd_ok)
    Button findpwdSetPwdOk;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_find_pwd_two;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        Intent intent = getIntent();
        final String mobile = intent.getStringExtra("mobile");
        final String code = intent.getStringExtra("code");
        findpwdSetPwdOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd = fpwdSetPwd.getText().toString();
                if(TextUtils.isEmpty(pwd.trim())){
                    LToast.show(context,"请输入新密码");
                    return;
                }
                String pwdTwo = findpwdSurePwd.getText().toString();
                if(TextUtils.isEmpty(pwdTwo.trim())){
                    LToast.show(context,"请确认新密码");
                    return;
                }
                if(!pwd.equals(pwdTwo)){
                    LToast.show(context,"密码不一致");
                    return;
                }

                RetrofitService.loginForgetSetNewPwd(mobile,code,pwd).compose(FindPwdTwoActivity.this.<JsonObject>bindToLife()).subscribe(new RxSubscribe<JsonObject>(FindPwdTwoActivity.this) {
                    @Override
                    protected void _onNext(JsonObject jsonObject) {
                        try {
                            JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                            String result = jsonObject1.optString("result");
                            //{"result":"fail","error":{"errorCode":90102,"errorText":"用户不存在"},"data":null}
                            if ("ok".equals(result)) {
                                SharedPreferencesUtils.setParam(context, "MovePhone", mobile + "");
                                SharedPreferencesUtils.setParam(context, "Password", "");
                                RxBus.getDefault().postSticky(new FindPwdOk(mobile));
                                LActivityManager.finishAllActivity();
                                LToast.show(context, "找回密码成功");
                                FindPwdTwoActivity.this.finish();
                                Intent intent = new Intent(context, LoginActivity.class);
                                startActivity(intent);
                            }else {
                                JSONObject jsonObject2 = new JSONObject(jsonObject.toString());
                                JSONObject jsonObject11 = jsonObject2.optJSONObject("error");
                                String errorText = jsonObject11.optString("errorText");
                                LToast.show(context, errorText);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        LToast.show(context, message);
                    }
                });

            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }



}
