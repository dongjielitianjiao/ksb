package com.itboy.dj.examtool.api.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/3.
 */

public class ExamPaper {

    /**
     * result : ok
     * error : {"errorCode":0,"errorText":"success"}
     * data : {"paperId":35,"examId":80,"examDetailPriority":1,"examCourseId":1,"examCourseName":"理论考试","totalScore":100,"items":[{"paperId":35,"priority":1,"no":1,"examId":80,"problemId":217,"score":5,"text":"悬挑构件受力钢筋布置在结构的（）。","answer":null,"objectiveType":"single","questionOptions":[{"questionId":217,"priority":1,"text":"下部"},{"questionId":217,"priority":2,"text":"上部"},{"questionId":217,"priority":3,"text":"中部"},{"questionId":217,"priority":4,"text":"没有规定"}],"objective":true},{"paperId":35,"priority":2,"no":1,"examId":80,"problemId":218,"score":5,"text":"比例尺的用途是（）。","answer":null,"objectiveType":"single","questionOptions":[{"questionId":218,"priority":1,"text":"画直线用的"},{"questionId":218,"priority":2,"text":"画曲线用的"},{"questionId":218,"priority":3,"text":"截取线段长度用的"},{"questionId":218,"priority":4,"text":"放大或缩小线段长度用的"}],"objective":true}]}
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
         * paperId : 35
         * examId : 80
         * examDetailPriority : 1
         * examCourseId : 1
         * examCourseName : 理论考试
         * totalScore : 100.0
         * items : [{"paperId":35,"priority":1,"no":1,"examId":80,"problemId":217,"score":5,"text":"悬挑构件受力钢筋布置在结构的（）。","answer":null,"objectiveType":"single","questionOptions":[{"questionId":217,"priority":1,"text":"下部"},{"questionId":217,"priority":2,"text":"上部"},{"questionId":217,"priority":3,"text":"中部"},{"questionId":217,"priority":4,"text":"没有规定"}],"objective":true},{"paperId":35,"priority":2,"no":1,"examId":80,"problemId":218,"score":5,"text":"比例尺的用途是（）。","answer":null,"objectiveType":"single","questionOptions":[{"questionId":218,"priority":1,"text":"画直线用的"},{"questionId":218,"priority":2,"text":"画曲线用的"},{"questionId":218,"priority":3,"text":"截取线段长度用的"},{"questionId":218,"priority":4,"text":"放大或缩小线段长度用的"}],"objective":true}]
         */

        private int paperId;
        private int examId;
        private int examDetailPriority;
        private int examCourseId;
        private String examCourseName;
        private double totalScore;
        private List<ItemsBean> items;

        public int getPaperId() {
            return paperId;
        }

        public void setPaperId(int paperId) {
            this.paperId = paperId;
        }

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

        public double getTotalScore() {
            return totalScore;
        }

        public void setTotalScore(double totalScore) {
            this.totalScore = totalScore;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * paperId : 35
             * priority : 1
             * no : 1
             * examId : 80
             * problemId : 217
             * score : 5.0
             * text : 悬挑构件受力钢筋布置在结构的（）。
             * answer : null
             * objectiveType : single
             * questionOptions : [{"questionId":217,"priority":1,"text":"下部"},{"questionId":217,"priority":2,"text":"上部"},{"questionId":217,"priority":3,"text":"中部"},{"questionId":217,"priority":4,"text":"没有规定"}]
             * objective : true
             */

            private int paperId;
            private int priority;
            private int no;
            private int examId;
            private int problemId;
            private double score;
            private String text;            private Object answer;

            private String objectiveType;
            private boolean objective;
            private List<QuestionOptionsBean> questionOptions;

            public int getPaperId() {
                return paperId;
            }

            public void setPaperId(int paperId) {
                this.paperId = paperId;
            }

            public int getPriority() {
                return priority;
            }

            public void setPriority(int priority) {
                this.priority = priority;
            }

            public int getNo() {
                return no;
            }

            public void setNo(int no) {
                this.no = no;
            }

            public int getExamId() {
                return examId;
            }

            public void setExamId(int examId) {
                this.examId = examId;
            }

            public int getProblemId() {
                return problemId;
            }

            public void setProblemId(int problemId) {
                this.problemId = problemId;
            }

            public double getScore() {
                return score;
            }

            public void setScore(double score) {
                this.score = score;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public Object getAnswer() {
                return answer;
            }

            public void setAnswer(Object answer) {
                this.answer = answer;
            }

            public String getObjectiveType() {
                return objectiveType;
            }

            public void setObjectiveType(String objectiveType) {
                this.objectiveType = objectiveType;
            }

            public boolean isObjective() {
                return objective;
            }

            public void setObjective(boolean objective) {
                this.objective = objective;
            }

            public List<QuestionOptionsBean> getQuestionOptions() {
                return questionOptions;
            }

            public void setQuestionOptions(List<QuestionOptionsBean> questionOptions) {
                this.questionOptions = questionOptions;
            }

            public static class QuestionOptionsBean {
                /**
                 * questionId : 217
                 * priority : 1
                 * text : 下部
                 */

                private int questionId;
                private int priority;
                private String text;

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
