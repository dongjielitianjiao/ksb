package com.itboy.dj.examtool.adapter;

import android.graphics.Typeface;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.api.bean.KaoPeiDetail;

import java.util.List;

/**
 * Created by Administrator on 2017/5/23.
 */

public class EnrolTableAdapter extends BaseQuickAdapter<KaoPeiDetail.DataBean.ExamDetailsBean, BaseViewHolder> {

    public EnrolTableAdapter(int layoutResId, List<KaoPeiDetail.DataBean.ExamDetailsBean> data) {
        super(layoutResId, data);
    }



    @Override
    protected void convert(BaseViewHolder helper, KaoPeiDetail.DataBean.ExamDetailsBean item) {
        int position = helper.getAdapterPosition();
        TextView textVie= helper.getView(R.id.exam_project);
        TextView textVie2= helper.getView(R.id.exam_type);
        TextView textVie1= helper.getView(R.id.exam_time);
        if(position==0){
            textVie.setTypeface(Typeface.DEFAULT_BOLD);
            textVie1.setTypeface(Typeface.DEFAULT_BOLD);
            textVie2.setTypeface(Typeface.DEFAULT_BOLD);
        }else {
            textVie1.setTextColor(textVie1.getResources().getColor(R.color.color_red1));
        }
        helper.setText(R.id.exam_project,item.getExamCourseName());
        helper.setText(R.id.exam_type,item.getExamModel());
        helper.setText(R.id.exam_time,item.getExamStartTime()+"-"+item.getExamEndTime());
    }
}
