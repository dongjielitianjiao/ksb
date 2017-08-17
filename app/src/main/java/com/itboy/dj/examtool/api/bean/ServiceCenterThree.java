package com.itboy.dj.examtool.api.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Administrator on 2017/7/24.
 */

public class ServiceCenterThree implements MultiItemEntity {

    private String threeStr;

    public ServiceCenterThree(String threeStr) {
        this.threeStr = threeStr;
    }

    public String getThreeStr() {
        return threeStr;
    }

    @Override
    public int getItemType() {
        return 2;
    }
}
