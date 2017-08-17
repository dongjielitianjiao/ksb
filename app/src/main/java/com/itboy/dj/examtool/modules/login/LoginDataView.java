package com.itboy.dj.examtool.modules.login;

import com.itboy.dj.examtool.modules.base.IBaseView;

import org.json.JSONException;

/**
 * Created by Administrator on 2017/3/29.
 */

public interface LoginDataView<T> extends IBaseView {
    /**
     * 加载数据
     *
     * @param data 数据
     */
    void loadData(T data) throws JSONException;
    /**
     * 加载更多
     * @param data 数据
     */
    void loadMoreData(T data);


    /**
     * 没有数据
     */
    void loadNoData();


}
