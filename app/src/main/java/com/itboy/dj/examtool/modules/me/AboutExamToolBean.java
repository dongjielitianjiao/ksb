package com.itboy.dj.examtool.modules.me;

/**
 * Created by Administrator on 2017/6/29.
 */

public class AboutExamToolBean {
    private String type;
    private String title;

    public AboutExamToolBean(String type, String title) {
        this.type = type;
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }
}
