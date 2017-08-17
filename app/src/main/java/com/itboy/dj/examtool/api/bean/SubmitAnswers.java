package com.itboy.dj.examtool.api.bean;

/**
 * Created by Administrator on 2017/6/6.
 */

public class SubmitAnswers {
    String answer;
    String questionId;


  /*  public SubmitAnswers(String questionId, String answer) {
        this.questionId = questionId;
        this.answer = answer;
    }
*/

    public SubmitAnswers(String answer, String questionId) {
        this.answer = answer;
        this.questionId = questionId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    //answer   questionId
    @Override
    public String toString() {
        return "SubmitAnswers{" +
                "questionId='" + questionId + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
