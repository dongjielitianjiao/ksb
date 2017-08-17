package com.itboy.dj.examtool.modules.home;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.modules.communion.CommunionFragment;
import com.itboy.dj.examtool.modules.ftpage.FtPageFragment;
import com.itboy.dj.examtool.modules.login.LoginActivity;
import com.itboy.dj.examtool.modules.me.MeFragment;
import com.itboy.dj.examtool.modules.study.StudyFragment;
import com.itboy.dj.examtool.utils.LActivityManager;
import com.itboy.dj.examtool.utils.Lerist;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;
import com.itboy.dj.examtool.widget.LPageIndicator;

import butterknife.BindView;

public class HomeActivity extends BaseActivity implements FtPageFragment.EightbtnListener {


    @BindView(R.id.layout)
    FrameLayout layout;
    @BindView(R.id.tabLayout)
    LPageIndicator tabLayout;
    //绑定布局
    private String[] str;
    public static int position = 0;
    private Fragment firstPageFragment, studyFragment, communionFragment, meFragment;
    private static final int LOGIN = 6;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_home;
    }

    //注解工具
    @Override
    protected void initInjector() {

    }

    //初始化视图
    @Override
    protected void initViews() {
        str = new String[]{getString(R.string.ft_page), getString(R.string.sec_study), getString(R.string.thr_communion), getString(R.string.four_me)};
        tabLayout.setNormalResourceIds(R.mipmap.a_ic_shouye, R.mipmap.a_ic_xuexiliebiao, R.mipmap.a_ic_jiaoliu, R.mipmap.a_ic_wode);
        tabLayout.setFocusResourceIds(R.mipmap.a_ic_shouye_s, R.mipmap.a_ic_xuexiliebiao_s, R.mipmap.a_ic_jiaoliu_s, R.mipmap.a_ic_wode_s);
        tabLayout.setIconSize(Lerist.dip2px(this, 24), Lerist.dip2px(this, 24));
        tabLayout.setInPadding(Lerist.dip2px(this, 6));
        tabLayout.setTexts(str);
        tabLayout.setTextSize(14);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.table_bg));
        tabLayout.setRippleColor(getResources().getColor(R.color.color_gray));
        tabLayout.setTextColor(getResources().getColor(R.color.color_white), getResources().getColor(R.color.table_select_color));

        //默认选中首页
        setSelect(0);




    }

    //改变视图
    @Override
    protected void updateViews(boolean isRefresh) {

        tabLayout.setIndicatorSelectListener(new LPageIndicator.IndicatorSelectListener() {
            @Override
            public void indicatorSelected(int selectedIndex) {
                switch (selectedIndex) {
                    case 0:
                        if(selectedIndex!=position){ //防止连续点击同一个item，界面一直闪的问题
                            position = 0;
                            setSelect(position);
                        }

                        break;
                    case 1:
                        String pwd1 = (String) SharedPreferencesUtils.getParam(context, "Password", "");
                        if (pwd1.equals("")) {
                            startActivityForResult(new Intent(context, LoginActivity.class).putExtra("study", "study"), 200);
                        } else {
                            if(selectedIndex!=position){
                                position = 1;
                                setSelect(position);
                            }
                        }
                        break;
                    case 2:
                        if(selectedIndex!=position){
                            position = 2;
                            setSelect(position);
                        }
                        break;
                    case 3:
                        String pwd = (String) SharedPreferencesUtils.getParam(context, "Password", "");
                        if (pwd.equals("")) {
                            startActivityForResult(new Intent(context, LoginActivity.class).putExtra("center", "center"), 100);
                        } else {
                            if(selectedIndex!=position){
                                position = 3;
                                setSelect(position);
                            }
                        }
                        break;
                    default:
                        break;


                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {//个人中心

            tabLayout.setIndicatorSeleted(data == null ? position : data.getIntExtra("position", position));
        } else if (requestCode == 200) {
            tabLayout.setIndicatorSeleted(data == null ? position : data.getIntExtra("position", position));
        }
    }

    //用于隐藏界面
    private void hideFragment(FragmentTransaction transaction) {
        if (firstPageFragment != null) {
            transaction.hide(firstPageFragment);
        }
        if (studyFragment != null) {
            transaction.hide(studyFragment);
        }
        if (communionFragment != null) {
            transaction.hide(communionFragment);
        }
        if (meFragment != null) {
            transaction.hide(meFragment);
        }

    }


    private void setSelect(int i) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        hideFragment(transaction);//先隐藏所有界面
        switch (i) {
            case 0:
                if (firstPageFragment == null) {
                    firstPageFragment = new FtPageFragment();
                    transaction.add(R.id.layout, firstPageFragment);
                } else {
                    transaction.show(firstPageFragment);
                }
                break;
            case 1:
                if (studyFragment == null) {
                    studyFragment = new StudyFragment();
                    transaction.add(R.id.layout, studyFragment);
                } else {
                    transaction.show(studyFragment);
                }
                break;
            case 2:
                if (communionFragment == null) {
                    communionFragment = new CommunionFragment();
                    transaction.add(R.id.layout, communionFragment);
                } else {
                    transaction.show(communionFragment);
                }

                break;
            case 3:
                if (meFragment == null) {
                    meFragment = new MeFragment();
                    transaction.add(R.id.layout, meFragment);
                } else {
                    transaction.show(meFragment);
                }

                break;
            default:
                break;
        }
        transaction.commit();
    }


    private long exitTime = 0;

    @Override
    public void onBackPressed() {

        if (System.currentTimeMillis() - exitTime > 2000) {
            Toast.makeText(this, "再点击一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {

            LActivityManager.finishAllActivity();
            HomeActivity.position=0;
        }


    }

    //点击学习跳转到二个模块
    @Override
    public void btn(int postion) {
        tabLayout.setIndicatorSeleted(postion);
    }
}
