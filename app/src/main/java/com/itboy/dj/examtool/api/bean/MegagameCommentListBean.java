package com.itboy.dj.examtool.api.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/25.
 */

public class MegagameCommentListBean {


    /**
     * result : ok
     * error : {"errorCode":0,"errorText":"success"}
     * data : [{"commentId":2,"dasaiId":154,"userId":7331,"status":"checked","isValid":true,"createDate":"2017-07-24","txt":"大赛评论测试2","userInfo":{"userId":7331,"nickName":"保卫萝卜","imgUrl":"http://img.kspx.ccla.com.cn/server/upload/image/2017/06/28/1498616597274087151.jpg","createDate":null}},{"commentId":3,"dasaiId":154,"userId":7331,"status":"checked","isValid":true,"createDate":"2017-07-24","txt":"大赛评论测试3","userInfo":{"userId":7331,"nickName":"保卫萝卜","imgUrl":"http://img.kspx.ccla.com.cn/server/upload/image/2017/06/28/1498616597274087151.jpg","createDate":null}},{"commentId":4,"dasaiId":154,"userId":7331,"status":"checked","isValid":true,"createDate":"2017-07-24","txt":"大赛评论测试4","userInfo":{"userId":7331,"nickName":"保卫萝卜","imgUrl":"http://img.kspx.ccla.com.cn/server/upload/image/2017/06/28/1498616597274087151.jpg","createDate":null}},{"commentId":5,"dasaiId":154,"userId":7331,"status":"checked","isValid":true,"createDate":"2017-07-24","txt":"大赛评论测试5","userInfo":{"userId":7331,"nickName":"保卫萝卜","imgUrl":"http://img.kspx.ccla.com.cn/server/upload/image/2017/06/28/1498616597274087151.jpg","createDate":null}},{"commentId":6,"dasaiId":154,"userId":7331,"status":"checked","isValid":true,"createDate":"2017-07-24","txt":"大赛评论测试6","userInfo":{"userId":7331,"nickName":"保卫萝卜","imgUrl":"http://img.kspx.ccla.com.cn/server/upload/image/2017/06/28/1498616597274087151.jpg","createDate":null}},{"commentId":1,"dasaiId":154,"userId":7331,"status":"checked","isValid":true,"createDate":"2017-07-24","txt":"大赛评论测试1","userInfo":{"userId":7331,"nickName":"保卫萝卜","imgUrl":"http://img.kspx.ccla.com.cn/server/upload/image/2017/06/28/1498616597274087151.jpg","createDate":null}}]
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
         * commentId : 2
         * dasaiId : 154
         * userId : 7331
         * status : checked
         * isValid : true
         * createDate : 2017-07-24
         * txt : 大赛评论测试2
         * userInfo : {"userId":7331,"nickName":"保卫萝卜","imgUrl":"http://img.kspx.ccla.com.cn/server/upload/image/2017/06/28/1498616597274087151.jpg","createDate":null}
         */

        private int commentId;
        private int dasaiId;
        private int userId;
        private String status;
        private boolean isValid;
        private String createDate;
        private String txt;
        private UserInfoBean userInfo;

        public int getCommentId() {
            return commentId;
        }

        public void setCommentId(int commentId) {
            this.commentId = commentId;
        }

        public int getDasaiId() {
            return dasaiId;
        }

        public void setDasaiId(int dasaiId) {
            this.dasaiId = dasaiId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public boolean isIsValid() {
            return isValid;
        }

        public void setIsValid(boolean isValid) {
            this.isValid = isValid;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getTxt() {
            return txt;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public static class UserInfoBean {
            /**
             * userId : 7331
             * nickName : 保卫萝卜
             * imgUrl : http://img.kspx.ccla.com.cn/server/upload/image/2017/06/28/1498616597274087151.jpg
             * createDate : null
             */

            private int userId;
            private String nickName;
            private String imgUrl;
            private Object createDate;

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public Object getCreateDate() {
                return createDate;
            }

            public void setCreateDate(Object createDate) {
                this.createDate = createDate;
            }
        }
    }
}
