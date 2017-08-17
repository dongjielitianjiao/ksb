package com.itboy.dj.examtool.modules.ftpage;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.adapter.EightBtnsAdapter;
import com.itboy.dj.examtool.adapter.NoticeAdapter;
import com.itboy.dj.examtool.api.bean.BannerItem;
import com.itboy.dj.examtool.api.bean.LocalImgTitle;
import com.itboy.dj.examtool.api.bean.NewsBean;
import com.itboy.dj.examtool.api.bean.Noticebean;
import com.itboy.dj.examtool.api.bean.NotifUpData;
import com.itboy.dj.examtool.injector.conponents.DaggerFtPageComponent;
import com.itboy.dj.examtool.injector.modules.FtModule;
import com.itboy.dj.examtool.modules.base.BaseFragment;
import com.itboy.dj.examtool.modules.ftpage.hotNews.BaoKaoDetailActivity;
import com.itboy.dj.examtool.modules.ftpage.hotNews.HotNewsActivity;
import com.itboy.dj.examtool.modules.login.LoginActivity;
import com.itboy.dj.examtool.rxbus.RxBus;
import com.itboy.dj.examtool.utils.Lerist;
import com.itboy.dj.examtool.utils.RecycleViewDivider;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;
import com.itboy.dj.examtool.utils.Utils;
import com.itboy.dj.examtool.widget.LToast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.RequestBody;
import rx.Subscriber;

