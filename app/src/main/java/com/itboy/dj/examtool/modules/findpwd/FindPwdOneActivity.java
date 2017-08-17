package com.itboy.dj.examtool.modules.findpwd;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.dl7.tag.TagView;
import com.google.gson.JsonObject;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.RxSubscribe;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.utils.RxHelper;
import com.itboy.dj.examtool.utils.VerificationUtil;
import com.itboy.dj.examtool.widget.LToast;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import carbon.widget.Button;
import rx.Subscriber;

public class FindPwdOneActivity extends BaseActivity {

    @BindView(R.id.findpwd_input_phone)
    EditText findpwdInputPhone; //输入手机号
    @BindView(R.id.tag_skip)
    TagView tagSkip; //获取验证码
    @BindView(R.id.findpwd_input_code)
    EditText findpwdInputCode; //输入验证码
    @BindView(R.id.fpwd_next)
    Button fpwdNext; //下一步

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_find_pwd_one;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {

        tagSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneStr = findpwdInputPhone.getText().toString();
                if (TextUtils.isEmpty(phoneStr.trim())) {
                    LToast.show(context, "请输入手机号");
                    return;
                }
                if (!VerificationUtil.isValidTelNumber(phoneStr)) {
                    LToast.show(context, "请输入正确的手机号");
                    return;
                }
                RetrofitService.getCode(phoneStr, "login_forget", "").subscribe(new RxSubscribe<JsonObject>(FindPwdOneActivity.this) {
                    @Override
                   protected void _onNext(JsonObject data) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(data.toString());
                            String string = jsonObject.optString("result");
                            if ("ok".equals(string)) {
                                LToast.show(getApplicationContext(), "获取成功");
                                RxHelper.countdown(60)
                                        .compose(FindPwdOneActivity.this.<Integer>bindToLife()) //当Activity结束掉以后，Observable停止发送数据，订阅关系解除。(rxlifecycle库)
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


        fpwdNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String phoneStr = findpwdInputPhone.getText().toString();
                if (TextUtils.isEmpty(phoneStr.trim())) {
                    LToast.show(context, "请输入手机号");
                    return;
                }
                if (!VerificationUtil.isValidTelNumber(phoneStr)) {
                    LToast.show(context, "请输入正确的手机号");
                    return;
                }
                final String codeStr = findpwdInputCode.getText().toString();
                if (TextUtils.isEmpty(codeStr.trim())) {
                    LToast.show(context, "请输入验证码");
                    return;
                }

                RetrofitService.loginForgetPwd(phoneStr, codeStr).compose(FindPwdOneActivity.this.<JsonObject>bindToLife()).subscribe(new RxSubscribe<JsonObject>(FindPwdOneActivity.this) {
                    @Override
                    protected void _onNext(JsonObject jsonObject) {
                        try {
                            JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                            String result = jsonObject1.optString("result");
                            if ("ok".equals(result)) {
                                Intent intent = new Intent(FindPwdOneActivity.this, FindPwdTwoActivity.class);
                                intent.putExtra("mobile", phoneStr);
                                intent.putExtra("code", codeStr);
                                startActivity(intent);
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
