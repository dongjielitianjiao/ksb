package com.itboy.dj.examtool.modules.ftpage.apply;

/**
 * Created by Administrator on 2017/6/17.
 */

public class ClassListItem {
/*    helper.setText(R.id.two_title, lv1.getTwoTitle());
    helper.setText(R.id.two_time,"[ 发布日期:" +lv1.getPublishTime()+"]");
    helper.setText(R.id.two_start_end_time, "报名时间: "+lv1.getFirstTime() + lv1.getLastTime());
    helper.setText(R.id.two_duixiang, "报名对象: "+lv1.getBaomingObject());
    helper.setText(R.id.two_train_adr,"培训地址: "+ lv1.getTrainAdr());*/
    public String title,time,startTime,endTime,duixiang,trainAdrss;

    public boolean checked;

    public ClassListItem(String title, String time, String startTime, String endTime, String duixiang, String trainAdrss, boolean checked) {
        this.title = title;
        this.time = time;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duixiang = duixiang;
        this.trainAdrss = trainAdrss;
        this.checked = checked;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDuixiang() {
        return duixiang;
    }

    public void setDuixiang(String duixiang) {
        this.duixiang = duixiang;
    }

    public String getTrainAdrss() {
        return trainAdrss;
    }

    public void setTrainAdrss(String trainAdrss) {
        this.trainAdrss = trainAdrss;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
