package com.itboy.dj.examtool.playvideo;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.adapter.MediaBottomAdapter;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.modules.study.CourseContentFragment;
import com.itboy.dj.examtool.modules.study.CourseListFragment;
import com.itboy.dj.examtool.modules.study.TeacherIntroduceFragment;
import com.itboy.dj.examtool.playvideo.listener.SampleListener;
import com.itboy.dj.examtool.utils.CreatBitmap;
import com.itboy.dj.examtool.widget.tabContainer.TabContainerView1;
import com.itboy.dj.examtool.widget.tabContainer.listener.OnTabSelectedListener;
import com.itboy.dj.examtool.widget.tabContainer.widget.Tab;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.GSYVideoPlayer;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
/*这个无自动播放下一视频的功能*/
public class MediaPlayerActivity extends BaseActivity implements CourseListFragment.PlayUrlListener {


    private static final int CODE_MEDIA_PLAY = 2;
    @BindView(R.id.media_bot_table)
    TabContainerView1 mediaBotTable;

    @BindView(R.id.detail_player)
    LandLayoutVideo detailPlayer;
    private String[] itemNames = {"课程目录", "课程内容", "讲师介绍"};

    private boolean isPlay;
    private boolean isPause;

    private OrientationUtils orientationUtils;
    private String Everyurl;
    private String titleName;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_media_player;
    }

    @Override
    protected void initInjector() {

    }

    //视频回调处理
    @Override
    public void onVideoUrl(String videoUrl, String chapterName) {
        //增加封面(拿到视频第一帧)
        final ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        Observable<Bitmap> videoFirst = Observable.just(videoUrl).subscribeOn(Schedulers.io())
                .map(new Func1<String, Bitmap>() {
                    @Override
                    public Bitmap call(String s) {
                        return CreatBitmap.createBitmapFromVideoPath(s, 300, 300);
                    }
                });
        videoFirst.observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Bitmap>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
                detailPlayer.setThumbImageView(imageView);
            }
        });
        detailPlayer.releaseAllVideos();
        Everyurl = videoUrl;
        titleName = chapterName;
        detailPlayer.setUp(Everyurl, false, null, titleName);
        detailPlayer.startPlayLogic();

        //  detailPlayer.setUp("",false,null,titleName);
    }

    @Override
    protected void initViews() {
        GSYVideoManager.instance().setTimeOut(4000, true);
        resolveNormalVideoUI();
        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils(this, detailPlayer);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);
        detailPlayer.setIsTouchWiget(true);
        //detailPlayer.setIsTouchWigetFull(false);
        //关闭自动旋转
        detailPlayer.setRotateViewAuto(false);
        detailPlayer.setLockLand(false);
        detailPlayer.setShowFullAnimation(false);
        detailPlayer.setNeedLockFull(true);
        //detailPlayer.setOpenPreView(false);

        detailPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接横屏
                orientationUtils.resolveByClick();

                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                detailPlayer.startWindowFullscreen(MediaPlayerActivity.this, true, true);
            }
        });


        //设置视频状态回调
        detailPlayer.setStandardVideoAllCallBack(new SampleListener() {
            @Override
            public void onClickStartThumb(String url, Object... objects) {
                super.onClickStartThumb(url, objects);

            }

            @Override
            public void onPrepared(String url, Object... objects) {
                super.onPrepared(url, objects);
                //开始播放了才能旋转和全屏
                orientationUtils.setEnable(true);
                isPlay = true;
            }

            @Override
            public void onAutoComplete(String url, Object... objects) {
                Toast.makeText(context, "objects.length:" + objects.length, Toast.LENGTH_SHORT).show();
                super.onAutoComplete(url, objects);
            }

            @Override
            public void onClickStartError(String url, Object... objects) {
                super.onClickStartError(url, objects);
            }

            @Override
            public void onQuitFullscreen(String url, Object... objects) {
                super.onQuitFullscreen(url, objects);
                if (orientationUtils != null) {
                    orientationUtils.backToProtVideo();
                }
            }
        });


        detailPlayer.setLockClickListener(new LockClickListener() {
            @Override
            public void onClick(View view, boolean lock) {
                if (orientationUtils != null) {
                    //配合下方的onConfigurationChanged
                    orientationUtils.setEnable(!lock);
                }
            }
        });


        //传递数据到三个碎片
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String userCourseId = extras.getString("userCourseId");
        String type = extras.getString("type");
        /*   用这种方式来控制fragment， 解耦*/
        //课程列表

        CourseListFragment courseListFragment = CourseListFragment.newInstance(userCourseId, type);
        //课程内容
        CourseContentFragment courseContentFragment = CourseContentFragment.newInstance(userCourseId, "contentCourse");
        //讲师介绍
        TeacherIntroduceFragment teacherIntroduceFragment = TeacherIntroduceFragment.newInstance(userCourseId, "kecheng");

        mediaBotTable.setAdapter(new MediaBottomAdapter(getSupportFragmentManager(),
                new Fragment[]{courseListFragment, courseContentFragment, teacherIntroduceFragment}, itemNames));

        mediaBotTable.setOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(Tab tab) {

            }
        });
        mediaBotTable.setOffscreenPageLimit(itemNames.length);

    }

    @Override
    protected void updateViews(boolean isRefresh) {


        /*       //获取播放状态
        detailPlayer.getCurrentState();
        //当前播放进度
        detailPlayer.getCurrentPositionWhenPlaying();
        *//*获取总时长*//*
        detailPlayer.getDuration();
        *//*暂停*//*
        detailPlayer.onVideoPause();
        *//*重新播放*//*
        detailPlayer.onVideoResume();*/

    }

    //全屏的时候展示
    private void resolveNormalVideoUI() {
        //增加title
        detailPlayer.getTitleTextView().setVisibility(View.GONE);
        detailPlayer.getTitleTextView().setText("测试视频");

        detailPlayer.getBackButton().setVisibility(View.VISIBLE);
    }


    @Override
    public void onBackPressed() {
        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();
        }
        if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }


    @Override
    protected void onPause() {
        super.onPause();
        isPause = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isPause = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoPlayer.releaseAllVideos();
        //GSYPreViewManager.instance().releaseMediaPlayer();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
        if (isPlay && !isPause) {
            if (newConfig.orientation == ActivityInfo.SCREEN_ORIENTATION_USER) {
                if (!detailPlayer.isIfCurrentIsFullscreen()) {
                    detailPlayer.startWindowFullscreen(MediaPlayerActivity.this, true, true);
                }
            } else {
                //新版本isIfCurrentIsFullscreen的标志位内部提前设置了，所以不会和手动点击冲突
                if (detailPlayer.isIfCurrentIsFullscreen()) {
                    StandardGSYVideoPlayer.backFromWindowFull(this);
                }
                if (orientationUtils != null) {
                    orientationUtils.setEnable(true);
                }
            }
        }
    }


}
