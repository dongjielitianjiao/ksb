package com.itboy.dj.examtool.modules.ftpage.exam;

/**
 * Created by Administrator on 2017/7/19.
 */

public class SaveExamBean {
    private String answer;
    private int  problem;
  public SaveExamBean(){

  }

    public SaveExamBean(String answer, int problem) {
        this.answer = answer;
        this.problem = problem;
    }

    public SaveExamBean(String answer) {
        this.answer = answer;
    }

    public int getProblem() {
        return problem;
    }

    public void setProblem(int problem) {
        this.problem = problem;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }
}
