package com.itboy.dj.examtool.playvideo;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.JsonObject;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.adapter.MediaBottomAdapter;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.bean.CourseListBean;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.modules.ftpage.exam.StartExamMsgActivity;
import com.itboy.dj.examtool.modules.study.CourseContentFragment;
import com.itboy.dj.examtool.modules.study.CourseListFragment;
import com.itboy.dj.examtool.modules.study.TeacherIntroduceFragment;
import com.itboy.dj.examtool.playvideo.listener.SampleListener;
import com.itboy.dj.examtool.rxbus.RxBus;
import com.itboy.dj.examtool.utils.CreatBitmap;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;
import com.itboy.dj.examtool.widget.tabContainer.TabContainerView1;
import com.itboy.dj.examtool.widget.tabContainer.listener.OnTabSelectedListener;
import com.itboy.dj.examtool.widget.tabContainer.widget.Tab;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.GSYVideoPlayer;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.model.GSYVideoModel;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/*/**
 * 暂停状态

public void onVideoPause()
/**
 * 恢复暂停状态

public void onVideoResume()

此外，GSYVideoManager也有对应的静态方法可以使用。
/**
 * 暂停播放
public static void onPause()
/**
 * 恢复播放
public static void onResume()
     */
/*https://github.com/CarGuo/GSYVideoPlayer*/
public class MediaPlayerListActivity extends BaseActivity implements CourseListFragment.PlayUrlListListener {

    @BindView(R.id.to_do_exam)
    Button toDoExam;
    @BindView(R.id.detail_player)
    MyListGSYVideoPlayer detailPlayer;
    @BindView(R.id.media_bot_table)
    TabContainerView1 mediaBotTable;
    private boolean isPlay;
    private boolean isPause;
    private String[] itemNames = {"课程目录", "课程内容", "讲师介绍"};
    private OrientationUtils orientationUtils;
    private int mPosition;
    private String type;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private String token;

