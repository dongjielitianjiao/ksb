package com.itboy.dj.examtool.modules.ftpage.exam;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.adapter.ExamMsgShowAdapter;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.bean.ExamMsgShow;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.utils.RecycleViewDivider;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.Subscriber;

/*考试科目的具体信息*/
public class StartExamMsgActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.base_title_name)
    TextView baseTitleName;
    @BindView(R.id.exam_person_head_img1)
    CircleImageView examPersonHeadImg1;
    @BindView(R.id.exam_person_name1)
    TextView examPersonName1;

 /*   @BindView(R.id.exam_type)
    TextView examType;
    @BindView(R.id.exam_name)
    TextView examName;
    @BindView(R.id.exam_pass)
    TextView examPass;
    @BindView(R.id.exam_time)
    TextView examTime;
    @BindView(R.id.exam_last_score)
    TextView examLastScore;
    @BindView(R.id.start_do_exam)
    Button startDoExam;*/

    @BindView(R.id.exam_msg_rec)
    RecyclerView examMsgRec;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_start_exam_msg;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        baseTitleName.setText("考试答题");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        examMsgRec.setLayoutManager(new LinearLayoutManager(StartExamMsgActivity.this));
        examMsgRec.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL, R.drawable.recycle_divider));


    }

    @Override
    protected void updateViews(boolean isRefresh) {
        String examId = getIntent().getStringExtra("examId");
        final String token = (String) SharedPreferencesUtils.getParam(StartExamMsgActivity.this, "Token", "");
        RetrofitService.getExamDeatailAndExamScore(examId, token).subscribe(new Subscriber<ExamMsgShow>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ExamMsgShow examMsgShow) {
                List<ExamMsgShow.DataBean.UserExamDetailsBean> userExamDetails1 = examMsgShow.getData().getUserExamDetails();
                ExamMsgShowAdapter examMsgShowAdapter = new ExamMsgShowAdapter(R.layout.item_exam_msg_show, userExamDetails1);
                examMsgRec.setAdapter(examMsgShowAdapter);

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

  /*  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_exam_msg);
    }*/
}
