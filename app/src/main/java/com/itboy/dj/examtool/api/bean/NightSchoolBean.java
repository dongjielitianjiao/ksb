package com.itboy.dj.examtool.api.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/12.
 */

public class NightSchoolBean {


    /**
     * result : ok
     * error : {"errorCode":0,"errorText":"success"}
     * data : {"total":2,"rows":[{"courseId":2,"courseMode":"线下听课","title":"民工夜校课程测试2","titleImgURL":null,"teacherId":12,"address":"北京市大兴区英才培训学校","startDate":"2017-07-01","endDate":"2017-07-31","fy":0,"txt":"<p><img src=\"/server/upload/image/2017/06/12/1497238857597059346.jpg\" title=\"1497238857597059346.jpg\" alt=\"u203.jpg\"/><\/p>","topLevel":0,"createDate":"2017-06-12","isRecommend":false,"courseCatalogs":null,"register":null},{"courseId":1,"courseMode":"线下听课","title":"民工夜校课程测试1","titleImgURL":null,"teacherId":11,"address":"北京市大兴区英才培训学校","startDate":"2017-07-01","endDate":"2017-07-31","fy":200,"txt":"<p><img src=\"/server/upload/image/2017/06/12/1497238607485037362.jpg\" title=\"1497238607485037362.jpg\" alt=\"u203.jpg\"/><\/p>","topLevel":0,"createDate":"2017-06-12","isRecommend":false,"courseCatalogs":null,"register":null}],"footer":[]}
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
         * rows : [{"courseId":2,"courseMode":"线下听课","title":"民工夜校课程测试2","titleImgURL":null,"teacherId":12,"address":"北京市大兴区英才培训学校","startDate":"2017-07-01","endDate":"2017-07-31","fy":0,"txt":"<p><img src=\"/server/upload/image/2017/06/12/1497238857597059346.jpg\" title=\"1497238857597059346.jpg\" alt=\"u203.jpg\"/><\/p>","topLevel":0,"createDate":"2017-06-12","isRecommend":false,"courseCatalogs":null,"register":null},{"courseId":1,"courseMode":"线下听课","title":"民工夜校课程测试1","titleImgURL":null,"teacherId":11,"address":"北京市大兴区英才培训学校","startDate":"2017-07-01","endDate":"2017-07-31","fy":200,"txt":"<p><img src=\"/server/upload/image/2017/06/12/1497238607485037362.jpg\" title=\"1497238607485037362.jpg\" alt=\"u203.jpg\"/><\/p>","topLevel":0,"createDate":"2017-06-12","isRecommend":false,"courseCatalogs":null,"register":null}]
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

        public static class RowsBean {
            /**
             * courseId : 2
             * courseMode : 线下听课
             * title : 民工夜校课程测试2
             * titleImgURL : null
             * teacherId : 12
             * address : 北京市大兴区英才培训学校
             * startDate : 2017-07-01
             * endDate : 2017-07-31
             * fy : 0.0
             * txt : <p><img src="/server/upload/image/2017/06/12/1497238857597059346.jpg" title="1497238857597059346.jpg" alt="u203.jpg"/></p>
             * topLevel : 0
             * createDate : 2017-06-12
             * isRecommend : false
             * courseCatalogs : null
             * register : null
             */

            private int courseId;
            private String courseMode;
            private String title;
            private Object titleImgURL;
            private int teacherId;
            private String address;
            private String startDate;
            private String endDate;
            private double fy;
            private String txt;
            private int topLevel;
            private String createDate;
            private boolean isRecommend;
            private Object courseCatalogs;
            private Object register;

            public int getCourseId() {
                return courseId;
            }

            public void setCourseId(int courseId) {
                this.courseId = courseId;
            }

            public String getCourseMode() {
                return courseMode;
            }

            public void setCourseMode(String courseMode) {
                this.courseMode = courseMode;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public Object getTitleImgURL() {
                return titleImgURL;
            }

            public void setTitleImgURL(Object titleImgURL) {
                this.titleImgURL = titleImgURL;
            }

            public int getTeacherId() {
                return teacherId;
            }

            public void setTeacherId(int teacherId) {
                this.teacherId = teacherId;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getStartDate() {
                return startDate;
            }

            public void setStartDate(String startDate) {
                this.startDate = startDate;
            }

            public String getEndDate() {
                return endDate;
            }

            public void setEndDate(String endDate) {
                this.endDate = endDate;
            }

            public double getFy() {
                return fy;
            }

            public void setFy(double fy) {
                this.fy = fy;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }

            public int getTopLevel() {
                return topLevel;
            }

            public void setTopLevel(int topLevel) {
                this.topLevel = topLevel;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public boolean isIsRecommend() {
                return isRecommend;
            }

            public void setIsRecommend(boolean isRecommend) {
                this.isRecommend = isRecommend;
            }

            public Object getCourseCatalogs() {
                return courseCatalogs;
            }

            public void setCourseCatalogs(Object courseCatalogs) {
                this.courseCatalogs = courseCatalogs;
            }

            public Object getRegister() {
                return register;
            }

            public void setRegister(Object register) {
                this.register = register;
            }
        }
    }
}
