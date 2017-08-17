package com.itboy.dj.examtool.modules.me;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.adapter.ServiceCenterAdapter;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.bean.ServiceCenterBean;
import com.itboy.dj.examtool.api.bean.ServiceCenterOne;
import com.itboy.dj.examtool.api.bean.ServiceCenterTwo;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.utils.RecycleViewDivider;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Subscriber;

public class ServiceCenterActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.base_title_name)
    TextView baseTitleName;
    @BindView(R.id.base_img_bg)
    ImageView baseImgBg;
    @BindView(R.id.right_rt)
    RelativeLayout rightRt;
    @BindView(R.id.service_rec)
    RecyclerView serviceRec;
    private List<MultiItemEntity> res=new ArrayList<>();
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_service_center;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        baseTitleName.setText("客服中心");
        back.setOnClickListener(this);

        serviceRec.setLayoutManager(new LinearLayoutManager(ServiceCenterActivity.this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });


        serviceRec.addItemDecoration(new RecycleViewDivider(ServiceCenterActivity.this, LinearLayoutManager.HORIZONTAL, R.drawable.recycle_divider));
        String token = (String) SharedPreferencesUtils.getParam(ServiceCenterActivity.this, "Token", "");


        RetrofitService.getService(token).subscribe(new Subscriber<ServiceCenterBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ServiceCenterBean jsonObject) {
                List<ServiceCenterBean.DataBean.ContentsBean> contents = jsonObject.getData().getContents();
                for (int i = 0; i < contents.size(); i++) {
                    String title = contents.get(i).getTitle();
                    String text = contents.get(i).getText();
                    ServiceCenterOne serviceCenterOne=new ServiceCenterOne(title);
                    ServiceCenterTwo serviceCenterTw=new ServiceCenterTwo(text);
                    serviceCenterOne.addSubItem(serviceCenterTw);
                    res.add(serviceCenterOne);

                }
                ServiceCenterAdapter serviceCenterAdapter=new ServiceCenterAdapter(res);
                serviceRec.setAdapter(serviceCenterAdapter);


            }
        });

    }

    @Override
    protected void updateViews(boolean isRefresh) {


    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
            default:
                break;
        }
    }
}
