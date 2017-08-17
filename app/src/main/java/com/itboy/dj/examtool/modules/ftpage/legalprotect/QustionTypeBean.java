package com.itboy.dj.examtool.modules.ftpage.legalprotect;

/**
 * Created by Administrator on 2017/6/28.
 */

public class QustionTypeBean {
    String typeName;
    int typeId;

    public QustionTypeBean(String typeName, int typeId) {
        this.typeName = typeName;
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public int getTypeId() {
        return typeId;
    }
}
