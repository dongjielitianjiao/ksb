package com.itboy.dj.examtool.api.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/5/22.
 */

public class KaoPeiDetail implements Serializable {


    /**
     * result : ok
     * error : {"errorCode":0,"errorText":"success"}
     * data : [{"examId":78,"examName":"阿斯达斯 阿萨德","examModel":"online","examTarget":"钢筋工中级工证书持有者","examStartTime":"2017-03-01","examEndTime":"2018-03-08","examRegisterStartTime":"2017-03-01","examRegisterEndTime":"2018-03-01","profession":"土木建筑类","work":"钢筋工","rank":"高级工","province":null,"organization":"中国建设劳动学会","createDate":"2017-03-08","totalCost":2500,"trainId":51,"trainModel":"online","examDetails":[{"examId":78,"examDetailPriority":1,"examModel":"online","examCourseName":"理论考试","examStartTime":"2017-03-01","examEndTime":"2018-03-01","totalTime":120,"totalScore":100,"passScore":60,"examAddress":null,"examPaper":null},{"examId":78,"examDetailPriority":2,"examModel":"offline","examCourseName":"实操技能","examStartTime":"2017-03-01","examEndTime":"2018-03-08","totalTime":120,"totalScore":100,"passScore":60,"examAddress":null,"examPaper":null},{"examId":78,"examDetailPriority":3,"examModel":"online","examCourseName":"安全培训","examStartTime":"2017-03-01","examEndTime":"2018-03-01","totalTime":120,"totalScore":100,"passScore":60,"examAddress":null,"examPaper":null}],"train":true},null]
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
         * examModel : online
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
         * totalCost : 2500
         * trainId : 51
         * trainModel : online
         * examDetails : [{"examId":78,"examDetailPriority":1,"examModel":"online","examCourseName":"理论考试","examStartTime":"2017-03-01","examEndTime":"2018-03-01","totalTime":120,"totalScore":100,"passScore":60,"examAddress":null,"examPaper":null},{"examId":78,"examDetailPriority":2,"examModel":"offline","examCourseName":"实操技能","examStartTime":"2017-03-01","examEndTime":"2018-03-08","totalTime":120,"totalScore":100,"passScore":60,"examAddress":null,"examPaper":null},{"examId":78,"examDetailPriority":3,"examModel":"online","examCourseName":"安全培训","examStartTime":"2017-03-01","examEndTime":"2018-03-01","totalTime":120,"totalScore":100,"passScore":60,"examAddress":null,"examPaper":null}]
         * train : true
         */
        public boolean checked;
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
        private int totalCost;
        private int trainId;
        private String trainModel;
        private boolean train;
        private List<ExamDetailsBean> examDetails;

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

        public int getTotalCost() {
            return totalCost;
        }

        public void setTotalCost(int totalCost) {
            this.totalCost = totalCost;
        }

        public int getTrainId() {
            return trainId;
        }

        public void setTrainId(int trainId) {
            this.trainId = trainId;
        }

        public String getTrainModel() {
            return trainModel;
        }

        public void setTrainModel(String trainModel) {
            this.trainModel = trainModel;
        }

        public boolean isTrain() {
            return train;
        }

        public void setTrain(boolean train) {
            this.train = train;
        }

        public List<ExamDetailsBean> getExamDetails() {
            return examDetails;
        }

        public void setExamDetails(List<ExamDetailsBean> examDetails) {
            this.examDetails = examDetails;
        }

        public static class ExamDetailsBean implements Serializable{
            /**
             * examId : 78
             * examDetailPriority : 1
             * examModel : online
             * examCourseName : 理论考试
             * examStartTime : 2017-03-01
             * examEndTime : 2018-03-01
             * totalTime : 120
             * totalScore : 100
             * passScore : 60
             * examAddress : null
             * examPaper : null
             */

            private int examId;
            private int examDetailPriority;
            private String examModel;
            private String examCourseName;
            private String examStartTime;
            private String examEndTime;
            private int totalTime;
            private int totalScore;
            private int passScore;
            private Object examAddress;
            private Object examPaper;

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

            @Override
            public String toString() {
                return "ExamDetailsBean{" +
                        "examId=" + examId +
                        ", examDetailPriority=" + examDetailPriority +
                        ", examModel='" + examModel + '\'' +
                        ", examCourseName='" + examCourseName + '\'' +
                        ", examStartTime='" + examStartTime + '\'' +
                        ", examEndTime='" + examEndTime + '\'' +
                        ", totalTime=" + totalTime +
                        ", totalScore=" + totalScore +
                        ", passScore=" + passScore +
                        ", examAddress=" + examAddress +
                        ", examPaper=" + examPaper +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "checked=" + checked +
                    ", examId=" + examId +
                    ", examName='" + examName + '\'' +
                    ", examModel='" + examModel + '\'' +
                    ", examTarget='" + examTarget + '\'' +
                    ", examStartTime='" + examStartTime + '\'' +
                    ", examEndTime='" + examEndTime + '\'' +
                    ", examRegisterStartTime='" + examRegisterStartTime + '\'' +
                    ", examRegisterEndTime='" + examRegisterEndTime + '\'' +
                    ", profession='" + profession + '\'' +
                    ", work='" + work + '\'' +
                    ", rank='" + rank + '\'' +
                    ", province=" + province +
                    ", organization='" + organization + '\'' +
                    ", createDate='" + createDate + '\'' +
                    ", totalCost=" + totalCost +
                    ", trainId=" + trainId +
                    ", trainModel='" + trainModel + '\'' +
                    ", train=" + train +
                    ", examDetails=" + examDetails +
                    '}';
        }
    }
}
