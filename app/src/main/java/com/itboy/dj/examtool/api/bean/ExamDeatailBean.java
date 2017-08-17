package com.itboy.dj.examtool.api.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/2.
 */

public class ExamDeatailBean {


    /**
     * result : ok
     * error : {"errorCode":0,"errorText":"success"}
     * data : {"examId":80,"examName":"土木建筑钢筋工高级考试1","examModel":"线上","examTarget":null,"examStartTime":"2017-05-01","examEndTime":"2021-05-01","examRegisterStartTime":"2017-05-01","examRegisterEndTime":"2021-05-01","profession":null,"work":null,"rank":null,"province":null,"organization":null,"createDate":"2017-05-25","totalCost":null,"trainId":72,"trainModel":null,"examDetails":null,"userExamId":null,"userId":null,"examType":null,"examDate":null,"score":null,"userCertificate":null,"examProgress":null,"userExamDetails":[{"examId":80,"examDetailPriority":1,"examModel":"线上","examCourseName":"理论考试","examStartTime":null,"examEndTime":null,"totalTime":120,"totalScore":100,"passScore":60,"examAddress":null,"examPaper":null,"examCourseId":1,"priority":null,"score":null,"examDate":null,"passed":false},{"examId":80,"examDetailPriority":2,"examModel":"线上","examCourseName":"实操技能","examStartTime":null,"examEndTime":null,"totalTime":120,"totalScore":100,"passScore":60,"examAddress":null,"examPaper":null,"examCourseId":2,"priority":null,"score":null,"examDate":null,"passed":false},{"examId":80,"examDetailPriority":3,"examModel":"线上","examCourseName":"安全培训","examStartTime":null,"examEndTime":null,"totalTime":120,"totalScore":100,"passScore":60,"examAddress":null,"examPaper":null,"examCourseId":3,"priority":null,"score":null,"examDate":null,"passed":false}],"passed":false,"issued":false,"train":true}
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
         * examId : 80
         * examName : 土木建筑钢筋工高级考试1
         * examModel : 线上
         * examTarget : null
         * examStartTime : 2017-05-01
         * examEndTime : 2021-05-01
         * examRegisterStartTime : 2017-05-01
         * examRegisterEndTime : 2021-05-01
         * profession : null
         * work : null
         * rank : null
         * province : null
         * organization : null
         * createDate : 2017-05-25
         * totalCost : null
         * trainId : 72
         * trainModel : null
         * examDetails : null
         * userExamId : null
         * userId : null
         * examType : null
         * examDate : null
         * score : null
         * userCertificate : null
         * examProgress : null
         * userExamDetails : [{"examId":80,"examDetailPriority":1,"examModel":"线上","examCourseName":"理论考试","examStartTime":null,"examEndTime":null,"totalTime":120,"totalScore":100,"passScore":60,"examAddress":null,"examPaper":null,"examCourseId":1,"priority":null,"score":null,"examDate":null,"passed":false},{"examId":80,"examDetailPriority":2,"examModel":"线上","examCourseName":"实操技能","examStartTime":null,"examEndTime":null,"totalTime":120,"totalScore":100,"passScore":60,"examAddress":null,"examPaper":null,"examCourseId":2,"priority":null,"score":null,"examDate":null,"passed":false},{"examId":80,"examDetailPriority":3,"examModel":"线上","examCourseName":"安全培训","examStartTime":null,"examEndTime":null,"totalTime":120,"totalScore":100,"passScore":60,"examAddress":null,"examPaper":null,"examCourseId":3,"priority":null,"score":null,"examDate":null,"passed":false}]
         * passed : false
         * issued : false
         * train : true
         */

        private int examId;
        private String examName;
        private String examModel;
        private Object examTarget;
        private String examStartTime;
        private String examEndTime;
        private String examRegisterStartTime;
        private String examRegisterEndTime;
        private Object profession;
        private Object work;
        private Object rank;
        private Object province;
        private Object organization;
        private String createDate;
        private Object totalCost;
        private int trainId;
        private Object trainModel;
        private Object examDetails;
        private Object userExamId;
        private Object userId;
        private Object examType;
        private Object examDate;
        private Object score;
        private Object userCertificate;
        private Object examProgress;
        private boolean passed;
        private boolean issued;
        private boolean train;
        private List<UserExamDetailsBean> userExamDetails;

        public int getExamId() {
            return examId;
        }

        public void setExamId(int examId) {
            this.examId = examId;
        }

        public String getExamName() {
            return examName;
        }

        public void setExamName(String examName) {
            this.examName = examName;
        }

        public String getExamModel() {
            return examModel;
        }

        public void setExamModel(String examModel) {
            this.examModel = examModel;
        }

        public Object getExamTarget() {
            return examTarget;
        }

        public void setExamTarget(Object examTarget) {
            this.examTarget = examTarget;
        }

        public String getExamStartTime() {
            return examStartTime;
        }

        public void setExamStartTime(String examStartTime) {
            this.examStartTime = examStartTime;
        }

        public String getExamEndTime() {
            return examEndTime;
        }

        public void setExamEndTime(String examEndTime) {
            this.examEndTime = examEndTime;
        }

