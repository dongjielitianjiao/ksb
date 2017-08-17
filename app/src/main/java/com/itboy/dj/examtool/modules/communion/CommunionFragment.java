package com.itboy.dj.examtool.modules.communion;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.adapter.ViewPagerAdapter;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.bean.CardListBean;
import com.itboy.dj.examtool.api.bean.NotifUpData;
import com.itboy.dj.examtool.modules.base.BaseFragment;
import com.itboy.dj.examtool.rxbus.RxBus;
import com.itboy.dj.examtool.utils.Lerist;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscriber;


public class CommunionFragment extends BaseFragment {


    @BindView(R.id.layout)
    TextView layout;
    Unbinder unbinder;
    @BindView(R.id.base_title_name)
    TextView baseTitleName;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_communion;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        baseTitleName.setText("交流");
        int sysVersion = Integer.parseInt(Build.VERSION.SDK);
        if (sysVersion > 20) {
            layout.setHeight(Lerist.getStatusBarHeight(getActivity()));
        } else {
            layout.setHeight(0);
        }
           initRequest();

        RxBus.getDefault().toObservableSticky(NotifUpData.class).compose(this.<NotifUpData>bindToLife()).subscribe(new Subscriber<NotifUpData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NotifUpData notifUpData) {
                initRequest();
            }
        });


    }

    private void initRequest() {
    /*实用于碎片较多的情况*/
        final ViewPagerAdapter communPageAdapter = new ViewPagerAdapter(getChildFragmentManager());
        /*碎片较少的情况*/
        // final CommunPageAdapter communPageAdapter=new CommunPageAdapter(getChildFragmentManager());
        viewPager.setAdapter(communPageAdapter);
        tabLayout.setupWithViewPager(viewPager);

        final List<Fragment> fragments = new ArrayList<>();
        final List<String> titles = new ArrayList<>();
        final String token = (String) SharedPreferencesUtils.getParam(getActivity(), "Token", "");
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

        //请求回来的数据

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
