package com.itboy.dj.examtool.api.bean;

/**
 * Created by Administrator on 2017/6/26.
 */

public class ChangeNickNameOk {
    private  String nickNm;

    public ChangeNickNameOk(String nickNm) {
        this.nickNm = nickNm;
    }

    public String getNickNm() {
        return nickNm;
    }
}
