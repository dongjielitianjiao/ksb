package com.itboy.dj.examtool.api.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.itboy.dj.examtool.adapter.ServiceCenterAdapter;

/**
 * Created by Administrator on 2017/7/24.
 */

public class ServiceCenterOne  extends AbstractExpandableItem<ServiceCenterTwo> implements MultiItemEntity {

    private String oneStr;

    public ServiceCenterOne(String oneStr) {
        this.oneStr = oneStr;
    }

    public String getOneStr() {
        return oneStr;
    }

    @Override
    public int getLevel() {
        return ServiceCenterAdapter.TYPE_LEVEL_0;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
