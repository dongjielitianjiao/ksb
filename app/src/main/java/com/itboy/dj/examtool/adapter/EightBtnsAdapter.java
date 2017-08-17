package com.itboy.dj.examtool.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.api.bean.LocalImgTitle;

import java.util.List;

/**
 * Created by Administrator on 2017/4/1.
 */

public class EightBtnsAdapter extends BaseQuickAdapter<LocalImgTitle, BaseViewHolder> {

    public EightBtnsAdapter(int layoutResId, List<LocalImgTitle> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, LocalImgTitle item) {
        helper.setText(R.id.text, item.getTitles());
        helper.setImageResource(R.id.icon, item.getImgs());
        int showLoading = item.getShowLoading();
        switch (showLoading){
            case 0:
                helper.getView(R.id.qwe).setVisibility(View.VISIBLE);
                break;
            case 1:
                helper.getView(R.id.qwe).setVisibility(View.VISIBLE);
                break;
            case 2:
                helper.getView(R.id.qwe).setVisibility(View.VISIBLE);
                break;
            case 3:
                helper.getView(R.id.qwe).setVisibility(View.INVISIBLE);
                break;
            case 4:
                helper.getView(R.id.qwe).setVisibility(View.INVISIBLE);
                break;
            case 5:
                helper.getView(R.id.qwe).setVisibility(View.INVISIBLE);
                break;
            case 6:
                helper.getView(R.id.qwe).setVisibility(View.INVISIBLE);
                break;
            case 7:
                helper.getView(R.id.qwe).setVisibility(View.INVISIBLE);
                break;


        }
        helper.setImageResource(R.id.qwe,R.mipmap.a_ic_jiantou);

    }
}
