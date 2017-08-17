package com.itboy.dj.examtool.modules.ftpage.share;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;
import com.itboy.dj.examtool.widget.LToast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import cn.sharesdk.onekeyshare.OnekeyShare;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class ShareCodeActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.base_title_name)
    TextView baseTitleName;
    @BindView(R.id.base_img_bg)
    ImageView baseImgBg;
    @BindView(R.id.right_rt)
    RelativeLayout rightRt;
    @BindView(R.id.code_img)
    ImageView codeImg;
    @BindView(R.id.code_num)
    TextView codeNum;
    @BindView(R.id.save_code_img)
    Button saveCodeImg;
    @BindView(R.id.share_code_img)
    Button shareCodeImg;


    @Override
    protected int attachLayoutRes() {
        return (R.layout.activity_share_code);
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        //进来直接保存一次
        ShareCodeActivityPermissionsDispatcher.savePictureWithCheck(ShareCodeActivity.this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        baseTitleName.setText("分享");
        rightRt.setVisibility(View.VISIBLE);
        baseImgBg.setImageResource(R.mipmap.h_ic_wenhao);
        rightRt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShareCodeActivity.this, AboutShareActivity.class);
                startActivity(intent);
            }
        });
        String inviteCode = (String) SharedPreferencesUtils.getParam(ShareCodeActivity.this, "inviteCode", "");
        codeNum.setText("我的邀请码：" + inviteCode);

        saveCodeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //保存再次点击
                ShareCodeActivityPermissionsDispatcher.savePictureWithCheck(ShareCodeActivity.this);


            }
        });
        //分享
        shareCodeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });

    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
     void savePicture() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.codeimg);
        saveFile(ShareCodeActivity.this, "codeimg.PNG", bitmap);
        Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show();
    }


    // @OnShowRationale注释在向用户解释为什么需要这个权限的方法上。
    @OnShowRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void showRationaleForWriteStorage(final PermissionRequest request) {
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
        }).setCancelable(false).setMessage("分享需要存储权限，应用将要申请存储权限").show();
    }


    @OnPermissionDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void showRecordDenied(){
        LToast.show(context,"您禁止了存储权限");
    }

    // @OnNeverAskAgain勾选了不再提示禁止后调用的方法
    @OnNeverAskAgain(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void onRecordNeverAskAgain() {
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
        }).setCancelable(false).setMessage("你禁用了存储权限，是否开启？").show();


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ShareCodeActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }




    /**
     * 保存图片放到文件中
     */
    public String saveFile(Context c, String fileName, Bitmap bitmap) {
        return saveFile(c, "", fileName, bitmap);
    }

    public String saveFile(Context c, String filePath, String fileName, Bitmap bitmap) {
        byte[] bytes = bitmapToBytes(bitmap);
        return saveFile(c, filePath, fileName, bytes);
    }

    public static byte[] bitmapToBytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }

    public String saveFile(Context c, String filePath, String fileName, byte[] bytes) {
        String fileFullName = "";
        FileOutputStream fos = null;
    /*    String dateFolder = new SimpleDateFormat("yyyyMMdd", Locale.CHINA)
                .format(new Date());*/
        String dateFolder = "codeIMGs";
        try {
            String suffix = "";
            if (filePath == null || filePath.trim().length() == 0) {
                filePath = Environment.getExternalStorageDirectory() + "/Ksb/" + dateFolder + "/";
            }
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            File fullFile = new File(filePath, fileName + suffix);
            fileFullName = fullFile.getPath();
            fos = new FileOutputStream(new File(filePath, fileName + suffix));
            fos.write(bytes);
        } catch (Exception e) {
            fileFullName = "";
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    fileFullName = "";
                }
            }
        }
        return fileFullName;
    }

    //分享
    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // 分享时Notification的图标和文字  2.5.9以后的版本不     调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("https://www.pgyer.com/6HMi");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("一款您值得拥有的考试神器，官方下载地址：https://www.pgyer.com/6HMi");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        String s = Environment.getExternalStorageDirectory() + "/Ksb/codeIMGs" + "/codeimg.PNG";
        oks.setImagePath(s);//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("考试宝就是好啊");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("https://www.pgyer.com/6HMi");

        // 启动分享GUI
        oks.show(this);
    }


    //保存原则 按时间来保存
    private static String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);
        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }


    @Override
    protected void updateViews(boolean isRefresh) {

    }

}
