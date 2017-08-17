package com.itboy.dj.examtool.modules.ftpage;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.modules.ftpage.city.CityAdapter;
import com.itboy.dj.examtool.modules.ftpage.city.CityEntity;
import com.itboy.dj.examtool.widget.LToast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.indexablerv.EntityWrapper;
import me.yokeyword.indexablerv.IndexableAdapter;
import me.yokeyword.indexablerv.IndexableLayout;
import me.yokeyword.indexablerv.SimpleHeaderAdapter;

public class CityPkActivity extends BaseActivity {
    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.base_title_name)
    TextView baseTitleName;
    /*  @BindView(R.id.mian)
      FrameLayout mian;*/
    /*
        @BindView(R.id.searchview)
        SearchView searchview;
        @BindView(R.id.indexableLayout)
        IndexableLayout indexableLayout;
        @BindView(R.id.progress)
        FrameLayout progress;*/
    private SearchView searchview;
    private FrameLayout progress;

    private List<CityEntity> mDatas;
    private SimpleHeaderAdapter mHotCityAdapter;
    Search1Fragment searchFragment;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_city_pk;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        baseTitleName.setText("选择城市");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        IndexableLayout indexableLayout = (IndexableLayout) findViewById(R.id.indexableLayout);
        indexableLayout.setLayoutManager(new GridLayoutManager(this, 3));

        searchview = (SearchView) findViewById(R.id.searchview);
        progress = (FrameLayout) findViewById(R.id.progress);
        //不能用bnf去找，会出错
        searchFragment = new Search1Fragment();
        getSupportFragmentManager().beginTransaction().add(R.id.mian, searchFragment).commit();
        //searchFragment = (Search1Fragment) getSupportFragmentManager().findFragmentById(R.id.search_fragment);


        // 设定adapter
        CityAdapter adapter = new CityAdapter(this);
        indexableLayout.setAdapter(adapter);

        // 初始化城市数据
        mDatas = initDatas();

        // 快速排序。  排序规则设置为：只按首字母  （默认全拼音排序）  效率很高，是默认的10倍左右。  按需开启～
        indexableLayout.setCompareMode(IndexableLayout.MODE_FAST);


        adapter.setDatas(mDatas, new IndexableAdapter.IndexCallback<CityEntity>() {
            @Override
            public void onFinished(List<EntityWrapper<CityEntity>> datas) {
                // 数据处理完成后回调
                searchFragment.bindDatas(mDatas);
                progress.setVisibility(View.GONE);
            }
        });

        // set Center OverlayView
        indexableLayout.setOverlayStyle_Center();


        // 点击item
        adapter.setOnItemContentClickListener(new IndexableAdapter.OnItemContentClickListener<CityEntity>() {
            @Override
            public void onItemClick(View v, int originalPosition, int currentPosition, CityEntity entity) {

                if (originalPosition >= 0) {
                    //点击了内容部分的城市（从A-Z的城市）
                    Log.d("CityPkActivity", entity.getName());
                    LToast.show(CityPkActivity.this, "选中:" + entity.getName() + "  当前位置:" + currentPosition + "  原始所在数组位置:" + originalPosition);
                } else {
                    //点击了头部标签的内容部分（热门城市和当前城市的内容部分）
                    Log.d("CityPkActivity", entity.getName());
                    LToast.show(CityPkActivity.this, "选中Header----:" + entity.getName() + "  当前位置----:" + currentPosition);

                }
            }
        });


        /*点击了分类的标签 热门城市 当前城市 A B C......*/
        adapter.setOnItemTitleClickListener(new IndexableAdapter.OnItemTitleClickListener() {
            @Override
            public void onItemClick(View v, int currentPosition, String indexTitle) {
                LToast.show(CityPkActivity.this, "选中==:" + indexTitle + "  当前位置===:" + currentPosition);
            }
        });

        // 添加 HeaderView DefaultHeaderAdapter接收一个IndexableAdapter, 使其布局以及点击事件和IndexableAdapter一致
        // 如果想自定义布局,点击事件, 可传入 new IndexableHeaderAdapter

        // 热门城市
        mHotCityAdapter = new SimpleHeaderAdapter<>(adapter, "热", "热门城市", iniyHotCityDatas());
        indexableLayout.addHeaderAdapter(mHotCityAdapter);

        // 定位到的城市
        final List<CityEntity> gpsCity = iniyGPSCityDatas();
        final SimpleHeaderAdapter gpsHeaderAdapter = new SimpleHeaderAdapter<>(adapter, "定", "当前城市", gpsCity);
        indexableLayout.addHeaderAdapter(gpsHeaderAdapter);


        // 模拟定位
        indexableLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                gpsCity.get(0).setName("杭州市");
                gpsHeaderAdapter.notifyDataSetChanged();
            }
        }, 3000);

        //搜索城市
        initSearch();

         /*  // 显示真实索引
      indexableLayout.showAllLetter(false);*/
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    // 更新数据点击事件
    public void update(View view) {
        List<CityEntity> list = new ArrayList<>();
        list.add(new CityEntity("杭州市"));
        list.add(new CityEntity("北京市"));
        list.add(new CityEntity("上海市"));
        list.add(new CityEntity("广州市"));
        mHotCityAdapter.addDatas(list);
        Toast.makeText(this, "更新数据", Toast.LENGTH_SHORT).show();
    }

    private List<CityEntity> initDatas() {
        List<CityEntity> list = new ArrayList<>();
        List<String> cityStrings = Arrays.asList(getResources().getStringArray(R.array.city_array));
        for (String item : cityStrings) {
            CityEntity cityEntity = new CityEntity();
            cityEntity.setName(item);
            list.add(cityEntity);
        }
        return list;
    }

    private List<CityEntity> iniyHotCityDatas() {
        List<CityEntity> list = new ArrayList<>();
        list.add(new CityEntity("杭州市"));
        list.add(new CityEntity("北京市"));
        list.add(new CityEntity("上海市"));
        list.add(new CityEntity("广州市"));
        return list;
    }

    //当前定位城市，由上个界面传过来的
    private List<CityEntity> iniyGPSCityDatas() {
        List<CityEntity> list = new ArrayList<>();
        list.add(new CityEntity("定位中..."));
        return list;
    }

    //搜索城市
    private void initSearch() {
        getSupportFragmentManager().beginTransaction().hide(searchFragment).commit();

        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.trim().length() > 0) {
                    if (searchFragment.isHidden()) {
                        getSupportFragmentManager().beginTransaction().show(searchFragment).commit();
                    }
                } else {
                    if (!searchFragment.isHidden()) {
                        getSupportFragmentManager().beginTransaction().hide(searchFragment).commit();
                    }
                }

                searchFragment.bindQueryText(newText);
                return false;
            }
        });
    }


    //重写返回键
    @Override
    public void onBackPressed() {
        if (!searchFragment.isHidden()) {
            // 隐藏 搜索
            searchview.setQuery(null, false);
            getSupportFragmentManager().beginTransaction().hide(searchFragment).commit();
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
