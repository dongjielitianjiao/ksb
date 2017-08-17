package com.itboy.dj.examtool.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.api.bean.NightSchoolBean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/12.
 */

public class NightSchoolStudyListAdapter extends BaseQuickAdapter<NightSchoolBean.DataBean.RowsBean, BaseViewHolder> {

    public NightSchoolStudyListAdapter(int layoutResId, List<NightSchoolBean.DataBean.RowsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NightSchoolBean.DataBean.RowsBean item) {
        ImageView imageView = helper.getView(R.id.night_bg_img);
        helper.setText(R.id.night_school_study_time, "上课时间：" + item.getStartDate() + "-" + item.getEndDate());
        helper.setText(R.id.night_school_teacher, "主讲人：" + item.getTeacherId());
        helper.setText(R.id.night_school_name, item.getTitle());
        helper.setText(R.id.night_school_adress, "地点：" + item.getAddress());
        String address = item.getAddress();
        if(TextUtils.isEmpty(address)||"null".equals(address)){
            helper.setText(R.id.night_my_status,   "未报名");
        }else {
            helper.setText(R.id.night_my_status,   "已报名");
        }


    }
}
