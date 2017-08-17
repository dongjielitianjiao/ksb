package com.itboy.dj.examtool.modules.study;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.adapter.StudyAdapter;
import com.itboy.dj.examtool.adapter.StudyUnCommandAdapter;
import com.itboy.dj.examtool.api.bean.NotifUpData;
import com.itboy.dj.examtool.api.bean.StudyCommandBean;
import com.itboy.dj.examtool.api.bean.StudyUnCommandBean;
import com.itboy.dj.examtool.injector.conponents.DaggerStudyComponent;
import com.itboy.dj.examtool.injector.modules.StudyModule;
import com.itboy.dj.examtool.modules.base.BaseFragment;
import com.itboy.dj.examtool.playvideo.MediaPlayerListActivity;
import com.itboy.dj.examtool.rxbus.RxBus;
import com.itboy.dj.examtool.utils.Lerist;
import com.itboy.dj.examtool.utils.RecycleViewDivider;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.RequestBody;
import rx.Subscriber;


public class StudyFragment extends BaseFragment<StudyPresenter> implements StudyDataView {

    Unbinder unbinder;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.back)
    LinearLayout back;
    private static final int CODE_STUDY = 1;
    private static final int CODE_MEDIA_PLAY = 2;
    @BindView(R.id.layout)
    TextView layout;
    @BindView(R.id.video_unCommand_rec)
    RecyclerView videoUnCommandRec;
    @BindView(R.id.video_command_rec)
    RecyclerView videoCommandRec;

    private BaseQuickAdapter studyAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_study;
    }

    @Override
    protected void initInjector() {
        DaggerStudyComponent
                .builder()
                .applicationComponent(getAppComponent())
                .studyModule(new StudyModule(this))
                .build()
                .inject(this);
    }


    @Override
    protected void initViews() {
        titleName.setText(getString(R.string.ft_study));
        back.setVisibility(View.GONE);
        int sysVersion = Integer.parseInt(Build.VERSION.SDK);
        if (sysVersion > 20) {
            layout.setHeight(Lerist.getStatusBarHeight(getActivity()));
        } else {
            layout.setHeight(0);
        }


      //  videoUnCommandRec.setLayoutManager(new LinearLayoutManager(getActivity()));
        //  videoCommandRec.setLayoutManager(new LinearLayoutManager(getActivity()));

        videoUnCommandRec.setLayoutManager(new LinearLayoutManager(getActivity()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        videoUnCommandRec.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL, R.drawable.recycle_divider));


        videoCommandRec.setLayoutManager(new LinearLayoutManager(getActivity()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });

        videoCommandRec.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL, R.drawable.recycle_divider));

        initRequest();
        RxBus.getDefault().toObservableSticky(NotifUpData.class).compose(this.<NotifUpData>bindToLife()).subscribe(new Subscriber<NotifUpData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NotifUpData notifUpData) {
                initRequest();
            }
        });
    }

    private void initRequest() {
        final String token = (String) SharedPreferencesUtils.getParam(getActivity(), "Token", "");

        //请求未推荐课程
        final Map<String, RequestBody> map = new HashMap<String, RequestBody>();
        map.put("pageNumber", RequestBody.create(null, 1 + ""));
        map.put("pageSize", RequestBody.create(null, 4 + ""));
        mPresenter.videoListUnCommand(map, token);

        //推荐
        final Map<String, RequestBody> map1 = new HashMap<String, RequestBody>();
        map1.put("pageNumber", RequestBody.create(null, 1 + ""));
        map1.put("pageSize", RequestBody.create(null, 4 + ""));
        mPresenter.videoListCommand(map1, token);
    }


    @Override
    protected void updateViews(boolean isRefresh) {

    }

    //非推荐课程

    @Override
    public void loadVideoListUnCom(final List<StudyUnCommandBean.DataBean.RowsBean> studyBeans) {

        StudyUnCommandAdapter studyUnCommandAdapter = new StudyUnCommandAdapter(R.layout.video_list, studyBeans);
        studyUnCommandAdapter.openLoadAnimation();
        videoUnCommandRec.setAdapter(studyUnCommandAdapter);

        videoUnCommandRec.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("playUrl", "http://baobab.wdjcdn.com/14564977406580.mp4");
                bundle.putString("userCourseId", studyBeans.get(position).getUserCourseId() + "");//视频列表就是userCouseId
                bundle.putString("courseId", studyBeans.get(position).getCourseId() + "");//讲师介绍还是用的courseid
                bundle.putString("type", "uncommand");
                //去考试需要用的
                bundle.putString("examId", studyBeans.get(position).getExamId()+"");
                startActivityForResult(MediaPlayerListActivity.class, bundle, CODE_STUDY);
            }
        });
    }

    //推荐课程
    @Override
    public void loadVideoListCom(final List<StudyCommandBean.DataBean> studyBeans) {
        studyAdapter = new StudyAdapter(R.layout.video_list, studyBeans);
        studyAdapter.openLoadAnimation();
        videoCommandRec.setAdapter(studyAdapter);

        videoCommandRec.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("playUrl", "http://baobab.wdjcdn.com/14564977406580.mp4");
                bundle.putString("userCourseId", studyBeans.get(position).getCourseId() + "");//讲师介绍和视频列表都是用的courseID
                bundle.putString("type", "command");
                bundle.putString("examId", studyBeans.get(position).getExamId()+"");
                startActivityForResult(MediaPlayerListActivity.class, bundle, CODE_STUDY);
            }
        });


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
      /*  if (requestCode == CODE_STUDY && resultCode == CODE_MEDIA_PLAY) {
            int lookmuch = data.getIntExtra("lookmuch", 0);
            studyBeanTest.setMomentProgress(lookmuch);
            studyAdapter.setData(btnPostion, studyBeanTest);
        }*/
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
