package com.itboy.dj.examtool.modules.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.bean.FindPwdOk;
import com.itboy.dj.examtool.api.bean.LoginBean;
import com.itboy.dj.examtool.api.bean.NotifUpData;
import com.itboy.dj.examtool.api.bean.RegistOk;
import com.itboy.dj.examtool.injector.conponents.DaggerLoginComponent;
import com.itboy.dj.examtool.injector.modules.LoginModule;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.modules.findpwd.FindPwdOneActivity;
import com.itboy.dj.examtool.modules.home.HomeActivity;
import com.itboy.dj.examtool.modules.regist.RegistActivity;
import com.itboy.dj.examtool.rxbus.RxBus;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;
import com.itboy.dj.examtool.widget.LToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import rx.Subscriber;
import rx.functions.Action1;

//13252104544
//13612933107
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginDataView<LoginBean>, View.OnClickListener {

    @BindView(R.id.login_input_phone)
    EditText loginInputPhone;
    @BindView(R.id.login_input_pwd)
    EditText loginInputPwd;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.login_to_findpwd)
    TextView loginToFindpwd;
    @BindView(R.id.login_to_regist)
    TextView loginToRegist;
    public static final int LOGIN_CODIN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initViews();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void initInjector() {
        DaggerLoginComponent.builder()
                .applicationComponent(getAppComponent())
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);

    }

    @Override
    protected void initViews() {
        loginInputPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
        login.setOnClickListener(this);
        loginToRegist.setOnClickListener(this);
        loginToFindpwd.setOnClickListener(this);
        //注册成功
        RxBus.getDefault().toObservableSticky(RegistOk.class).compose(this.<RegistOk>bindToLife()).subscribe(new Action1<RegistOk>() {
            @Override
            public void call(RegistOk registOk) {
                loginInputPhone.setText(registOk.getUserPhone());
                loginInputPwd.setText(registOk.getUserPwd());
            }
        });
        //找回密码返回界面
        RxBus.getDefault().toObservableSticky(FindPwdOk.class).compose(this.<FindPwdOk>bindToLife()).subscribe(new Subscriber<FindPwdOk>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(FindPwdOk findPwdOk) {
                loginInputPhone.setText(findPwdOk.getNotif());
                loginInputPwd.setText("");
            }
        });
        //初始化界面直接读取
        String movePhone = (String) SharedPreferencesUtils.getParam(LoginActivity.this, "MovePhone", "");
        String password = (String) SharedPreferencesUtils.getParam(LoginActivity.this, "Password", "");
        loginInputPhone.setText(movePhone);
        loginInputPwd.setText(password);
    }

    @Override
    protected void updateViews(boolean isRefresh) {
    }



    //对登录返回的结果进行处理
    @Override
    public void loadData(LoginBean data) {
        String result = data.getResult();
        switch (result) {
            case "ok":

                //   Log.d("LoginActivity", data.getData().getHeadPortrait());
                LoginBean.DataBean data1 = data.getData();
                SharedPreferencesUtils.setParam(context, "MovePhone", data1.getMovePhone() + "");
                SharedPreferencesUtils.setParam(context, "UserId", data1.getUserId() + "");
                SharedPreferencesUtils.setParam(context, "Password", data1.getPassword() + "");
                SharedPreferencesUtils.setParam(context, "nikename", data1.getNikename() + "");
                SharedPreferencesUtils.setParam(context, "HeadPortrait", data1.getHeadPortrait() + "");
                SharedPreferencesUtils.setParam(context, "Token", data1.getToken().getAccessToken() + "");
                SharedPreferencesUtils.setParam(context, "Sex", data1.getSex() + "");
                SharedPreferencesUtils.setParam(context, "inviteCode", data1.getInviteCode() + ""); //自己的邀请码

                SharedPreferencesUtils.setParam(context, "realname", data1.getRealname() + "");
                SharedPreferencesUtils.setParam(context, "idCard", data1.getIdCard() + "");

                //工作状态
                LoginBean.DataBean.JobBean job = data1.getJob();
                SharedPreferencesUtils.setParam(context, "iswork", job.isIsWorking() + "");
                //职业/工种
                SharedPreferencesUtils.setParam(context, "professional", job.getWork() + "");
                //等级
                SharedPreferencesUtils.setParam(context, "rank", job.getRank() + "");
                //工作地址
                SharedPreferencesUtils.setParam(context, "jobaddress", job.getAddress() + "");
             /*   startActivity(HomeActivity.class, true);
                finish();*/
                Intent intent = getIntent();
                if (intent.getStringExtra("main") != null) {  //点击首页中需要登录的模块跳转
                    HomeActivity.position = 0;
                    finish();
                } else if(getIntent().getStringExtra("study") != null){
                    HomeActivity.position = 1;
                    finish();
                }else if (getIntent().getStringExtra("center") != null) {
                    HomeActivity.position = 3;
                    finish();
                }else {
                    setResult(RESULT_OK);
                    finish();
                }

                String registrationID = JPushInterface.getRegistrationID(context);

                RetrofitService.limitPush(registrationID,data1.getToken().getAccessToken()).subscribe(new Subscriber<JsonObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        //     Log.d("LoginActivity", jsonObject.toString());
                    }
                });
                LToast.show(context, "登录成功");
                //通知首页的数据更新
                RxBus.getDefault().postSticky(new NotifUpData("updata"));
                break;
            case "fail":
                LToast.show(context, data.getError().getErrorText());
                break;

        }

    }


    @Override
    public void loadMoreData(LoginBean data) {

    }


    @Override
    public void loadNoData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                String loginPhone = loginInputPhone.getText().toString();
                String loginPwd = loginInputPwd.getText().toString();
                if (TextUtils.isEmpty(loginPhone.trim())) {
                    LToast.show(getApplicationContext(), "请输入用户名");
                    return;
                }
                if (TextUtils.isEmpty(loginPwd.trim())) {
                    LToast.show(getApplicationContext(), "请输入密码");
                    return;
                }
                mPresenter.login(loginPhone, loginPwd, LoginActivity.this);
                break;
            case R.id.login_to_regist:
                startActivity(new Intent(LoginActivity.this, RegistActivity.class));
                break;
            case R.id.login_to_findpwd:
                startActivity(new Intent(LoginActivity.this, FindPwdOneActivity.class));
                break;
            default:
                break;
        }
    }


}
