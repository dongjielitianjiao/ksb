package com.itboy.dj.examtool.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.widget.tabContainer.adapter.BaseAdapter;

/**
 * Created by Administrator on 2017/6/10.
 */
/*我要维权*/

public class CommunAdapter extends BaseAdapter {

    private FragmentManager fragmentManager;

    private Fragment[] fragmentArray;
    private String[] itemNames;


    public CommunAdapter(FragmentManager fragmentManager, Fragment[] fragmentArray, String[] itemNames) {
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
        return new int[]{R.drawable.base_line_normal,R.drawable.base_line_normal, R.drawable.base_line_normal, R.drawable.base_line_normal};
    }

    //#E7B670
    @Override
    public int[] getSelectedIconImageArray() {
        return new int[]{R.drawable.base_line_select_red, R.drawable.base_line_select_red, R.drawable.base_line_select_red,R.drawable.base_line_select_red};
    }

    @Override
    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }
}
