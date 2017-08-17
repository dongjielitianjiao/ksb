package com.itboy.dj.examtool.modules.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.adapter.CollectAdapter;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.bean.ForumListBean;
import com.itboy.dj.examtool.api.bean.MyCollecBean;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.modules.communion.ForumDetailActivity;
import com.itboy.dj.examtool.utils.CustomLoadMoreView;
import com.itboy.dj.examtool.utils.RecycleViewDivider;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;

/*我的收藏*/
public class MyCollectActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.base_title_name)
    TextView baseTitleName;
    @BindView(R.id.base_img_bg)
    ImageView baseImgBg;
    @BindView(R.id.right_rt)
    RelativeLayout rightRt;
    @BindView(R.id.collect_rec)
    RecyclerView collectRec;
    private int index = 1;
    private int page_count; //总的页数
    private int item_size = 10;//每页的条数
    private int mCurrentCounter = 0; //当前个数

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_my_collect;
    }

    @Override
    protected void initInjector() {

    }


    @Override
    protected void initViews() {
        baseTitleName.setText("我的收藏");
        back.setOnClickListener(this);
        collectRec.setLayoutManager(new LinearLayoutManager(MyCollectActivity.this));
        collectRec.addItemDecoration(new RecycleViewDivider(MyCollectActivity.this, LinearLayoutManager.HORIZONTAL, R.drawable.forum_recycle_divider));
        //  getForumData();


        //接口走通，但是没有数据
        final String token = (String) SharedPreferencesUtils.getParam(MyCollectActivity.this, "Token", "");
/*        final Map<String, RequestBody> map = new HashMap<String, RequestBody>();
        map.put("pageNumber", RequestBody.create(null, 1 + ""));
        map.put("pageSize", RequestBody.create(null, 4 + ""));*/
        RetrofitService.getCollect(index + "", item_size + "", "ext_weiquan_forum", token).subscribe(new Subscriber<MyCollecBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(MyCollecBean myCollecBean) { //ITEM_COLLECT

                final int total = myCollecBean.getData().getTotal();
                ; //总的个数
                page_count = total % item_size == 0 ? total / item_size : total / item_size + 1; //拿到总的页数

                final List<MyCollecBean.DataBean.RowsBean> rows = myCollecBean.getData().getRows();
                int size = rows.size();
                for (int i = 0; i < size; i++) {
                    List<String> videoUrls = (List<String>) rows.get(i).getRelationObj().getVideoUrls();
                    int size1 = videoUrls.size();
                    List<String> imageUrls = (List<String>) rows.get(i).getRelationObj().getImageUrls();
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
                final CollectAdapter collectAdapter = new CollectAdapter(rows);
                collectAdapter.setLoadMoreView(new CustomLoadMoreView());
                collectRec.setAdapter(collectAdapter);
                mCurrentCounter = collectAdapter.getData().size(); //当前个数
                collectAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {

                    @Override
                    public void onLoadMoreRequested() {
                        if (collectAdapter.getData().size() < item_size) {
                            collectAdapter.loadMoreEnd(true);//没有更多数据
                        } else {
                            if (mCurrentCounter >= total) { //加载完成的提示（如果不止一页的话）
                                collectAdapter.loadMoreEnd(false);// true is gone,false is visible  // 加载完成
                            } else if (index < page_count) {  //正常的加载更多
                                index += 1;
                                RetrofitService.getCollect(index + "", item_size + "", "ext_weiquan_forum", token).subscribe(new Subscriber<MyCollecBean>() {
                                    @Override
                                    public void onCompleted() {

                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onNext(MyCollecBean myCollecBean) {
                                        final List<MyCollecBean.DataBean.RowsBean> rows = myCollecBean.getData().getRows();
                                        int size = rows.size();
                                        for (int i = 0; i < size; i++) {
                                            List<String> videoUrls = (List<String>) rows.get(i).getRelationObj().getVideoUrls();
                                            int size1 = videoUrls.size();
                                            List<String> imageUrls = (List<String>) rows.get(i).getRelationObj().getImageUrls();
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
                                        collectAdapter.addData(rows);
                                        mCurrentCounter = collectAdapter.getData().size();
                                        collectAdapter.loadMoreComplete();
                                    }
                                });

                            }

                        }


                    }
                }, collectRec);


                collectRec.addOnItemTouchListener(new OnItemClickListener() {
                    @Override
                    public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                        String forumId = rows.get(position).getRelationObj().getForumId() + "";
                        Intent intent = new Intent(MyCollectActivity.this, ForumDetailActivity.class);
                        intent.putExtra("forumId", forumId);
                        startActivity(intent);
                    }
                });


            }
        });


    }

/*        //填充的假数据
    private void getForumData() {
*//*        final Map<String, RequestBody> map = new HashMap<String, RequestBody>();
        map.put("pageNumber", RequestBody.create(null, 1 + ""));
        map.put("pageSize", RequestBody.create(null, 6 + ""));
        map.put("type", RequestBody.create(null, 150 + ""));
        final String token = (String) SharedPreferencesUtils.getParam(MyCollectActivity.this, "Token", "");
        RetrofitService.ForumEveryType(map, token).subscribe(new Subscriber<ForumListBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.d("MyCollectActivity", "eee");
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
                RedoForumAdapter redoForumAdapter=new RedoForumAdapter(rows);
                collectRec.setAdapter(redoForumAdapter);
                collectRec.addOnItemTouchListener(new OnItemClickListener() {
                    @Override
                    public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                        String forumId = rows.get(position).getForumId() + "";
                        Intent intent = new Intent(MyCollectActivity.this, ForumDetailActivity.class);
                        intent.putExtra("forumId", forumId);
                        startActivity(intent);
                    }
                });*//*







         *//*       final List<ForumListBean.DataBean.RowsBean> rows = forumListBean.getData().getRows();
                ForumListAdapter forumListAdapter = new ForumListAdapter(R.layout.item_forum, rows);
                collectRec.setAdapter(forumListAdapter);
                collectRec.addOnItemTouchListener(new OnItemClickListener() {
                    @Override
                    public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                        String forumId = rows.get(position).getForumId() + "";
                        Intent intent = new Intent(MyCollectActivity.this, ForumDetailActivity.class);
                        intent.putExtra("forumId", forumId);
                        startActivity(intent);
                    }
                });*//*

            }
        });
    }*/

    @Override
    protected void updateViews(boolean isRefresh) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            default:
                break;


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
