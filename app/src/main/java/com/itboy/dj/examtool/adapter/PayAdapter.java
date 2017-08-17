package com.itboy.dj.examtool.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.modules.ftpage.getcertificate.PayBean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/22.
 */

public class PayAdapter  extends BaseQuickAdapter<PayBean,BaseViewHolder> {

    public PayAdapter(int layoutResId, List<PayBean> data) {
        super(layoutResId, data);
    }
    /*-1 默认没有选择项   其他数字，默认选中该项*/
    private int checkItemPosition=-1;
    public void setCheckItem(int position) {
        checkItemPosition = position;
        notifyDataSetChanged();
    }
    @Override
    protected void convert(BaseViewHolder helper, PayBean item) {
        helper.setImageResource(R.id.pay_img,item.getPayLogoImg());
        helper.setText(R.id.pay_text,item.getPayLogoStr());
        int position = helper.getAdapterPosition();
        ImageView view = helper.getView(R.id.pay_choose_item);
        if (checkItemPosition != -1) {
            if (checkItemPosition == position){
                view.setVisibility(View.VISIBLE);

                helper.setImageResource(R.id.pay_choose_item, R.mipmap.f_ic_duigou);
            } else {
                view.setVisibility(View.GONE);
            }
        }
    }

}
