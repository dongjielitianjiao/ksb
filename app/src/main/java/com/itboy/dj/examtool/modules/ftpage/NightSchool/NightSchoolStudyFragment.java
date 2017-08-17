package com.itboy.dj.examtool.modules.ftpage.NightSchool;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.adapter.NightSchoolStudyListAdapter;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.RxSubscribe;
import com.itboy.dj.examtool.api.bean.NightSchoolBean;
import com.itboy.dj.examtool.modules.base.BaseFragment;
import com.itboy.dj.examtool.utils.RecycleViewDivider;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.RequestBody;

/*夜校学习列表*/
public class NightSchoolStudyFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.night_rec)
    RecyclerView nightRec;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_night_school_study;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        nightRec.setLayoutManager(new LinearLayoutManager(getActivity()));
        nightRec.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.HORIZONTAL, R.drawable.recycle_divider3));
        final Map<String, RequestBody> map = new HashMap<String, RequestBody>();
        map.put("pageNumber", RequestBody.create(null, 1 + ""));
        map.put("pageSize", RequestBody.create(null, 4 + ""));
        final String token = (String) SharedPreferencesUtils.getParam(getActivity(), "Token", "");

        RetrofitService.nightSchoolList(map,token).subscribe(new RxSubscribe<NightSchoolBean>(getActivity()) {
            @Override
            protected void _onNext(NightSchoolBean nightSchoolBean) {
                final List<NightSchoolBean.DataBean.RowsBean> rows = nightSchoolBean.getData().getRows();
                NightSchoolStudyListAdapter nightSchoolStudyListAdapter = new NightSchoolStudyListAdapter(R.layout.item_night_school, rows);
                nightSchoolStudyListAdapter.openLoadAnimation();
                nightRec.setAdapter(nightSchoolStudyListAdapter);
                nightRec.addOnItemTouchListener(new OnItemClickListener() {
                    @Override
                    public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent intent = new Intent(getActivity(), NightSchoolDetailActivity.class);
                        intent.putExtra("courseId",rows.get(position).getCourseId()+"");
                        startActivity(intent);

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
