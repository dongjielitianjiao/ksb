package com.itboy.dj.examtool.api.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/24.
 */

public class ServiceCenterBean {


    /**
     * result : ok
     * error : {"errorCode":0,"errorText":"success"}
     * data : {"phone":null,"contents":[{"contentId":164,"title":"证书有什么作用呢？","shortTitle":null,"description":null,"titleImg":null,"releaseDate":"2017-07-14","type":null,"views":0,"text":"<p>&nbsp;啊撒打算打算阿萨德阿斯达斯<\/p>","linkUrl":null},{"contentId":163,"title":"怎样能够快速拿证？","shortTitle":null,"description":null,"titleImg":null,"releaseDate":"2017-07-14","type":null,"views":0,"text":"<p>啊撒打算打算阿斯达斯<\/p>","linkUrl":null},{"contentId":162,"title":"如何使用APP？","shortTitle":null,"description":null,"titleImg":null,"releaseDate":"2017-07-14","type":null,"views":0,"text":"<p>啊撒打算打算打算<\/p>","linkUrl":null}]}
     */

    private String result;
    private ErrorBean error;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
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
         * phone : null
         * contents : [{"contentId":164,"title":"证书有什么作用呢？","shortTitle":null,"description":null,"titleImg":null,"releaseDate":"2017-07-14","type":null,"views":0,"text":"<p>&nbsp;啊撒打算打算阿萨德阿斯达斯<\/p>","linkUrl":null},{"contentId":163,"title":"怎样能够快速拿证？","shortTitle":null,"description":null,"titleImg":null,"releaseDate":"2017-07-14","type":null,"views":0,"text":"<p>啊撒打算打算阿斯达斯<\/p>","linkUrl":null},{"contentId":162,"title":"如何使用APP？","shortTitle":null,"description":null,"titleImg":null,"releaseDate":"2017-07-14","type":null,"views":0,"text":"<p>啊撒打算打算打算<\/p>","linkUrl":null}]
         */

        private Object phone;
        private List<ContentsBean> contents;

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
            this.phone = phone;
        }

        public List<ContentsBean> getContents() {
            return contents;
        }

        public void setContents(List<ContentsBean> contents) {
            this.contents = contents;
        }

        public static class ContentsBean {
            /**
             * contentId : 164
             * title : 证书有什么作用呢？
             * shortTitle : null
             * description : null
             * titleImg : null
             * releaseDate : 2017-07-14
             * type : null
             * views : 0
             * text : <p>&nbsp;啊撒打算打算阿萨德阿斯达斯</p>
             * linkUrl : null
             */

            private int contentId;
            private String title;
            private Object shortTitle;
            private Object description;
            private Object titleImg;
            private String releaseDate;
            private Object type;
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

            public Object getTitleImg() {
                return titleImg;
            }

            public void setTitleImg(Object titleImg) {
                this.titleImg = titleImg;
            }

            public String getReleaseDate() {
                return releaseDate;
            }

            public void setReleaseDate(String releaseDate) {
                this.releaseDate = releaseDate;
            }

            public Object getType() {
                return type;
            }

            public void setType(Object type) {
                this.type = type;
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
}
