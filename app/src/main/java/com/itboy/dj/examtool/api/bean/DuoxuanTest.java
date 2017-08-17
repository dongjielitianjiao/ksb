package com.itboy.dj.examtool.api.bean;

/**
 * Created by Administrator on 2017/6/8.
 */

public class DuoxuanTest {
    private String type;
    private String datas;

    public DuoxuanTest(String type, String datas) {
        this.type = type;
        this.datas = datas;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDatas() {
        return datas;
    }

    public void setDatas(String datas) {
        this.datas = datas;
    }
}
