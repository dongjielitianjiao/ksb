package com.itboy.dj.examtool.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.api.bean.StudyCommandBean;
import com.itboy.dj.examtool.widget.MyProgressBar;

import java.util.List;

/**
 * Created by Administrator on 2017/4/7.
 */
//推荐课程
public class StudyAdapter extends BaseQuickAdapter<StudyCommandBean.DataBean, BaseViewHolder> {


    public StudyAdapter(int layoutResId, List<StudyCommandBean.DataBean> data) {
        super(layoutResId, data);
    }

    /*      {
            "userCourseId": null,
            "userId": 7374,
            "courseId": 48,
            "examId": 54,
            "courseStudyStatus": null,
            "courseName": "钢筋工高级工考前培训课程",
            "examName": "2016年度钢筋工高级工考试",
            "coursePicture": null,
            "viewCount": 2,
            "studyChapterName": null,
            "doneChapterRate": null
        }*/

    @Override
    protected void convert(BaseViewHolder helper, StudyCommandBean.DataBean item) {
        helper.setText(R.id.video_title_logo, "老子的推荐天下无敌");

        ImageView view = helper.getView(R.id.video_img);
        String imgUrl = (String) item.getCoursePicture();
        if (TextUtils.isEmpty(imgUrl) || "null".equals(imgUrl)) {
        } else {
            Glide.with(view.getContext()).load(imgUrl).into(view);
        }
        MyProgressBar myProgressBar = helper.getView(R.id.video_current_progress);
        myProgressBar.setMax(100);
        myProgressBar.setProgress(item.getViewCount());
        helper.setText(R.id.watch_people, "100人");
        helper.setText(R.id.video_title, item.getCourseName());
    }
}
