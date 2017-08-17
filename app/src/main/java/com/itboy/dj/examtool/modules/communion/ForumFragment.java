package com.itboy.dj.examtool.modules.communion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.adapter.RedoForumAdapter;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.bean.ForumListBean;
import com.itboy.dj.examtool.modules.base.BaseFragment;
import com.itboy.dj.examtool.utils.CustomLoadMoreView;
import com.itboy.dj.examtool.utils.RecycleViewDivider;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscriber;


public class ForumFragment extends BaseFragment {


    @BindView(R.id.ro_write_forum)
    TextView roWriteForum;
    @BindView(R.id.push_forum)
    RelativeLayout pushForum;
    @BindView(R.id.forum_rec)
    RecyclerView forumRec;
    private Unbinder unbinder;
    private String mNewsId;
    private static final String FORUM_TYPE_KEY = "NewsTypeKey";


    private int index = 1;
    private int page_count; //总的页数
    private int item_size = 10;//每页的条数
    private int mCurrentCounter = 0; //当前个数

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mNewsId = getArguments().getString(FORUM_TYPE_KEY);
            Log.d("ForumFragment", mNewsId);
        }

    }

    public static ForumFragment newInstance(String newsId) {
        ForumFragment fragment = new ForumFragment();
        Bundle bundle = new Bundle();
        bundle.putString(FORUM_TYPE_KEY, newsId);
        fragment.setArguments(bundle);
        return fragment;
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
        return R.layout.fragment_forum;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {

        roWriteForum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChooseForumTypeActivity.class);
                startActivity(intent);
            }
        });

        forumRec.setLayoutManager(new LinearLayoutManager(getActivity()));
        forumRec.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL, R.drawable.forum_recycle_divider));
        getForumData();
    }


    private void getForumData() {
/*        final Map<String, RequestBody> map = new HashMap<String, RequestBody>();
        map.put("pageNumber", RequestBody.create(null, 1 + ""));
        map.put("pageSize", RequestBody.create(null, 15 + ""));*/
        // map.put("type", RequestBody.create(null, mNewsId));
        final String token = (String) SharedPreferencesUtils.getParam(getActivity(), "Token", "");
        RetrofitService.ForumEveryType(index + "", item_size + "", "", token).subscribe(new Subscriber<ForumListBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ForumListBean forumListBean) {
                final int total = forumListBean.getData().getTotal(); //总的个数
                page_count = total % item_size == 0 ? total / item_size : total / item_size + 1; //拿到总的页数
                final List<ForumListBean.DataBean.RowsBean> rows = forumListBean.getData().getRows();


                //添加类型
                int size = rows.size();
                for (int i = 0; i < size; i++) {
                    List<String> videoUrls = (List<String>) rows.get(i).getVideoUrls();
                    int size1 = videoUrls.size();
                    List<String> imageUrls = (List<String>) rows.get(i).getImageUrls();
                    int size2 = imageUrls.size();
                    if (size1 == 0 && size2 == 0) {
                        rows.get(i).setItemType(ForumListBean.DataBean.RowsBean.TYPE_1);
                    } else if (size1 == 1 && size2 == 0) {
                        rows.get(i).setItemType(ForumListBean.DataBean.RowsBean.TYPE_2);
                    } else if (size1 == 0 && size2 == 1) {
                        rows.get(i).setItemType(ForumListBean.DataBean.RowsBean.TYPE_3);
                    } else if (size1 == 0 && size2 == 2) {
                        rows.get(i).setItemType(ForumListBean.DataBean.RowsBean.TYPE_4);
                    } else if (size1 == 0 && size2 == 3) {
                        rows.get(i).setItemType(ForumListBean.DataBean.RowsBean.TYPE_5);
                    }
                }

                final RedoForumAdapter redoForumAdapter = new RedoForumAdapter(rows);
                redoForumAdapter.setLoadMoreView(new CustomLoadMoreView());
                forumRec.setAdapter(redoForumAdapter);
                mCurrentCounter = redoForumAdapter.getData().size(); //当前个数


                redoForumAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {

                    @Override
                    public void onLoadMoreRequested() {
                        if (redoForumAdapter.getData().size() < item_size) {
                            redoForumAdapter.loadMoreEnd(true);//没有更多数据
                        } else {
                            if (mCurrentCounter >= total) { //加载完成的提示（如果不止一页的话）
                                redoForumAdapter.loadMoreEnd(false);// true is gone,false is visible  // 加载完成
                            } else if (index < page_count) {  //正常的加载更多
                                index += 1;
                                RetrofitService.ForumEveryType(index + "", item_size + "", "", token).subscribe(new Subscriber<ForumListBean>() {
                                    @Override
                                    public void onCompleted() {

                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onNext(ForumListBean forumListBean) {
                                        final List<ForumListBean.DataBean.RowsBean> rows = forumListBean.getData().getRows();
                                        int size = rows.size();
                                        for (int i = 0; i < size; i++) {
                                            List<String> videoUrls = (List<String>)rows.get(i).getVideoUrls();
                                            int size1 = videoUrls.size();
                                            List<String> imageUrls =(List<String>) rows.get(i).getImageUrls();
                                            int size2 = imageUrls.size();
                                            if(size1==0&&size2==0){
                                                rows.get(i).setItemType(ForumListBean.DataBean.RowsBean.TYPE_1);
                                            }else if(size1==1&&size2==0){
                                                rows.get(i).setItemType(ForumListBean.DataBean.RowsBean.TYPE_2);
                                            }else if(size1==0&&size2==1){
                                                rows.get(i).setItemType(ForumListBean.DataBean.RowsBean.TYPE_3);
                                            } else if(size1==0&&size2==2){
                                                rows.get(i).setItemType(ForumListBean.DataBean.RowsBean.TYPE_4);
                                            } else if(size1==0&&size2==3){
                                                rows.get(i).setItemType(ForumListBean.DataBean.RowsBean.TYPE_5);
                                            }
                                        }
                                        redoForumAdapter.addData(rows);
                                        mCurrentCounter = redoForumAdapter.getData().size();
                                        redoForumAdapter.loadMoreComplete();
                                    }
                                });

                            }

                        }


                    }
                }, forumRec);


                forumRec.addOnItemTouchListener(new OnItemClickListener() {
                    @Override
                    public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                        String forumId = rows.get(position).getForumId() + "";
                        Intent intent = new Intent(getActivity(), ForumDetailActivity.class);
                        intent.putExtra("forumId", forumId);
                        startActivity(intent);
                    }
                });


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
