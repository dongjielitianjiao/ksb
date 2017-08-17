package com.itboy.dj.examtool.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itboy.dj.examtool.R;

import java.util.List;

/**
 * Created by Administrator on 2017/5/25.
 */

public class TestAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public TestAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.test,item);
    }
}
