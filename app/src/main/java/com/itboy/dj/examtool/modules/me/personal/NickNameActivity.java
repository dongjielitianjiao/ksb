package com.itboy.dj.examtool.modules.me.personal;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.gson.JsonObject;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.RxSubscribe;
import com.itboy.dj.examtool.api.bean.ChangeNickNameOk;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.rxbus.RxBus;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;
import com.itboy.dj.examtool.widget.LToast;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;

public class NickNameActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.new_nick_name)
    EditText newNickName;
    @BindView(R.id.commit_btn)
    Button commitBtn;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_nick_name;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        String nickname = getIntent().getStringExtra("nickname");
        newNickName.setText(nickname);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        commitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String token = (String) SharedPreferencesUtils.getParam(NickNameActivity.this, "Token", "");
                final String nickName = newNickName.getText().toString();
                if (TextUtils.isEmpty(nickName.trim())) {
                    LToast.show(context, "昵称不能为空");
                    return;
                }
                int length = nickName.length();
                if (length > 5) {
                    LToast.show(context, "昵称过长");
                    return;
                }
                RetrofitService.changeNickName("nikename", nickName + " ", token).subscribe(new RxSubscribe<JsonObject>(NickNameActivity.this) {
                    @Override
                    protected void _onNext(JsonObject jsonObject) {
                        String string = jsonObject.toString();
                        try {
                            JSONObject jsonObject1 = new JSONObject(string);
                            String result = jsonObject1.optString("result");
                            if ("ok".equals(result)) {
                                LToast.show(context, "修改成功");
                                Intent intent = new Intent();
                                intent.putExtra("nickname", nickName);
                                RxBus.getDefault().postSticky(new ChangeNickNameOk(nickName));
                                SharedPreferencesUtils.setParam(context, "nikename", nickName + "");
                                setResult(RESULT_OK, intent);
                                finish();

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
