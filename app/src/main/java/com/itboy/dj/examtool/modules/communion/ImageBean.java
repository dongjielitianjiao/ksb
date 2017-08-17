package com.itboy.dj.examtool.modules.communion;


import com.itboy.dj.examtool.modules.communion.photoshow.ImageShowPickerBean;

/**
 * Author 姚智胜
 * Version V1.0版本
 * Description:
 * Date: 2017/4/10
 */

public class ImageBean extends ImageShowPickerBean {
    public ImageBean(String url) {
        this.url = url;
    }

    public ImageBean(int resId) {
        this.resId = resId;
    }

    private String url;

    private int resId;


    @Override
    public String setImageShowPickerUrl() {
        return url;
    }

    @Override
    public int setImageShowPickerDelRes() {
        return resId;
    }
}
