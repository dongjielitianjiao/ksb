package com.itboy.dj.examtool.api.bean;

/**
 * Created by Administrator on 2017/4/24.
 */

public class BannerItem {

    private String title;
    private String image;
    private String id;


    public BannerItem() {

    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
