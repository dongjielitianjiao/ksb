package com.itboy.dj.examtool.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.api.bean.ServiceCenterOne;
import com.itboy.dj.examtool.api.bean.ServiceCenterThree;
import com.itboy.dj.examtool.api.bean.ServiceCenterTwo;

import java.util.List;

/**
 * Created by Administrator on 2017/7/24.
 */

public class ServiceCenterAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;
    public static final int TYPE_ITEM = 2;


    public ServiceCenterAdapter(List<MultiItemEntity> data) {
        super(data);

        addItemType(TYPE_LEVEL_0, R.layout.item_expandable_lv1);
        addItemType(TYPE_LEVEL_1, R.layout.item_expandable_lv2_two_service);
        addItemType(TYPE_ITEM, R.layout.item_expandable_lv3);
    }

    @Override
    protected void convert(final BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case TYPE_LEVEL_0:
                final ServiceCenterOne serviceCenterOne = (ServiceCenterOne) item;
                helper.setText(R.id.one, serviceCenterOne.getOneStr());
                helper.setImageResource(R.id.kaopei_img, serviceCenterOne.isExpanded() ? R.mipmap.c_ic_youjiantou : R.mipmap.c_ic_xiajiantou);
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = helper.getAdapterPosition();
                        if (serviceCenterOne.isExpanded()) {
                            collapse(pos);
                        } else {
                            expand(pos);
                        }
                    }
                });
                break;
            case TYPE_LEVEL_1:
                ServiceCenterTwo serviceCenterTwo = (ServiceCenterTwo) item;
                helper.setText(R.id.two, serviceCenterTwo.getTwoStr());
                break;
            case TYPE_ITEM:
                //没有使用 目前为2级
                final ServiceCenterThree serviceCenterThree = (ServiceCenterThree) item;
                break;

        }
    }
}
