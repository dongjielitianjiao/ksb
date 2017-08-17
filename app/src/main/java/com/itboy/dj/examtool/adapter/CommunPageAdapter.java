package com.itboy.dj.examtool.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 * http://blog.csdn.net/q908555281/article/details/48435647
 */

public class CommunPageAdapter extends FragmentPagerAdapter {

    List<Fragment> fragments;
    List<String> mTitles;
    public CommunPageAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        mTitles = new ArrayList<String>();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }
    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return  mTitles.get(position);
    }
    //防止重新加载数据
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    public void setItems(List<Fragment> fragments, List<String> mTitles) {
        this.fragments = fragments;
        this.mTitles = mTitles;
        notifyDataSetChanged();
    }


}
