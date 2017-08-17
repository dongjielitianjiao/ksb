package com.itboy.dj.examtool.modules.me.personal;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dl7.tag.TagView;
import com.google.gson.JsonObject;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.RxSubscribe;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.utils.IDCardValidate;
import com.itboy.dj.examtool.utils.RxHelper;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;
import com.itboy.dj.examtool.utils.VerificationUtil;
import com.itboy.dj.examtool.widget.LToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.RequestBody;
import rx.Subscriber;

/*实名认证*/
public class RealNameRZActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.base_title_name)
    TextView baseTitleName;
    @BindView(R.id.real_name)
    EditText realName;
    @BindView(R.id.real_card)
    EditText realCard;
    @BindView(R.id.tag_skip)
    TagView tagSkip;
    @BindView(R.id.real_phone)
    EditText realPhone;
    @BindView(R.id.real_yz_code)
    EditText realYzCode;
    @BindView(R.id.complete)
    Button complete;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_real_name_rz;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        baseTitleName.setText("实名认证");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final String token = (String) SharedPreferencesUtils.getParam(RealNameRZActivity.this, "Token", "");

        tagSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String realPhoneStr = realPhone.getText().toString();
                if (TextUtils.isEmpty(realPhoneStr.trim())) {
                    LToast.show(context, "请输入手机号");
                    return;
                }
                boolean validTelNumber = VerificationUtil.isValidTelNumber(realPhoneStr);
                if(!validTelNumber){
                    LToast.show(context, "手机号不正确");
                    return;
                }
                RetrofitService.getCode(realPhoneStr, "user_realAuth",token).subscribe(new RxSubscribe<JsonObject>(context) {
                    @Override
                    protected void _onNext(JsonObject data) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(data.toString());
                            String string = jsonObject.optString("result");
                            if ("ok".equals(string)) {
                                LToast.show(getApplicationContext(), "获取成功");
                                RxHelper.countdown(60)
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
                        LToast.show(getApplicationContext(), message);
                    }


                });
            }
        });

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String realNameStr = realName.getText().toString();
                if (TextUtils.isEmpty(realNameStr.trim())) {
                    LToast.show(context, "姓名不能为空");
                    return;
                }

                String realCardStr = realCard.getText().toString();
                if (TextUtils.isEmpty(realCardStr.trim())) {
                    LToast.show(context, "身份证号码不能为空");
                    return;
                }
                //验证身份证
                if (!TextUtils.isEmpty(realCardStr)) {
                    IDCardValidate validate = new IDCardValidate();
                    IDCardValidate.Result msg = validate.validateIDNum(realCardStr);
                    if (!TextUtils.isEmpty(msg.getError())) {
                        msg.show(RealNameRZActivity.this);
                        return;
                    }
                }
                String realPhoneStr = realPhone.getText().toString();
                if (TextUtils.isEmpty(realPhoneStr.trim())) {
                    LToast.show(context, "手机号不能为空");
                    return;
                }
                boolean validTelNumber = VerificationUtil.isValidTelNumber(realPhoneStr);
                if(!validTelNumber){
                    LToast.show(context, "手机号不正确");
                    return;
                }
                String realCodeStr = realYzCode.getText().toString();
                if (TextUtils.isEmpty(realCodeStr.trim())) {
                    LToast.show(context, "验证码不能为空");
                    return;
                }


                final Map<String, RequestBody> map = new HashMap<String, RequestBody>();
                map.put("realname", RequestBody.create(null, realNameStr));
                map.put("idCard", RequestBody.create(null, realCardStr));
                map.put("mobile", RequestBody.create(null, realPhoneStr));
                map.put("code", RequestBody.create(null, realCodeStr));
                RetrofitService.realName(map, token).subscribe(new RxSubscribe<JsonObject>(context) {
                    @Override
                    protected void _onNext(JsonObject jsonObject) {
                        try {
                            JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                            String result = jsonObject1.optString("result");
                            if ("ok".equals(result)) {
                                LToast.show(context,"实名认证成功");
                                setResult(RESULT_OK);
                                finish();
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


    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