/*首页*/
public class FtPageFragment extends BaseFragment<FtPagePresenter> implements FtPageView {
    @BindView(R.id.location_show_tx)
    TextView locationShowTx; //展示定位地址
    @BindView(R.id.choose_address)
    RelativeLayout chooseAddress; //手动选择地址
    @BindView(R.id.message)
    RelativeLayout message; //消息按钮
    @BindView(R.id.layout)
    TextView layout;
    @BindView(R.id.convenient_Banner)
    ConvenientBanner convenientBanner;
    @BindView(R.id.recycle_btn_item)
    RecyclerView recycleBtnItem;
    @BindView(R.id.recycle_hot_new)
    RecyclerView recycleHotNew;
    Unbinder unbinder;
    //定位
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = new AMapLocationClientOption();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }


    private void initLocation() {
        //初始化client
        locationClient = new AMapLocationClient(this.getActivity());
        //设置定位参数
        locationClient.setLocationOption(getDefaultOption());
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
    }

    //定位回调监听
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation loc) {
            if (null != loc) {
                //解析定位结果
                String locationStr = Utils.getCity(loc);

                if ("error".equals(locationStr + "")) {
                    LToast.show(getActivity(), "定位失败，请检查网络连接");
                } else {
                    locationShowTx.setText(locationStr);
                    //保存省市信息,个人中心需要展示
                    String proAndCity = Utils.getProAndCity(loc);
                    SharedPreferencesUtils.setParam(context, "proAndCity", proAndCity + "");
                }
            } else {
                locationShowTx.setText("定位失败");
            }
        }
    };

    //设置定位参数
    private AMapLocationClientOption getDefaultOption() {
        if (null == locationOption) {
            locationOption = new AMapLocationClientOption();
        }
        /*
        * 1.低功耗:Battery_Saving
        * 2.仅设备Device_Sensors
        * 3.高精度Hight_Accuracy*/
        //   AMapLocationClientOption mOption = new AMapLocationClientOption();
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        locationOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        locationOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        locationOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        locationOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        locationOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false
        locationOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        locationOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        locationOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        locationOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        return locationOption;
    }


    /**
     * 开始定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void startLocation() {
        //根据控件的选择，重新设置定位参数
        //  resetOption();
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }


    /**
     * 停止定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void stopLocation() {
        // 停止定位
        locationClient.stopLocation();
    }




    /*
     * 销毁定位
     */
    private void destroyLocation() {
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        destroyLocation();
    }




    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_blank;
    }

    @Override
    protected void initInjector() {
        DaggerFtPageComponent.builder()
                .applicationComponent(getAppComponent())
                .ftModule(new FtModule(this))
                .build()
                .inject(this);

    }

    @Override
    protected void initViews() {
        int sysVersion = Integer.parseInt(Build.VERSION.SDK);
        if (sysVersion > 20) {
            layout.setHeight(Lerist.getStatusBarHeight(getActivity()));
        } else {
            layout.setHeight(0);
        }

        //初始化定位
        initLocation();

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   startActivity(new Intent(getActivity(), MyMsgActivity.class));
                //   startActivity(new Intent(getActivity(), ExamResultNoPassActivity.class));
                //    startActivity(new Intent(getActivity(), ErrorReviewActivity.class));
                startActivity(new Intent(getActivity(), LoginActivity.class));
                // startActivity(new Intent(getActivity(),ExamActivity.class));


            }
        });

        //8个点击事件
        recycleBtnItem.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        recycleBtnItem.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL, R.drawable.recycle_divider));
        mPresenter.LocalItem();
        //热门资讯

        recycleHotNew.setLayoutManager(new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
      //    recycleHotNew.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleHotNew.setNestedScrollingEnabled(false);
        recycleHotNew.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL, R.drawable.recycle_divider));


        chooseAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CityPkActivity.class));
            }
        });

        initRequestData();

        //登录后从新请求
        RxBus.getDefault().toObservableSticky(NotifUpData.class).compose(this.<NotifUpData>bindToLife()).subscribe(new Subscriber<NotifUpData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NotifUpData notifUpData) {
                initRequestData();
            }
        });

    }

    //初始化数据
    private void initRequestData() {
        final String token = (String) SharedPreferencesUtils.getParam(getActivity(), "Token", "");


        //首页数据(banner)
        final Map<String, RequestBody> map = new HashMap<String, RequestBody>();
        map.put("pageNumber", RequestBody.create(null, 1 + ""));
        map.put("pageSize", RequestBody.create(null, 4 + ""));
        //banner请求数据
        mPresenter.bannerGetNews(map, "banner", token);



        //首页热门资讯
        final Map<String, RequestBody> map1 = new HashMap<String, RequestBody>();
        map1.put("pageNumber", RequestBody.create(null, 1 + ""));
        map1.put("pageSize", RequestBody.create(null, 4 + ""));
        map1.put("recommend", RequestBody.create(null, "true"));
        //热门资讯
        mPresenter.hotGetNews(map1, "", token);
    }


    @Override
    protected void updateViews(boolean isRefresh) {
        //   mPresenter.getData(true);

        //开始定位
        startLocation();


    }

    //轮播banner
    @Override
    public void loadBanner(NewsBean strbanner) {

        String result = strbanner.getResult();
        //  Log.d("FtPageFragment", strbanner.toString());
        switch (result) {
            case "ok":
                final List<BannerItem> bannerItems = new ArrayList<>();
                List<NewsBean.DataBean> data = strbanner.getData();
                BannerItem bannerItem = null;
                for (int i = 0; i < data.size(); i++) {
                    bannerItem = new BannerItem();
                    bannerItem.setTitle(data.get(i).getTitle());
                    bannerItem.setImage(data.get(i).getTitleImg());
                    bannerItem.setId(data.get(i).getContentId() + "");
                    bannerItems.add(bannerItem);
                }
                convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
                    @Override
                    public NetworkImageHolderView createHolder() {
                        return new NetworkImageHolderView();
                    }
                }, bannerItems);

                convenientBanner.setPointViewVisible(true)
                        .setPageIndicator(new int[]{R.drawable.dot_unselected, R.drawable.dot_selected}) //选择与未选中是小点的显示状态
                        .startTurning(3000)
                        .setManualPageable(true); //能否手动
                convenientBanner.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL); //设置小点显示位置
                ;
                convenientBanner.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                        String id = bannerItems.get(position).getId();
                        String imageUrl = bannerItems.get(position).getImage();
                        Intent intent = new Intent(getActivity(), MegagameActivity.class);
                        intent.putExtra("contentid", id);
                        intent.putExtra("imageUrl", imageUrl);
                        startActivity(intent);


                    }
                });

                break;
            case "fail":
                String errorText = (String) strbanner.getError().getErrorText();

                LToast.show(getActivity(), errorText);
                break;

        }


    }

    public class NetworkImageHolderView implements Holder<BannerItem> {
        private View view;

        @Override
        public View createView(Context context) {
            view = LayoutInflater.from(context).inflate(R.layout.banner_item, null, false);
            return view;
        }

        @Override
        public void UpdateUI(Context context, int position, BannerItem data) {
            ((TextView) view.findViewById(R.id.tv_title)).setText(data.getTitle());
            ImageView viewById = (ImageView) view.findViewById(R.id.sdv_background);
            Glide.with(getActivity()).load(data.getImage()).placeholder(R.mipmap.e_img_gongxi).error(R.mipmap.d_ic_bofang).into(viewById);
        }
    }


    //点击学习回调给HomeActivity  展示第二个模块
    public interface EightbtnListener {
        void btn(int postion);
    }

    private static final int FT_CODE = 10;

    /*8个点击事件*/
    @Override
    public void loadEightBtn(final List<LocalImgTitle> localImgTitles) {
        BaseQuickAdapter homeAdapter = new EightBtnsAdapter(R.layout.ft_page_btns, localImgTitles);
        homeAdapter.openLoadAnimation();
        recycleBtnItem.addOnItemTouchListener(new com.chad.library.adapter.base.listener.OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position == 1) {
                    EightbtnListener eightbtnListener = (EightbtnListener) getActivity();
                    eightbtnListener.btn(1);
                } else if (position == 2 || position == 3 || position == 7) { //2 考试 3 领证  7.交流
                    String pwd = (String) SharedPreferencesUtils.getParam(context, "Password", "");
                    if (pwd.equals("")) {
                        Intent intent = new Intent(context, LoginActivity.class);
                        intent.putExtra("main", "main");
                        startActivityForResult(intent, FT_CODE);
                    } else {
                        Intent intent = new Intent(getActivity(), localImgTitles.get(position).getClassName());
                        startActivity(intent);
                    }


                } else {
                    Intent intent = new Intent(getActivity(), localImgTitles.get(position).getClassName());
                    startActivity(intent);
                }


            }
        });
        recycleBtnItem.setAdapter(homeAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    //热门资讯
    @Override
    public void loadHotNews(final List<Noticebean.DataBean> dataBeen) {
        BaseQuickAdapter noticeAdapter = new NoticeAdapter(R.layout.ft_page_hot_news, dataBeen);
        noticeAdapter.openLoadAnimation();
        Log.d("FtPageFragment", "dataBeen.size():" + dataBeen.size());
        //加载更多
        View footView = LayoutInflater.from(getActivity()).inflate(R.layout.ft_load_more, null);
        noticeAdapter.addFooterView(footView);
        footView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HotNewsActivity.class));
            }
        });
        recycleHotNew.setAdapter(noticeAdapter);


        noticeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), BaoKaoDetailActivity.class);
                intent.putExtra("noticeid", dataBeen.get(position).getNoticeId() + "");
                startActivity(intent);


               // noticeAdapter.
            }
        });
    }


}
