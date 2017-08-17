package com.itboy.dj.examtool.modules.study;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.JsonObject;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.adapter.CourseListAdapter;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.bean.CourseListBean;
import com.itboy.dj.examtool.modules.base.BaseFragment;
import com.itboy.dj.examtool.playvideo.AutoPlayCompleteNotfi;
import com.itboy.dj.examtool.playvideo.PlayNot;
import com.itboy.dj.examtool.rxbus.RxBus;
import com.itboy.dj.examtool.utils.RecycleViewDivider;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.RequestBody;
import rx.Subscriber;


public class CourseListFragment extends BaseFragment {


    @BindView(R.id.rec_course_list)
    RecyclerView recCourseList;
    Unbinder unbinder;
    /*为了解耦，传参数用这种方式传*/
    private String mArgument;
    private String type;
    public static final String ARGUMENT = "argument";
    public static final String ARGUMENT_TYPE = "mType";
    private List<CourseListBean.DataBean.ChaptersBean> chapters;
    private CourseListBean courseListBeen;
    private String courseId;
    private CourseListAdapter courseAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null)
            mArgument = bundle.getString(ARGUMENT);
        type = bundle.getString(ARGUMENT_TYPE);

    }


    /**
     * 传入需要的参数，设置给arguments
     *
     * @param argument
     * @return
     */
    public static CourseListFragment newInstance(String argument, String type) {
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT, argument);
        bundle.putString(ARGUMENT_TYPE, type);
        CourseListFragment contentFragment = new CourseListFragment();
        contentFragment.setArguments(bundle);
        return contentFragment;
    }

    //碎片向宿主activity传值(没用)
    public interface PlayUrlListener {
        void onVideoUrl(String videoUrl, String videoCourseName);
    }

    //传个集合过去
    public interface PlayUrlListListener {
        //1.chaptersBeen包含视频列表数据的集合   2.currentPlaying 退出全屏时的播放进度 3.播放位置，4 类型（是我的课程还是推荐课程）
        // void onVideoListUrl(List<CourseListBean.DataBean.ChaptersBean> chaptersBeen, int currentPlaying, int position);
        void onVideoListUrl(CourseListBean courseListBeen, int currentPlaying, int position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_course_list;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        recCourseList.setLayoutManager(new LinearLayoutManager(getActivity()));
        recCourseList.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL, R.drawable.recycle_divider));
        final String token = (String) SharedPreferencesUtils.getParam(getActivity(), "Token", "");
        final Map<String, RequestBody> map = new HashMap<String, RequestBody>();

        getPlayList(token, map, type);


        //退出全屏的时候拿到播放进度和位置回传到MediaPlayerListActivity
        RxBus.getDefault().toObservable(PlayNot.class).subscribe(new Subscriber<PlayNot>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(PlayNot playNot) {
                //当前播放进度
                int release = playNot.getRelease();
                //当前播放位置
                int position = playNot.getPosition();
                //回调
                PlayUrlListListener playUrlListListener = (PlayUrlListListener) getActivity();
                //   playUrlListListener.onVideoListUrl(chapters, release, position);
                playUrlListListener.onVideoListUrl(courseListBeen, release, position);
            }
        });


    }

    private void getPlayList(final String token, final Map<String, RequestBody> map, String type) {
        if ("command".equals(type)) {
            RetrofitService.getCouresList(mArgument, token).subscribe(new Subscriber<CourseListBean>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                }

                @Override
                public void onNext(CourseListBean courseListBean) {
                    courseListBeen = courseListBean;
                    chapters = courseListBean.getData().getChapters();
                    Collections.reverse(chapters);
                    //把集合回传给MediaPlayerListActivity
                    final PlayUrlListListener playUrlListListener = (PlayUrlListListener) getActivity();

                    courseAdapter = new CourseListAdapter(R.layout.item_course_list_item, chapters);
                    recCourseList.setAdapter(courseAdapter);


                    int size = chapters.size();
                    for (int i = 0; i < size; i++) {
                        CourseListBean.DataBean.ChaptersBean chaptersBean = chapters.get(i);
                        boolean isPassed = chaptersBean.isIsPassed();

                        if (!isPassed) {
                            //没有通过则拿到当前学习时间
                            String studyVideoTime = (String) chaptersBean.getStudyVideoTime();
                            if (!"null".equals(studyVideoTime)) {
                                long l = Long.parseLong(studyVideoTime);
                                int currenting = (int) (l * 1000);//强转为整形
                                playUrlListListener.onVideoListUrl(courseListBeen, currenting, i);
                            } else {
                                playUrlListListener.onVideoListUrl(courseListBeen, 0, i);
                            }

                            courseAdapter.setCheckItem(i);
                            break;
                        }
                    }
                    //点击章节 视频回调播放
                    recCourseList.addOnItemTouchListener(new OnItemClickListener() {
                        @Override
                        public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                            playUrlListListener.onVideoListUrl(courseListBeen, 0, position);
                            courseAdapter.setCheckItem(position);

                        }
                    });

                    //接收MediaPlayerListActivity传过来的播放位置，改变item的背景
                    getAutoPlayPosition();


                }
            });
        } else if ("uncommand".equals(type)) {
            RetrofitService.getUnCommandCouresList(mArgument, token).subscribe(new Subscriber<CourseListBean>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                }

                @Override
                public void onNext(CourseListBean courseListBean) {

                    courseListBeen = courseListBean;
                    chapters = courseListBean.getData().getChapters();
                    //     Collections.reverse(chapters);
                    //把集合回传给MediaPlayerListActivity
                    final PlayUrlListListener playUrlListListener = (PlayUrlListListener) getActivity();


                    //   playUrlListListener.onVideoListUrl(chapters, 0, 0);
                    //展示界面
                    courseAdapter = new CourseListAdapter(R.layout.item_course_list_item, chapters);
                    recCourseList.setAdapter(courseAdapter);
                   int size = chapters.size();
                    for (int i = 0; i < size; i++) {
                        CourseListBean.DataBean.ChaptersBean chaptersBean = chapters.get(i);
                        boolean isPassed = chaptersBean.isIsPassed();
                        if (!isPassed) {
                            //没有通过则拿到当前学习时间
                            Object studyVideoTime = chaptersBean.getStudyVideoTime();
                            String current = studyVideoTime.toString();

                            if(!"null".equals(current)){
                                double num;
                                java.text.DecimalFormat myformat=new java.text.DecimalFormat("#0.000");
                                num=Double.parseDouble(current);//装换为double类型
                                num=Double.parseDouble(myformat.format(num));//保留2为小数
                                int currentTime = (int) (num * 1000);
                                playUrlListListener.onVideoListUrl(courseListBeen, currentTime, i);
                            }else {
                                playUrlListListener.onVideoListUrl(courseListBeen, 0, i);
                            }





                            courseAdapter.setCheckItem(i);
                            break;
                        }
                    }

                    //  courseAdapter.setCheckItem(0);
                    //点击章节 视频回调播放
                    recCourseList.addOnItemTouchListener(new OnItemClickListener() {
                        @Override
                        public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                            playUrlListListener.onVideoListUrl(courseListBeen, 0, position);
                            courseAdapter.setCheckItem(position);
                        }
                    });


                    //接收MediaPlayerListActivity传过来的播放位置，改变item的背景
                    getAutoPlayPosition();


                }
            });

        }


    }

    //自动播放改变背景
    private void getAutoPlayPosition() {
        RxBus.getDefault().toObservable(AutoPlayCompleteNotfi.class).subscribe(new Subscriber<AutoPlayCompleteNotfi>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(AutoPlayCompleteNotfi autoPlayCompleteNotfi) {
                int postition = autoPlayCompleteNotfi.getPostition();
                CourseListBean.DataBean.ChaptersBean chaptersBean = chapters.get(postition - 1);
                chaptersBean.setIsPassed(true);
                courseAdapter.setData(postition - 1, chaptersBean);

                int itemCount = courseAdapter.getItemCount();
                if (postition != 0 && postition < itemCount) {
                    courseAdapter.setCheckItem(postition);
                }
            }
        });
    }


    //播放完成请求播放完成接口(这个界面无用)
    private void requestCompleted(CourseListBean courseListBean, final Map<String, RequestBody> map, final String token) {
        final String courseId = courseListBean.getData().getCourseId() + "";
        recCourseList.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                map.put("courseId", RequestBody.create(null, courseId));
                map.put("chapterId", RequestBody.create(null, chapters.get(position).getChapterId() + ""));
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
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
