package com.itboy.dj.examtool.modules.ftpage.exam;

/**
 * Created by Administrator on 2017/7/21.
 */
//考试结果
public class ExamResult {


    /**
     * result : ok
     * error : {"errorCode":0,"errorText":"success"}
     * data : {"examId":87,"examDetailPriority":null,"examModel":null,"examCourseName":"理论考试","examStartTime":null,"examEndTime":null,"totalTime":null,"totalScore":null,"passScore":null,"examAddress":null,"examPaper":null,"examCourseId":1,"priority":1,"score":25,"examDate":"2017-07-21","passed":false}
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
         * examId : 87
         * examDetailPriority : null
         * examModel : null
         * examCourseName : 理论考试
         * examStartTime : null
         * examEndTime : null
         * totalTime : null
         * totalScore : null
         * passScore : null
         * examAddress : null
         * examPaper : null
         * examCourseId : 1
         * priority : 1
         * score : 25.0
         * examDate : 2017-07-21
         * passed : false
         */

        private int examId;
        private Object examDetailPriority;
        private Object examModel;
        private String examCourseName;
        private Object examStartTime;
        private Object examEndTime;
        private Object totalTime;
        private Object totalScore;
        private Object passScore;
        private Object examAddress;
        private Object examPaper;
        private int examCourseId;
        private int priority;
        private double score;
        private String examDate;
        private boolean passed;

        public int getExamId() {
            return examId;
        }

        public void setExamId(int examId) {
            this.examId = examId;
        }

        public Object getExamDetailPriority() {
            return examDetailPriority;
        }

        public void setExamDetailPriority(Object examDetailPriority) {
            this.examDetailPriority = examDetailPriority;
        }

        public Object getExamModel() {
            return examModel;
        }

        public void setExamModel(Object examModel) {
            this.examModel = examModel;
        }

        public String getExamCourseName() {
            return examCourseName;
        }

        public void setExamCourseName(String examCourseName) {
            this.examCourseName = examCourseName;
        }

        public Object getExamStartTime() {
            return examStartTime;
        }

        public void setExamStartTime(Object examStartTime) {
            this.examStartTime = examStartTime;
        }

        public Object getExamEndTime() {
            return examEndTime;
        }

        public void setExamEndTime(Object examEndTime) {
            this.examEndTime = examEndTime;
        }

        public Object getTotalTime() {
            return totalTime;
        }

        public void setTotalTime(Object totalTime) {
            this.totalTime = totalTime;
        }

        public Object getTotalScore() {
            return totalScore;
        }

        public void setTotalScore(Object totalScore) {
            this.totalScore = totalScore;
        }

        public Object getPassScore() {
            return passScore;
        }

        public void setPassScore(Object passScore) {
            this.passScore = passScore;
        }

        public Object getExamAddress() {
            return examAddress;
        }

        public void setExamAddress(Object examAddress) {
            this.examAddress = examAddress;
        }

        public Object getExamPaper() {
            return examPaper;
        }

        public void setExamPaper(Object examPaper) {
            this.examPaper = examPaper;
        }

        public int getExamCourseId() {
            return examCourseId;
        }

        public void setExamCourseId(int examCourseId) {
            this.examCourseId = examCourseId;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getExamDate() {
            return examDate;
        }

        public void setExamDate(String examDate) {
            this.examDate = examDate;
        }

        public boolean isPassed() {
            return passed;
        }

        public void setPassed(boolean passed) {
            this.passed = passed;
        }
    }
}
