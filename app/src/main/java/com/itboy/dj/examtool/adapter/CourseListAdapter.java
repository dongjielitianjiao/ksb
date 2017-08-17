package com.itboy.dj.examtool.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.api.bean.CourseListBean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/2.
 */

public class CourseListAdapter extends BaseQuickAdapter<CourseListBean.DataBean.ChaptersBean, BaseViewHolder> {

    private int checkItemPosition = -1;

    public void setCheckItem(int position) {
        checkItemPosition = position;
        notifyDataSetChanged();
    }

    public CourseListAdapter(int layoutResId, List<CourseListBean.DataBean.ChaptersBean> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, CourseListBean.DataBean.ChaptersBean item) {

        helper.setText(R.id.course_section_which, "第" + item.getPriority() + "节");
        helper.setText(R.id.course_item_name, item.getChapterName());
        boolean isPassed = item.isIsPassed();
        if (isPassed) {
            helper.setText(R.id.course_study_status, "学习完成");
            helper.setBackgroundRes(R.id.course_study_status, R.drawable.rectrg_normal);
            helper.setTextColor(R.id.course_study_status, R.color.color_black);
        } else {
            helper.setText(R.id.course_study_status, "继续学习");
            helper.setBackgroundRes(R.id.course_study_status, R.drawable.rectrg);
            helper.setTextColor(R.id.course_study_status, R.color.color_white);
        }

        int position = helper.getAdapterPosition();
        if (checkItemPosition != -1) {
            if (checkItemPosition == position) {
                helper.setBackgroundRes(R.id.course_rt, R.drawable.rectrg_course_select);
            } else {
                helper.setBackgroundRes(R.id.course_rt, R.drawable.rectrg_normal);
            }
        }

    }


}
