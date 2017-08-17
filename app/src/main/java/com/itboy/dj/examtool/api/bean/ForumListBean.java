package com.itboy.dj.examtool.api.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/6/19.
 */

public class ForumListBean {
    /**
     * result : ok
     * error : {"errorCode":0,"errorText":"success"}
     * data : {"total":14,"rows":[{"forumId":16,"userId":7331,"forumType":"150","title":"调试后视频测试","txt":"嘛嗯，那就是什么事了","videoUrls":[],"imageUrls":[],"createDate":"2017-06-19","lastMofityDate":null,"lastReplyDate":"2017-07-28","viewCount":11,"storeCount":1,"replyCount":1,"upCount":0,"downCount":0,"status":null,"isValid":true,"isClosed":false,"replies":null},{"forumId":15,"userId":7331,"forumType":"150","title":"下午测试","txt":"就读小学语文教师资格证","videoUrls":[],"imageUrls":["http://img.kspx.ccla.com.cn/server/upload/image/2017/06/19/1497857118530064453.jpg"],"createDate":"2017-06-19","lastMofityDate":null,"lastReplyDate":"2017-07-28","viewCount":7,"storeCount":0,"replyCount":1,"upCount":0,"downCount":0,"status":null,"isValid":true,"isClosed":false,"replies":null},{"forumId":14,"userId":7331,"forumType":"150","title":"一个人","txt":"工商联负责人","videoUrls":[],"imageUrls":[],"createDate":"2017-06-19","lastMofityDate":null,"lastReplyDate":"2017-07-26","viewCount":4,"storeCount":0,"replyCount":1,"upCount":0,"downCount":0,"status":null,"isValid":true,"isClosed":false,"replies":null},{"forumId":13,"userId":7331,"forumType":"150","title":"这种","txt":"这种生个儿子的小伙伴的支持","videoUrls":[],"imageUrls":[],"createDate":"2017-06-19","lastMofityDate":null,"lastReplyDate":null,"viewCount":1,"storeCount":0,"replyCount":0,"upCount":0,"downCount":0,"status":null,"isValid":true,"isClosed":false,"replies":null},{"forumId":12,"userId":7331,"forumType":"150","title":"早哦","txt":"你就不知道怎么了、一","videoUrls":[],"imageUrls":[],"createDate":"2017-06-19","lastMofityDate":null,"lastReplyDate":"2017-07-05","viewCount":5,"storeCount":0,"replyCount":9,"upCount":0,"downCount":0,"status":null,"isValid":true,"isClosed":false,"replies":null},{"forumId":11,"userId":7331,"forumType":"150","title":"第八波","txt":"工商管理专科学校团委学生会办公室","videoUrls":[],"imageUrls":[],"createDate":"2017-06-19","lastMofityDate":null,"lastReplyDate":"2017-07-05","viewCount":7,"storeCount":0,"replyCount":6,"upCount":0,"downCount":0,"status":null,"isValid":true,"isClosed":false,"replies":null}],"footer":[]}
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
         * total : 14
         * rows : [{"forumId":16,"userId":7331,"forumType":"150","title":"调试后视频测试","txt":"嘛嗯，那就是什么事了","videoUrls":[],"imageUrls":[],"createDate":"2017-06-19","lastMofityDate":null,"lastReplyDate":"2017-07-28","viewCount":11,"storeCount":1,"replyCount":1,"upCount":0,"downCount":0,"status":null,"isValid":true,"isClosed":false,"replies":null},{"forumId":15,"userId":7331,"forumType":"150","title":"下午测试","txt":"就读小学语文教师资格证","videoUrls":[],"imageUrls":["http://img.kspx.ccla.com.cn/server/upload/image/2017/06/19/1497857118530064453.jpg"],"createDate":"2017-06-19","lastMofityDate":null,"lastReplyDate":"2017-07-28","viewCount":7,"storeCount":0,"replyCount":1,"upCount":0,"downCount":0,"status":null,"isValid":true,"isClosed":false,"replies":null},{"forumId":14,"userId":7331,"forumType":"150","title":"一个人","txt":"工商联负责人","videoUrls":[],"imageUrls":[],"createDate":"2017-06-19","lastMofityDate":null,"lastReplyDate":"2017-07-26","viewCount":4,"storeCount":0,"replyCount":1,"upCount":0,"downCount":0,"status":null,"isValid":true,"isClosed":false,"replies":null},{"forumId":13,"userId":7331,"forumType":"150","title":"这种","txt":"这种生个儿子的小伙伴的支持","videoUrls":[],"imageUrls":[],"createDate":"2017-06-19","lastMofityDate":null,"lastReplyDate":null,"viewCount":1,"storeCount":0,"replyCount":0,"upCount":0,"downCount":0,"status":null,"isValid":true,"isClosed":false,"replies":null},{"forumId":12,"userId":7331,"forumType":"150","title":"早哦","txt":"你就不知道怎么了、一","videoUrls":[],"imageUrls":[],"createDate":"2017-06-19","lastMofityDate":null,"lastReplyDate":"2017-07-05","viewCount":5,"storeCount":0,"replyCount":9,"upCount":0,"downCount":0,"status":null,"isValid":true,"isClosed":false,"replies":null},{"forumId":11,"userId":7331,"forumType":"150","title":"第八波","txt":"工商管理专科学校团委学生会办公室","videoUrls":[],"imageUrls":[],"createDate":"2017-06-19","lastMofityDate":null,"lastReplyDate":"2017-07-05","viewCount":7,"storeCount":0,"replyCount":6,"upCount":0,"downCount":0,"status":null,"isValid":true,"isClosed":false,"replies":null}]
         * footer : []
         */

