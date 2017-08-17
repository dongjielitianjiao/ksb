package com.itboy.dj.examtool.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.api.bean.EarnBean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/1.
 */

public class EarnAdapter extends BaseQuickAdapter<EarnBean.DataBean,BaseViewHolder> {

    public EarnAdapter(int layoutResId, List<EarnBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EarnBean.DataBean item) {
        helper.setText(R.id.earn_id,item.getUserId());
        helper.setText(R.id.earn_money,item.getCashbackAmount()+"");


    }
}
