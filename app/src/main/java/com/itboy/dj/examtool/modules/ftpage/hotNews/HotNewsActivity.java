package com.itboy.dj.examtool.modules.ftpage.hotNews;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.adapter.ViewPagerAdapter;
import com.itboy.dj.examtool.api.bean.NewsTabTitle;
import com.itboy.dj.examtool.modules.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HotNewsActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.base_title_name)
    TextView baseTitleName;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_hot_news;
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

        baseTitleName.setText("热门资讯");

            /*实用于碎片较多的情况*/
        final ViewPagerAdapter newListViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(newListViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

       //初始化数据
        final List<NewsTabTitle> newsTabTitles = new ArrayList<>();
        NewsTabTitle newsTabTitle = new NewsTabTitle("通知类", "1","notice");
        newsTabTitles.add(newsTabTitle);
        newsTabTitle = new NewsTabTitle("报考类", "2","noPara"); //因为报考类的接口不一样，所以传个类型判断一下
        newsTabTitles.add(newsTabTitle);
        newsTabTitle = new NewsTabTitle("政策类", "1","zcwj");
        newsTabTitles.add(newsTabTitle);


        final List<Fragment> fragments = new ArrayList<>();
        final List<String> titles = new ArrayList<>();
        for (NewsTabTitle databean : newsTabTitles) {
            titles.add(databean.getName());
            fragments.add(NewsListFragment.newInstance(databean.getType(),databean.getParameter()));
        }
        newListViewPagerAdapter.setItems(fragments, titles);

    }

    @Override
    protected void updateViews(boolean isRefresh) {


    }



}