        public String getExamRegisterStartTime() {
            return examRegisterStartTime;
        }

        public void setExamRegisterStartTime(String examRegisterStartTime) {
            this.examRegisterStartTime = examRegisterStartTime;
        }

        public String getExamRegisterEndTime() {
            return examRegisterEndTime;
        }

        public void setExamRegisterEndTime(String examRegisterEndTime) {
            this.examRegisterEndTime = examRegisterEndTime;
        }

        public Object getProfession() {
            return profession;
        }

        public void setProfession(Object profession) {
            this.profession = profession;
        }

        public Object getWork() {
            return work;
        }

        public void setWork(Object work) {
            this.work = work;
        }

        public Object getRank() {
            return rank;
        }

        public void setRank(Object rank) {
            this.rank = rank;
        }

        public Object getProvince() {
            return province;
        }

        public void setProvince(Object province) {
            this.province = province;
        }

        public Object getOrganization() {
            return organization;
        }

        public void setOrganization(Object organization) {
            this.organization = organization;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public Object getTotalCost() {
            return totalCost;
        }

        public void setTotalCost(Object totalCost) {
            this.totalCost = totalCost;
        }

        public int getTrainId() {
            return trainId;
        }

        public void setTrainId(int trainId) {
            this.trainId = trainId;
        }

        public Object getTrainModel() {
            return trainModel;
        }

        public void setTrainModel(Object trainModel) {
            this.trainModel = trainModel;
        }

        public Object getExamDetails() {
            return examDetails;
        }

        public void setExamDetails(Object examDetails) {
            this.examDetails = examDetails;
        }

        public Object getUserExamId() {
            return userExamId;
        }

        public void setUserExamId(Object userExamId) {
            this.userExamId = userExamId;
        }

        public Object getUserId() {
            return userId;
        }

        public void setUserId(Object userId) {
            this.userId = userId;
        }

        public Object getExamType() {
            return examType;
        }

        public void setExamType(Object examType) {
            this.examType = examType;
        }

        public Object getExamDate() {
            return examDate;
        }

        public void setExamDate(Object examDate) {
            this.examDate = examDate;
        }

        public Object getScore() {
            return score;
        }

        public void setScore(Object score) {
            this.score = score;
        }

        public Object getUserCertificate() {
            return userCertificate;
        }

        public void setUserCertificate(Object userCertificate) {
            this.userCertificate = userCertificate;
        }

        public Object getExamProgress() {
            return examProgress;
        }

        public void setExamProgress(Object examProgress) {
            this.examProgress = examProgress;
        }

        public boolean isPassed() {
            return passed;
        }

        public void setPassed(boolean passed) {
            this.passed = passed;
        }

        public boolean isIssued() {
            return issued;
        }

        public void setIssued(boolean issued) {
            this.issued = issued;
        }

        public boolean isTrain() {
            return train;
        }

        public void setTrain(boolean train) {
            this.train = train;
        }

        public List<UserExamDetailsBean> getUserExamDetails() {
            return userExamDetails;
        }

        public void setUserExamDetails(List<UserExamDetailsBean> userExamDetails) {
            this.userExamDetails = userExamDetails;
        }

        public static class UserExamDetailsBean {
            /**
             * examId : 80
             * examDetailPriority : 1
             * examModel : 线上
             * examCourseName : 理论考试
             * examStartTime : null
             * examEndTime : null
             * totalTime : 120
             * totalScore : 100
             * passScore : 60
             * examAddress : null
             * examPaper : null
             * examCourseId : 1
             * priority : null
             * score : null
             * examDate : null
             * passed : false
             */

            private int examId;
            private int examDetailPriority;
            private String examModel;
            private String examCourseName;
            private Object examStartTime;
            private Object examEndTime;
            private int totalTime;
            private int totalScore;
            private int passScore;
            private Object examAddress;
            private Object examPaper;
            private int examCourseId;
            private Object priority;
            private Object score;
            private Object examDate;
            private boolean passed;

            public int getExamId() {
                return examId;
            }

            public void setExamId(int examId) {
                this.examId = examId;
            }

            public int getExamDetailPriority() {
                return examDetailPriority;
            }

            public void setExamDetailPriority(int examDetailPriority) {
                this.examDetailPriority = examDetailPriority;
            }

            public String getExamModel() {
                return examModel;
            }

            public void setExamModel(String examModel) {
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

            public int getTotalTime() {
                return totalTime;
            }

            public void setTotalTime(int totalTime) {
                this.totalTime = totalTime;
            }

            public int getTotalScore() {
                return totalScore;
            }

            public void setTotalScore(int totalScore) {
                this.totalScore = totalScore;
            }

            public int getPassScore() {
                return passScore;
            }

            public void setPassScore(int passScore) {
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

            public Object getPriority() {
                return priority;
            }

            public void setPriority(Object priority) {
                this.priority = priority;
            }

            public Object getScore() {
                return score;
            }

            public void setScore(Object score) {
                this.score = score;
            }

            public Object getExamDate() {
                return examDate;
            }

            public void setExamDate(Object examDate) {
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
}
