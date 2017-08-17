package com.itboy.dj.examtool.api.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */

public class Noticebean {


    /**
     * result : ok
     * error : {"errorCode":0,"errorText":"success"}
     * data : [{"noticeId":102,"title":"土木建筑钢筋工高级考试6","imgURL":"http://img.kspx.ccla.com.cn/server/upload/image/2017/06/07/1496799113843090257.png","text":"<p>的撒旦撒大力射门德雷克萨马德拉斯<\/p>","createDate":"2017-06-07","views":0,"professionId":94,"workId":177,"rankId":4,"province":1,"organizationId":1},{"noticeId":97,"title":"土木建筑钢筋工高级考试3","imgURL":null,"text":"<p>土木建筑钢筋工高级考试1土木建筑钢筋工高级考试1土木建筑钢筋工高级考试1土木建筑钢筋工高级考试1土木建筑钢筋工高级考试1土木建筑钢筋工高级考试1土木建筑钢筋工高级考试1土木建筑钢筋工高级考试1土木建筑钢筋工高级考试1土木建筑钢筋工高级考试1<\/p>","createDate":"2017-05-25","views":0,"professionId":94,"workId":177,"rankId":4,"province":1,"organizationId":1},{"noticeId":93,"title":"阿斯达斯","imgURL":null,"text":"<p>阿斯达斯阿斯打算打算撒打算<\/p><p>阿萨德阿斯阿斯打算阿斯打算<\/p>","createDate":"2017-03-08","views":0,"professionId":94,"workId":177,"rankId":4,"province":1,"organizationId":1},{"noticeId":96,"title":"土木建筑钢筋工高级考试2","imgURL":null,"text":"<p>土木建筑钢筋工高级考试1土木建筑钢筋工高级考试1土木建筑钢筋工高级考试1土木建筑钢筋工高级考试1土木建筑钢筋工高级考试1土木建筑钢筋工高级考试1土木建筑钢筋工高级考试1土木建筑钢筋工高级考试1土木建筑钢筋工高级考试1土木建筑钢筋工高级考试1土木建筑钢筋工高级考试1土木建筑钢筋工高级考试1土木建筑钢筋工高级考试1土木建筑钢筋工高级考试1土木建筑钢筋工高级考试1<\/p>","createDate":"2017-05-25","views":1,"professionId":94,"workId":177,"rankId":4,"province":1,"organizationId":1}]
     */

    private String result;
    private ErrorBean error;
    private List<DataBean> data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ErrorBean getError() {
        return error;
    }

    public void setError(ErrorBean error) {
        this.error = error;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class ErrorBean {
        /**
         * errorCode : 0
         * errorText : success
         */

        private int errorCode;
        private String errorText;

        public int getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorText() {
            return errorText;
        }

        public void setErrorText(String errorText) {
            this.errorText = errorText;
        }
    }

    public static class DataBean {
        /**
         * noticeId : 102
         * title : 土木建筑钢筋工高级考试6
         * imgURL : http://img.kspx.ccla.com.cn/server/upload/image/2017/06/07/1496799113843090257.png
         * text : <p>的撒旦撒大力射门德雷克萨马德拉斯</p>
         * createDate : 2017-06-07
         * views : 0
         * professionId : 94
         * workId : 177
         * rankId : 4
         * province : 1
         * organizationId : 1
         */

        private int noticeId;
        private String title;
        private String imgURL;
        private String text;
        private String createDate;
        private int views;
        private int professionId;
        private int workId;
        private int rankId;
        private int province;
        private int organizationId;

        public int getNoticeId() {
            return noticeId;
        }

        public void setNoticeId(int noticeId) {
            this.noticeId = noticeId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImgURL() {
            return imgURL;
        }

        public void setImgURL(String imgURL) {
            this.imgURL = imgURL;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public int getProfessionId() {
            return professionId;
        }

        public void setProfessionId(int professionId) {
            this.professionId = professionId;
        }

        public int getWorkId() {
            return workId;
        }

        public void setWorkId(int workId) {
            this.workId = workId;
        }

        public int getRankId() {
            return rankId;
        }

        public void setRankId(int rankId) {
            this.rankId = rankId;
        }

        public int getProvince() {
            return province;
        }

        public void setProvince(int province) {
            this.province = province;
        }

        public int getOrganizationId() {
            return organizationId;
        }

        public void setOrganizationId(int organizationId) {
            this.organizationId = organizationId;
        }
    }
}
