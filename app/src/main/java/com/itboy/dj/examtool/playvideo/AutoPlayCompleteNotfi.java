package com.itboy.dj.examtool.playvideo;

/**
 * Created by Administrator on 2017/7/5.
 */
/*自动播放完成一段视频或者手动点击播放某一段视频发送通知*/
public class AutoPlayCompleteNotfi {

    private  int postition;

    public AutoPlayCompleteNotfi(int postition) {
        this.postition = postition;
    }

    public int getPostition() {
        return postition;
    }
}
