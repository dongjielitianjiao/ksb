package com.itboy.dj.examtool.modules.me;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baoyachi.stepview.HorizontalStepView;
import com.baoyachi.stepview.bean.StepBean;
import com.google.gson.JsonObject;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Subscriber;

public class MsgDeatilActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.base_title_name)
    TextView baseTitleName;
    @BindView(R.id.cf_time)
    TextView cfTime;
    @BindView(R.id.cf_content)
    TextView cfContent;
    @BindView(R.id.step_view)
    HorizontalStepView stepView;
    @BindView(R.id.cf_title)
    TextView cfTitle;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_msg_deatil;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        baseTitleName.setText("消息详情");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //消息
        Intent intent = getIntent();
        String msgid = intent.getStringExtra("msgid");
        final String token = (String) SharedPreferencesUtils.getParam(MsgDeatilActivity.this, "Token", "");
        RetrofitService.MsgDetail(msgid, token).subscribe(new Subscriber<JsonObject>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(JsonObject jsonObject) {
                /*{"result":"ok","error":{"errorCode":0,"errorText":"success"},"data":{"messageId":27,"messageSentId":109,"sendUser":null,"receiverUser":5134,"title":"订单已提交","text":"你的订单[2017072500000016]已提交，请尽快付款。","sendDate":"2017-07-25","type":"user_order_message","status":"message_unread","box":"inbox"}}*/
                try {
                    JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                    JSONObject jsonObject11 = jsonObject1.optJSONObject("data");
                    String title = jsonObject11.optString("title");
                    String text = jsonObject11.optString("text");
                    String date = jsonObject11.optString("sendDate");
                    cfContent.setText(text);
                    cfTime.setText(date);
                    cfTitle.setText(title);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


        //进度
        List<StepBean> stepsBeanList = new ArrayList<>();
        StepBean stepBean0 = new StepBean("申请证书", 1);
        StepBean stepBean1 = new StepBean("制作证书", 1);
        StepBean stepBean2 = new StepBean("寄送证书", 1);
        StepBean stepBean3 = new StepBean("已签收", -1);
        stepsBeanList.add(stepBean0);
        stepsBeanList.add(stepBean1);
        stepsBeanList.add(stepBean2);
        stepsBeanList.add(stepBean3);
        showSetpView4(stepsBeanList);


    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    //领证进度
    private void showSetpView4(List<StepBean> stepBeanList) {
        /*
        * -1 未完成
        * 0 正在进行
        * 1 已完成
        * */


        stepView.setStepViewTexts(stepBeanList)
                .setTextSize(13)//set textSize
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(this, R.color.text_color_8c))//设置StepsViewIndicator完成线的颜色
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(this, R.color.text_color_8c))//设置StepsViewIndicator未完成线的颜色
                .setStepViewComplectedTextColor(ContextCompat.getColor(this, R.color.text_color_8c))//设置StepsView text完成的颜色
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(this, R.color.text_color_8c))//设置StepsView text未完成的颜色
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(this, R.drawable.dot_cf_completed))//设置StepsViewIndicator CompleteIcon //已执行完成步骤
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(this, R.drawable.dot_cf_uncompleted));//设置StepsViewIndicator DefaultIcon // 还未执行步骤
        //  .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(this, R.drawable.dot_cf_uncompleted));//设置StepsViewIndicator AttentionIcon  //赶往该步骤处


    }


}