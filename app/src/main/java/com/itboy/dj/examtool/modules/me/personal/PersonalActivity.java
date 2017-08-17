package com.itboy.dj.examtool.modules.me.personal;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baoyz.actionsheet.ActionSheet;
import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.RxSubscribe;
import com.itboy.dj.examtool.api.bean.PhotoSuccess;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.rxbus.RxBus;
import com.itboy.dj.examtool.utils.FileUtil;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;
import com.itboy.dj.examtool.widget.LToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.RxGalleryFinalApi;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultSubscriber;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageRadioResultEvent;
import cn.finalteam.rxgalleryfinal.utils.MediaScanner;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

import static com.itboy.dj.examtool.R.id.invite_rt;
@RuntimePermissions
public class PersonalActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.back)
    RelativeLayout back; //返回键
    @BindView(R.id.head_img)
    CircleImageView headImg; //头像
    @BindView(R.id.head_img_rt)
    RelativeLayout headImgRt;//选择头像
    @BindView(R.id.nick_name_show)
    TextView nickNameShow;//昵称
    @BindView(R.id.nick_name_rt)
    RelativeLayout nickNameRt;//选择昵称
    @BindView(R.id.work_yes)
    RadioButton workYes;  //工作中
    @BindView(R.id.work_no)
    RadioButton workNo; //离职
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.profession_rt)
    RelativeLayout professionRt; //职业选择
    @BindView(R.id.profession_show)
    TextView professionShow; //职业展示
    @BindView(R.id.work_address_rt)
    RelativeLayout workAddressRt;//工作地址选择
    @BindView(R.id.work_address_show)
    TextView workAddressShow;//工作地址展示
    @BindView(R.id.me_phone_show)
    TextView mePhoneShow; //电话展示
    @BindView(R.id.phone_rt)
    RelativeLayout phoneRt; //展示选择
    @BindView(R.id.id_card_show)
    TextView idCardShow; //身份证
    @BindView(R.id.card_rt)
    RelativeLayout cardRt; //改变身份证
    @BindView(R.id.command_person_phone_tx_show)
    TextView commandPersonPhoneTxShow;
    @BindView(invite_rt)
    RelativeLayout inviteRt;
    @BindView(R.id.activity_personal)
    LinearLayout activityPersonal;
    private static final int ADRESS_CODE = 1; //改变地址
    //拍照需要自己写裁剪功能
    private static final int REQUESTCODE_CUTTING = 2;    // 裁剪

    private static final int PROFESSIONAL_CODE = 3;//选择职业
    private static final int NICKNAME_CODE = 4;//选择职业
    private static final int INVITE_CODE = 5;//选择职业



    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_personal;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        final String token = (String) SharedPreferencesUtils.getParam(PersonalActivity.this, "Token", "");

        String headImgUrl = (String) SharedPreferencesUtils.getParam(PersonalActivity.this, "HeadPortrait", "");
        if (!TextUtils.isEmpty(headImgUrl) && (!"null".equals(headImgUrl))) {
            Glide.with(PersonalActivity.this).load(headImgUrl).into(headImg);
        } else {
            Glide.with(PersonalActivity.this).load(R.mipmap.b_bg).into(headImg);
        }
        //工作地址
        String adress = (String) SharedPreferencesUtils.getParam(PersonalActivity.this, "jobaddress", "");
        if (!TextUtils.isEmpty(adress) && (!"null".equals(adress))) {
            workAddressShow.setText(adress);
        } else {
            workAddressShow.setText("暂无地址");
        }

        //身份证
        final String idCard = (String) SharedPreferencesUtils.getParam(PersonalActivity.this, "idCard", "");
        if (!TextUtils.isEmpty(idCard) && (!"null".equals(idCard))) {
            idCardShow.setText(idCard);
        } else {
            idCardShow.setText("未录入身份信息");
        }
        //职业信息
        final String professional = (String) SharedPreferencesUtils.getParam(PersonalActivity.this, "professional", "");
        if (!TextUtils.isEmpty(professional) && (!"null".equals(professional))) {
            professionShow.setText(professional);
        } else {
            professionShow.setText("暂无信息");
        }

        //工作状态
        final String iswork = (String) SharedPreferencesUtils.getParam(PersonalActivity.this, "iswork", "");
        if ("false".equals(iswork)) {
            workNo.setChecked(true);
        } else {
            workYes.setChecked(true);
        }

        final String mobile = (String) SharedPreferencesUtils.getParam(PersonalActivity.this, "MovePhone", "");
        mePhoneShow.setText(mobile);

        final String nikeName = (String) SharedPreferencesUtils.getParam(PersonalActivity.this, "nikename", "");
        if (!TextUtils.isEmpty(nikeName) && (!"null".equals(nikeName))) {
            nickNameShow.setText(nikeName);
        } else {
            nickNameShow.setText("");
        }

        final String otherCode = (String) SharedPreferencesUtils.getParam(PersonalActivity.this, "otherCode", "");
        if (!TextUtils.isEmpty(otherCode) && (!"null".equals(otherCode))) {
            commandPersonPhoneTxShow.setText(otherCode);
        } else {
            commandPersonPhoneTxShow.setText("");
        }

        back.setOnClickListener(this);
        headImgRt.setOnClickListener(this);
        workAddressRt.setOnClickListener(this);
        professionRt.setOnClickListener(this);
        phoneRt.setOnClickListener(this);
        inviteRt.setOnClickListener(this);
        nickNameRt.setOnClickListener(this);





        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.work_yes:
                        RetrofitService.isOnWork(true, token).subscribe(new RxSubscribe<JsonObject>(context) {
                            @Override
                            protected void _onNext(JsonObject data) {
                                JSONObject jsonObject = null;
                                try {
                                    jsonObject = new JSONObject(data.toString());
                                    String string = jsonObject.optString("result");
                                    if ("ok".equals(string)) {
                                        LToast.show(context, "修改成功");
                                        RxBus.getDefault().postSticky(new WorkStatus("1"));
                                        SharedPreferencesUtils.setParam(context, "iswork", true + "");
                                    } else {
                                        JSONObject jsonObject1 = jsonObject.optJSONObject("error");
                                        jsonObject1.optString("errorCode");
                                        String errorText = jsonObject1.optString("errorText");
                                        LToast.show(getApplicationContext(), errorText);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            protected void _onError(String message) {
                                LToast.show(getApplicationContext(), message);
                            }
                        });

                        break;
                    case R.id.work_no:
                        RetrofitService.isOnWork(false, token).subscribe(new RxSubscribe<JsonObject>(context) {
                            @Override
                            protected void _onNext(JsonObject data) {
                                JSONObject jsonObject = null;
                                try {
                                    jsonObject = new JSONObject(data.toString());
                                    String string = jsonObject.optString("result");
                                    if ("ok".equals(string)) {
                                        LToast.show(context, "修改成功");
                                        RxBus.getDefault().postSticky(new WorkStatus("2"));
                                        SharedPreferencesUtils.setParam(context, "iswork", false + "");
                                    } else {
                                        JSONObject jsonObject1 = jsonObject.optJSONObject("error");
                                        jsonObject1.optString("errorCode");
                                        String errorText = jsonObject1.optString("errorText");
                                        LToast.show(getApplicationContext(), errorText);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            protected void _onError(String message) {
                                LToast.show(getApplicationContext(), message);
                            }
                        });

                        break;


                    default:
                        break;


                }
            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.head_img_rt:  //头像
                ActionSheet.Builder builder = ActionSheet.createBuilder(PersonalActivity.this, getSupportFragmentManager());
                builder.setCancelButtonTitle("取消")
                        .setOtherButtonTitles("打开相册", "拍照")
                        .setCancelableOnTouchOutside(false).setListener(new ActionSheet.ActionSheetListener() {
                    @Override
                    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {
                    }

                    @Override
                    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
                        switch (index) {
                            case 0:
                                RxGalleryFinal
                                        .with(PersonalActivity.this)
                                        .image()
                                        .radio()
                                        .crop()
                                        .hideCamera()
                                        .imageLoader(ImageLoaderType.GLIDE)
                                        .subscribe(new RxBusResultSubscriber<ImageRadioResultEvent>() {
                                            @Override
                                            protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
                                                String s = imageRadioResultEvent.getResult().getOriginalPath();
                                                uploadHeadimg(s);
                                            }
                                        })
                                        .openGallery();
                                break;
                            case 1:
                                PersonalActivityPermissionsDispatcher.takePhotoWithCheck(PersonalActivity.this);

                                break;
                            default:
                                break;

                        }

                    }



                }).show();
                break;

            case R.id.work_address_rt:  //修改工作地址
                Intent intent = new Intent(PersonalActivity.this, ChangeAdressActivity.class);
                startActivityForResult(intent, ADRESS_CODE);
                break;

            case R.id.profession_rt: //修改职业
                Intent intent1 = new Intent(this, ChangeProfessionalActivity.class);
                startActivityForResult(intent1, PROFESSIONAL_CODE);
                break;

            case R.id.phone_rt: //修改电话
                Intent intent2 = new Intent(this, ChangePhoneNumActivity.class);
                startActivity(intent2);
                break;

            case R.id.invite_rt:
                Intent intent3 = new Intent(this, InviteCodeActivity.class);
                startActivityForResult(intent3, INVITE_CODE);
                break;
            case R.id.nick_name_rt:
                Intent intent4 = new Intent(this, NickNameActivity.class);
                intent4.putExtra("nickname", nickNameShow.getText().toString());
                startActivityForResult(intent4, NICKNAME_CODE);

                break;
            default:
                break;

        }

    }
    @NeedsPermission({Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA})
     void takePhoto() {
        RxGalleryFinalApi.openZKCamera(PersonalActivity.this);
    }




    // @OnShowRationale注释在向用户解释为什么需要这个权限的方法上。
    @OnShowRationale({Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA})
    void showRationaleForWC(final PermissionRequest request) {
        new AlertDialog.Builder(this).setPositiveButton("好的", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                request.proceed();
            }
        }).setNegativeButton("不给", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                request.cancel();
            }
        }).setCancelable(false).setMessage("拍照需要存储和相机权限，应用将要申请存储和相机权限").show();
    }

    @OnPermissionDenied({Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA})
    void showWCDenied(){
        LToast.show(context,"您已拒绝存储权限或相机权限");
    }

    // @OnNeverAskAgain勾选了不再提示禁止后调用的方法
    @OnNeverAskAgain({Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA})
    void onWCNeverAskAgain() {
        new AlertDialog.Builder(this).setPositiveButton("好的", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).setCancelable(false).setMessage("你禁用了存储权限或相机权限，是否开启？").show();


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PersonalActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }



    //直接拍照的回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RxGalleryFinalApi.TAKE_IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            //  Log.d("PersonalActivity", RxGalleryFinalApi.fileImagePath.getPath());
            File temp = new File(RxGalleryFinalApi.fileImagePath.getPath());
            startPhotoZoom(Uri.fromFile(temp));
            //刷新相册数据库
            RxGalleryFinalApi.openZKCameraForResult(PersonalActivity.this, new MediaScanner.ScanCallback() {
                @Override
                public void onScanCompleted(String[] strings) {
                    //   Log.d("PersonalActivity", strings[0]);
                }
            });
        }
        if (requestCode == REQUESTCODE_CUTTING) {
            if (data != null) {
                setPicToView(data);
            }
        } else {
            //   Logger.i("失敗");
        }
        //修改地址回调
        if (requestCode == ADRESS_CODE && resultCode == RESULT_OK) {
            String adress = data.getStringExtra("adress");
            workAddressShow.setText(adress);
        }
        if (requestCode == PROFESSIONAL_CODE && resultCode == RESULT_OK) {

        }
        if (requestCode == NICKNAME_CODE && resultCode == RESULT_OK) {
            String nickname = data.getStringExtra("nickname");
            nickNameShow.setText(nickname);
        }
        if (requestCode == INVITE_CODE && resultCode == RESULT_OK) {
            String nickname = data.getStringExtra("invite");
            commandPersonPhoneTxShow.setText(nickname);
        }

    }












    /*上传头像*/
    private void uploadHeadimg(String path) {
        String access_token = (String) SharedPreferencesUtils.getParam(context, "Token", "");
        File file = new File(path);
        Log.d("PersonalActivity", "file.exists() +file.canWrite()+file.exists():" + file.exists() + file.canWrite() + file.exists() + "");
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("multipartFile", file.getName(), requestFile);
        RetrofitService
                .uploadHeadImg1(requestFile, body, access_token)
                .compose(this.<JsonObject>bindToLife())
                .subscribe(new RxSubscribe<JsonObject>(context) {
                    @Override
                    protected void _onNext(JsonObject jsonObject) {
                   Log.d("PersonalActivity", jsonObject.toString());
                        try {
                            JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                            String result = jsonObject1.optString("result");
                            String errhint = jsonObject1.optJSONObject("error").optString("errorText");
                            String imgUrl = jsonObject1.optString("data");
                            if ("ok".equals(result)) {
                                Glide.with(PersonalActivity.this).load(imgUrl).into(headImg);
                                RxBus.getDefault().post(new PhotoSuccess(imgUrl));
                                SharedPreferencesUtils.setParam(context, "HeadPortrait", imgUrl + "");
                                LToast.show(context, "上传成功");
                            } else {
                                LToast.show(context, errhint);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    protected void _onError(String message) {
                        LToast.show(context, message);
                    }


                });


    }





    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, REQUESTCODE_CUTTING);
    }

    //取得裁剪后的照片
    private void setPicToView(Intent picdata) {
        Bundle extras = picdata.getExtras();
        Bitmap photo = extras.getParcelable("data");
        String s = FileUtil.saveFile(getApplication(), getOutTradeNo() + ".jpg", photo);
        uploadHeadimg(s);
    }

    private static String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);
        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }

}