    /*记录当前播放位置（无论是自动播放还是)*/

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_media_player_list;
    }

    @Override
    protected void initInjector() {

    }

    private List<CourseListBean.DataBean.ChaptersBean> chaptersBeanList = new ArrayList<>();
    private CourseListBean courseBean;



    @Override
    protected void initViews() {


        token = (String) SharedPreferencesUtils.getParam(MediaPlayerListActivity.this, "Token", "");
        //自动播放的逻辑处理
        detailPlayer.setOnItemNotfiListener(new MyListGSYVideoPlayer.OnItemNotifListener() {
            @Override
            public void onItemClick(int position) {


                /*1.拿到的position是当前已经播放完成的item position
                * 每学习完成 都需要请求学习完成的接口*/
                final Map<String, RequestBody> map = new HashMap<String, RequestBody>();
                requestCompleted(courseBean, map, token, position);

                //自动播放完成也要请求记录记录
                String url = chaptersBeanList.get(position).getResourceURL();
                final Map<String, RequestBody> map1 = new HashMap<String, RequestBody>();
                upRecordTime(courseBean, map1, url, token, position,"auto");


                //继续播放的逻辑处理
                int positionNext = position + 1;
                RxBus.getDefault().post(new AutoPlayCompleteNotfi(positionNext));
                int size = chaptersBeanList.size();
                for (int i = positionNext; i < size; i++) {
                    CourseListBean.DataBean.ChaptersBean chaptersBean = chaptersBeanList.get(i);
                    boolean isPassed = chaptersBean.isIsPassed();
                    if (!isPassed) {
                        //没有通过则拿到当前学习地址
                        String resourceURL = chaptersBean.getResourceURL();
                        detailPlayer.setUp(resourceURL, true, i, "");
                        detailPlayer.startPlayLogic();
                        RxBus.getDefault().post(new AutoPlayCompleteNotfi(i));

                        //拿到当前位置（手动点击播放）
                        mPosition = i;
                        break;
                    } else {
                        //  detailPlayer.onAutoCompletion();
                        detailPlayer.onCompletion();
                    }

                }
            }


        });


        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils(this, detailPlayer);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);

        detailPlayer.setIsTouchWiget(true);
        //关闭自动旋转
        detailPlayer.setRotateViewAuto(false);
        detailPlayer.setLockLand(false);
        detailPlayer.setShowFullAnimation(false);
        detailPlayer.setNeedLockFull(true);

        detailPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接横屏
                orientationUtils.resolveByClick();

                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                detailPlayer.startWindowFullscreen(MediaPlayerListActivity.this, true, true);
            }
        });


        detailPlayer.setStandardVideoAllCallBack(new SampleListener() {
            @Override
            public void onPrepared(String url, Object... objects) {
                super.onPrepared(url, objects);
                //开始播放了才能旋转和全屏
                orientationUtils.setEnable(true);
                isPlay = true;

            }


            /*需要发送当前播放时间*/
            @Override
            public void onClickStop(String url, Object... objects) {
                super.onClickStop(url, objects);

                String url1 = chaptersBeanList.get(mPosition).getResourceURL();
                final Map<String, RequestBody> map1 = new HashMap<String, RequestBody>();
                upRecordTime(courseBean, map1, url1, token, mPosition,"stop");
            }

            /*需要发送当前播放时间*/
            @Override
            public void onClickStopFullscreen(String url, Object... objects) {
                super.onClickStopFullscreen(url, objects);


                String url1 = chaptersBeanList.get(mPosition).getResourceURL();
                final Map<String, RequestBody> map1 = new HashMap<String, RequestBody>();
                upRecordTime(courseBean, map1, url1, token, mPosition,"stop");

            }

            @Override
            public void onClickResume(String url, Object... objects) {
                super.onClickResume(url, objects);

            }

            @Override
            public void onClickResumeFullscreen(String url, Object... objects) {
                super.onClickResumeFullscreen(url, objects);

            }

            @Override
            public void onAutoComplete(String url, Object... objects) {
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
                    //退出全屏的时候按到当前进度
                    int currentPositionWhenPlaying = detailPlayer.getCurrentPositionWhenPlaying();

                    //把当前播放进度和位置传到CourseListFragment
                    RxBus.getDefault().postSticky(new PlayNot(currentPositionWhenPlaying, mPosition));

                }
            }
        });


        //锁屏
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
        type = extras.getString("type");  //是我的课程还是推荐课程

        String teacherCourseId = null;
        if ("uncommand".equals(type)) {
            teacherCourseId = extras.getString("courseId");
        } else {
            teacherCourseId = extras.getString("userCourseId");
        }


        //去考试
        final String examId = extras.getString("examId");
        toDoExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaPlayerListActivity.this, StartExamMsgActivity.class);
                intent.putExtra("examId", examId);
                startActivity(intent);
                Log.d("MediaPlayerListActivity", "mPosition:" + mPosition);
            }
        });





        /* 用这种方式来控制fragment， 解耦*/
        //课程列表
        CourseListFragment courseListFragment = CourseListFragment.newInstance(userCourseId, type);
        //课程内容
        CourseContentFragment courseContentFragment = CourseContentFragment.newInstance(userCourseId, "contentCourse");
        //讲师介绍
        TeacherIntroduceFragment teacherIntroduceFragment = TeacherIntroduceFragment.newInstance(teacherCourseId, "kecheng");

        mediaBotTable.setAdapter(new MediaBottomAdapter(getSupportFragmentManager(),
                new Fragment[]{courseListFragment, courseContentFragment, teacherIntroduceFragment}, itemNames));

        mediaBotTable.setOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(Tab tab) {

            }
        });
        mediaBotTable.setOffscreenPageLimit(itemNames.length);

    }


    //回传所有
    /*List<CourseListBean.DataBean.ChaptersBean> chaptersBeen*/
    @Override
    public void onVideoListUrl(CourseListBean courseListBean, int currentPlaying, int postition) {
/*        //先清空数据再做数据处理
        courseBean=null;
        chaptersBeanList.clear();*/


        //拿到当前位置（手动点击播放）
        mPosition = postition;

        /*业务逻辑处理*/
        courseBean = courseListBean;
        //记录当前播放的是第几个位置视频
        chaptersBeanList = courseListBean.getData().getChapters();
        /*    mPosition = postition;
        //发送当前位置给CourseListFragment
        RxBus.getDefault().postSticky(new AutoPlayCompleteNotfi(mPosition));*/
        //退出全屏的时候拿到进度然后在这设置（解决退出全屏播放地址与实际不符合的问题）
        Log.d("MediaPlayerListActivity", "currentPlaying:" + currentPlaying);
        detailPlayer.setSeekOnStart(currentPlaying);


        //给播放列表填充数据
        List<GSYVideoModel> urls = new ArrayList<>();
        int size = chaptersBeanList.size();
        for (int i = 0; i < size; i++) {
            urls.add(new GSYVideoModel(chaptersBeanList.get(i).getResourceURL(), chaptersBeanList.get(i).getChapterName()));
        }
        //拿到当前播放的地址
        String url = urls.get(postition).getUrl();
        //增加封面(拿到视频第一帧)
        final ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Observable<Bitmap> videoFirst = Observable.just(url).subscribeOn(Schedulers.io())
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

        detailPlayer.setUp(urls, true, postition, "");
        detailPlayer.startPlayLogic();
        resolveNormalVideoUI();
    }

    //播放完成请求播放完成接口
    private void requestCompleted(CourseListBean courseListBean, final Map<String, RequestBody> map, final String token, int position) {
        String courseId = null;
        if ("command".equals(type)) {
            courseId = courseListBean.getData().getCourseId() + ""; //推荐课程用的courseid
        } else if ("uncommand".equals(type)) {
            courseId = courseListBean.getData().getUserCourseId() + ""; //我的课程课程用的courseid
        }
        int chapterId = courseListBean.getData().getChapters().get(position).getChapterId();

        map.put("courseId", RequestBody.create(null, courseId));
        map.put("chapterId", RequestBody.create(null, chapterId + ""));
        RetrofitService.studyFinish(map, token).subscribe(new Subscriber<JsonObject>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(JsonObject jsonObject) {

            }
        });
    }

    //记录过程(当前视频播放完成,暂停，退出都要请求这个记录过程上传时间)
    /*
    *1.返回数据
    * 2.上传map
    * 3.当前播放url
    * 4.登录时获取的token
    * 5.当前位置
    * 6 是拿到整个视频的长度还是只拿当前播放的长度（auto：整个视频的长度 btn：当前已播放的长度）
    * */
    private void upRecordTime(CourseListBean courseListBean, final Map<String, RequestBody> map, String url, final String token, int position, final String autoOrbtnType) {
        String courseId = null;
        if ("command".equals(type)) {
            courseId = courseListBean.getData().getCourseId() + ""; //推荐课程用的courseid
        } else if ("uncommand".equals(type)) {
            courseId = courseListBean.getData().getUserCourseId() + ""; //我的课程课程用的courseid
        }

        final String finalCourseId = courseId;
        final int chapterId = courseListBean.getData().getChapters().get(position).getChapterId();

        //根据视频地址拿到视频长度
        Observable<Integer> alltime = Observable.just(url).subscribeOn(Schedulers.io())
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        int duration=0;
                        if("auto".equals(autoOrbtnType)){//自动播放完成拿到整段视频完整的时间
                            MediaPlayer mediaPlaer = new MediaPlayer();
                            try {
                                mediaPlaer.setDataSource(s);
                                mediaPlaer.prepare();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            duration = mediaPlaer.getDuration();
                        }else {
                            duration=  detailPlayer.getCurrentPositionWhenPlaying(); //暂停或者退出拿到当前播放时间

                        }

                        return duration;
                    }
                });



        alltime.map(new Func1<Integer, Long>() {
            @Override
            public Long call(Integer integer) {
                return Long.valueOf(integer / 1000);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long l) {

                map.put("courseId", RequestBody.create(null, finalCourseId));
                map.put("chapterId", RequestBody.create(null, chapterId + ""));
                map.put("chapterId", RequestBody.create(null, l + ""));
                RetrofitService.studyProcess(map, token).subscribe(new Subscriber<JsonObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {


                    }
                });


            }
        });
    }


    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void onBackPressed() {
        MyListGSYVideoPlayer.releaseAllVideos();
        GSYVideoManager.clearAllDefaultCache(context);
        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();

        }
        //退出全屏
        if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }

    /*需要发送当前播放时间*/
    @Override
    protected void onPause() {
        super.onPause();
        isPause = true;
        detailPlayer.onVideoPause();
        try  {
            String url = chaptersBeanList.get(mPosition).getResourceURL() ;
            final Map<String, RequestBody> map1 = new HashMap<String, RequestBody>();
            upRecordTime(courseBean, map1, url, token, mPosition,"stop");
        }catch (IndexOutOfBoundsException e){

        }


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
                    detailPlayer.startWindowFullscreen(MediaPlayerListActivity.this, true, true);
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

    /*该方法要放在视频渲染成功后面 要不设置会被覆盖*/
    private void resolveNormalVideoUI() {
        //增加title
        detailPlayer.getTitleTextView().setVisibility(View.GONE);
        detailPlayer.getBackButton().setVisibility(View.VISIBLE);
        detailPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
