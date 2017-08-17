package com.itboy.dj.examtool.adapter;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.api.bean.NightSchoolCourseRangeList;

import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */

public class NightSchoolCouseRangeListAdapter extends BaseQuickAdapter<NightSchoolCourseRangeList.DataBean,BaseViewHolder> {

    public NightSchoolCouseRangeListAdapter(int layoutResId, List<NightSchoolCourseRangeList.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NightSchoolCourseRangeList.DataBean item) {
        helper.setText(R.id.night_course_section_which,"第"+item.getPriority()+"节");
        helper.setText(R.id.night_course_item_name,item.getTitle());
        String sign = (String) item.getSign();
        if(TextUtils.isEmpty(sign)||"null".equals(sign)){

        }else {
            helper.setText(R.id.night_sign_in,item.getSign()+"");
        }

        helper.setText(R.id.night_course_time,item.getCreateDate());

    }
}
