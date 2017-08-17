package com.itboy.dj.examtool.modules.me;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.utils.RecycleViewDivider;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;

import butterknife.BindView;
import rx.Subscriber;

/*提现记录*/
public class BeRealMoneyRecordActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.base_title_name)
    TextView baseTitleName;
    @BindView(R.id.record_rec)
    RecyclerView recordRec;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_be_real_money_record;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        baseTitleName.setText("提现记录");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recordRec.setLayoutManager(new LinearLayoutManager(this));
        recordRec.addItemDecoration(new RecycleViewDivider(BeRealMoneyRecordActivity.this, LinearLayoutManager.HORIZONTAL, R.drawable.recycle_divider));
        final String token = (String) SharedPreferencesUtils.getParam(BeRealMoneyRecordActivity.this, "Token", "");
        RetrofitService.beRealMoneyRecord(token).subscribe(new Subscriber<JsonObject>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(JsonObject jsonObject) {
                Log.d("BeRealMoneyRecordActivi", jsonObject.toString());
            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }


}
