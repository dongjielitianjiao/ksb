package com.itboy.dj.examtool.modules.ftpage.megagamefragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.gson.JsonObject;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.adapter.MegaGameCommentListAdapter;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.bean.MegagameCommentListBean;
import com.itboy.dj.examtool.modules.base.BaseFragment;
import com.itboy.dj.examtool.utils.RecycleViewDivider;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscriber;

public class MegagameCommentFragment extends BaseFragment {

/*
    @BindView(R.id.push_comment)
    RelativeLayout pushComment; //点击发布
    @BindView(R.id.comment_et)
    EditText commentEt; //书写的内容*/
    @BindView(R.id.comment_rec)
    RecyclerView commentRec; //评论列表
    private Unbinder bind;
    public static final String ARGUMENT_ME_COMMENT = "commentid";
    private String mArgumentId;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if(arguments!=null)
            mArgumentId = arguments.getString(ARGUMENT_ME_COMMENT);
    }

    public static MegagameCommentFragment newInstance(String id) {
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT_ME_COMMENT, id);
        MegagameCommentFragment megagameCommentFragment = new MegagameCommentFragment();
        megagameCommentFragment.setArguments(bundle);
        return megagameCommentFragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        bind = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_megagame_comment;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {

        commentRec.setLayoutManager(new LinearLayoutManager(getActivity()));

        commentRec.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL, R.drawable.recycle_divider));


        getData();

    }

    private void getData() {
        final String token = (String) SharedPreferencesUtils.getParam(getActivity(), "Token", "");
        RetrofitService.getCommentList(mArgumentId,token).subscribe(new Subscriber<MegagameCommentListBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(MegagameCommentListBean megagameCommentListBean) {
                final List<MegagameCommentListBean.DataBean> data = megagameCommentListBean.getData();
                Collections.reverse(data);
                MegaGameCommentListAdapter megaGameCommentListAdapter=new MegaGameCommentListAdapter(R.layout.mega_game_list_item,data);
                megaGameCommentListAdapter.openLoadAnimation();
                View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.foot_push_comment, null);
                RelativeLayout rt = (RelativeLayout) inflate.findViewById(R.id.push_comment);
                final EditText et = (EditText) inflate.findViewById(R.id.comment_et);
                rt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      RetrofitService.pushComment(mArgumentId,et.getText().toString(),token).subscribe(new Subscriber<JsonObject>() {
                          @Override
                          public void onCompleted() {

                          }

                          @Override
                          public void onError(Throwable e) {

                          }

                          @Override
                          public void onNext(JsonObject jsonObject) {
                              getData();
                          }
                      });
                    }
                });
                megaGameCommentListAdapter.addFooterView(inflate);
                commentRec.setAdapter(megaGameCommentListAdapter);
            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }


}
