package com.itboy.dj.examtool.adapter;

import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.api.bean.KaoPeiTwo;
import com.itboy.dj.examtool.api.bean.KaopeiOne;

import java.util.List;

/**
 * Created by Administrator on 2017/5/22.
 */

public class ExpandableItemTwoAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;
    private int checkItemPosition = 0;

    public void setCheckItem(int position) {
        checkItemPosition = position;
        notifyDataSetChanged();
    }
    public ExpandableItemTwoAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.item_expandable_lv1);
        addItemType(TYPE_LEVEL_1, R.layout.item_expandable_lv2_two);
    }

    @Override
    protected void convert(final BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case TYPE_LEVEL_0:
                final KaopeiOne lv0 = (KaopeiOne) item;
                helper.setText(R.id.one, lv0.getEpOneName());
                helper.setImageResource(R.id.kaopei_img, lv0.isExpanded() ? R.mipmap.e_ic_dianjikaishi : R.mipmap.e_ic_dianjikaishi);
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = helper.getAdapterPosition();
                        if (lv0.isExpanded()) {
                            collapse(pos);
                        } else {
                            expand(pos);
                        }
                    }
                });
                break;
            case TYPE_LEVEL_1:
                final KaoPeiTwo lv1 = (KaoPeiTwo) item;
                helper.setText(R.id.two_title, lv1.getTwoTitle());
                helper.setText(R.id.two_time,"[ 发布日期:" +lv1.getPublishTime()+"]");
                helper.setText(R.id.two_start_end_time, "报名时间: "+lv1.getFirstTime() + lv1.getLastTime());
                helper.setText(R.id.two_duixiang, "报名对象: "+lv1.getBaomingObject());
                helper.setText(R.id.two_train_adr,"培训地址: "+ lv1.getTrainAdr());
                int position1 = helper.getAdapterPosition();
            LinearLayout linearLayout= helper.getView(R.id.ln_back);
                if (checkItemPosition != -1) {
                    if (checkItemPosition == position1){
                        helper.setBackgroundColor(R.id.ln_back, linearLayout.getResources().getColor(R.color.color_gray));
                    } else {
                        helper.setBackgroundColor(R.id.ln_back, linearLayout.getResources().getColor(R.color.white));
                    }
                }
                break;
        }


    }


}
