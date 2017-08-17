package com.itboy.dj.examtool.modules.ftpage.exam;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.utils.Lerist;

import butterknife.BindView;
import butterknife.ButterKnife;

//考试结果未通过
public class ExamResultNoPassActivity extends BaseActivity {
    @BindView(R.id.back)
    RelativeLayout back; //返回键
    @BindView(R.id.base_title_name)
    TextView baseTitleName; //标题栏
    @BindView(R.id.answ_time)
    TextView answTime;  //答题时间
    @BindView(R.id.score_detail)
    TextView scoreDetail;//得分详情
    @BindView(R.id.score_logo)
    TextView scoreLogo; //分数背景
    @BindView(R.id.score_rt)
    RelativeLayout scoreRt; //分数的背景
    @BindView(R.id.score)
    TextView score; //分数
    @BindView(R.id.fen)
    TextView fen;
    @BindView(R.id.look_error)
    TextView lookError; //查看错题
    @BindView(R.id.to_study)
    Button toStudy; //去学习
    @BindView(R.id.study_course_name)
    TextView studyCourseName;  //学习科目
    @BindView(R.id.pass_or_lose_hint)
    TextView passOrLoseHint;
    @BindView(R.id.hand_time)
    TextView handTime;  //实操时间
    @BindView(R.id.hand_adr)
    TextView handAdr; //实操地址
    @BindView(R.id.hand_rt)
    RelativeLayout handRt; //实操时显示
    @BindView(R.id.is_rember)
    Button isRember; //实操时显示
    @BindView(R.id.when_hand_hide_rt)
    RelativeLayout whenHandHideRt; //实操时隐藏
    @BindView(R.id.hand_bless)
    TextView handBless; //实操的时候显示
    @BindView(R.id.look_error_rt)
    RelativeLayout lookErrorRt; //实操的时候隐藏

    private int id = 1;


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_exam_result_no_pass;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        Intent intent=getIntent();
        int score = intent.getIntExtra("score", 0);
        boolean ispas = intent.getBooleanExtra("ispas", true);
        String timeString = intent.getStringExtra("timeString");//时间
        final int examid = intent.getIntExtra("examid", 0);
        final int examcourseId = intent.getIntExtra("examcourseId", 0);
        if (id == 1) { //未通过
            passOrLoseHint.setText("抱歉，你没有通过xxxx高级工理论考试");
            studyCourseName.setText("去学习xxxx高级理论考试");
            toStudy.setText("去学习");
            this.score.setText(score+"");
            answTime.setText(timeString);
            lookError.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1=new Intent(ExamResultNoPassActivity.this,ErrorReviewActivity.class);
                    intent1.putExtra("examid",examid);
                    intent1.putExtra("examcourseId",examcourseId);
                    startActivity(intent1);
                }
            });


        } else if (id == 2) { //通过理论考试无实操（可以领证）
            scoreLogo.setBackgroundResource(R.mipmap.e_img_gongxi);
            passOrLoseHint.setText("恭喜通过xxxx高级工理论考试");
            studyCourseName.setText("快来领取你的资格证书吧");
            toStudy.setText("去领证");

            studyCourseName.setTextColor(getResources().getColor(R.color.exam_choose_color));
            this.score.setTextColor(getResources().getColor(R.color.exam_choose_color));
            fen.setTextColor(getResources().getColor(R.color.exam_choose_color));
            RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(240, 160);
            params1.setMargins(Gravity.CENTER, Lerist.dip2px(this, 10), 0, 0);
            scoreRt.setLayoutParams(params1);
        } else if (id == 3) { //通过了理论考试,但是还有其他的考试
            scoreLogo.setBackgroundResource(R.mipmap.e_img_gongxi);
            passOrLoseHint.setText("恭喜通过xxxx高级工理论考试");
            studyCourseName.setText("去学习其他的xxxx高级理论考试");
            toStudy.setText("去学习");
            studyCourseName.setTextColor(getResources().getColor(R.color.theme_black_accent));
            this.score.setTextColor(getResources().getColor(R.color.exam_choose_color));
            fen.setTextColor(getResources().getColor(R.color.exam_choose_color));
            RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(240, 160);
            params1.setMargins(Gravity.CENTER, Lerist.dip2px(this, 10), 0, 0);
            scoreRt.setLayoutParams(params1);
        } else if (id == 4) {
            scoreLogo.setBackgroundResource(R.mipmap.e_img_gongxi);
            passOrLoseHint.setText("恭喜通过xxxx高级工理论考试");
            passOrLoseHint.setTextColor(getResources().getColor(R.color.exam_choose_color));
            handRt.setVisibility(View.VISIBLE);
            isRember.setVisibility(View.VISIBLE);
            handBless.setVisibility(View.VISIBLE);
            lookErrorRt.setVisibility(View.GONE);
            whenHandHideRt.setVisibility(View.GONE);
            this.score.setTextColor(getResources().getColor(R.color.exam_choose_color));
            fen.setTextColor(getResources().getColor(R.color.exam_choose_color));
            RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(240, 160);
            params1.setMargins(Gravity.CENTER, Lerist.dip2px(this, 10), 0, 0);
            scoreRt.setLayoutParams(params1);

        }


    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
