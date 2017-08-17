package com.itboy.dj.examtool.modules.ftpage.communstudy;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.adapter.ViewPagerAdapter;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.bean.CardListBean;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.modules.communion.ForumFragment;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Subscriber;

public class CommunStudyActivity extends BaseActivity {

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
        return R.layout.activity_commun_study;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        baseTitleName.setText("交流");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

                /*实用于碎片较多的情况*/
        final ViewPagerAdapter communPageAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        /*碎片较少的情况*/
        // final CommunPageAdapter communPageAdapter=new CommunPageAdapter(getChildFragmentManager());
        viewPager.setAdapter(communPageAdapter);
        tabLayout.setupWithViewPager(viewPager);

        final List<Fragment> fragments = new ArrayList<>();
        final List<String> titles = new ArrayList<>();
        final String token = (String) SharedPreferencesUtils.getParam(CommunStudyActivity.this, "Token", "");
        RetrofitService.cardList("forum_type", token).subscribe(new Subscriber<CardListBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(CardListBean cardListBean) {
                /* // table.setTabMode(table.MODE_FIXED);
                  //table.setTabMode(table.MODE_SCROLLABLE);左右滑动显示适用于数量较多*/
                List<CardListBean.DataBean> data = cardListBean.getData();
                int size = data.size();
                if (size < 5) {
                    tabLayout.setTabMode(TabLayout.MODE_FIXED);
                } else {
                    tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
                }
                for (CardListBean.DataBean databean : data) {
                    titles.add(databean.getName());
                    fragments.add(ForumFragment.newInstance(databean.getId() + ""));
                }
                communPageAdapter.setItems(fragments, titles);
            }
        });


    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

}
