package com.itboy.dj.examtool.modules.ftpage.NightSchool;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.adapter.MediaBottomAdapter;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.RxSubscribe;
import com.itboy.dj.examtool.api.bean.NightSchoolCourseDetailBean;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.modules.study.CourseContentFragment;
import com.itboy.dj.examtool.modules.study.TeacherIntroduceFragment;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;
import com.itboy.dj.examtool.widget.tabContainer.TabContainerView1;
import com.itboy.dj.examtool.widget.tabContainer.listener.OnTabSelectedListener;
import com.itboy.dj.examtool.widget.tabContainer.widget.Tab;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*夜校详情*/
public class NightSchoolDetailActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.base_title_name)
    TextView baseTitleName;
    @BindView(R.id.night_bg_img)
    ImageView nightBgImg;
    @BindView(R.id.night_study_type)
    TextView nightStudyType;
    @BindView(R.id.night_school_name)
    TextView nightSchoolName;
    @BindView(R.id.night_school_teacher)
    TextView nightSchoolTeacher;
    @BindView(R.id.night_school_adress)
    TextView nightSchoolAdress;
    @BindView(R.id.night_school_study_time)
    TextView nightSchoolStudyTime;
    @BindView(R.id.night_my_status)
    TextView nightMyStatus;
    @BindView(R.id.to_report_or_signin)
    Button toReportOrSignin;
    @BindView(R.id.media_bot_table)
    TabContainerView1 mediaBotTable;
    private String[] itemNames = {"课程安排", "课程介绍", "讲师介绍"};

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_night_school_detail;
    }

    private List<NightSchoolCourseDetailBean.DataBean.CourseCatalogsBean> courseCatalogs = new ArrayList<>();

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        baseTitleName.setText("夜校学习");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        final String token = (String) SharedPreferencesUtils.getParam(this, "Token", "");
        String courseId = intent.getStringExtra("courseId");
        RetrofitService.NightSchoolDetail(courseId, token).subscribe(new RxSubscribe<NightSchoolCourseDetailBean>(this) {
            @Override
            protected void _onNext(NightSchoolCourseDetailBean nightSchoolCourseRangeBean) {
                NightSchoolCourseDetailBean.DataBean data = nightSchoolCourseRangeBean.getData();
                String titleImgURL = data.getTitleImgURL();
                Glide.with(NightSchoolDetailActivity.this).load(titleImgURL).into(nightBgImg);
                String title = data.getTitle();
                nightSchoolName.setText(title);
                String address = data.getAddress();
                nightSchoolAdress.setText("地点:" + address);
                String register = (String) data.getRegister();
                if (TextUtils.isEmpty(register) || "null".equals(register)) {
                    nightMyStatus.setText("未报名");
                } else {
                    nightMyStatus.setText("已报名");
                }
                String startDate = data.getStartDate();
                String endDate = data.getEndDate();
                nightSchoolStudyTime.setText("上课时间：" + startDate + "-" + endDate);


            }

            @Override
            protected void _onError(String message) {

            }
        });

        //课程安排
        NightCourseRangeFragment nightCourseRangeFragment = NightCourseRangeFragment.newInstance(courseId);
        //课程内容
        CourseContentFragment courseContentFragment = CourseContentFragment.newInstance(courseId, "contentnight");
        //讲师介绍
        TeacherIntroduceFragment teacherIntroduceFragment = TeacherIntroduceFragment.newInstance(courseId, "yexiao");

        mediaBotTable.setAdapter(new MediaBottomAdapter(getSupportFragmentManager(),
                new Fragment[]{nightCourseRangeFragment, courseContentFragment, teacherIntroduceFragment}, itemNames));

        mediaBotTable.setOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(Tab tab) {

            }
        });
        mediaBotTable.setOffscreenPageLimit(itemNames.length);


    }

    @Override
    protected void updateViews(boolean isRefresh) {
        //扫码功能
        toReportOrSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(NightSchoolDetailActivity.this);
                intentIntegrator.initiateScan();
            }
        });


    }

    //扫码的回调用
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}
