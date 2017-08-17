package com.itboy.dj.examtool.api.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */

public class NewsBean {


    /**
     * result : ok
     * error : {"errorCode":-1,"errorText":null}
     * data : [{"contentId":157,"title":"点点滴滴","shortTitle":null,"description":null,"titleImg":"http://img.kspx.ccla.com.cn/server/upload/image/2017/04/20/1492669938780095967.png","releaseDate":"2017-04-20","views":0,"text":"<p>大啊啊啊啊啊啊啊啊啊<\/p>","linkUrl":null},{"contentId":154,"title":"考事宝APP上线了","shortTitle":null,"description":null,"titleImg":"http://img.kspx.ccla.com.cn/server/upload/image/2017/04/20/1492661750105018106.png","releaseDate":"2017-04-19","views":0,"text":"<p>考事宝APP上线了考事宝APP上线了考事宝APP上线了考事宝APP上线了考事宝APP上线了<\/p>","linkUrl":null}]
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
         * errorCode : -1
         * errorText : null
         */

        private int errorCode;
        private Object errorText;

        public int getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }

        public Object getErrorText() {
            return errorText;
        }

        public void setErrorText(Object errorText) {
            this.errorText = errorText;
        }
    }

    public static class DataBean {



        /**
         * contentId : 157
         * title : 点点滴滴
         * shortTitle : null
         * description : null
         * titleImg : http://img.kspx.ccla.com.cn/server/upload/image/2017/04/20/1492669938780095967.png
         * releaseDate : 2017-04-20
         * views : 0
         * text : <p>大啊啊啊啊啊啊啊啊啊</p>
         * linkUrl : null
         */

        private int contentId;
        private String title;
        private Object shortTitle;
        private Object description;
        private String titleImg;
        private String releaseDate;
        private int views;
        private String text;
        private Object linkUrl;

        public int getContentId() {
            return contentId;
        }

        public void setContentId(int contentId) {
            this.contentId = contentId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Object getShortTitle() {
            return shortTitle;
        }

        public void setShortTitle(Object shortTitle) {
            this.shortTitle = shortTitle;
        }

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
            this.description = description;
        }

        public String getTitleImg() {
            return titleImg;
        }

        public void setTitleImg(String titleImg) {
            this.titleImg = titleImg;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Object getLinkUrl() {
            return linkUrl;
        }

        public void setLinkUrl(Object linkUrl) {
            this.linkUrl = linkUrl;
        }
    }
}
