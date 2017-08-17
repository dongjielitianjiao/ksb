package com.itboy.dj.examtool.modules.me;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.RxSubscribe;
import com.itboy.dj.examtool.api.bean.ChangeNickNameOk;
import com.itboy.dj.examtool.api.bean.NotifUpData;
import com.itboy.dj.examtool.api.bean.PhotoSuccess;
import com.itboy.dj.examtool.modules.base.BaseFragment;
import com.itboy.dj.examtool.modules.ftpage.NightSchool.NightSchoolActivity;
import com.itboy.dj.examtool.modules.me.personal.PersonalActivity;
import com.itboy.dj.examtool.modules.me.personal.RealNameRZActivity;
import com.itboy.dj.examtool.modules.me.personal.WorkStatus;
import com.itboy.dj.examtool.modules.me.settings.SettingActivity;
import com.itboy.dj.examtool.rxbus.RxBus;
import com.itboy.dj.examtool.utils.Lerist;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;
import com.itboy.dj.examtool.widget.LToast;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.Subscriber;

import static android.app.Activity.RESULT_OK;
import static com.itboy.dj.examtool.R.id.real_name_rz;

public class MeFragment extends BaseFragment implements View.OnClickListener {


    @BindView(R.id.user_name)
    TextView userName;  //用户姓名
    @BindView(R.id.setting_btn)
    LinearLayout settingBtn; //设置按钮
    @BindView(R.id.me_head_img)
    CircleImageView meHeadImg; //头像
    @BindView(R.id.me_rank)
    TextView meRank; //我的工种等级
    @BindView(R.id.me_work_status)
    TextView meWorkStatus; //目前工作状态
    @BindView(R.id.nick_name)
    TextView nickName; //昵称
    @BindView(real_name_rz)
    carbon.widget.TextView realNameRz; //实名认证
    @BindView(R.id.user_location)
    TextView userLocation;//定位
    @BindView(R.id.show)
    RelativeLayout show;
    @BindView(R.id.new_msgs)
    TextView newMsgs; //新消息
    @BindView(R.id.msg_rt)
    RelativeLayout msgRt; //我的消息
    @BindView(R.id.study_rt)
    RelativeLayout studyRt; //学习
    @BindView(R.id.collect_rt)
    RelativeLayout collectRt;//我的收藏
    @BindView(R.id.certif_rt)
    RelativeLayout certifRt; //我的证书
    @BindView(R.id.profit_rt)
    RelativeLayout profitRt;//我的收益
    @BindView(R.id.service_rt)
    RelativeLayout serviceRt; //客服中心
    @BindView(R.id.fdback_rt)
    RelativeLayout fdbackRt;//意见反馈
    @BindView(R.id.about_etool_rt)
    RelativeLayout aboutEtoolRt; //关于考试宝
    Unbinder unbinder;
    @BindView(R.id.to_person_msg)
    RelativeLayout toPersonMsg; //跳转到个人中心
    @BindView(R.id.layout)
    TextView layout;
    @BindView(R.id.to_person)
    RelativeLayout toPerson;
    @BindView(R.id.work_status_rt)
    RelativeLayout workStatusRt;

