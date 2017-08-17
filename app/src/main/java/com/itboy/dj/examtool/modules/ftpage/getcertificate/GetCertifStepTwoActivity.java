package com.itboy.dj.examtool.modules.ftpage.getcertificate;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.adapter.PayAdapter;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.utils.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GetCertifStepTwoActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.base_title_name)
    TextView baseTitleName;
    @BindView(R.id.cfr_type_tx)
    TextView cfrTypeTx;
    @BindView(R.id.cfr_num_tx)
    TextView cfrNumTx;
    @BindView(R.id.cfr_name_tx)
    TextView cfrNameTx;
    @BindView(R.id.cfr_recv_goods_msg_tx)
    TextView cfrRecvGoodsMsgTx;
    @BindView(R.id.cfr_pirce_tx)
    TextView cfrPirceTx;
    @BindView(R.id.pay_choose_rec)
    RecyclerView payChooseRec;
    @BindView(R.id.pay)
    Button pay;  //支付费用
    private List<PayBean> payBeens = new ArrayList<>();
    private int[] payimgs = {R.mipmap.f_ic_zhifubao, R.mipmap.f_ic_weixin};
    private String[] payStrs = {"支付宝", "微信"};
    private int[] payIds = {0, 1};

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_get_certif_step_two;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {

        payChooseRec.setLayoutManager(new LinearLayoutManager(GetCertifStepTwoActivity.this));
        payChooseRec.addItemDecoration(new RecycleViewDivider(GetCertifStepTwoActivity.this, LinearLayoutManager.HORIZONTAL, R.drawable.recycle_divider));
        initData();
        final PayAdapter payAdapter = new PayAdapter(R.layout.item_pay, payBeens);
        payChooseRec.setAdapter(payAdapter);
        payChooseRec.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                payAdapter.setCheckItem(position);
                Toast.makeText(context, "payBeens.get(position).getChooseId():" + payBeens.get(position).getChooseId(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void initData() {
        PayBean payBean = null;
        int i = 2;
        for (int i1 = 0; i1 < i; i1++) {
            payBean = new PayBean(payimgs[i1], payStrs[i1], payIds[i1]);
            payBeens.add(payBean);
        }


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
