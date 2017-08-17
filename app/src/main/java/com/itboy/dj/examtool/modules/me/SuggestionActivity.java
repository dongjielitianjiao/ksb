package com.itboy.dj.examtool.modules.me;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.gson.JsonObject;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;
import com.itboy.dj.examtool.widget.LToast;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import rx.Subscriber;

public class SuggestionActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.suggest_content)
    EditText suggestContent;
    @BindView(R.id.email_qq)
    EditText emailQq;
    @BindView(R.id.suggest_complete_btn)
    Button suggestCompleteBtn;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_suggestion;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final String token = (String) SharedPreferencesUtils.getParam(SuggestionActivity.this, "Token", "");
        suggestCompleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String suggestStr = suggestContent.getText().toString();
                if (TextUtils.isEmpty(suggestStr.trim())) {
                    LToast.show(context, "反馈意见不能为空");
                    return;
                }
                if(suggestStr.length()<10){
                    LToast.show(context, "意见不能小于10个字");
                    return;
                }
                String contactstr = emailQq.getText().toString();
                RetrofitService.uploadSuggtion(suggestStr, contactstr, token).subscribe(new Subscriber<JsonObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        Log.d("SuggestionActivity", jsonObject.toString());
                        try {
                            JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                            String result = jsonObject1.optString("result");
                            if ("ok".equals(result)) {
                                LToast.show(context, "谢谢您提出的宝贵意见");
                                finish();
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
