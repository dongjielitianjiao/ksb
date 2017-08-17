package com.itboy.dj.examtool.modules.me.cf;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.bean.CfBean;
import com.itboy.dj.examtool.modules.base.BaseFragment;
import com.itboy.dj.examtool.utils.RecycleViewDivider;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscriber;


public class ToApplyCfFragment extends BaseFragment {

    @BindView(R.id.ro_apply_rec)
    RecyclerView roApplyRec;
    private Unbinder unbinder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;


    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_to_apply_cf;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        roApplyRec.setLayoutManager(new LinearLayoutManager(getActivity()));
        roApplyRec.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL, R.drawable.recycle_divider));


        final String token = (String) SharedPreferencesUtils.getParam(getActivity(), "Token", "");
        RetrofitService.getAllCf("waitApply",token).subscribe(new Subscriber<CfBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(CfBean cfBean) {
                Log.d("ToApplyCfFragment", cfBean.toString());

           /*     List<CfBean.DataBean> data = cfBean.getData();
                CfAdapter cfAdapter = new CfAdapter(R.layout.item_cf_layout, data);
                cfRec.setAdapter(cfAdapter);*/

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