    private boolean isHidden = false;
    private static final int MEFRAGEMENT_CODE = 1;
    private String token;


    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {


        int sysVersion = Integer.parseInt(Build.VERSION.SDK);
        if (sysVersion > 20) {
            layout.setHeight(Lerist.getStatusBarHeight(getActivity()));
        } else {
            layout.setHeight(0);
        }
        initRequest();


        RxBus.getDefault().toObservableSticky(NotifUpData.class).compose(this.<NotifUpData>bindToLife()).subscribe(new Subscriber<NotifUpData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NotifUpData notifUpData) {
                Log.d("MeFragment", notifUpData.getNotifi());
                initRequest();
            }
        });
        //-----------------------------------------------------------------------------------------------------

        workStatusRt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String workStatus = meWorkStatus.getText().toString();
                if("待业中".equals(workStatus)){
                    RetrofitService.isOnWork(true, token).subscribe(new RxSubscribe<JsonObject>(getActivity()) {
                        @Override
                        protected void _onNext(JsonObject data) {
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(data.toString());
                                String string = jsonObject.optString("result");
                                if ("ok".equals(string)) {
                                    LToast.show(context, "修改成功");
                                    meWorkStatus.setText("在职");
                                    SharedPreferencesUtils.setParam(context, "iswork", true + "");
                                } else {
                                    JSONObject jsonObject1 = jsonObject.optJSONObject("error");
                                    jsonObject1.optString("errorCode");
                                    String errorText = jsonObject1.optString("errorText");
                                    LToast.show(getActivity(), errorText);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        protected void _onError(String message) {
                            LToast.show(getActivity(), message);
                        }
                    });

                }else if("在职".equals(workStatus)){
                   RetrofitService.isOnWork(false, token).subscribe(new RxSubscribe<JsonObject>(getActivity()) {
                        @Override
                        protected void _onNext(JsonObject data) {
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(data.toString());
                                String string = jsonObject.optString("result");
                                if ("ok".equals(string)) {
                                    LToast.show(context, "修改成功");
                                    meWorkStatus.setText("待业中");
                                    SharedPreferencesUtils.setParam(context, "iswork", false + "");
                                } else {
                                    JSONObject jsonObject1 = jsonObject.optJSONObject("error");
                                    jsonObject1.optString("errorCode");
                                    String errorText = jsonObject1.optString("errorText");
                                    LToast.show(getActivity(), errorText);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        protected void _onError(String message) {
                            LToast.show(getActivity(), message);
                        }
                    });


                }

            }
        });


        RxBus.getDefault().toObservable(WorkStatus.class).subscribe(new Subscriber<WorkStatus>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(WorkStatus workStatus) {
                String workStatus1 = workStatus.getWorkStatus();
                if ("1".equals(workStatus1)) {
                    meWorkStatus.setText("在职");
                } else if ("2".equals(workStatus1)) {
                    meWorkStatus.setText("待业中");
                }
            }
        });


        //当前定位
        final String proAndCity = (String) SharedPreferencesUtils.getParam(getActivity(), "proAndCity", "");
        if (!TextUtils.isEmpty(proAndCity)) {
            userLocation.setText(proAndCity);
        } else {
            userLocation.setText("定位失败");
        }


        settingBtn.setOnClickListener(this);
        toPerson.setOnClickListener(this);
        msgRt.setOnClickListener(this);
        studyRt.setOnClickListener(this);
        collectRt.setOnClickListener(this);
        certifRt.setOnClickListener(this);
        profitRt.setOnClickListener(this);
        serviceRt.setOnClickListener(this);
        fdbackRt.setOnClickListener(this);
        aboutEtoolRt.setOnClickListener(this);
        realNameRz.setOnClickListener(this);

    }

    private void initRequest() {
        token = (String) SharedPreferencesUtils.getParam(getActivity(), "Token", "");

       //头像
        String headImgUrl = (String) SharedPreferencesUtils.getParam(getActivity(), "HeadPortrait", "");
        if (!TextUtils.isEmpty(headImgUrl)) {
            Glide.with(getActivity()).load(headImgUrl).into(meHeadImg);
        } else {
            Glide.with(getActivity()).load(R.mipmap.b_bg).into(meHeadImg);
        }

        //昵称
        final String nikeName = (String) SharedPreferencesUtils.getParam(getActivity(), "nikename", "");
        if (!TextUtils.isEmpty(nikeName) && (!"null".equals(nikeName))) {
            nickName.setVisibility(View.VISIBLE);
            nickName.setText(nikeName);
        } else {
            nickName.setVisibility(View.INVISIBLE);
            nickName.setText("暂无昵称");
        }

        //实名认证
        final String idCard = (String) SharedPreferencesUtils.getParam(getActivity(), "idCard", "");
        if (!TextUtils.isEmpty(idCard) && (!"null".equals(idCard))) {
            realNameRz.setText("已实名认证");
        } else {
            realNameRz.setText("未实名认证");
        }
        //职业
        final String professional = (String) SharedPreferencesUtils.getParam(getActivity(), "professional", "");
        if (!TextUtils.isEmpty(professional) && (!"null".equals(professional))) {
            meRank.setText(professional);
        } else {
            meRank.setText("暂无信息");
        }
        //工作状态
        final String iswork = (String) SharedPreferencesUtils.getParam(getActivity(), "iswork", "");
        if ("false".equals(iswork)) {
            meWorkStatus.setText("待业中");
        } else {
            meWorkStatus.setText("在职");
        }
    }

    @Override
    protected void updateViews(boolean isRefresh) {
        //个人中心头像更新了 通知一下当前界面更新
        RxBus.getDefault()
                .toObservable(PhotoSuccess.class)
                .compose(this.<PhotoSuccess>bindToLife())
                .subscribe(new Subscriber<PhotoSuccess>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(PhotoSuccess photoSuccess) {
                        String imgUrl = photoSuccess.getBroadcast();
                        Glide.with(getActivity()).load(imgUrl).into(meHeadImg);
                    }
                });

        //个人中心昵称更新了
        RxBus.getDefault().toObservable(ChangeNickNameOk.class).compose(this.<ChangeNickNameOk>bindToLife()).subscribe(new Subscriber<ChangeNickNameOk>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ChangeNickNameOk o) {
                String nickNm = o.getNickNm();
                nickName.setVisibility(View.VISIBLE);
                nickName.setText(nickNm);
            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_btn:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.to_person:
                startActivity(new Intent(getActivity(), PersonalActivity.class));
                break;
            case R.id.msg_rt:
                startActivity(new Intent(getActivity(), MyMsgActivity.class));
                break;
            case R.id.study_rt:
                startActivity(new Intent(getActivity(), NightSchoolActivity.class));
                break;
            case R.id.collect_rt:
                startActivity(new Intent(getActivity(), MyCollectActivity.class));
                break;
            case R.id.certif_rt:
                startActivity(new Intent(getActivity(), MyCertificateActivity.class));
                break;
            case R.id.profit_rt:
                startActivity(new Intent(getActivity(), MyEarnings2Activity.class));
                break;
            case R.id.service_rt:
                startActivity(new Intent(getActivity(), ServiceCenterActivity.class));
                break;
            case R.id.fdback_rt:
                startActivity(new Intent(getActivity(), SuggestionActivity.class));
                break;
            case R.id.about_etool_rt:
                startActivity(new Intent(getActivity(), AboutExamToolActivity.class));
                break;
            case R.id.real_name_rz:
                Intent intent = new Intent(getActivity(), RealNameRZActivity.class);
                startActivityForResult(intent, MEFRAGEMENT_CODE);
            default:
                break;


        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MEFRAGEMENT_CODE && resultCode == RESULT_OK) {
            realNameRz.setText("已实名认证");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
