package com.itboy.dj.examtool.playvideo;

/**
 * Created by Administrator on 2017/7/3.
 */

public class PlayNot {
    private int release;
    private int position;

    public PlayNot(int release, int position) {
        this.release = release;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public int getRelease() {
        return release;
    }
}
