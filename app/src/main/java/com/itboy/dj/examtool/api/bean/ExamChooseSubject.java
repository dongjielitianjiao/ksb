package com.itboy.dj.examtool.api.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/5/25.
 */

public class ExamChooseSubject  implements  Serializable{


    /**
     * result : ok
     * error : {"errorCode":0,"errorText":"success"}
     * data : [{"examId":78,"examName":"阿斯达斯 阿萨德","examModel":"线上","examTarget":"钢筋工中级工证书持有者","examStartTime":"2017-03-01","examEndTime":"2018-03-08","examRegisterStartTime":"2017-03-01","examRegisterEndTime":"2018-03-01","profession":"土木建筑类","work":"钢筋工","rank":"高级工","province":null,"organization":"中国建设劳动学会","createDate":"2017-03-08","totalCost":null,"trainId":51,"trainModel":null,"examDetails":null,"userExamId":null,"userId":null,"examType":null,"examDate":null,"score":null,"userCertificate":null,"examProgress":"待考试","userExamDetails":null,"passed":false,"issued":false,"train":true}]
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

    public static class DataBean implements Serializable {
        /**
         * examId : 78
         * examName : 阿斯达斯 阿萨德
         * examModel : 线上
         * examTarget : 钢筋工中级工证书持有者
         * examStartTime : 2017-03-01
         * examEndTime : 2018-03-08
         * examRegisterStartTime : 2017-03-01
         * examRegisterEndTime : 2018-03-01
         * profession : 土木建筑类
         * work : 钢筋工
         * rank : 高级工
         * province : null
         * organization : 中国建设劳动学会
         * createDate : 2017-03-08
         * totalCost : null
         * trainId : 51
         * trainModel : null
         * examDetails : null
         * userExamId : null
         * userId : null
         * examType : null
         * examDate : null
         * score : null
         * userCertificate : null
         * examProgress : 待考试
         * userExamDetails : null
         * passed : false
         * issued : false
         * train : true
         */

        private int examId;
        private String examName;
        private String examModel;
        private String examTarget;
        private String examStartTime;
        private String examEndTime;
        private String examRegisterStartTime;
        private String examRegisterEndTime;
        private String profession;
        private String work;
        private String rank;
        private Object province;
        private String organization;
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
        private String examProgress;
        private Object userExamDetails;
        private boolean passed;
        private boolean issued;
        private boolean train;

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

        public String getExamTarget() {
            return examTarget;
        }

        public void setExamTarget(String examTarget) {
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

        public String getProfession() {
            return profession;
        }

        public void setProfession(String profession) {
            this.profession = profession;
        }

        public String getWork() {
            return work;
        }

        public void setWork(String work) {
            this.work = work;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public Object getProvince() {
            return province;
        }

        public void setProvince(Object province) {
            this.province = province;
        }

        public String getOrganization() {
            return organization;
        }

        public void setOrganization(String organization) {
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

        public String getExamProgress() {
            return examProgress;
        }

        public void setExamProgress(String examProgress) {
            this.examProgress = examProgress;
        }

        public Object getUserExamDetails() {
            return userExamDetails;
        }

        public void setUserExamDetails(Object userExamDetails) {
            this.userExamDetails = userExamDetails;
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
    }
}
