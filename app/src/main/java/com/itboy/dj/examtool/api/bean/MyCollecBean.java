package com.itboy.dj.examtool.api.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */

public class MyCollecBean {


    /**
     * result : ok
     * error : {"errorCode":0,"errorText":"success"}
     * data : {"total":2,"rows":[{"storeId":20,"userId":5134,"catalogId":null,"storeSource":"ext_weiquan_forum","relationId":30,"createDate":"2017-07-28","remarks":null,"relationObj":{"forumId":30,"userId":7374,"forumType":"土木建筑","title":null,"txt":null,"videoUrls":[],"imageUrls":["http://img.kspx.ccla.com.cn/server/upload/image/2017/06/19/1497869803698078949.png"],"createDate":"2017-06-19","lastMofityDate":null,"lastReplyDate":"2017-08-03","viewCount":11,"storeCount":1,"replyCount":3,"upCount":0,"downCount":0,"status":"checked","isValid":true,"isClosed":false,"replies":null}},{"storeId":19,"userId":5134,"catalogId":null,"storeSource":"ext_weiquan_forum","relationId":29,"createDate":"2017-07-28","remarks":null,"relationObj":{"forumId":29,"userId":7374,"forumType":"土木建筑","title":null,"txt":null,"videoUrls":[],"imageUrls":[],"createDate":"2017-06-19","lastMofityDate":null,"lastReplyDate":null,"viewCount":5,"storeCount":1,"replyCount":0,"upCount":0,"downCount":0,"status":"checked","isValid":true,"isClosed":false,"replies":null}}],"footer":[]}
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
         * total : 2
         * rows : [{"storeId":20,"userId":5134,"catalogId":null,"storeSource":"ext_weiquan_forum","relationId":30,"createDate":"2017-07-28","remarks":null,"relationObj":{"forumId":30,"userId":7374,"forumType":"土木建筑","title":null,"txt":null,"videoUrls":[],"imageUrls":["http://img.kspx.ccla.com.cn/server/upload/image/2017/06/19/1497869803698078949.png"],"createDate":"2017-06-19","lastMofityDate":null,"lastReplyDate":"2017-08-03","viewCount":11,"storeCount":1,"replyCount":3,"upCount":0,"downCount":0,"status":"checked","isValid":true,"isClosed":false,"replies":null}},{"storeId":19,"userId":5134,"catalogId":null,"storeSource":"ext_weiquan_forum","relationId":29,"createDate":"2017-07-28","remarks":null,"relationObj":{"forumId":29,"userId":7374,"forumType":"土木建筑","title":null,"txt":null,"videoUrls":[],"imageUrls":[],"createDate":"2017-06-19","lastMofityDate":null,"lastReplyDate":null,"viewCount":5,"storeCount":1,"replyCount":0,"upCount":0,"downCount":0,"status":"checked","isValid":true,"isClosed":false,"replies":null}}]
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

        public static class RowsBean   implements MultiItemEntity{
            /**
             * storeId : 20
             * userId : 5134
             * catalogId : null
             * storeSource : ext_weiquan_forum
             * relationId : 30
             * createDate : 2017-07-28
             * remarks : null
             * relationObj : {"forumId":30,"userId":7374,"forumType":"土木建筑","title":null,"txt":null,"videoUrls":[],"imageUrls":["http://img.kspx.ccla.com.cn/server/upload/image/2017/06/19/1497869803698078949.png"],"createDate":"2017-06-19","lastMofityDate":null,"lastReplyDate":"2017-08-03","viewCount":11,"storeCount":1,"replyCount":3,"upCount":0,"downCount":0,"status":"checked","isValid":true,"isClosed":false,"replies":null}
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

            private int storeId;
            private int userId;
            private Object catalogId;
            private String storeSource;
            private int relationId;
            private String createDate;
            private Object remarks;
            private RelationObjBean relationObj;


            public int getStoreId() {
                return storeId;
            }

            public void setStoreId(int storeId) {
                this.storeId = storeId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public Object getCatalogId() {
                return catalogId;
            }

            public void setCatalogId(Object catalogId) {
                this.catalogId = catalogId;
            }

            public String getStoreSource() {
                return storeSource;
            }

            public void setStoreSource(String storeSource) {
                this.storeSource = storeSource;
            }

            public int getRelationId() {
                return relationId;
            }

            public void setRelationId(int relationId) {
                this.relationId = relationId;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public Object getRemarks() {
                return remarks;
            }

            public void setRemarks(Object remarks) {
                this.remarks = remarks;
            }

            public RelationObjBean getRelationObj() {
                return relationObj;
            }

            public void setRelationObj(RelationObjBean relationObj) {
                this.relationObj = relationObj;
            }

            @Override
            public int getItemType() {
                return itemType;
            }

            public static class RelationObjBean{
                /**
                 * forumId : 30
                 * userId : 7374
                 * forumType : 土木建筑
                 * title : null
                 * txt : null
                 * videoUrls : []
                 * imageUrls : ["http://img.kspx.ccla.com.cn/server/upload/image/2017/06/19/1497869803698078949.png"]
                 * createDate : 2017-06-19
                 * lastMofityDate : null
                 * lastReplyDate : 2017-08-03
                 * viewCount : 11
                 * storeCount : 1
                 * replyCount : 3
                 * upCount : 0
                 * downCount : 0
                 * status : checked
                 * isValid : true
                 * isClosed : false
                 * replies : null
                 */

                private int forumId;
                private int userId;
                private String forumType;
                private Object title;
                private Object txt;
                private String createDate;
                private Object lastMofityDate;
                private String lastReplyDate;
                private int viewCount;
                private int storeCount;
                private int replyCount;
                private int upCount;
                private int downCount;
                private String status;
                private boolean isValid;
                private boolean isClosed;
                private Object replies;
                private List<?> videoUrls;
                private List<String> imageUrls;




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

                public Object getTitle() {
                    return title;
                }

                public void setTitle(Object title) {
                    this.title = title;
                }

                public Object getTxt() {
                    return txt;
                }

                public void setTxt(Object txt) {
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

                public List<String> getImageUrls() {
                    return imageUrls;
                }

                public void setImageUrls(List<String> imageUrls) {
                    this.imageUrls = imageUrls;
                }


            }
        }
    }
}
