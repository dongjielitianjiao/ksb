package com.itboy.dj.examtool.modules.ftpage.apply;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.adapter.EnrolTableAdapter;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.bean.KaoPeiDetail;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.utils.LActivityManager;
import com.itboy.dj.examtool.utils.RecycleViewDivider;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;
import com.itboy.dj.examtool.widget.LToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.RequestBody;
import rx.Subscriber;

//填写报名信息
public class WriteEnrolMsgActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.base_title_name)
    TextView baseTitleName;
    @BindView(R.id.wt_your_real_name)
    EditText wtYourRealName;
    @BindView(R.id.wt_your_phone)
    EditText wtYourPhone;
    @BindView(R.id.two_title_1)
    TextView twoTitle1;
    @BindView(R.id.two_time_1)
    TextView twoTime1;
    @BindView(R.id.two_start_end_time_1)
    TextView twoStartEndTime1;
    @BindView(R.id.two_duixiang_1)
    TextView twoDuixiang1;
    @BindView(R.id.two_train_adr_1)
    TextView twoTrainAdr1;
    @BindView(R.id.rec_table)
    RecyclerView recTable;
    @BindView(R.id.apply_right_now)
    Button applyRightNow;
    private int examId;

    //
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_write_enrol_msg;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        baseTitleName.setText("填写报名信息");

        recTable.setLayoutManager(new LinearLayoutManager(WriteEnrolMsgActivity.this));
        recTable.addItemDecoration(new RecycleViewDivider(WriteEnrolMsgActivity.this, LinearLayoutManager.HORIZONTAL, R.drawable.recycle_divider));

        //虽然集合中有2个，但是只取0

        List<KaoPeiDetail.DataBean> datas = (List<KaoPeiDetail.DataBean>) getIntent().getSerializableExtra("nextShowData");
        Log.d("WriteEnrolMsgActivity", datas.toString());

        //得到考试id
        examId = datas.get(0).getExamDetails().get(0).getExamId();
        KaoPeiDetail.DataBean dataBean = datas.get(0);
        twoTitle1.setText(dataBean.getExamTarget());
        twoTime1.setText("[ 发布日期:" + dataBean.getCreateDate() + "]");
        twoStartEndTime1.setText("报名时间: " + dataBean.getExamStartTime() +"--"+ dataBean.getExamEndTime());
        twoDuixiang1.setText("报名对象: " + dataBean.getExamName());
        twoTrainAdr1.setText("培训地址: " + dataBean.getTrainModel());
        //下方表格
        List<KaoPeiDetail.DataBean.ExamDetailsBean> examDetailsBeens = new ArrayList<>();
        //添加假数据
        KaoPeiDetail.DataBean.ExamDetailsBean detailsBean1 = new KaoPeiDetail.DataBean.ExamDetailsBean();
        detailsBean1.setExamCourseName("科目名称");
        detailsBean1.setExamStartTime("考试日期和");
        detailsBean1.setExamEndTime("时间");
        detailsBean1.setExamModel("类型");
        examDetailsBeens.add(detailsBean1);

        int size = datas.get(0).getExamDetails().size();
        List<KaoPeiDetail.DataBean.ExamDetailsBean> examDetails = datas.get(0).getExamDetails();
        KaoPeiDetail.DataBean.ExamDetailsBean detailsBean = null;
        for (int i = 0; i < size; i++) {
            detailsBean = new KaoPeiDetail.DataBean.ExamDetailsBean();
            detailsBean.setExamCourseName(examDetails.get(i).getExamCourseName());
            detailsBean.setExamStartTime(examDetails.get(i).getExamStartTime());
            detailsBean.setExamEndTime(examDetails.get(i).getExamEndTime());
            detailsBean.setExamModel(examDetails.get(i).getExamModel());
            examDetailsBeens.add(detailsBean);
        }



        EnrolTableAdapter enrolTableAdapter = new EnrolTableAdapter(R.layout.enrol_table_item, examDetailsBeens);
        recTable.setAdapter(enrolTableAdapter);


    }

    @Override
    protected void updateViews(boolean isRefresh) {

        //考试报名
        applyRightNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String realPhone = wtYourRealName.getText().toString();
                String realName = wtYourPhone.getText().toString();

                if (TextUtils.isEmpty(realName)) {
                    LToast.show(WriteEnrolMsgActivity.this, "请输入您的真实姓名");
                    return;
                }
                if (TextUtils.isEmpty(realPhone)) {
                    LToast.show(WriteEnrolMsgActivity.this, "请输入您的电话");
                    return;
                }
                String token = (String) SharedPreferencesUtils.getParam(WriteEnrolMsgActivity.this, "Token", "");
                final Map<String, RequestBody> map = new HashMap<String, RequestBody>();
                map.put("examId", RequestBody.create(null, examId + ""));
                map.put("realname", RequestBody.create(null, realName + ""));
                map.put("mobile", RequestBody.create(null, realPhone + ""));
                RetrofitService.baomingRight(map, token).subscribe(new Subscriber<JsonObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
/* {"result":"ok","error":{"errorCode":0,"errorText":"success"},"data":[{"examId":78,"examName":"阿斯达斯 阿萨德","examModel":"online","examTarget":"钢筋工中级工证书持有者","examStartTime":"2017-03-01","examEndTime":"2018-03-08","examRegisterStartTime":"2017-03-01","examRegisterEndTime":"2018-03-01","profession":"土木建筑类","work":"钢筋工","rank":"高级工","province":null,"organization":"中国建设劳动学会","createDate":"2017-03-08","totalCost":null,"trainId":51,"trainModel":"online","examDetails":null,"train":true}]}*/
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {

                        try {
                            JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                            String result = jsonObject1.optString("result");
                            if ("ok".equals(result)) {
                                LToast.show(context, "报名成功");
                                ApplyChooseProfessionActivity activity = LActivityManager.getActivity(ApplyChooseProfessionActivity.class);
                                KaoPeiMsgActivity activity1 = LActivityManager.getActivity(KaoPeiMsgActivity.class);
                                LActivityManager.removeActivity(activity);
                                LActivityManager.removeActivity(activity1);
                                activity.finish();
                                activity1.finish();
                                WriteEnrolMsgActivity.this.finish();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });


            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}
