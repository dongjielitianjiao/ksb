package com.itboy.dj.examtool.api.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/5/24.
 */
/*推荐*/
public class StudyCommandBean  implements Serializable {


    /**
     * result : ok
     * error : {"errorCode":0,"errorText":"success"}
     * data : [{"userCourseId":null,"userId":7374,"courseId":48,"examId":54,"courseStudyStatus":null,"courseName":"钢筋工高级工考前培训课程","examName":"2016年度钢筋工高级工考试","coursePicture":null,"viewCount":2,"studyChapterName":null,"doneChapterRate":null},{"userCourseId":null,"userId":7374,"courseId":47,"examId":64,"courseStudyStatus":null,"courseName":"古建油漆工高级工考前培训课程","examName":"2016年度古建油漆工高级工考试(有培训)","coursePicture":"http://img.kspx.ccla.com.cn/server/upload/image/2016/06/14/1465885067812008796.jpg","viewCount":2,"studyChapterName":null,"doneChapterRate":null}]
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
         * userCourseId : null
         * userId : 7374
         * courseId : 48
         * examId : 54
         * courseStudyStatus : null
         * courseName : 钢筋工高级工考前培训课程
         * examName : 2016年度钢筋工高级工考试
         * coursePicture : null
         * viewCount : 2
         * studyChapterName : null
         * doneChapterRate : null
         */

        private Object userCourseId;
        private int userId;
        private int courseId;
        private int examId;
        private Object courseStudyStatus;
        private String courseName;
        private String examName;
        private Object coursePicture;
        private int viewCount;
        private Object studyChapterName;
        private Object doneChapterRate;

        public Object getUserCourseId() {
            return userCourseId;
        }

        public void setUserCourseId(Object userCourseId) {
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

        public Object getCourseStudyStatus() {
            return courseStudyStatus;
        }

        public void setCourseStudyStatus(Object courseStudyStatus) {
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

        public int getViewCount() {
            return viewCount;
        }

        public void setViewCount(int viewCount) {
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
    }
}