        private int total;
        private List<RowsBean> rows;
        private List<?> footer;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public List<?> getFooter() {
            return footer;
        }

        public void setFooter(List<?> footer) {
            this.footer = footer;
        }

        public static class RowsBean implements MultiItemEntity {
            /**
             * forumId : 16
             * userId : 7331
             * forumType : 150
             * title : 调试后视频测试
             * txt : 嘛嗯，那就是什么事了
             * videoUrls : []
             * imageUrls : []
             * createDate : 2017-06-19
             * lastMofityDate : null
             * lastReplyDate : 2017-07-28
             * viewCount : 11
             * storeCount : 1
             * replyCount : 1
             * upCount : 0
             * downCount : 0
             * status : null
             * isValid : true
             * isClosed : false
             * replies : null
             */

            public static final int TYPE_1 = 1;
            public static final int TYPE_2 = 2;
            public static final int TYPE_3 = 3;
            public static final int TYPE_4 = 4;
            public static final int TYPE_5 = 5;

            private int itemType;

            public void setItemType(int itemType) {
                this.itemType = itemType;
            }

            private int forumId;
            private int userId;
            private String forumType;
            private String title;
            private String txt;
            private String createDate;
            private Object lastMofityDate;
            private String lastReplyDate;
            private int viewCount;
            private int storeCount;
            private int replyCount;
            private int upCount;
            private int downCount;
            private Object status;
            private boolean isValid;
            private boolean isClosed;
            private Object replies;
            private List<?> videoUrls;
            private List<?> imageUrls;

            public int getForumId() {
                return forumId;
            }

            public void setForumId(int forumId) {
                this.forumId = forumId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getForumType() {
                return forumType;
            }

            public void setForumType(String forumType) {
                this.forumType = forumType;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public Object getLastMofityDate() {
                return lastMofityDate;
            }

            public void setLastMofityDate(Object lastMofityDate) {
                this.lastMofityDate = lastMofityDate;
            }

            public String getLastReplyDate() {
                return lastReplyDate;
            }

            public void setLastReplyDate(String lastReplyDate) {
                this.lastReplyDate = lastReplyDate;
            }

            public int getViewCount() {
                return viewCount;
            }

            public void setViewCount(int viewCount) {
                this.viewCount = viewCount;
            }

            public int getStoreCount() {
                return storeCount;
            }

            public void setStoreCount(int storeCount) {
                this.storeCount = storeCount;
            }

            public int getReplyCount() {
                return replyCount;
            }

            public void setReplyCount(int replyCount) {
                this.replyCount = replyCount;
            }

            public int getUpCount() {
                return upCount;
            }

            public void setUpCount(int upCount) {
                this.upCount = upCount;
            }

            public int getDownCount() {
                return downCount;
            }

            public void setDownCount(int downCount) {
                this.downCount = downCount;
            }

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }

            public boolean isIsValid() {
                return isValid;
            }

            public void setIsValid(boolean isValid) {
                this.isValid = isValid;
            }

            public boolean isIsClosed() {
                return isClosed;
            }

            public void setIsClosed(boolean isClosed) {
                this.isClosed = isClosed;
            }

            public Object getReplies() {
                return replies;
            }

            public void setReplies(Object replies) {
                this.replies = replies;
            }

            public List<?> getVideoUrls() {
                return videoUrls;
            }

            public void setVideoUrls(List<?> videoUrls) {
                this.videoUrls = videoUrls;
            }

            public List<?> getImageUrls() {
                return imageUrls;
            }

            public void setImageUrls(List<?> imageUrls) {
                this.imageUrls = imageUrls;
            }

            @Override
            public int getItemType() {
                return itemType;
            }
        }
    }


