package com.itboy.dj.examtool.api.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/2.
 */

public class CourseListBean {

    /**
     * result : ok
     * error : {"errorCode":0,"errorText":"success"}
     * data : {"userCourseId":69,"userId":7331,"courseId":66,"examId":83,"courseStudyStatus":"待学习","courseName":"土木建筑钢筋工高级考试4","examName":"土木建筑钢筋工高级考试4","coursePicture":null,"viewCount":null,"studyChapterName":null,"doneChapterRate":null,"studyChapterId":null,"courseText":null,"teacher":null,"chapters":[{"chapterId":133,"priority":4,"chapterName":"钢筋工技能培训视频教程第4节","chapterDescription":null,"playNumber":null,"satisfactionDegree":null,"isFree":false,"hasQuestion":false,"score":0,"resourceURL":"http://img.kspx.ccla.com.cn/server/upload/file/2016/05/18/1463564174500038598.flv","studyVideoTime":null,"isPassed":false},{"chapterId":132,"priority":3,"chapterName":"钢筋工技能培训视频课程第3节","chapterDescription":null,"playNumber":null,"satisfactionDegree":null,"isFree":false,"hasQuestion":false,"score":0,"resourceURL":"http://img.kspx.ccla.com.cn/server/upload/file/2016/05/18/1463564246687063504.flv","studyVideoTime":null,"isPassed":false},{"chapterId":131,"priority":2,"chapterName":"钢筋工技能培训视频课程第2节","chapterDescription":null,"playNumber":null,"satisfactionDegree":null,"isFree":false,"hasQuestion":false,"score":0,"resourceURL":"http://img.kspx.ccla.com.cn/server/upload/file/2016/05/18/1463564271640065348.flv","studyVideoTime":null,"isPassed":false},{"chapterId":130,"priority":1,"chapterName":"钢筋工技能培训视频教程第1节","chapterDescription":null,"playNumber":null,"satisfactionDegree":null,"isFree":false,"hasQuestion":false,"score":0,"resourceURL":"http://img.kspx.ccla.com.cn/server/upload/file/2016/05/18/1463564213359044761.flv","studyVideoTime":null,"isPassed":false}]}
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
         * userCourseId : 69
         * userId : 7331
         * courseId : 66
         * examId : 83
         * courseStudyStatus : 待学习
         * courseName : 土木建筑钢筋工高级考试4
         * examName : 土木建筑钢筋工高级考试4
         * coursePicture : null
         * viewCount : null
         * studyChapterName : null
         * doneChapterRate : null
         * studyChapterId : null
         * courseText : null
         * teacher : null
         * chapters : [{"chapterId":133,"priority":4,"chapterName":"钢筋工技能培训视频教程第4节","chapterDescription":null,"playNumber":null,"satisfactionDegree":null,"isFree":false,"hasQuestion":false,"score":0,"resourceURL":"http://img.kspx.ccla.com.cn/server/upload/file/2016/05/18/1463564174500038598.flv","studyVideoTime":null,"isPassed":false},{"chapterId":132,"priority":3,"chapterName":"钢筋工技能培训视频课程第3节","chapterDescription":null,"playNumber":null,"satisfactionDegree":null,"isFree":false,"hasQuestion":false,"score":0,"resourceURL":"http://img.kspx.ccla.com.cn/server/upload/file/2016/05/18/1463564246687063504.flv","studyVideoTime":null,"isPassed":false},{"chapterId":131,"priority":2,"chapterName":"钢筋工技能培训视频课程第2节","chapterDescription":null,"playNumber":null,"satisfactionDegree":null,"isFree":false,"hasQuestion":false,"score":0,"resourceURL":"http://img.kspx.ccla.com.cn/server/upload/file/2016/05/18/1463564271640065348.flv","studyVideoTime":null,"isPassed":false},{"chapterId":130,"priority":1,"chapterName":"钢筋工技能培训视频教程第1节","chapterDescription":null,"playNumber":null,"satisfactionDegree":null,"isFree":false,"hasQuestion":false,"score":0,"resourceURL":"http://img.kspx.ccla.com.cn/server/upload/file/2016/05/18/1463564213359044761.flv","studyVideoTime":null,"isPassed":false}]
         */

        private int userCourseId;
        private int userId;
        private int courseId;
        private int examId;
        private String courseStudyStatus;
        private String courseName;
        private String examName;
        private Object coursePicture;
        private Object viewCount;
        private Object studyChapterName;
        private Object doneChapterRate;
        private Object studyChapterId;
        private Object courseText;
        private Object teacher;
        private List<ChaptersBean> chapters;

