package com.itboy.dj.examtool.api.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */

public class NightSchoolCourseDetailBean implements Serializable {

    /**
     * result : ok
     * error : {"errorCode":0,"errorText":"success"}
     * data : {"courseId":2,"courseMode":"线下听课","title":"民工夜校课程测试2","titleImgURL":"http://img.kspx.ccla.com.cn/server/upload/image/2017/06/12/1497247533171038296.png","teacherId":12,"address":"北京市大兴区英才培训学校","startDate":"2017-07-01","endDate":"2017-07-31","fy":0,"txt":"<p><img src=\"/server/upload/image/2017/06/12/1497238857597059346.jpg\" title=\"1497238857597059346.jpg\" alt=\"u203.jpg\"/><\/p>","topLevel":0,"createDate":"2017-06-12","isRecommend":false,"courseCatalogs":[{"courseId":2,"priority":1,"title":"课程1","teacherId":null,"address":null,"startDate":"2017-07-19","endDate":"2017-07-19","txt":null,"createDate":"2017-06-12"},{"courseId":2,"priority":2,"title":"课程2","teacherId":null,"address":null,"startDate":"2017-07-20","endDate":"2017-07-20","txt":null,"createDate":"2017-06-12"},{"courseId":2,"priority":3,"title":"课程3","teacherId":null,"address":null,"startDate":"2017-07-21","endDate":"2017-07-21","txt":null,"createDate":"2017-06-12"}],"register":null}
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

    public static class DataBean  implements Serializable{
        /**
         * courseId : 2
         * courseMode : 线下听课
         * title : 民工夜校课程测试2
         * titleImgURL : http://img.kspx.ccla.com.cn/server/upload/image/2017/06/12/1497247533171038296.png
         * teacherId : 12
         * address : 北京市大兴区英才培训学校
         * startDate : 2017-07-01
         * endDate : 2017-07-31
         * fy : 0.0
         * txt : <p><img src="/server/upload/image/2017/06/12/1497238857597059346.jpg" title="1497238857597059346.jpg" alt="u203.jpg"/></p>
         * topLevel : 0
         * createDate : 2017-06-12
         * isRecommend : false
         * courseCatalogs : [{"courseId":2,"priority":1,"title":"课程1","teacherId":null,"address":null,"startDate":"2017-07-19","endDate":"2017-07-19","txt":null,"createDate":"2017-06-12"},{"courseId":2,"priority":2,"title":"课程2","teacherId":null,"address":null,"startDate":"2017-07-20","endDate":"2017-07-20","txt":null,"createDate":"2017-06-12"},{"courseId":2,"priority":3,"title":"课程3","teacherId":null,"address":null,"startDate":"2017-07-21","endDate":"2017-07-21","txt":null,"createDate":"2017-06-12"}]
         * register : null
         */

        private int courseId;
        private String courseMode;
        private String title;
        private String titleImgURL;
        private int teacherId;
        private String address;
        private String startDate;
        private String endDate;
        private double fy;
        private String txt;
        private int topLevel;
        private String createDate;
        private boolean isRecommend;
        private Object register;
        private List<CourseCatalogsBean> courseCatalogs;

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

        public String getTitleImgURL() {
            return titleImgURL;
        }

        public void setTitleImgURL(String titleImgURL) {
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

        public Object getRegister() {
            return register;
        }

        public void setRegister(Object register) {
            this.register = register;
        }

        public List<CourseCatalogsBean> getCourseCatalogs() {
            return courseCatalogs;
        }

        public void setCourseCatalogs(List<CourseCatalogsBean> courseCatalogs) {
            this.courseCatalogs = courseCatalogs;
        }

        public static class CourseCatalogsBean  implements Serializable{
            /**
             * courseId : 2
             * priority : 1
             * title : 课程1
             * teacherId : null
             * address : null
             * startDate : 2017-07-19
             * endDate : 2017-07-19
             * txt : null
             * createDate : 2017-06-12
             */

            private int courseId;
            private int priority;
            private String title;
            private Object teacherId;
            private Object address;
            private String startDate;
            private String endDate;
            private Object txt;
            private String createDate;

            public int getCourseId() {
                return courseId;
            }

            public void setCourseId(int courseId) {
                this.courseId = courseId;
            }

            public int getPriority() {
                return priority;
            }

            public void setPriority(int priority) {
                this.priority = priority;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public Object getTeacherId() {
                return teacherId;
            }

            public void setTeacherId(Object teacherId) {
                this.teacherId = teacherId;
            }

            public Object getAddress() {
                return address;
            }

            public void setAddress(Object address) {
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
        }
    }
}
