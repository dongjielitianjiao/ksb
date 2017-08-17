package com.itboy.dj.examtool.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.api.bean.CfBean;
import com.itboy.dj.examtool.modules.me.cf.CfDetailActivity;

import java.util.List;

/**
 * Created by Administrator on 2017/6/30.
 */

public class CfAdapter extends BaseQuickAdapter<CfBean.DataBean, BaseViewHolder> {
    public CfAdapter(int layoutResId, List<CfBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CfBean.DataBean item) {
        helper.setText(R.id.cf_book_name, item.getOrderName());
        helper.setText(R.id.cf_status, "状态：" +item.getCertificateStatus()+ "通过");
        helper.setText(R.id.cf_btn, "领取证书");
        final TextView textView = helper.getView(R.id.cf_btn);
        final Context context = textView.getContext();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CfDetailActivity.class);
                context.startActivity(intent);

            }
        });
    }
}
