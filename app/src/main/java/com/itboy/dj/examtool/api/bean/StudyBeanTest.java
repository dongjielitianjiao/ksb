package com.itboy.dj.examtool.api.bean;

/**
 * Created by Administrator on 2017/4/7.
 */

public class StudyBeanTest {

    private String imgUrl; //图片地址
    private int  momentProgress; //当前进度
    private String watchPeople; //观看人数
    private String title; //正中央标题标题
    private String video_title;//左下角标题
    private String playUrl;

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getMomentProgress() {
        return momentProgress;
    }

    public void setMomentProgress(int momentProgress) {
        this.momentProgress = momentProgress;
    }

    public String getWatchPeople() {
        return watchPeople;
    }

    public void setWatchPeople(String watchPeople) {
        this.watchPeople = watchPeople;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideo_title() {
        return video_title;
    }

    public void setVideo_title(String video_title) {
        this.video_title = video_title;
    }
}
