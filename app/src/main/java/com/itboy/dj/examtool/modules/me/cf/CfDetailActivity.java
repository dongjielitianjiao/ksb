package com.itboy.dj.examtool.modules.me.cf;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.modules.base.BaseActivity;

import butterknife.BindView;

public class CfDetailActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.base_title_name)
    TextView baseTitleName;
    @BindView(R.id.order_num)
    TextView orderNum;
    @BindView(R.id.status)
    TextView status;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.adress)
    TextView adress;
    @BindView(R.id.cf_img)
    ImageView cfImg;
    @BindView(R.id.cf_book_name)
    TextView cfBookName;
    @BindView(R.id.cf_status)
    TextView cfStatus;
    @BindView(R.id.cf_btn)
    TextView cfBtn;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_cf_detail;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        baseTitleName.setText("证书详情");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }



}
