package com.itboy.dj.examtool.api.bean;

/**
 * Created by Administrator on 2017/3/31.
 */
//首页8个item
public class LocalImgTitle {

    private String titles;
    private int imgs;
    private Class<?> className;
    private int showLoading;
    public LocalImgTitle(String titles, int imgs, Class<?> className) {
        this.titles = titles;
        this.imgs = imgs;
        this.className = className;
    }

    public LocalImgTitle(String titles, int imgs, Class<?> className, int showLoading) {
        this.titles = titles;
        this.imgs = imgs;
        this.className = className;
        this.showLoading = showLoading;
    }

    public LocalImgTitle(String titles, int imgs) {
        this.titles = titles;
        this.imgs = imgs;
    }

    public int getShowLoading() {
        return showLoading;
    }

    public void setShowLoading(int showLoading) {
        this.showLoading = showLoading;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public int getImgs() {
        return imgs;
    }

    public void setImgs(int imgs) {
        this.imgs = imgs;
    }

    public Class<?> getClassName() {
        return className;
    }

    public void setClassName(Class<?> className) {
        this.className = className;
    }
}
