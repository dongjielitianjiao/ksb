package com.itboy.dj.examtool.playvideo;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.shuyu.gsyvideoplayer.model.GSYVideoModel;
import com.shuyu.gsyvideoplayer.video.ListGSYVideoPlayer;

/**
 * Created by Administrator on 2017/7/4.
 */

public class MyListGSYVideoPlayer extends ListGSYVideoPlayer {

    public MyListGSYVideoPlayer(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public MyListGSYVideoPlayer(Context context) {
        super(context);

    }
    public MyListGSYVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void onAutoCompletion() {

        if(this.mPlayPosition < this.mUriList.size() - 1) {
            ++this.mPlayPosition;
            GSYVideoModel gsyVideoModel = (GSYVideoModel)this.mUriList.get(this.mPlayPosition);
            this.setUp(gsyVideoModel.getUrl(), this.mCache, this.mCachePath, this.mObjects);
            if(!TextUtils.isEmpty(gsyVideoModel.getTitle())) {
                this.mTitleTextView.setText(gsyVideoModel.getTitle());
            }
            this.startPlayLogic();

            mListener.onItemClick(mPlayPosition-1);
        } else {
            super.onAutoCompletion();
            mListener.onItemClick(mPlayPosition);
        }



    }


    //连续自动播放的视频，每播放一个视频 写一个接口回调
    private OnItemNotifListener mListener;

    public  void  setOnItemNotfiListener(OnItemNotifListener listener){
        this.mListener=listener;
    }
    public  interface  OnItemNotifListener{
        void onItemClick(int position);
    }
}

