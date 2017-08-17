package com.itboy.dj.examtool.modules.ftpage.getcertificate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baoyachi.stepview.HorizontalStepView;
import com.baoyachi.stepview.bean.StepBean;
import com.baoyz.actionsheet.ActionSheet;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.modules.communion.ImageBean;
import com.itboy.dj.examtool.modules.communion.Loader;
import com.itboy.dj.examtool.modules.communion.photoshow.ImageShowPickerBean;
import com.itboy.dj.examtool.modules.communion.photoshow.ImageShowPickerListener;
import com.itboy.dj.examtool.modules.communion.photoshow.ImageShowPickerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.RxGalleryFinalApi;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultSubscriber;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageRadioResultEvent;
import cn.finalteam.rxgalleryfinal.utils.MediaScanner;

public class GetCertifStepOneActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.base_title_name)
    TextView baseTitleName;
    @BindView(R.id.cf_intoduce)
    TextView cfIntoduce; //证书说明
    @BindView(R.id.step_view)
    HorizontalStepView stepView;
    @BindView(R.id.cfr_name)
    EditText cfrName; //姓名
    @BindView(R.id.cfr_card)
    EditText cfrCard; //身份证号
    @BindView(R.id.cfr_phone)
    EditText cfrPhone; //电话号码
    @BindView(R.id.cfr_email)
    EditText cfrEmail; //邮箱
    @BindView(R.id.it_picker_view)
    ImageShowPickerView itPickerView; //图片选择
    List<ImageBean> listPhotoOrVideo;
    @BindView(R.id.getcf_next_one)
    Button getcfNextOne;  //下一步

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_get_certif_step_one;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        baseTitleName.setText("领取证书");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        showSetpView4();


        itPickerView.setmAddLabel(R.mipmap.f_ic_zhengjian);
        listPhotoOrVideo = new ArrayList<>();
        itPickerView.setImageLoaderInterface(new Loader());
        itPickerView.setNewData(listPhotoOrVideo);
        itPickerView.setPickerListener(new ImageShowPickerListener() {
            @Override
            public void addOnClickListener(int remainNum) {
                ActionSheet.Builder builder = ActionSheet.createBuilder(GetCertifStepOneActivity.this, getSupportFragmentManager());
                builder.setCancelButtonTitle("取消")
                        .setOtherButtonTitles("拍照", "相册")
                        .setCancelableOnTouchOutside(false).setListener(new ActionSheet.ActionSheetListener() {
                    @Override
                    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {


                    }

                    @Override
                    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
                        switch (index) {
                            case 0:
                                RxGalleryFinalApi.openZKCamera(GetCertifStepOneActivity.this);
                                break;
                            case 1:
                                RxGalleryFinal
                                        .with(GetCertifStepOneActivity.this)
                                        .image()
                                        .radio()
                                        .hideCamera()
                                        .imageLoader(ImageLoaderType.GLIDE)
                                        .subscribe(new RxBusResultSubscriber<ImageRadioResultEvent>() {
                                            @Override
                                            protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
                                                String originalPath = imageRadioResultEvent.getResult().getOriginalPath();
                                                //   Log.d("GetCertifStepOneActivit", originalPath);
                                                itPickerView.addData(new ImageBean(originalPath));
                                            }
                                        }).openGallery();

                                break;
                            default:
                                break;

                        }

                    }

                }).show();


            }

            @Override
            public void picOnClickListener(List<ImageShowPickerBean> list, int position, int remainNum) {

            }

            @Override
            public void delOnClickListener(int position, int remainNum) {

            }
        });
        itPickerView.show();

        getcfNextOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GetCertifStepOneActivity.this, GetCertifStepTwoActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    //领证进度
    private void showSetpView4() {
        /*
        * -1 未完成
        * 0 正在进行
        * 1 已完成
        * */
        List<StepBean> stepsBeanList = new ArrayList<>();
        StepBean stepBean0 = new StepBean("申请证书", 1);
        StepBean stepBean1 = new StepBean("制作证书", 1);
        StepBean stepBean2 = new StepBean("寄送证书", 1);
        StepBean stepBean3 = new StepBean("已签收", -1);
        stepsBeanList.add(stepBean0);
        stepsBeanList.add(stepBean1);
        stepsBeanList.add(stepBean2);
        stepsBeanList.add(stepBean3);

        stepView.setStepViewTexts(stepsBeanList)
                .setTextSize(13)//set textSize
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(this, R.color.text_color_8c))//设置StepsViewIndicator完成线的颜色
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(this, R.color.text_color_8c))//设置StepsViewIndicator未完成线的颜色
                .setStepViewComplectedTextColor(ContextCompat.getColor(this, R.color.theme_grey_accent))//设置StepsView text完成的颜色
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(this, R.color.cf))//设置StepsView text未完成的颜色
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(this, R.drawable.dot_cf_completed))//设置StepsViewIndicator CompleteIcon //已执行完成步骤
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(this, R.drawable.dot_cf_uncompleted));//设置StepsViewIndicator DefaultIcon // 还未执行步骤
        //  .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(this, R.drawable.dot_cf_uncompleted));//设置StepsViewIndicator AttentionIcon  //赶往该步骤处


    }


    //直接拍照的回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RxGalleryFinalApi.TAKE_IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            String originalPath = RxGalleryFinalApi.fileImagePath.getPath();
            itPickerView.addData(new ImageBean(originalPath));
            //刷新相册数据库
            RxGalleryFinalApi.openZKCameraForResult(GetCertifStepOneActivity.this, new MediaScanner.ScanCallback() {
                @Override
                public void onScanCompleted(String[] strings) {

                }
            });
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
