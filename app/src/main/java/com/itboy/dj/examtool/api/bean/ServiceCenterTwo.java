package com.itboy.dj.examtool.api.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.itboy.dj.examtool.adapter.ServiceCenterAdapter;

/**
 * Created by Administrator on 2017/7/24.
 */

public class ServiceCenterTwo extends AbstractExpandableItem<ServiceCenterThree> implements MultiItemEntity {
    private String twoStr;

    public ServiceCenterTwo(String twoStr) {
        this.twoStr = twoStr;
    }

    public String getTwoStr() {
        return twoStr;
    }


    @Override
    public int getLevel() {
        return ServiceCenterAdapter.TYPE_LEVEL_1;
    }

    @Override
    public int getItemType() {
        return 1;
    }
}
