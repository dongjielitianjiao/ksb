package com.itboy.dj.examtool.modules.ftpage.exam;

/**
 * Created by Administrator on 2017/7/20.
 */

public class FillPostDataAndPos {
    private int itemPt;
    private int posit;
    private String data;


    public FillPostDataAndPos(int itemPt, int posit, String data) {
        this.itemPt = itemPt;
        this.posit = posit;
        this.data = data;
    }

    public int getItemPt() {
        return itemPt;
    }

    public int getPosit() {
        return posit;
    }

    public String getData() {
        return data;
    }
}
