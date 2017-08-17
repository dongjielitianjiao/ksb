package com.itboy.dj.examtool.modules.regist;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;

import com.google.gson.JsonObject;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.api.bean.RegistOk;
import com.itboy.dj.examtool.injector.conponents.DaggerSetPwdComponent;
import com.itboy.dj.examtool.injector.modules.SetPwdModule;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.modules.login.LoginActivity;
import com.itboy.dj.examtool.modules.login.LoginDataView;
import com.itboy.dj.examtool.rxbus.RxBus;
import com.itboy.dj.examtool.utils.LActivityManager;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;
import com.itboy.dj.examtool.widget.LToast;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import carbon.widget.Button;

public class SettingPwdActivity extends BaseActivity<SetPwdPresenter> implements View.OnClickListener, LoginDataView<JsonObject> {

    @BindView(R.id.regist_set_pwd)
    EditText registSetPwd;
    @BindView(R.id.regist_sure_pwd)
    EditText registSurePwd;
    @BindView(R.id.set_pwd_ok)
    Button setPwdOk;
    private String mobile;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_setting_pwd;
    }

    @Override
    protected void initInjector() {

        DaggerSetPwdComponent
                .builder()
                .applicationComponent(getAppComponent())
                .setPwdModule(new SetPwdModule(this))
                .build().inject(this);


    }

    @Override
    protected void initViews() {
        registSetPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
        registSurePwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
        setPwdOk.setOnClickListener(this);
    }

    @Override
    protected void updateViews(boolean isRefresh) {
        Intent intent = getIntent();
        mobile = intent.getStringExtra("mobile");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.set_pwd_ok:
                String pwd = registSetPwd.getText().toString();
                String surePwd = registSurePwd.getText().toString();

                if (TextUtils.isEmpty(pwd.trim())) {
                    LToast.show(getApplicationContext(), "请输入密码");
                    return;
                }

                if (TextUtils.isEmpty(surePwd.trim())) {
                    LToast.show(getApplicationContext(), "请确认密码");
                    return;
                }
                if (!pwd.equals(surePwd)) {
                    LToast.show(getApplicationContext(), "两次输入的密码不一致");
                    return;
                }
                mPresenter.setPwd(mobile, pwd, SettingPwdActivity.this);
                break;
            default:
                break;


        }
    }

    @Override
    public void loadData(JsonObject data) {
        try {
            JSONObject jsonObject = new JSONObject(data.toString());
            String result = jsonObject.optString("result");
            switch (result) {
                case "ok":
                    Intent intent = new Intent(context, LoginActivity.class);
                   // intent.putExtra("mobile", mobile);
                    String pwd = registSurePwd.getText().toString();
                 //   intent.putExtra("pwd", pwd);
                    SharedPreferencesUtils.setParam(context, "MovePhone", mobile + "");
                    SharedPreferencesUtils.setParam(context, "Password", pwd + "");
                    RxBus.getDefault().postSticky(new RegistOk(mobile, pwd));
                    startActivity(intent);
                    RegistActivity activity = LActivityManager.getActivity(RegistActivity.class);
                    if (activity != null) {
                        activity.finish();
                    }
                    LActivityManager.removeActivity(activity);
                    LToast.show(context, "注册成功");
                    this.finish();


                    break;
                case "fail":
                    JSONObject jsonObject1 = jsonObject.optJSONObject("response");
                    String errorText = jsonObject1.optString("errorText");
                    LToast.show(getApplicationContext(), errorText);
                    break;
                default:
                    break;

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadMoreData(JsonObject data) {

    }


    @Override
    public void loadNoData() {

    }
}
