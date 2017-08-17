package com.itboy.dj.examtool.api.bean;

/**
 * Created by Administrator on 2017/6/16.
 */

public class NewsTabTitle {
    String name;
    String type;
    String parameter;
 /*   public NewsTabTitle(String name, String type) {
        this.name = name;
        this.type = type;
    }*/

    public NewsTabTitle(String name, String type, String parameter) {
        this.name = name;
        this.type = type;
        this.parameter = parameter;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getParameter() {
        return parameter;
    }
}
