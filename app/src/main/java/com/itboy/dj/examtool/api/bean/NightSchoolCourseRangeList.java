package com.itboy.dj.examtool.api.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */

public class NightSchoolCourseRangeList {

    /**
     * result : ok
     * error : {"errorCode":0,"errorText":"success"}
     * data : [{"courseId":2,"priority":1,"title":"课程1","teacherId":null,"address":null,"startDate":"2017-07-19","endDate":"2017-07-19","txt":null,"createDate":"2017-06-12","sign":null},{"courseId":2,"priority":2,"title":"课程2","teacherId":null,"address":null,"startDate":"2017-07-20","endDate":"2017-07-20","txt":null,"createDate":"2017-06-12","sign":null},{"courseId":2,"priority":3,"title":"课程3","teacherId":null,"address":null,"startDate":"2017-07-21","endDate":"2017-07-21","txt":null,"createDate":"2017-06-12","sign":null}]
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
         * courseId : 2
         * priority : 1
         * title : 课程1
         * teacherId : null
         * address : null
         * startDate : 2017-07-19
         * endDate : 2017-07-19
         * txt : null
         * createDate : 2017-06-12
         * sign : null
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
        private Object sign;

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

        public Object getSign() {
            return sign;
        }

        public void setSign(Object sign) {
            this.sign = sign;
        }
    }
}
