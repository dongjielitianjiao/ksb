package com.itboy.dj.examtool;

/**
 * Created by Administrator on 2017/5/31.
 */
//试题测试
public class ExamsBean {
    private int index;
    private String qustion;
    private String qustionid;
    private String chooseA;
    private String chooseB;
    private String chooseC;
    private String chooseD;
    private String answer;
    public ExamsBean(int index, String qustion, String qustionid, String chooseA, String chooseB, String chooseC, String chooseD, String answer) {
        this.index = index;
        this.qustion = qustion;
        this.qustionid = qustionid;
        this.chooseA = chooseA;
        this.chooseB = chooseB;
        this.chooseC = chooseC;
        this.chooseD = chooseD;
        this.answer = answer;
    }



    public ExamsBean(int index, String qustion, String chooseA, String chooseB, String chooseC, String chooseD, String answer) {
        this.index = index;
        this.qustion = qustion;
        this.chooseA = chooseA;
        this.chooseB = chooseB;
        this.chooseC = chooseC;
        this.chooseD = chooseD;
        this.answer = answer;
    }

    public String getQustionid() {
        return qustionid;
    }

    public void setQustionid(String qustionid) {
        this.qustionid = qustionid;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getQustion() {
        return qustion;
    }

    public void setQustion(String qustion) {
        this.qustion = qustion;
    }

    public String getChooseA() {
        return chooseA;
    }

    public void setChooseA(String chooseA) {
        this.chooseA = chooseA;
    }

    public String getChooseB() {
        return chooseB;
    }

    public void setChooseB(String chooseB) {
        this.chooseB = chooseB;
    }

    public String getChooseC() {
        return chooseC;
    }

    public void setChooseC(String chooseC) {
        this.chooseC = chooseC;
    }

    public String getChooseD() {
        return chooseD;
    }

    public void setChooseD(String chooseD) {
        this.chooseD = chooseD;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
