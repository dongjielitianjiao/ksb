package com.itboy.dj.examtool.modules.me.settings;

import android.graphics.Color;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.widget.LToast;
import com.kyleduo.switchbutton.SwitchButton;

import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;

/*设置界面*/
public class SettingActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.base_title_name)
    TextView baseTitleName;
    @BindView(R.id.base_img_bg)
    ImageView baseImgBg;
    @BindView(R.id.right_rt)
    RelativeLayout rightRt;


    @BindView(R.id.set_cache_clear_rt)
    RelativeLayout setCacheClearRt; //清除缓存
    @BindView(R.id.switch_button)
    SwitchButton switchButton; //是否接收消息推送
    @BindView(R.id.show_cache_tx)
    TextView showCacheTx;

    private Handler handler = new Handler() {

        public void handleMessage(android.os.Message msg) {

            switch (msg.what) {

                case 0:
                    Toast.makeText(SettingActivity.this, "清理完成", Toast.LENGTH_SHORT).show();
                    try {
                        showCacheTx.setText(CacheDataManager.getTotalCacheSize(SettingActivity.this));
                    } catch (Exception e) {

                        e.printStackTrace();

                    }

            }

        }

        ;

    };


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        baseTitleName.setText("设置");
        back.setOnClickListener(this);


        //显示缓存大小
        try {
            showCacheTx.setText(CacheDataManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //清除缓存
        setCacheClearRt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final NormalDialog dialog = new NormalDialog(context);
                dialog.isTitleShow(false)//
                        .bgColor(Color.parseColor("#FFFFFF"))//
                        .cornerRadius(5)//
                        .content("是否清除应用缓存？")//
                        .contentGravity(Gravity.CENTER)//
                        .contentTextColor(Color.parseColor("#000000"))//
                        .dividerColor(Color.parseColor("#222222"))//
                        .btnTextSize(15.5f, 15.5f)//
                        .btnTextColor(Color.parseColor("#000000"), Color.parseColor("#000000"))//
                        .btnPressColor(Color.parseColor("#2B2B2B"))//
                        .widthScale(0.75f)//
                        .heightScale(0.30f)
                        .show();
                dialog.setOnBtnClickL(new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                }, new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        new Thread(new clearCache()).start();
                        showCacheTx.setText("清除中...");
                        dialog.dismiss();
                    }
                });


            }
        });

        //是否接收消息推送
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //恢复极光推送
                    JPushInterface.resumePush(getApplicationContext());

                } else {
                    //停止接收极光推送
                    JPushInterface.stopPush(getApplicationContext());
                    LToast.show(context, "已关闭");
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
            default:
                break;
        }
    }


    class clearCache implements Runnable {

        @Override

        public void run() {

            try {

                CacheDataManager.clearAllCache(SettingActivity.this);
                Thread.sleep(2000);
                if (CacheDataManager.getTotalCacheSize(SettingActivity.this).startsWith("0")) {
                    handler.sendEmptyMessage(0);

                }

            } catch (Exception e) {

                return;

            }

        }

    }


}
