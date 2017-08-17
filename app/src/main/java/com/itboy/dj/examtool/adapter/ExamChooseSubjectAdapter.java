package com.itboy.dj.examtool.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.api.bean.ExamChooseSubject;

import java.util.List;

/**
 * Created by Administrator on 2017/5/25.
 */

public class ExamChooseSubjectAdapter extends BaseQuickAdapter<ExamChooseSubject.DataBean,BaseViewHolder> {


    public ExamChooseSubjectAdapter(int layoutResId, List<ExamChooseSubject.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ExamChooseSubject.DataBean item) {
    helper.setText(R.id.exam_sub_name,item.getExamName());
    }
}
