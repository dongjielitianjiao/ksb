package com.itboy.dj.examtool.api.bean;

/**
 * Created by Administrator on 2017/5/17.
 */

public class ProfessionRxPost {
    private String name;
    private int  id;

    public ProfessionRxPost(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

}
