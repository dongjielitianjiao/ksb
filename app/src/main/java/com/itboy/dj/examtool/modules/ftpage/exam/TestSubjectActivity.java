package com.itboy.dj.examtool.modules.ftpage.exam;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.adapter.ExamChooseSubjectAdapter;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.bean.ExamChooseSubject;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;

import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.Subscriber;

/*考试科目选择界面*/
public class TestSubjectActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.base_title_name)
    TextView baseTitleName;
    @BindView(R.id.exam_person_head_img)
    CircleImageView examPersonHeadImg;
    @BindView(R.id.exam_person_name)
    TextView examPersonName;
    @BindView(R.id.exam_choose_sub_rec)
    RecyclerView examChooseSubRec;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_test_subject;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        baseTitleName.setText("考试科目选择");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String headImgUrl = (String) SharedPreferencesUtils.getParam(TestSubjectActivity.this, "HeadPortrait", "");
        if (!TextUtils.isEmpty(headImgUrl)) {
            Glide.with(TestSubjectActivity.this).load(headImgUrl).into(examPersonHeadImg);
        } else {
            Glide.with(TestSubjectActivity.this).load(R.mipmap.b_bg).into(examPersonHeadImg);
        }

        examChooseSubRec.setLayoutManager(new LinearLayoutManager(TestSubjectActivity.this));


    }

    @Override
    protected void updateViews(boolean isRefresh) {

        final String token = (String) SharedPreferencesUtils.getParam(TestSubjectActivity.this, "Token", "");
        RetrofitService.getExamList(token).compose(this.<ExamChooseSubject>bindToLife()).subscribe(new Subscriber<ExamChooseSubject>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ExamChooseSubject examChooseSubject) {
                final List<ExamChooseSubject.DataBean> data = examChooseSubject.getData();
                ExamChooseSubjectAdapter examChooseSubjectAdapter = new ExamChooseSubjectAdapter(R.layout.item_choose_test_subject, data);
                examChooseSubRec.setAdapter(examChooseSubjectAdapter);
                examChooseSubRec.addOnItemTouchListener(new OnItemClickListener() {
                    @Override
                    public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                        ExamChooseSubject.DataBean dataBean = data.get(position);
                        String examId = dataBean.getExamId()+"";
                        Intent intent = new Intent(TestSubjectActivity.this, StartExamMsgActivity.class);
                        intent.putExtra("examId", examId);
                        startActivity(intent);
                    }
                });



            }


        });


    }

 /*   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }*/


}
