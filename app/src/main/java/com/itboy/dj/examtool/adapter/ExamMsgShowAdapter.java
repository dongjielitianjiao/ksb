package com.itboy.dj.examtool.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.api.bean.ExamMsgShow;
import com.itboy.dj.examtool.modules.ftpage.exam.ExamActivity;

import java.util.List;

/**
 * Created by Administrator on 2017/6/2.
 */

public class ExamMsgShowAdapter extends BaseQuickAdapter<ExamMsgShow.DataBean.UserExamDetailsBean, BaseViewHolder> {

    public ExamMsgShowAdapter(int layoutResId, List<ExamMsgShow.DataBean.UserExamDetailsBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, final ExamMsgShow.DataBean.UserExamDetailsBean item) {
        helper.setText(R.id.exam_course_title, item.getExamCourseName());
        helper.setText(R.id.exam_type, "多选");
        helper.setText(R.id.exam_name, "20道题，每道5分");
        helper.setText(R.id.exam_pass, item.getPassScore() + "分合格");
        helper.setText(R.id.exam_time, item.getTotalTime() + "分钟");
        helper.setText(R.id.exam_last_score, "00分");
        final TextView view = helper.getView(R.id.start_do_exam);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   //87  HE 1
                Intent intent = new Intent(view.getContext(), ExamActivity.class);
                intent.putExtra("examId", item.getExamId() + "");
                intent.putExtra("examCourseId", item.getExamCourseId() + "");
                view.getContext().startActivity(intent);
                Log.d("exam", "item.getExamId()+item.getExamCourseId():" + (item.getExamId() + item.getExamCourseId()));

            }
        });
    }
}
