package com.itboy.dj.examtool.modules.regist;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dl7.tag.TagView;
import com.google.gson.JsonObject;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.injector.conponents.DaggerRegistComponent;
import com.itboy.dj.examtool.injector.modules.RegistModule;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.modules.login.LoginDataView;
import com.itboy.dj.examtool.utils.RxHelper;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;
import com.itboy.dj.examtool.widget.LToast;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;

//可以用注册的DataView
public class RegistActivity extends BaseActivity<RegistPresenter> implements LoginDataView<JsonObject>, View.OnClickListener {

    @BindView(R.id.regist_input_phone)
    EditText registInputPhone;
    @BindView(R.id.tag_skip)
    TagView tagSkip;
    @BindView(R.id.regist_input_code)
    EditText registInputCode;
    @BindView(R.id.regist_input_invite_code)
    EditText registInputInviteCode;
    @BindView(R.id.rgt_next)
    Button rgtNext;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_regist;
    }

    @Override
    protected void initInjector() {

        DaggerRegistComponent.builder()
                .applicationComponent(getAppComponent())
                .registModule(new RegistModule(this))
                .build()
                .inject(this);

    }

    @Override
    protected void initViews() {
        rgtNext.setOnClickListener(this);
        tagSkip.setOnClickListener(this);
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initViews();
    }


    //获取验证(这里可以来判断获取验证码)
    @Override
    public void loadData(JsonObject data) {
        //  {"result":"ok","response":true}
     //   Log.d("RegistActivity", data.toString());
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(data.toString());
            String string = jsonObject.optString("result");
            if ("ok".equals(string)) {
                LToast.show(getApplicationContext(), "获取成功");
                RxHelper.countdown(60)
                        .compose(this.<Integer>bindToLife()) //当Activity结束掉以后，Observable停止发送数据，订阅关系解除。(rxlifecycle库)
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

    //这里可以用来判断是否执行注册下一步结果
    @Override
    public void loadMoreData(JsonObject data) {
      //  Log.d("RegistActivity", data.toString());

        try {
            JSONObject jsonObject = new JSONObject(data.toString());
            String result = jsonObject.optString("result");
            switch (result) {
                case "ok":
                    SharedPreferencesUtils.setParam(context, "otherCode",  registInputInviteCode.getText().toString()+""); //其他人的的邀请码
                    startActivity(new Intent(RegistActivity.this, SettingPwdActivity.class).putExtra("mobile", registInputPhone.getText().toString()));
                    break;
                case "fail":
                    JSONObject jsonObject1 = jsonObject.optJSONObject("response");
                    String errorText = jsonObject1.optString("errorText");
                    LToast.show(getApplicationContext(),errorText);
                    break;
                default:
                    break;

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



    @Override
    public void loadNoData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tag_skip:
                String phone = registInputPhone.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    LToast.show(getApplicationContext(), "请输入手机号");
                    return;
                }
                mPresenter.getCode(phone,"login_register","",RegistActivity.this);
                break;
            case R.id.rgt_next:
                String phone1 = registInputPhone.getText().toString();
                String code = registInputCode.getText().toString();
                String inviteCode = registInputInviteCode.getText().toString();
                if (TextUtils.isEmpty(phone1.trim())) {
                    LToast.show(getApplicationContext(), "请输入手机号");
                    return;
                }
                if (TextUtils.isEmpty(code.trim())) {
                    LToast.show(getApplicationContext(), "请输入验证码");
                    return;
                }
                SharedPreferencesUtils.setParam(context, "otherCode", inviteCode+""); //自己的邀请码
                mPresenter.regist(phone1, code, inviteCode + "");
                break;
            default:
                break;


        }


    }
}
