package com.itboy.dj.examtool.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.api.bean.Noticebean;
import com.itboy.dj.examtool.utils.GlideRoundTransform;

import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */

public class NoticeAdapter extends BaseQuickAdapter<Noticebean.DataBean, BaseViewHolder> {
    public NoticeAdapter(int layoutResId, List<Noticebean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Noticebean.DataBean item) {
        helper.setText(R.id.hot_new_title, item.getTitle());
        ImageView view = helper.getView(R.id.hot_img);

        Glide.with(view.getContext()).load("http://p5.so.qhimgs1.com/t01f85b5b575f160e8b.jpg").placeholder(R.mipmap.a_ic_yaoqinggongyou).error(R.mipmap.a_ic_yaoqinggongyou).transform(new GlideRoundTransform(view.getContext(), 10)).into(view);

        String titleImg = item.getImgURL();
        if (TextUtils.isEmpty(titleImg) || "null".equals(titleImg)) {

        } else {

        }
        helper.setText(R.id.hot_new_time, item.getCreateDate());
        helper.setText(R.id.hot_new_content, item.getText());
        helper.setText(R.id.hot_new_watchs, item.getViews() + "");
    }
}
