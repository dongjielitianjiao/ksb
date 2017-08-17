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
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;
import com.itboy.dj.examtool.widget.LToast;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import rx.Subscriber;

public class InviteCodeActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.write_command_person_invite_code)
    EditText writeCommandPersonInviteCode;
    @BindView(R.id.commit_btn)
    Button commitBtn;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_invite_code;
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
        final String token = (String) SharedPreferencesUtils.getParam(InviteCodeActivity.this, "Token", "");
        commitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String inviteCode= writeCommandPersonInviteCode.getText().toString();

                if(TextUtils.isEmpty(inviteCode.trim())){
                    LToast.show(context,"请输入推荐人邀请码");
                    return;
                }

                RetrofitService.inviteCode(inviteCode,token).subscribe(new Subscriber<JsonObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {

                        try {
                            JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                            if ("ok".equals(jsonObject1.getString("result"))) {
                                Intent intent=new Intent();
                                intent.putExtra("invite",inviteCode);
                                SharedPreferencesUtils.setParam(context, "otherCode", inviteCode+""); //其他人的的邀请码
                                setResult(RESULT_OK,intent);
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
