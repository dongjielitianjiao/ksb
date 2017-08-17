package com.itboy.dj.examtool.modules.ftpage.NightSchool;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.adapter.StudyUnCommandAdapter;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.RxSubscribe;
import com.itboy.dj.examtool.api.bean.StudyUnCommandBean;
import com.itboy.dj.examtool.modules.base.BaseFragment;
import com.itboy.dj.examtool.playvideo.MediaPlayerListActivity;
import com.itboy.dj.examtool.utils.RecycleViewDivider;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.RequestBody;

/*在线学习*/
public class OnlineStudyFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.online_rec)
    RecyclerView onlineRec;
    private static final int CODE_STUDY = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_online_study;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        onlineRec.setLayoutManager(new LinearLayoutManager(getActivity()));
        onlineRec.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.HORIZONTAL, R.drawable.recycle_divider));

        final Map<String, RequestBody> map = new HashMap<String, RequestBody>();
        map.put("pageNumber", RequestBody.create(null, 1 + ""));
        map.put("pageSize", RequestBody.create(null, 4 + ""));
        final String token = (String) SharedPreferencesUtils.getParam(getActivity(), "Token", "");

        RetrofitService.unCommandCourse(map, token).compose(this.<StudyUnCommandBean>bindToLife()).subscribe(new RxSubscribe<StudyUnCommandBean>(getActivity()) {
            @Override
            protected void _onNext(StudyUnCommandBean studyUnCommandBean) {
                final List<StudyUnCommandBean.DataBean.RowsBean> rows = studyUnCommandBean.getData().getRows();
                StudyUnCommandAdapter studyUnCommandAdapter = new StudyUnCommandAdapter(R.layout.video_list, rows);
                studyUnCommandAdapter.openLoadAnimation();
                onlineRec.setAdapter(studyUnCommandAdapter);
                onlineRec.addOnItemTouchListener(new OnItemClickListener() {
                    @Override
                    public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Bundle bundle = new Bundle();
                        bundle.putString("playUrl", "http://baobab.wdjcdn.com/14564977406580.mp4");
                        bundle.putString("userCourseId", rows.get(position).getUserCourseId() + "");//getUserCourseId()
                        bundle.putString("type", "uncommand");
                        //    startActivityForResult(MediaPlayerActivity.class, bundle, CODE_STUDY);
                        startActivityForResult(MediaPlayerListActivity.class, bundle, CODE_STUDY);
                    }
                });
            }

            @Override
            protected void _onError(String message) {

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
