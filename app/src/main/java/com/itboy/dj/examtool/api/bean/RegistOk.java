package com.itboy.dj.examtool.api.bean;

/**
 * Created by Administrator on 2017/4/20.
 */

public  class RegistOk {
    private String userPhone;
    private String userPwd;

    public RegistOk(String userPhone, String userPwd) {
        this.userPhone = userPhone;
        this.userPwd = userPwd;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserPwd() {
        return userPwd;
    }
}
