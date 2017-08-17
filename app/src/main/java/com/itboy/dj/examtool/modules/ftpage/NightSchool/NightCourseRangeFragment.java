package com.itboy.dj.examtool.modules.ftpage.NightSchool;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.adapter.NightSchoolCouseRangeListAdapter;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.bean.NightSchoolCourseRangeList;
import com.itboy.dj.examtool.modules.base.BaseFragment;
import com.itboy.dj.examtool.utils.RecycleViewDivider;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscriber;


public class NightCourseRangeFragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.rec_night_course_list)
    RecyclerView recNightCourseList;
    /*为了解耦，传参数用这种方式传*/
    private String mArgument;
    public static final String ARGUMENT = "argument";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null)
            mArgument = bundle.getString(ARGUMENT);
        Log.d("NightCourseRangeFragmen", mArgument);

    }

    public static NightCourseRangeFragment newInstance(String argument) {
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT, argument);
        NightCourseRangeFragment nightCourseRangeFragment = new NightCourseRangeFragment();
        nightCourseRangeFragment.setArguments(bundle);
        return nightCourseRangeFragment;
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
        return R.layout.fragment_night_course_range;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        recNightCourseList.setLayoutManager(new LinearLayoutManager(getActivity()));
        recNightCourseList.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL, R.drawable.recycle_divider));
        //  Log.d("NightCourseRangeFragmen", mArgument);
        final String token = (String) SharedPreferencesUtils.getParam(getActivity(), "Token", "");
        RetrofitService.NightSchoolCourseRangeList(mArgument, token).subscribe(new Subscriber<NightSchoolCourseRangeList>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NightSchoolCourseRangeList nightSchoolCourseRangeList) {
                List<NightSchoolCourseRangeList.DataBean> data = nightSchoolCourseRangeList.getData();
                NightSchoolCouseRangeListAdapter nightSchoolCouseRangeListAdapter = new NightSchoolCouseRangeListAdapter(R.layout.item_night_course_range, data);
                nightSchoolCouseRangeListAdapter.openLoadAnimation();
                recNightCourseList.setAdapter(nightSchoolCouseRangeListAdapter);
            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


}
