package com.itboy.dj.examtool.utils.networkDeal;

import android.app.AlertDialog;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.view.WindowManager;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.itboy.dj.examtool.R;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by Administrator on 2017/3/31.
 */

public class DialogUtils {

    private static AlertDialog mWifiDialog;
    private static NormalDialog dialog;

    public static void showWifiDlg(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context.getApplicationContext());
        if (mWifiDialog == null) {
            mWifiDialog = builder.setIcon(R.mipmap.a_ic_baomin) //
                    .setTitle("网络设置设置")            //
                    .setMessage("当前无网络").setPositiveButton("设置", new DialogInterface
                            .OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 跳转到系统的网络设置界面
                            Intent intent = null;
                            // 先判断当前系统版本
                            if (android.os.Build.VERSION.SDK_INT > 10) {  // 3.0以上
                                intent = new Intent(android.provider.Settings
                                        .ACTION_WIRELESS_SETTINGS);
                            } else {
                                intent = new Intent();
                                ComponentName component = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
                                intent.setComponent(component);
                                intent.setAction("android.intent.action.VIEW");
                            }
                            if ((context instanceof Application)) {
                                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                            }
                            context.startActivity(intent);

                        }
                    }).setNegativeButton("知道了", null).create();
            // 设置为系统的Dialog，这样使用Application的时候不会 报错
            mWifiDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        }
        mWifiDialog.show();


    }


    public static void NormalDialogStyleTwo(final Context mContext) {
        BaseAnimatorSet mBasIn = new BounceTopEnter();
        BaseAnimatorSet mBasOut = new SlideBottomExit();
        if (dialog == null) {
            dialog = new NormalDialog(mContext);
            dialog.content("当前无网络连接，是否设置？")//
                    .style(NormalDialog.STYLE_TWO)//
                    .btnText("取消", "设置")
                    .titleTextSize(23)//
                    .showAnim(mBasIn)//
                    .dismissAnim(mBasOut);

            dialog.setOnBtnClickL(
                    new OnBtnClickL() {
                        @Override
                        public void onBtnClick() {
                            dialog.dismiss();
                        }
                    },
                    new OnBtnClickL() {
                        @Override
                        public void onBtnClick() {
                            // 跳转到系统的网络设置界面
                            Intent intent = null;
                            // 先判断当前系统版本
                            if (android.os.Build.VERSION.SDK_INT > 10) {  // 3.0以上
                                intent = new Intent(android.provider.Settings
                                        .ACTION_WIFI_SETTINGS);
                            } else {
                                intent = new Intent();
                                intent.setClassName("com.android.settings",
                                        Settings.ACTION_WIFI_SETTINGS);
                            }
                            if ((mContext instanceof Application)) {
                                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                            }
                            mContext.startActivity(intent);
                            dialog.dismiss();
                        }
                    });
            dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        }
        dialog.show();

    }
}
