package com.itboy.dj.examtool.api.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/17.
 */

public class FirstProBean implements Serializable {

    private String name;
    private String id;


    private int originalId;

    public FirstProBean(String name, String id, int originalId) {
        this.name = name;
        this.id = id;
        this.originalId = originalId;
    }

    public FirstProBean() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getOriginalId() {
        return originalId;
    }

    public void setOriginalId(int originalId) {
        this.originalId = originalId;
    }
}
