package com.itboy.dj.examtool.modules.ftpage.NightSchool;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.modules.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
//这个是个人中心的我的学习模块,别看名字。。刚开始UI对接错误
public class NightSchoolActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.left_radio_btn)
    RadioButton leftRadioBtn;
    @BindView(R.id.right_radio_btn)
    RadioButton rightRadioBtn;
    @BindView(R.id.commun_radio)
    RadioGroup communRadio;
    @BindView(R.id.night_study_layout)
    FrameLayout nightStudyLayout;

    Fragment onLineFragment, nightSchoolFragment;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_night_school;
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

        setSelect(0);
        communRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.left_radio_btn:
                        setSelect(0);
                        break;
                    case R.id.right_radio_btn:
                        setSelect(1);
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


    private void hideFragment(FragmentTransaction transaction) {
        if (onLineFragment != null) {
            transaction.hide(onLineFragment);
        }
        if (nightSchoolFragment != null) {
            transaction.hide(nightSchoolFragment);
        }
    }

    private void setSelect(int i) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        hideFragment(transaction);//先隐藏所有界面
        switch (i) {
            case 0:
                if (onLineFragment == null) {
                    onLineFragment = new OnlineStudyFragment();
                    transaction.add(R.id.night_study_layout, onLineFragment);
                } else {
                    transaction.show(onLineFragment);
                }
                break;
            case 1:
                if (nightSchoolFragment == null) {
                    nightSchoolFragment = new NightSchoolStudyFragment();
                    transaction.add(R.id.night_study_layout, nightSchoolFragment);
                } else {
                    transaction.show(nightSchoolFragment);
                }
                break;

            default:
                break;
        }
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