        public int getUserCourseId() {
            return userCourseId;
        }

        public void setUserCourseId(int userCourseId) {
            this.userCourseId = userCourseId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public int getExamId() {
            return examId;
        }

        public void setExamId(int examId) {
            this.examId = examId;
        }

        public String getCourseStudyStatus() {
            return courseStudyStatus;
        }

        public void setCourseStudyStatus(String courseStudyStatus) {
            this.courseStudyStatus = courseStudyStatus;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getExamName() {
            return examName;
        }

        public void setExamName(String examName) {
            this.examName = examName;
        }

        public Object getCoursePicture() {
            return coursePicture;
        }

        public void setCoursePicture(Object coursePicture) {
            this.coursePicture = coursePicture;
        }

        public Object getViewCount() {
            return viewCount;
        }

        public void setViewCount(Object viewCount) {
            this.viewCount = viewCount;
        }

        public Object getStudyChapterName() {
            return studyChapterName;
        }

        public void setStudyChapterName(Object studyChapterName) {
            this.studyChapterName = studyChapterName;
        }

        public Object getDoneChapterRate() {
            return doneChapterRate;
        }

        public void setDoneChapterRate(Object doneChapterRate) {
            this.doneChapterRate = doneChapterRate;
        }

        public Object getStudyChapterId() {
            return studyChapterId;
        }

        public void setStudyChapterId(Object studyChapterId) {
            this.studyChapterId = studyChapterId;
        }

        public Object getCourseText() {
            return courseText;
        }

        public void setCourseText(Object courseText) {
            this.courseText = courseText;
        }

        public Object getTeacher() {
            return teacher;
        }

        public void setTeacher(Object teacher) {
            this.teacher = teacher;
        }

        public List<ChaptersBean> getChapters() {
            return chapters;
        }

        public void setChapters(List<ChaptersBean> chapters) {
            this.chapters = chapters;
        }

        public static class ChaptersBean {
            /**
             * chapterId : 133
             * priority : 4
             * chapterName : 钢筋工技能培训视频教程第4节
             * chapterDescription : null
             * playNumber : null
             * satisfactionDegree : null
             * isFree : false
             * hasQuestion : false
             * score : 0
             * resourceURL : http://img.kspx.ccla.com.cn/server/upload/file/2016/05/18/1463564174500038598.flv
             * studyVideoTime : null
             * isPassed : false
             */

            private int chapterId;
            private int priority;
            private String chapterName;
            private Object chapterDescription;
            private Object playNumber;
            private Object satisfactionDegree;
            private boolean isFree;
            private boolean hasQuestion;
            private int score;
            private String resourceURL;
            private Object studyVideoTime;
            private boolean isPassed;

            public int getChapterId() {
                return chapterId;
            }

            public void setChapterId(int chapterId) {
                this.chapterId = chapterId;
            }

            public int getPriority() {
                return priority;
            }

            public void setPriority(int priority) {
                this.priority = priority;
            }

            public String getChapterName() {
                return chapterName;
            }

            public void setChapterName(String chapterName) {
                this.chapterName = chapterName;
            }

            public Object getChapterDescription() {
                return chapterDescription;
            }

            public void setChapterDescription(Object chapterDescription) {
                this.chapterDescription = chapterDescription;
            }

            public Object getPlayNumber() {
                return playNumber;
            }

            public void setPlayNumber(Object playNumber) {
                this.playNumber = playNumber;
            }

            public Object getSatisfactionDegree() {
                return satisfactionDegree;
            }

            public void setSatisfactionDegree(Object satisfactionDegree) {
                this.satisfactionDegree = satisfactionDegree;
            }

            public boolean isIsFree() {
                return isFree;
            }

            public void setIsFree(boolean isFree) {
                this.isFree = isFree;
            }

            public boolean isHasQuestion() {
                return hasQuestion;
            }

            public void setHasQuestion(boolean hasQuestion) {
                this.hasQuestion = hasQuestion;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public String getResourceURL() {
                return resourceURL;
            }

            public void setResourceURL(String resourceURL) {
                this.resourceURL = resourceURL;
            }

            public Object getStudyVideoTime() {
                return studyVideoTime;
            }

            public void setStudyVideoTime(Object studyVideoTime) {
                this.studyVideoTime = studyVideoTime;
            }

            public boolean isIsPassed() {
                return isPassed;
            }

            public void setIsPassed(boolean isPassed) {
                this.isPassed = isPassed;
            }
        }
    }
}