 /*   *//**
     * result : ok
     * error : {"errorCode":0,"errorText":"success"}
     * data : {"total":14,"rows":[{"forumId":16,"userId":7331,"forumType":"150","title":"调试后视频测试","txt":"嘛嗯，那就是什么事了","videoUrls":["http://img.kspx.ccla.com.cn/server/upload/file/2017/06/19/1497857521367070105.mp4"],"imageUrls":null,"createDate":"2017-06-19","lastMofityDate":null,"lastReplyDate":null,"viewCount":0,"storeCount":0,"replyCount":0,"upCount":0,"downCount":0,"status":null,"isValid":true,"isClosed":false,"replies":null},{"forumId":15,"userId":7331,"forumType":"150","title":"下午测试","txt":"就读小学语文教师资格证","videoUrls":null,"imageUrls":["http://img.kspx.ccla.com.cn/server/upload/image/2017/06/19/1497857118530064453.jpg"],"createDate":"2017-06-19","lastMofityDate":null,"lastReplyDate":null,"viewCount":0,"storeCount":0,"replyCount":0,"upCount":0,"downCount":0,"status":null,"isValid":true,"isClosed":false,"replies":null},{"forumId":14,"userId":7331,"forumType":"150","title":"一个人","txt":"工商联负责人","videoUrls":null,"imageUrls":null,"createDate":"2017-06-19","lastMofityDate":null,"lastReplyDate":null,"viewCount":0,"storeCount":0,"replyCount":0,"upCount":0,"downCount":0,"status":null,"isValid":true,"isClosed":false,"replies":null},{"forumId":13,"userId":7331,"forumType":"150","title":"这种","txt":"这种生个儿子的小伙伴的支持","videoUrls":null,"imageUrls":null,"createDate":"2017-06-19","lastMofityDate":null,"lastReplyDate":null,"viewCount":0,"storeCount":0,"replyCount":0,"upCount":0,"downCount":0,"status":null,"isValid":true,"isClosed":false,"replies":null}],"footer":[]}
     *//*

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
        *//**
         * errorCode : 0
         * errorText : success
         *//*

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
        *//**
         * total : 14
         * rows : [{"forumId":16,"userId":7331,"forumType":"150","title":"调试后视频测试","txt":"嘛嗯，那就是什么事了","videoUrls":["http://img.kspx.ccla.com.cn/server/upload/file/2017/06/19/1497857521367070105.mp4"],"imageUrls":null,"createDate":"2017-06-19","lastMofityDate":null,"lastReplyDate":null,"viewCount":0,"storeCount":0,"replyCount":0,"upCount":0,"downCount":0,"status":null,"isValid":true,"isClosed":false,"replies":null},{"forumId":15,"userId":7331,"forumType":"150","title":"下午测试","txt":"就读小学语文教师资格证","videoUrls":null,"imageUrls":["http://img.kspx.ccla.com.cn/server/upload/image/2017/06/19/1497857118530064453.jpg"],"createDate":"2017-06-19","lastMofityDate":null,"lastReplyDate":null,"viewCount":0,"storeCount":0,"replyCount":0,"upCount":0,"downCount":0,"status":null,"isValid":true,"isClosed":false,"replies":null},{"forumId":14,"userId":7331,"forumType":"150","title":"一个人","txt":"工商联负责人","videoUrls":null,"imageUrls":null,"createDate":"2017-06-19","lastMofityDate":null,"lastReplyDate":null,"viewCount":0,"storeCount":0,"replyCount":0,"upCount":0,"downCount":0,"status":null,"isValid":true,"isClosed":false,"replies":null},{"forumId":13,"userId":7331,"forumType":"150","title":"这种","txt":"这种生个儿子的小伙伴的支持","videoUrls":null,"imageUrls":null,"createDate":"2017-06-19","lastMofityDate":null,"lastReplyDate":null,"viewCount":0,"storeCount":0,"replyCount":0,"upCount":0,"downCount":0,"status":null,"isValid":true,"isClosed":false,"replies":null}]
         * footer : []
         *//*

        private int total;
        private List<RowsBean> rows;
        private List<?> footer;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public List<?> getFooter() {
            return footer;
        }

        public void setFooter(List<?> footer) {
            this.footer = footer;
        }

        public static class RowsBean implements MultiItemEntity {
            *//**
             * forumId : 16
             * userId : 7331
             * forumType : 150
             * title : 调试后视频测试
             * txt : 嘛嗯，那就是什么事了
             * videoUrls : ["http://img.kspx.ccla.com.cn/server/upload/file/2017/06/19/1497857521367070105.mp4"]
             * imageUrls : null
             * createDate : 2017-06-19
             * lastMofityDate : null
             * lastReplyDate : null
             * viewCount : 0
             * storeCount : 0
             * replyCount : 0
             * upCount : 0
             * downCount : 0
             * status : null
             * isValid : true
             * isClosed : false
             * replies : null
             *//*


            *//*类型*//*
            public static final int TYPE_1 = 1;
            public static final int TYPE_2 = 2;
            public static final int TYPE_3 = 3;
            public static final int TYPE_4 = 4;
            public static final int TYPE_5 = 5;

            private int itemType;

            public void setItemType(int itemType) {
                this.itemType = itemType;
            }

            private int forumId;
            private int userId;
            private String forumType;
            private String title;
            private String txt;
            private List<String> imageUrls;
            private String createDate;
            private Object lastMofityDate;
            private Object lastReplyDate;
            private int viewCount;
            private int storeCount;
            private int replyCount;
            private int upCount;
            private int downCount;
            private Object status;
            private boolean isValid;
            private boolean isClosed;
            private Object replies;
            private List<String> videoUrls;

            public int getForumId() {
                return forumId;
            }

            public void setForumId(int forumId) {
                this.forumId = forumId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getForumType() {
                return forumType;
            }

            public void setForumType(String forumType) {
                this.forumType = forumType;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }

            public List<String> getImageUrls() {
                return imageUrls;
            }

            public void setImageUrls(List<String> imageUrls) {
                this.imageUrls = imageUrls;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public Object getLastMofityDate() {
                return lastMofityDate;
            }

            public void setLastMofityDate(Object lastMofityDate) {
                this.lastMofityDate = lastMofityDate;
            }

            public Object getLastReplyDate() {
                return lastReplyDate;
            }

            public void setLastReplyDate(Object lastReplyDate) {
                this.lastReplyDate = lastReplyDate;
            }

            public int getViewCount() {
                return viewCount;
            }

            public void setViewCount(int viewCount) {
                this.viewCount = viewCount;
            }

            public int getStoreCount() {
                return storeCount;
            }

            public void setStoreCount(int storeCount) {
                this.storeCount = storeCount;
            }

            public int getReplyCount() {
                return replyCount;
            }

            public void setReplyCount(int replyCount) {
                this.replyCount = replyCount;
            }

            public int getUpCount() {
                return upCount;
            }

            public void setUpCount(int upCount) {
                this.upCount = upCount;
            }

            public int getDownCount() {
                return downCount;
            }

            public void setDownCount(int downCount) {
                this.downCount = downCount;
            }

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }

            public boolean isIsValid() {
                return isValid;
            }

            public void setIsValid(boolean isValid) {
                this.isValid = isValid;
            }

            public boolean isIsClosed() {
                return isClosed;
            }

            public void setIsClosed(boolean isClosed) {
                this.isClosed = isClosed;
            }

            public Object getReplies() {
                return replies;
            }

            public void setReplies(Object replies) {
                this.replies = replies;
            }

            public List<String> getVideoUrls() {
                return videoUrls;
            }

            public void setVideoUrls(List<String> videoUrls) {
                this.videoUrls = videoUrls;
            }

            //类型
            @Override
            public int getItemType() {
                return itemType;
            }


        }
    }*/


}
