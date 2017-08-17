package com.itboy.dj.examtool.api.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/5/24.
 */
/*未推荐*/
public class StudyUnCommandBean  implements Serializable{


    /**
     * result : ok
     * error : {"errorCode":0,"errorText":"success"}
     * data : {"total":2,"rows":[{"userCourseId":40,"userId":7331,"courseId":53,"examId":null,"courseStudyStatus":"learning","courseName":"2016\u201c陕建杯\u201d职业技能大赛裁判员培训","examName":null,"coursePicture":"http://img.kspx.ccla.com.cn/server/upload/image/2016/07/29/1469773582560072718.png","viewCount":23,"studyChapterName":null,"doneChapterRate":0},{"userCourseId":39,"userId":7331,"courseId":49,"examId":null,"courseStudyStatus":"learning","courseName":"2016年古建油漆工高级工最新课程","examName":null,"coursePicture":"http://img.kspx.ccla.com.cn/server/upload/image/2016/06/20/1466390622593042901.jpg","viewCount":2,"studyChapterName":"古建油漆工高级工第二节","doneChapterRate":20}],"footer":[]}
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
         * rows : [{"userCourseId":40,"userId":7331,"courseId":53,"examId":null,"courseStudyStatus":"learning","courseName":"2016\u201c陕建杯\u201d职业技能大赛裁判员培训","examName":null,"coursePicture":"http://img.kspx.ccla.com.cn/server/upload/image/2016/07/29/1469773582560072718.png","viewCount":23,"studyChapterName":null,"doneChapterRate":0},{"userCourseId":39,"userId":7331,"courseId":49,"examId":null,"courseStudyStatus":"learning","courseName":"2016年古建油漆工高级工最新课程","examName":null,"coursePicture":"http://img.kspx.ccla.com.cn/server/upload/image/2016/06/20/1466390622593042901.jpg","viewCount":2,"studyChapterName":"古建油漆工高级工第二节","doneChapterRate":20}]
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
             * userCourseId : 40
             * userId : 7331
             * courseId : 53
             * examId : null
             * courseStudyStatus : learning
             * courseName : 2016“陕建杯”职业技能大赛裁判员培训
             * examName : null
             * coursePicture : http://img.kspx.ccla.com.cn/server/upload/image/2016/07/29/1469773582560072718.png
             * viewCount : 23
             * studyChapterName : null
             * doneChapterRate : 0.0
             */

            private int userCourseId;
            private int userId;
            private int courseId;
            private Object examId;
            private String courseStudyStatus;
            private String courseName;
            private Object examName;
            private String coursePicture;
            private int viewCount;
            private Object studyChapterName;
            private double doneChapterRate;

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

            public Object getExamId() {
                return examId;
            }

            public void setExamId(Object examId) {
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

            public Object getExamName() {
                return examName;
            }

            public void setExamName(Object examName) {
                this.examName = examName;
            }

            public String getCoursePicture() {
                return coursePicture;
            }

            public void setCoursePicture(String coursePicture) {
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

            public double getDoneChapterRate() {
                return doneChapterRate;
            }

            public void setDoneChapterRate(double doneChapterRate) {
                this.doneChapterRate = doneChapterRate;
            }
        }
    }
}
