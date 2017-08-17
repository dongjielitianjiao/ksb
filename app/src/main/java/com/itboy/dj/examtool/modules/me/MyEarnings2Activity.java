package com.itboy.dj.examtool.modules.me;

import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.adapter.EarnAdapter;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.bean.EarnBean;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.utils.Lerist;
import com.itboy.dj.examtool.utils.RecycleViewDivider;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.Subscriber;

/*我的收益*/
public class MyEarnings2Activity extends BaseActivity {


    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.right_rt)
    RelativeLayout rightRt; //记录
    @BindView(R.id.layout)
    TextView layout;
    @BindView(R.id.me_head_img)
    CircleImageView meHeadImg; //头像
    @BindView(R.id.me_name)
    TextView meName; //名字
    @BindView(R.id.me_earn_all_money)
    carbon.widget.TextView meEarnAllMoney; //总收益
    @BindView(R.id.to_be_real_mongy)
    Button toBeRealMongy; //提现按钮
    @BindView(R.id.tixian_money)
    carbon.widget.TextView tixianMoney;//提现的金额
    @BindView(R.id.id_money_rec)
    RecyclerView idMoneyRec;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_my_earnings2;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        int sysVersion = Integer.parseInt(Build.VERSION.SDK);
        if (sysVersion > 20) {
            layout.setHeight(Lerist.getStatusBarHeight(MyEarnings2Activity.this));
        } else {
            layout.setHeight(0);
        }



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //提现
        toBeRealMongy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyEarnings2Activity.this,BeRealMoneyApplyActivity.class);
                startActivity(intent);
            }
        });

        //提现记录
        rightRt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyEarnings2Activity.this, BeRealMoneyRecordActivity.class);
                startActivity(intent);
            }
        });

        final String token = (String) SharedPreferencesUtils.getParam(MyEarnings2Activity.this, "Token", "");
      //我的金额
        RetrofitService.myMoeny(token).subscribe(new Subscriber<JsonObject>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(JsonObject jsonObject) {
                /*{"result":"ok","error":{"errorCode":0,"errorText":"success"},"data":{"userId":7377,"totalMoney":0.0,"inviteTotalMoney":0.0,"inviteCurrAmount":0.0,"inviteTakeAmount":0.0}}*/
                Log.d("MyEarnings2Activity", jsonObject.toString());
                try {
                    JSONObject jsonObject1=new JSONObject(jsonObject.toString());
                    JSONObject jsonObject11 = jsonObject1.optJSONObject("data");
                    String totalMoney = jsonObject11.optString("totalMoney");
                    meEarnAllMoney.setText(totalMoney+"元");
                    String inviteTakeAmount = jsonObject11.optString("inviteTakeAmount");
                    tixianMoney.setText(inviteTakeAmount);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });




        idMoneyRec.setLayoutManager(new LinearLayoutManager(this));
        idMoneyRec.addItemDecoration(new RecycleViewDivider(MyEarnings2Activity.this, LinearLayoutManager.HORIZONTAL, R.drawable.recycle_divider));

        RetrofitService.getEarning(token).subscribe(new Subscriber<EarnBean>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable e) {

            }
            @Override
            public void onNext(EarnBean jsonObject) {
                List<EarnBean.DataBean> data = jsonObject.getData();
                EarnAdapter earnAdapter =new EarnAdapter(R.layout.earn_item,data);
                idMoneyRec.setAdapter(earnAdapter);


            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }


}
