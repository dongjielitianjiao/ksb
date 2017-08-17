package com.itboy.dj.examtool.modules.ftpage.getcertificate;

/**
 * Created by Administrator on 2017/6/22.
 */

public class PayBean {
    private int payLogoImg;
    private String payLogoStr;
    private int chooseId;

    public PayBean(int payLogoImg, String payLogoStr, int chooseId) {
        this.payLogoImg = payLogoImg;
        this.payLogoStr = payLogoStr;
        this.chooseId = chooseId;
    }

    public int getPayLogoImg() {
        return payLogoImg;
    }

    public void setPayLogoImg(int payLogoImg) {
        this.payLogoImg = payLogoImg;
    }

    public String getPayLogoStr() {
        return payLogoStr;
    }

    public void setPayLogoStr(String payLogoStr) {
        this.payLogoStr = payLogoStr;
    }

    public int getChooseId() {
        return chooseId;
    }

    public void setChooseId(int chooseId) {
        this.chooseId = chooseId;
    }
}
