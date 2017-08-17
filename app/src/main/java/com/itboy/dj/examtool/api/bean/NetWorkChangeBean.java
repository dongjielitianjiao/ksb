package com.itboy.dj.examtool.api.bean;

/**
 * Created by Administrator on 2017/5/24.
 */
//当网络变化时,界面重新去请求数据
public  class NetWorkChangeBean {

    private Boolean NetIsConnect;

    public NetWorkChangeBean(Boolean netIsConnect) {
        NetIsConnect = netIsConnect;
    }

    public Boolean getNetIsConnect() {
        return NetIsConnect;
    }
}
