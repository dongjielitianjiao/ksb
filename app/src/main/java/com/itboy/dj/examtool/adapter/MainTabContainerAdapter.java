package com.itboy.dj.examtool.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.widget.tabContainer.adapter.BaseAdapter;

/**
 * Created by chenpengfei on 2017/3/21.  通用的切换tab
 */
public class MainTabContainerAdapter extends BaseAdapter {

    private FragmentManager fragmentManager;

    private Fragment[] fragmentArray;
    private String[] itemNames;

  /*  public MainTabContainerAdapter(FragmentManager fragmentManager, Fragment[] fragmentArray) {
        this.fragmentManager = fragmentManager;
        this.fragmentArray = fragmentArray;
    }
*/
    public MainTabContainerAdapter(FragmentManager fragmentManager, Fragment[] fragmentArray, String[] itemNames) {
        this.fragmentArray = fragmentArray;
        this.fragmentManager = fragmentManager;
        this.itemNames = itemNames;
    }

    @Override
    public int getCount() {
        return itemNames.length;
    }


    @Override
    public String[] getTextArray() {
        return itemNames;
    }

    @Override
    public Fragment[] getFragmentArray() {
        return fragmentArray;
    }

    @Override
    public int[] getIconImageArray() {
        return new int[]{R.drawable.base_line_normal, R.drawable.base_line_normal, R.drawable.base_line_normal, R.drawable.base_line_normal,R.drawable.base_line_normal};
    }

    @Override
    public int[] getSelectedIconImageArray() {
        return new int[]{R.drawable.base_line_select, R.drawable.base_line_select, R.drawable.base_line_select, R.drawable.base_line_select, R.drawable.base_line_select};
    }

    @Override
    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }
}
