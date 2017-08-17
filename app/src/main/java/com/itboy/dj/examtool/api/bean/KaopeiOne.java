package com.itboy.dj.examtool.api.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.itboy.dj.examtool.adapter.ExpandableItemTwoAdapter;

/**
 * Created by Administrator on 2017/5/22.
 */

public class KaopeiOne extends AbstractExpandableItem<KaoPeiTwo> implements MultiItemEntity {
    private String epOneName;
    private String epOneId;


    public KaopeiOne(String epOneName, String epOneId) {
        this.epOneName = epOneName;
        this.epOneId = epOneId;
    }

    public String getEpOneId() {
        return epOneId;
    }

    public String getEpOneName() {
        return epOneName;
    }

    @Override
    public int getLevel() {
        return ExpandableItemTwoAdapter.TYPE_LEVEL_0;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
