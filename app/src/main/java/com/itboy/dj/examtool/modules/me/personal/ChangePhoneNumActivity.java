package com.itboy.dj.examtool.modules.me.personal;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.dl7.tag.TagView;
import com.google.gson.JsonObject;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.RxSubscribe;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.modules.login.LoginActivity;
import com.itboy.dj.examtool.utils.LActivityManager;
import com.itboy.dj.examtool.utils.RxHelper;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;
import com.itboy.dj.examtool.utils.VerificationUtil;
import com.itboy.dj.examtool.widget.LToast;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import rx.Subscriber;

public class ChangePhoneNumActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.input_login_pwd)
    EditText inputLoginPwd;
    @BindView(R.id.tag_skip)
    TagView tagSkip;
    @BindView(R.id.new_phone_num)
    EditText newPhoneNum;
    @BindView(R.id.yz_code_num)
    EditText yzCodeNum;
    @BindView(R.id.commit_btn)
    Button commitBtn;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_change_phone_num;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        final String token = (String) SharedPreferencesUtils.getParam(ChangePhoneNumActivity.this, "Token", "");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tagSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String phoneStr = newPhoneNum.getText().toString();
                if (TextUtils.isEmpty(phoneStr.trim())) {
                    LToast.show(context, "请输入手机号");
                    return;
                }
                if (!VerificationUtil.isValidTelNumber(phoneStr)) {
                    LToast.show(context, "请输入正确的手机号");
                    return;
                }
                RetrofitService.getCode(phoneStr, "user_changeMobile", token).subscribe(new RxSubscribe<JsonObject>(ChangePhoneNumActivity.this) {
                    @Override
                    protected void _onNext(JsonObject data) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(data.toString());
                            String string = jsonObject.optString("result");
                            if ("ok".equals(string)) {
                                LToast.show(getApplicationContext(), "获取成功");
                                RxHelper.countdown(60)
                                        .compose(ChangePhoneNumActivity.this.<Integer>bindToLife()) //当Activity结束掉以后，Observable停止发送数据，订阅关系解除。(rxlifecycle库)
                                        .subscribe(new Subscriber<Integer>() {

                                            @Override
                                            public void onCompleted() {
                                                tagSkip.setEnabled(true);
                                                tagSkip.setText("重新发送");
                                            }

                                            @Override
                                            public void onError(Throwable e) {
                                                tagSkip.setText("重新发送");
                                            }

                                            @Override
                                            public void onNext(Integer integer) {
                                                tagSkip.setText(integer + "秒");
                                                tagSkip.setEnabled(false);
                                            }
                                        });

                            } else {
                                JSONObject jsonObject1 = jsonObject.optJSONObject("error");
                                jsonObject1.optString("errorCode");
                                String errorText = jsonObject1.optString("errorText");
                                LToast.show(getApplicationContext(), errorText);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    protected void _onError(String message) {

                    }
                });


            }
        });

        commitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd = inputLoginPwd.getText().toString();
                if (TextUtils.isEmpty(pwd.trim())) {
                    LToast.show(context, "请输入登录密码");
                    return;
                }

                final String phoneStr = newPhoneNum.getText().toString();
                if (TextUtils.isEmpty(phoneStr.trim())) {
                    LToast.show(context, "请输入手机号");
                    return;
                }
                if (!VerificationUtil.isValidTelNumber(phoneStr)) {
                    LToast.show(context, "请输入正确的手机号");
                    return;
                }
                String yzStr = yzCodeNum.getText().toString();
                if (TextUtils.isEmpty(yzStr.trim())) {
                    LToast.show(context, "验证码不能为空");
                    return;
                }

                RetrofitService.changePhone(phoneStr, yzStr, pwd, token).subscribe(new Subscriber<JsonObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        //{"result":"fail","error":{"errorCode":90113,"errorText":"手机号已存在"},"data":null}
                        // {"result":"ok","error":{"errorCode":0,"errorText":"success"},"data":true}
                        try {
                            JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                            if ("ok".equals(jsonObject1.getString("result"))) {
                                SharedPreferencesUtils.setParam(context, "MovePhone",phoneStr);
                                SharedPreferencesUtils.setParam(context, "Password", "");
                                LActivityManager.finishAllActivity();
                                ChangePhoneNumActivity.this.finish();
                                Intent intent = new Intent(ChangePhoneNumActivity.this, LoginActivity.class);
                                startActivity(intent);
                                LToast.show(context, "修改成功");
                            } else {
                                JSONObject jsonObject2 = new JSONObject(jsonObject.toString());
                                JSONObject jsonObject11 = jsonObject2.optJSONObject("error");
                                String errorText = jsonObject11.optString("errorText");
                                LToast.show(context, errorText);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });


            }
        });


    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

}
