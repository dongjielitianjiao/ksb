package com.itboy.dj.examtool.modules.ftpage.exam;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */

public class ErrorReviewBean {


    /**
     * result : ok
     * error : {"errorCode":0,"errorText":"success"}
     * data : {"recordId":178,"userId":7331,"examId":87,"paperId":48,"paperName":"土木建筑钢筋工高级考试6-理论考试-卷2","examCourseId":1,"examCourseName":"理论考试","examTime":1,"startDate":"2017-07-21","endDate":"2017-07-21","isFinish":true,"userExamId":10975,"items":[{"priority":1,"score":5,"rightAnswer":"4","text":"要使构件能够安全，正常工作，除了满足强度和刚度要求外，还要满足()要求。","isObjective":true,"objectiveType":"single","fillBlankNumber":null,"questionId":227,"answer":"1","isTrue":false,"createDate":"2017-07-21","questionOptions":[{"questionId":227,"priority":1,"text":"形状"},{"questionId":227,"priority":2,"text":"尺寸"},{"questionId":227,"priority":3,"text":"对称性"},{"questionId":227,"priority":4,"text":"稳定性"}]},{"priority":2,"score":5,"rightAnswer":"1","text":"()元素是影响钢筋可焊性的重要元素。","isObjective":true,"objectiveType":"single","fillBlankNumber":null,"questionId":226,"answer":"2","isTrue":false,"createDate":"2017-07-21","questionOptions":[{"questionId":226,"priority":1,"text":"碳"},{"questionId":226,"priority":2,"text":"锰"},{"questionId":226,"priority":3,"text":"硅"},{"questionId":226,"priority":4,"text":"铁"}]},{"priority":3,"score":5,"rightAnswer":"3","text":"螺纹钢筋的直径是指它的()。","isObjective":true,"objectiveType":"single","fillBlankNumber":null,"questionId":225,"answer":"1","isTrue":false,"createDate":"2017-07-21","questionOptions":[{"questionId":225,"priority":1,"text":"内缘直径"},{"questionId":225,"priority":2,"text":"外缘直径"},{"questionId":225,"priority":3,"text":"当量直径"},{"questionId":225,"priority":4,"text":"当量直径和内线直径"}]}]}
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
         * recordId : 178
         * userId : 7331
         * examId : 87
         * paperId : 48
         * paperName : 土木建筑钢筋工高级考试6-理论考试-卷2
         * examCourseId : 1
         * examCourseName : 理论考试
         * examTime : 1
         * startDate : 2017-07-21
         * endDate : 2017-07-21
         * isFinish : true
         * userExamId : 10975
         * items : [{"priority":1,"score":5,"rightAnswer":"4","text":"要使构件能够安全，正常工作，除了满足强度和刚度要求外，还要满足()要求。","isObjective":true,"objectiveType":"single","fillBlankNumber":null,"questionId":227,"answer":"1","isTrue":false,"createDate":"2017-07-21","questionOptions":[{"questionId":227,"priority":1,"text":"形状"},{"questionId":227,"priority":2,"text":"尺寸"},{"questionId":227,"priority":3,"text":"对称性"},{"questionId":227,"priority":4,"text":"稳定性"}]},{"priority":2,"score":5,"rightAnswer":"1","text":"()元素是影响钢筋可焊性的重要元素。","isObjective":true,"objectiveType":"single","fillBlankNumber":null,"questionId":226,"answer":"2","isTrue":false,"createDate":"2017-07-21","questionOptions":[{"questionId":226,"priority":1,"text":"碳"},{"questionId":226,"priority":2,"text":"锰"},{"questionId":226,"priority":3,"text":"硅"},{"questionId":226,"priority":4,"text":"铁"}]},{"priority":3,"score":5,"rightAnswer":"3","text":"螺纹钢筋的直径是指它的()。","isObjective":true,"objectiveType":"single","fillBlankNumber":null,"questionId":225,"answer":"1","isTrue":false,"createDate":"2017-07-21","questionOptions":[{"questionId":225,"priority":1,"text":"内缘直径"},{"questionId":225,"priority":2,"text":"外缘直径"},{"questionId":225,"priority":3,"text":"当量直径"},{"questionId":225,"priority":4,"text":"当量直径和内线直径"}]}]
         */

