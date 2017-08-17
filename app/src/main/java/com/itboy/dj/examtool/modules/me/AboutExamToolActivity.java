package com.itboy.dj.examtool.modules.me;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.adapter.ViewPagerAdapter;
import com.itboy.dj.examtool.modules.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

//关于考试宝
public class AboutExamToolActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.base_title_name)
    TextView baseTitleName;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private  List<AboutExamToolBean> aboutExamToolBeen=new ArrayList<>();
    private String type[]={"sys.ksb.intro","sys.ksb.hfx","sys.ksb.qwx","sys.ksb.dbx"};
    private String title[]={"考试宝介绍","合法性","权威性","对比性"};
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_about_exam_tool;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {

        baseTitleName.setText(getString(R.string.abt_examtool));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        int i=4;
        for (int i1 = 0; i1 < i; i1++) {
            aboutExamToolBeen.add(new AboutExamToolBean(type[i1],title[i1]));
        }

               /*实用于碎片较多的情况*/
        final ViewPagerAdapter PageAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        /*碎片较少的情况*/
        // final CommunPageAdapter communPageAdapter=new CommunPageAdapter(getChildFragmentManager());
        viewPager.setAdapter(PageAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        final List<Fragment> fragments = new ArrayList<>();
        final List<String> titles = new ArrayList<>();
        for (AboutExamToolBean aboutExamToolBean : aboutExamToolBeen) {
            titles.add(aboutExamToolBean.getTitle());
            fragments.add(AboutExamToolFragment.newInstance(aboutExamToolBean.getType() + ""));
        }
        PageAdapter.setItems(fragments, titles);



    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }



}
