package com.itboy.dj.examtool.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.api.bean.MegagameCommentListBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/25.
 */

public class MegaGameCommentListAdapter extends BaseQuickAdapter<MegagameCommentListBean.DataBean,BaseViewHolder> {
    public MegaGameCommentListAdapter(int layoutResId, List<MegagameCommentListBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MegagameCommentListBean.DataBean item) {
        helper.setText(R.id.name,item.getUserInfo().getNickName());
        ImageView view = helper.getView(R.id.comment_head_img);
        String headimgUrl = item.getUserInfo().getImgUrl();
        if (TextUtils.isEmpty(headimgUrl) || "null".equals(headimgUrl)) {

        } else {
            Glide.with(view.getContext()).load(headimgUrl).into(view);
        }
        helper.setText(R.id.comment_content,item.getTxt());
        helper.setText(R.id.time,item.getCreateDate());
    }
}