        private int recordId;
        private int userId;
        private int examId;
        private int paperId;
        private String paperName;
        private int examCourseId;
        private String examCourseName;
        private int examTime;
        private String startDate;
        private String endDate;
        private boolean isFinish;
        private int userExamId;
        private List<ItemsBean> items;

        public int getRecordId() {
            return recordId;
        }

        public void setRecordId(int recordId) {
            this.recordId = recordId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getExamId() {
            return examId;
        }

        public void setExamId(int examId) {
            this.examId = examId;
        }

        public int getPaperId() {
            return paperId;
        }

        public void setPaperId(int paperId) {
            this.paperId = paperId;
        }

        public String getPaperName() {
            return paperName;
        }

        public void setPaperName(String paperName) {
            this.paperName = paperName;
        }

        public int getExamCourseId() {
            return examCourseId;
        }

        public void setExamCourseId(int examCourseId) {
            this.examCourseId = examCourseId;
        }

        public String getExamCourseName() {
            return examCourseName;
        }

        public void setExamCourseName(String examCourseName) {
            this.examCourseName = examCourseName;
        }

        public int getExamTime() {
            return examTime;
        }

        public void setExamTime(int examTime) {
            this.examTime = examTime;
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

        public boolean isIsFinish() {
            return isFinish;
        }

        public void setIsFinish(boolean isFinish) {
            this.isFinish = isFinish;
        }

        public int getUserExamId() {
            return userExamId;
        }

        public void setUserExamId(int userExamId) {
            this.userExamId = userExamId;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * priority : 1
             * score : 5.0
             * rightAnswer : 4
             * text : 要使构件能够安全，正常工作，除了满足强度和刚度要求外，还要满足()要求。
             * isObjective : true
             * objectiveType : single
             * fillBlankNumber : null
             * questionId : 227
             * answer : 1
             * isTrue : false
             * createDate : 2017-07-21
             * questionOptions : [{"questionId":227,"priority":1,"text":"形状"},{"questionId":227,"priority":2,"text":"尺寸"},{"questionId":227,"priority":3,"text":"对称性"},{"questionId":227,"priority":4,"text":"稳定性"}]
             */

            private int priority;
            private double score;
            private String rightAnswer;
            private String text;
            private boolean isObjective;
            private String objectiveType;
            private Object fillBlankNumber;
            private int questionId;
            private String answer;
            private boolean isTrue;
            private String createDate;
            private List<QuestionOptionsBean> questionOptions;

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

            public String getRightAnswer() {
                return rightAnswer;
            }

            public void setRightAnswer(String rightAnswer) {
                this.rightAnswer = rightAnswer;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public boolean isIsObjective() {
                return isObjective;
            }

            public void setIsObjective(boolean isObjective) {
                this.isObjective = isObjective;
            }

            public String getObjectiveType() {
                return objectiveType;
            }

            public void setObjectiveType(String objectiveType) {
                this.objectiveType = objectiveType;
            }

            public Object getFillBlankNumber() {
                return fillBlankNumber;
            }

            public void setFillBlankNumber(Object fillBlankNumber) {
                this.fillBlankNumber = fillBlankNumber;
            }

            public int getQuestionId() {
                return questionId;
            }

            public void setQuestionId(int questionId) {
                this.questionId = questionId;
            }

            public String getAnswer() {
                return answer;
            }

            public void setAnswer(String answer) {
                this.answer = answer;
            }

            public boolean isIsTrue() {
                return isTrue;
            }

            public void setIsTrue(boolean isTrue) {
                this.isTrue = isTrue;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public List<QuestionOptionsBean> getQuestionOptions() {
                return questionOptions;
            }

            public void setQuestionOptions(List<QuestionOptionsBean> questionOptions) {
                this.questionOptions = questionOptions;
            }

            public static class QuestionOptionsBean {
                /**
                 * questionId : 227
                 * priority : 1
                 * text : 形状
                 */

                private int questionId;
                private int priority;
                private String text;
                private String answer;

                public String getAnswer() {
                    return answer;
                }

                public void setAnswer(String answer) {
                    this.answer = answer;
                }

                public int getQuestionId() {
                    return questionId;
                }

                public void setQuestionId(int questionId) {
                    this.questionId = questionId;
                }

                public int getPriority() {
                    return priority;
                }

                public void setPriority(int priority) {
                    this.priority = priority;
                }

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }
            }
        }
    }
}
