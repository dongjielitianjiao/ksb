package com.itboy.dj.examtool.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.api.bean.StudyUnCommandBean;
import com.itboy.dj.examtool.widget.MyProgressBar;

import java.util.List;

/**
 * Created by Administrator on 2017/5/24.
 */

public class StudyUnCommandAdapter extends BaseQuickAdapter<StudyUnCommandBean.DataBean.RowsBean,BaseViewHolder> {

    public StudyUnCommandAdapter(int layoutResId, List<StudyUnCommandBean.DataBean.RowsBean> data) {
        super(layoutResId, data);
    }

    /*         {
                "userCourseId": 40,
                "userId": 7331,
                "courseId": 53,
                "examId": null,
                "courseStudyStatus": "learning",
                "courseName": "2016“陕建杯”职业技能大赛裁判员培训",
                "examName": null,
                "coursePicture": "http://img.kspx.ccla.com.cn/server/upload/image/2016/07/29/1469773582560072718.png",
                "viewCount": 23,
                "studyChapterName": null,
                "doneChapterRate": 0
            },*/

    @Override
    protected void convert(BaseViewHolder helper, StudyUnCommandBean.DataBean.RowsBean item) {
        helper.setText(R.id.video_title_logo, item.getCourseStudyStatus());
        ImageView view = helper.getView(R.id.video_img);
        String imgUrl = item.getCoursePicture();
        Glide.with(view.getContext()).load(imgUrl).into(view);
        MyProgressBar myProgressBar = helper.getView(R.id.video_current_progress);
        myProgressBar.setMax(100);
        myProgressBar.setProgress(item.getViewCount());

        helper.setText(R.id.watch_people, "100人");
        helper.setText(R.id.video_title, item.getCourseName());
    }
}
