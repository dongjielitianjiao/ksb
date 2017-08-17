package com.itboy.dj.examtool.modules.ftpage.exam;

import java.util.List;

/**
 * Created by Administrator on 2017/7/18.
 */
//单选,多选，判断，填空
public class ExamTypebean {


    /**
     * result : ok
     * error : {"errorCode":0,"errorText":"success"}
     * data : {"paperId":48,"examId":87,"examDetailPriority":7,"examCourseId":1,"examCourseName":"理论考试","totalScore":100,"items":[{"paperId":48,"priority":1,"no":null,"examId":87,"problemId":227,"score":5,"text":"要使构件能够安全，正常工作，除了满足强度和刚度要求外，还要满足()要求。","answer":null,"objectiveType":"single","fillBlankNumber":null,"questionOptions":[{"questionId":227,"priority":1,"text":"形状"},{"questionId":227,"priority":2,"text":"尺寸"},{"questionId":227,"priority":3,"text":"对称性"},{"questionId":227,"priority":4,"text":"稳定性"}],"objective":true},{"paperId":48,"priority":2,"no":null,"examId":87,"problemId":226,"score":5,"text":"()元素是影响钢筋可焊性的重要元素。","answer":null,"objectiveType":"single","fillBlankNumber":null,"questionOptions":[{"questionId":226,"priority":1,"text":"碳"},{"questionId":226,"priority":2,"text":"锰"},{"questionId":226,"priority":3,"text":"硅"},{"questionId":226,"priority":4,"text":"铁"}],"objective":true},{"paperId":48,"priority":3,"no":null,"examId":87,"problemId":225,"score":5,"text":"螺纹钢筋的直径是指它的()。","answer":null,"objectiveType":"single","fillBlankNumber":null,"questionOptions":[{"questionId":225,"priority":1,"text":"内缘直径"},{"questionId":225,"priority":2,"text":"外缘直径"},{"questionId":225,"priority":3,"text":"当量直径"},{"questionId":225,"priority":4,"text":"当量直径和内线直径"}],"objective":true},{"paperId":48,"priority":4,"no":null,"examId":87,"problemId":224,"score":5,"text":"绝对标高是从我国()平均海平面为零点，其他各地的标高都以它作为基准。","answer":null,"objectiveType":"single","fillBlankNumber":null,"questionOptions":[{"questionId":224,"priority":1,"text":"黄海"},{"questionId":224,"priority":2,"text":"东海"},{"questionId":224,"priority":3,"text":"渤海"},{"questionId":224,"priority":4,"text":"南海"}],"objective":true},{"paperId":48,"priority":5,"no":null,"examId":87,"problemId":223,"score":5,"text":"在钢筋混凝土构件代号中，\u201cJL\u201d是表示( )。","answer":null,"objectiveType":"single","fillBlankNumber":null,"questionOptions":[{"questionId":223,"priority":1,"text":"圈梁"},{"questionId":223,"priority":2,"text":"过梁"},{"questionId":223,"priority":3,"text":"连系梁"},{"questionId":223,"priority":4,"text":"基础梁"}],"objective":true},{"paperId":48,"priority":6,"no":null,"examId":87,"problemId":329,"score":5,"text":"泥浆护壁成孔灌注桩施工的工艺流程中，在\u201c下钢筋笼\u201d之前完成的工作有（  ）。","answer":null,"objectiveType":"multi","fillBlankNumber":null,"questionOptions":[{"questionId":329,"priority":1,"text":"测定桩位"},{"questionId":329,"priority":2,"text":"埋设护筒"},{"questionId":329,"priority":3,"text":"制备泥浆"},{"questionId":329,"priority":4,"text":"绑扎承台钢筋"},{"questionId":329,"priority":5,"text":"成孔"}],"objective":true},{"paperId":48,"priority":7,"no":null,"examId":87,"problemId":328,"score":5,"text":"锤击沉桩法的施工程序中，（  ）是\u201c接桩\u201d之前必须完成个工作。","answer":null,"objectiveType":"multi","fillBlankNumber":null,"questionOptions":[{"questionId":328,"priority":1,"text":"打桩机就位"},{"questionId":328,"priority":2,"text":"吊桩和喂桩"},{"questionId":328,"priority":3,"text":"校正"},{"questionId":328,"priority":4,"text":"锤击沉桩"},{"questionId":328,"priority":5,"text":"送桩"}],"objective":true},{"paperId":48,"priority":8,"no":null,"examId":87,"problemId":327,"score":5,"text":"打桩时应注意观察的事项有（  ）。","answer":null,"objectiveType":"multi","fillBlankNumber":null,"questionOptions":[{"questionId":327,"priority":1,"text":"打桩入土的速度"},{"questionId":327,"priority":2,"text":"打桩架的垂直度"},{"questionId":327,"priority":3,"text":"桩身压缩情况"},{"questionId":327,"priority":4,"text":"桩锤的回弹情况"},{"questionId":327,"priority":5,"text":"贯入度变化情况"}],"objective":true},{"paperId":48,"priority":10,"no":null,"examId":87,"problemId":325,"score":5,"text":"预制桩的接桩工艺包括（ ）。","answer":null,"objectiveType":"multi","fillBlankNumber":null,"questionOptions":[{"questionId":325,"priority":1,"text":"硫磺胶泥浆锚法接桩"},{"questionId":325,"priority":2,"text":"挤压法接桩"},{"questionId":325,"priority":3,"text":"焊接法接桩"},{"questionId":325,"priority":4,"text":"法兰螺栓接桩法"},{"questionId":325,"priority":5,"text":"直螺纹接桩法"}],"objective":true},{"paperId":48,"priority":11,"no":null,"examId":87,"problemId":730,"score":5,"text":"接零装置的零线上能装熔断器和断路器。","answer":null,"objectiveType":"judgement","fillBlankNumber":null,"questionOptions":[{"questionId":730,"priority":1,"text":"1"},{"questionId":730,"priority":2,"text":"0"}],"objective":true},{"paperId":48,"priority":12,"no":null,"examId":87,"problemId":729,"score":5,"text":"把电气设备的某一金属部分通过导体与土壤间作良好的电气连接称为接地。","answer":null,"objectiveType":"judgement","fillBlankNumber":null,"questionOptions":[{"questionId":729,"priority":1,"text":"1"},{"questionId":729,"priority":2,"text":"0"}],"objective":true},{"paperId":48,"priority":13,"no":null,"examId":87,"problemId":399,"score":5,"text":"三点支撑式打桩机垂直精度调整灵活，整机稳定性好。","answer":null,"objectiveType":"judgement","fillBlankNumber":null,"questionOptions":[{"questionId":399,"priority":1,"text":"1"},{"questionId":399,"priority":2,"text":"0"}],"objective":true},{"paperId":48,"priority":14,"no":null,"examId":87,"problemId":398,"score":5,"text":"悬挂式履带打桩机可以施打后仰20°范围内的斜桩。","answer":null,"objectiveType":"judgement","fillBlankNumber":null,"questionOptions":[{"questionId":398,"priority":1,"text":"1"},{"questionId":398,"priority":2,"text":"0"}],"objective":true},{"paperId":48,"priority":15,"no":null,"examId":87,"problemId":397,"score":5,"text":"履带式打桩机可以悬挂各种打桩锤，分别施打各种不同类型的预制桩。","answer":null,"objectiveType":"judgement","fillBlankNumber":null,"questionOptions":[{"questionId":397,"priority":1,"text":"1"},{"questionId":397,"priority":2,"text":"0"}],"objective":true},{"paperId":48,"priority":16,"no":null,"examId":87,"problemId":344,"score":5,"text":"导管法水下浇筑混凝土应连续浇筑，一旦堵管 ____ 以上，应立即用备用导管浇筑。","answer":null,"objectiveType":"fill_blank","fillBlankNumber":1,"questionOptions":[],"objective":true},{"paperId":48,"priority":17,"no":null,"examId":87,"problemId":343,"score":5,"text":"沉管成孔灌注桩施工时常易发生 ____ 、 ____ 和吊脚等问题。","answer":null,"objectiveType":"fill_blank","fillBlankNumber":2,"questionOptions":[],"objective":true},{"paperId":48,"priority":18,"no":null,"examId":87,"problemId":342,"score":5,"text":"孔口围圈应高出地面？____ 米以上，不施工时孔口应 ____ 。","answer":null,"objectiveType":"fill_blank","fillBlankNumber":2,"questionOptions":[],"objective":true},{"paperId":48,"priority":19,"no":null,"examId":87,"problemId":341,"score":5,"text":"每台用电设备，应有各自专用的开关箱，必须实行 ____制，严禁用同一开关电器直接控制____用电设备（含插座）。","answer":null,"objectiveType":"fill_blank","fillBlankNumber":2,"questionOptions":[],"objective":true},{"paperId":48,"priority":20,"no":null,"examId":87,"problemId":340,"score":5,"text":"井下作业应戴 ____ ，穿 ____ 。","answer":null,"objectiveType":"fill_blank","fillBlankNumber":2,"questionOptions":[],"objective":true}]}
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
         * paperId : 48
         * examId : 87
         * examDetailPriority : 7
         * examCourseId : 1
         * examCourseName : 理论考试
         * totalScore : 100.0
         * items : [{"paperId":48,"priority":1,"no":null,"examId":87,"problemId":227,"score":5,"text":"要使构件能够安全，正常工作，除了满足强度和刚度要求外，还要满足()要求。","answer":null,"objectiveType":"single","fillBlankNumber":null,"questionOptions":[{"questionId":227,"priority":1,"text":"形状"},{"questionId":227,"priority":2,"text":"尺寸"},{"questionId":227,"priority":3,"text":"对称性"},{"questionId":227,"priority":4,"text":"稳定性"}],"objective":true},{"paperId":48,"priority":2,"no":null,"examId":87,"problemId":226,"score":5,"text":"()元素是影响钢筋可焊性的重要元素。","answer":null,"objectiveType":"single","fillBlankNumber":null,"questionOptions":[{"questionId":226,"priority":1,"text":"碳"},{"questionId":226,"priority":2,"text":"锰"},{"questionId":226,"priority":3,"text":"硅"},{"questionId":226,"priority":4,"text":"铁"}],"objective":true},{"paperId":48,"priority":3,"no":null,"examId":87,"problemId":225,"score":5,"text":"螺纹钢筋的直径是指它的()。","answer":null,"objectiveType":"single","fillBlankNumber":null,"questionOptions":[{"questionId":225,"priority":1,"text":"内缘直径"},{"questionId":225,"priority":2,"text":"外缘直径"},{"questionId":225,"priority":3,"text":"当量直径"},{"questionId":225,"priority":4,"text":"当量直径和内线直径"}],"objective":true},{"paperId":48,"priority":4,"no":null,"examId":87,"problemId":224,"score":5,"text":"绝对标高是从我国()平均海平面为零点，其他各地的标高都以它作为基准。","answer":null,"objectiveType":"single","fillBlankNumber":null,"questionOptions":[{"questionId":224,"priority":1,"text":"黄海"},{"questionId":224,"priority":2,"text":"东海"},{"questionId":224,"priority":3,"text":"渤海"},{"questionId":224,"priority":4,"text":"南海"}],"objective":true},{"paperId":48,"priority":5,"no":null,"examId":87,"problemId":223,"score":5,"text":"在钢筋混凝土构件代号中，\u201cJL\u201d是表示( )。","answer":null,"objectiveType":"single","fillBlankNumber":null,"questionOptions":[{"questionId":223,"priority":1,"text":"圈梁"},{"questionId":223,"priority":2,"text":"过梁"},{"questionId":223,"priority":3,"text":"连系梁"},{"questionId":223,"priority":4,"text":"基础梁"}],"objective":true},{"paperId":48,"priority":6,"no":null,"examId":87,"problemId":329,"score":5,"text":"泥浆护壁成孔灌注桩施工的工艺流程中，在\u201c下钢筋笼\u201d之前完成的工作有（  ）。","answer":null,"objectiveType":"multi","fillBlankNumber":null,"questionOptions":[{"questionId":329,"priority":1,"text":"测定桩位"},{"questionId":329,"priority":2,"text":"埋设护筒"},{"questionId":329,"priority":3,"text":"制备泥浆"},{"questionId":329,"priority":4,"text":"绑扎承台钢筋"},{"questionId":329,"priority":5,"text":"成孔"}],"objective":true},{"paperId":48,"priority":7,"no":null,"examId":87,"problemId":328,"score":5,"text":"锤击沉桩法的施工程序中，（  ）是\u201c接桩\u201d之前必须完成个工作。","answer":null,"objectiveType":"multi","fillBlankNumber":null,"questionOptions":[{"questionId":328,"priority":1,"text":"打桩机就位"},{"questionId":328,"priority":2,"text":"吊桩和喂桩"},{"questionId":328,"priority":3,"text":"校正"},{"questionId":328,"priority":4,"text":"锤击沉桩"},{"questionId":328,"priority":5,"text":"送桩"}],"objective":true},{"paperId":48,"priority":8,"no":null,"examId":87,"problemId":327,"score":5,"text":"打桩时应注意观察的事项有（  ）。","answer":null,"objectiveType":"multi","fillBlankNumber":null,"questionOptions":[{"questionId":327,"priority":1,"text":"打桩入土的速度"},{"questionId":327,"priority":2,"text":"打桩架的垂直度"},{"questionId":327,"priority":3,"text":"桩身压缩情况"},{"questionId":327,"priority":4,"text":"桩锤的回弹情况"},{"questionId":327,"priority":5,"text":"贯入度变化情况"}],"objective":true},{"paperId":48,"priority":10,"no":null,"examId":87,"problemId":325,"score":5,"text":"预制桩的接桩工艺包括（ ）。","answer":null,"objectiveType":"multi","fillBlankNumber":null,"questionOptions":[{"questionId":325,"priority":1,"text":"硫磺胶泥浆锚法接桩"},{"questionId":325,"priority":2,"text":"挤压法接桩"},{"questionId":325,"priority":3,"text":"焊接法接桩"},{"questionId":325,"priority":4,"text":"法兰螺栓接桩法"},{"questionId":325,"priority":5,"text":"直螺纹接桩法"}],"objective":true},{"paperId":48,"priority":11,"no":null,"examId":87,"problemId":730,"score":5,"text":"接零装置的零线上能装熔断器和断路器。","answer":null,"objectiveType":"judgement","fillBlankNumber":null,"questionOptions":[{"questionId":730,"priority":1,"text":"1"},{"questionId":730,"priority":2,"text":"0"}],"objective":true},{"paperId":48,"priority":12,"no":null,"examId":87,"problemId":729,"score":5,"text":"把电气设备的某一金属部分通过导体与土壤间作良好的电气连接称为接地。","answer":null,"objectiveType":"judgement","fillBlankNumber":null,"questionOptions":[{"questionId":729,"priority":1,"text":"1"},{"questionId":729,"priority":2,"text":"0"}],"objective":true},{"paperId":48,"priority":13,"no":null,"examId":87,"problemId":399,"score":5,"text":"三点支撑式打桩机垂直精度调整灵活，整机稳定性好。","answer":null,"objectiveType":"judgement","fillBlankNumber":null,"questionOptions":[{"questionId":399,"priority":1,"text":"1"},{"questionId":399,"priority":2,"text":"0"}],"objective":true},{"paperId":48,"priority":14,"no":null,"examId":87,"problemId":398,"score":5,"text":"悬挂式履带打桩机可以施打后仰20°范围内的斜桩。","answer":null,"objectiveType":"judgement","fillBlankNumber":null,"questionOptions":[{"questionId":398,"priority":1,"text":"1"},{"questionId":398,"priority":2,"text":"0"}],"objective":true},{"paperId":48,"priority":15,"no":null,"examId":87,"problemId":397,"score":5,"text":"履带式打桩机可以悬挂各种打桩锤，分别施打各种不同类型的预制桩。","answer":null,"objectiveType":"judgement","fillBlankNumber":null,"questionOptions":[{"questionId":397,"priority":1,"text":"1"},{"questionId":397,"priority":2,"text":"0"}],"objective":true},{"paperId":48,"priority":16,"no":null,"examId":87,"problemId":344,"score":5,"text":"导管法水下浇筑混凝土应连续浇筑，一旦堵管 ____ 以上，应立即用备用导管浇筑。","answer":null,"objectiveType":"fill_blank","fillBlankNumber":1,"questionOptions":[],"objective":true},{"paperId":48,"priority":17,"no":null,"examId":87,"problemId":343,"score":5,"text":"沉管成孔灌注桩施工时常易发生 ____ 、 ____ 和吊脚等问题。","answer":null,"objectiveType":"fill_blank","fillBlankNumber":2,"questionOptions":[],"objective":true},{"paperId":48,"priority":18,"no":null,"examId":87,"problemId":342,"score":5,"text":"孔口围圈应高出地面？____ 米以上，不施工时孔口应 ____ 。","answer":null,"objectiveType":"fill_blank","fillBlankNumber":2,"questionOptions":[],"objective":true},{"paperId":48,"priority":19,"no":null,"examId":87,"problemId":341,"score":5,"text":"每台用电设备，应有各自专用的开关箱，必须实行 ____制，严禁用同一开关电器直接控制____用电设备（含插座）。","answer":null,"objectiveType":"fill_blank","fillBlankNumber":2,"questionOptions":[],"objective":true},{"paperId":48,"priority":20,"no":null,"examId":87,"problemId":340,"score":5,"text":"井下作业应戴 ____ ，穿 ____ 。","answer":null,"objectiveType":"fill_blank","fillBlankNumber":2,"questionOptions":[],"objective":true}]
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
             * paperId : 48
             * priority : 1
             * no : null
             * examId : 87
             * problemId : 227
             * score : 5.0
             * text : 要使构件能够安全，正常工作，除了满足强度和刚度要求外，还要满足()要求。
             * answer : null
             * objectiveType : single
             * fillBlankNumber : null
             * questionOptions : [{"questionId":227,"priority":1,"text":"形状"},{"questionId":227,"priority":2,"text":"尺寸"},{"questionId":227,"priority":3,"text":"对称性"},{"questionId":227,"priority":4,"text":"稳定性"}]
             * objective : true
             */

            private int paperId;
            private int priority;
            private Object no;
            private int examId;
            private int problemId;
            private double score;
            private String text;
            private Object answer;
            private String objectiveType;
            private Object fillBlankNumber;
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

            public Object getNo() {
                return no;
            }

            public void setNo(Object no) {
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

            public Object getFillBlankNumber() {
                return fillBlankNumber;
            }

            public void setFillBlankNumber(Object fillBlankNumber) {
                this.fillBlankNumber = fillBlankNumber;
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
                 * questionId : 227
                 * priority : 1
                 * text : 形状
                 */

                private int questionId;
                private int priority;
                private String text;
                private String intdexAZ;

                public String getIntdexAZ() {
                    return intdexAZ;
                }

                public void setIntdexAZ(String intdexAZ) {
                    this.intdexAZ = intdexAZ;
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
