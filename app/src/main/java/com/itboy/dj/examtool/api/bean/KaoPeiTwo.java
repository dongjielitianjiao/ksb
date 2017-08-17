package com.itboy.dj.examtool.api.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.itboy.dj.examtool.adapter.ExpandableItemTwoAdapter;

/**
 * Created by Administrator on 2017/5/22.
 */

public class KaoPeiTwo implements MultiItemEntity {
    private String twoTitle;
    private  String publishTime;
    private  String firstTime;
    private  String lastTime;
    private  String baomingObject;
    private String trainAdr;

    public KaoPeiTwo() {

    }


    public String getTwoTitle() {
        return twoTitle;
    }

    public void setTwoTitle(String twoTitle) {
        this.twoTitle = twoTitle;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(String firstTime) {
        this.firstTime = firstTime;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getBaomingObject() {
        return baomingObject;
    }

    public void setBaomingObject(String baomingObject) {
        this.baomingObject = baomingObject;
    }

    public String getTrainAdr() {
        return trainAdr;
    }

    public void setTrainAdr(String trainAdr) {
        this.trainAdr = trainAdr;
    }

    @Override
    public int getItemType() {
        return ExpandableItemTwoAdapter.TYPE_LEVEL_1;
    }
}
