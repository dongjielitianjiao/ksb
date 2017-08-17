package com.itboy.dj.examtool.modules.ftpage.hotNews;

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
import com.itboy.dj.examtool.adapter.HotNewsAdapter;
import com.itboy.dj.examtool.adapter.NoticeAdapter;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.RxSubscribe;
import com.itboy.dj.examtool.api.bean.NewsBean;
import com.itboy.dj.examtool.api.bean.Noticebean;
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


public class NewsListFragment extends BaseFragment {


    @BindView(R.id.new_list_rec)
    RecyclerView newListRec;
    private Unbinder unbinder;
    private String type; //未使用这个参数
    private static final String TYPE_KEY = "NewsTypeKey";

    private String parameter;
    private static final String PARAMETER_KEY = "NewsParameterKey";


    //通知类
    private int noticeIndex = 1;
    private int noticePage_count; //总的页数
    private int noticeItem_size = 10;//每页的条数
    private int noticeCurrentCounter = 0; //当前个数

    //报考类
    private int noparaIndex = 1;
    private int noparaPage_count; //总的页数
    private int noparaItem_size = 10;//每页的条数
    private int noparaCurrentCounter = 0; //当前个数

    //政策类
    private int zcwjIndex = 1;
    private int zcwjPage_count; //总的页数
    private int zcwjItem_size = 10;//每页的条数
    private int zcwjCurrentCounter = 0; //当前个数


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getString(TYPE_KEY);
            parameter = getArguments().getString(PARAMETER_KEY);
        }

    }

    public static NewsListFragment newInstance(String newsId, String NewsParameter) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TYPE_KEY, newsId);
        bundle.putString(PARAMETER_KEY, NewsParameter);
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
        return R.layout.fragment_news_list2;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        newListRec.setLayoutManager(new LinearLayoutManager(getActivity()));
        newListRec.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL, R.drawable.recycle_divider));

        //     notice(通知类)  noPara（报考类）   zcwj（政策类）

        final String token = (String) SharedPreferencesUtils.getParam(getActivity(), "Token", "");


        switch (parameter) {
            case "notice":


                RetrofitService.getNews(noticeIndex + "", noticeItem_size + "", "notice", token).subscribe(new RxSubscribe<NewsBean>(getActivity()) {
                    @Override
                    protected void _onNext(NewsBean newsBean) {
                        final List<NewsBean.DataBean> dataBeen = newsBean.getData();
                        final BaseQuickAdapter hotNewsAdapter = new HotNewsAdapter(R.layout.ft_page_hot_news, dataBeen);
                        hotNewsAdapter.openLoadAnimation();
                        newListRec.setAdapter(hotNewsAdapter);






                        newListRec.addOnItemTouchListener(new OnItemClickListener() {
                            @Override
                            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                                intent.putExtra("contentid", dataBeen.get(position).getContentId() + "");
                                startActivity(intent);
                                NewsBean.DataBean dataBean = dataBeen.get(position);
                                dataBean.setViews(dataBean.getViews()+1);
                                hotNewsAdapter.setData(position,dataBean);


                            }
                        });
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                });


                break;
            case "noPara":
                final Map<String, RequestBody> map = new HashMap<String, RequestBody>();
                map.put("pageNumber", RequestBody.create(null, 1 + ""));
                map.put("pageSize", RequestBody.create(null, 4 + ""));
                map.put("recommend", RequestBody.create(null, "true"));
                RetrofitService.getBaokao(map, token).subscribe(new RxSubscribe<Noticebean>(getActivity()) {
                    @Override
                    protected void _onNext(Noticebean newsBean) {
                        final List<Noticebean.DataBean> dataBeen = newsBean.getData();
                        BaseQuickAdapter hotNewsAdapter = new NoticeAdapter(R.layout.ft_page_hot_news, dataBeen);
                        hotNewsAdapter.openLoadAnimation();
                        newListRec.setAdapter(hotNewsAdapter);
                        newListRec.addOnItemTouchListener(new OnItemClickListener() {
                            @Override
                            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                                Intent intent = new Intent(getActivity(), BaoKaoDetailActivity.class);
                                intent.putExtra("noticeid", dataBeen.get(position).getNoticeId() + "");
                                startActivity(intent);
                            }
                        });
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                });

                break;
            case "zcwj":
                RetrofitService.getNews(zcwjIndex + "", zcwjItem_size + "", "zcwj", token).subscribe(new RxSubscribe<NewsBean>(getActivity()) {
                    @Override
                    protected void _onNext(NewsBean newsBean) {
                        final List<NewsBean.DataBean> dataBeen = newsBean.getData();
                        final BaseQuickAdapter hotNewsAdapter = new HotNewsAdapter(R.layout.ft_page_hot_news, dataBeen);
                        hotNewsAdapter.openLoadAnimation();
                        newListRec.setAdapter(hotNewsAdapter);
                        newListRec.addOnItemTouchListener(new OnItemClickListener() {
                            @Override
                            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                                intent.putExtra("contentid", dataBeen.get(position).getContentId() + "");
                                startActivity(intent);
                                NewsBean.DataBean dataBean = dataBeen.get(position);
                                dataBean.setViews(dataBean.getViews()+1);
                                hotNewsAdapter.setData(position,dataBean);

                            }
                        });
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                });

                break;
            default:
                break;


        }


    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }


}
