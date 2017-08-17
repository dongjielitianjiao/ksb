package com.itboy.dj.examtool.modules.me;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.adapter.MainTabContainerAdapter;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.modules.me.cf.AllCfFragment;
import com.itboy.dj.examtool.modules.me.cf.ApplyingCfFragment;
import com.itboy.dj.examtool.modules.me.cf.CfExpressingCfFragment;
import com.itboy.dj.examtool.modules.me.cf.ReceivedCfFragment;
import com.itboy.dj.examtool.modules.me.cf.ToApplyCfFragment;
import com.itboy.dj.examtool.widget.tabContainer.TabContainerView1;
import com.itboy.dj.examtool.widget.tabContainer.listener.OnTabSelectedListener;
import com.itboy.dj.examtool.widget.tabContainer.widget.Tab;

import butterknife.BindView;
import butterknife.ButterKnife;

/*我的证书*/
public class MyCertificateActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.base_title_name)
    TextView baseTitleName;
    @BindView(R.id.base_img_bg)
    ImageView baseImgBg;
    @BindView(R.id.right_rt)
    RelativeLayout rightRt;
    @BindView(R.id.tab_containerview_main)
    TabContainerView1 tabContainerviewMain;
    private String[] itemNames = {"全部", "申请领证", "申请中", "通过申请", "已签收"};

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_my_certificate;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        baseTitleName.setText("我的证书");
        back.setOnClickListener(this);
/*  "" ,全部
 waitApply, // 待申请证书
    auditing, // 证书申请中，待审核
    audited, // 已审核，快递中
    finished// 已签收，完成  */
        tabContainerviewMain.setAdapter(new MainTabContainerAdapter(getSupportFragmentManager(),
                new Fragment[]{new AllCfFragment(), new ToApplyCfFragment(), new ApplyingCfFragment(), new CfExpressingCfFragment(), new ReceivedCfFragment()}, itemNames));

        tabContainerviewMain.setOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(Tab tab) {

            }
        });
        tabContainerviewMain.setOffscreenPageLimit(itemNames.length);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
