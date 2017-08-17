package com.itboy.dj.examtool.modules.ftpage.NightSchool;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.adapter.NightSchoolStudyListAdapter;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.RxSubscribe;
import com.itboy.dj.examtool.api.bean.NightSchoolBean;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.utils.RecycleViewDivider;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.RequestBody;

public class WorkerNightSchoolActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.base_title_name)
    TextView baseTitleName;
    @BindView(R.id.work_night_rec)
    RecyclerView workNightRec;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_worker_night_school;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        baseTitleName.setText("夜校学习");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        workNightRec.setLayoutManager(new LinearLayoutManager(WorkerNightSchoolActivity.this));
        workNightRec.addItemDecoration(new RecycleViewDivider(WorkerNightSchoolActivity.this, LinearLayoutManager.HORIZONTAL, R.drawable.recycle_divider3));
        final Map<String, RequestBody> map = new HashMap<String, RequestBody>();
        map.put("pageNumber", RequestBody.create(null, 1 + ""));
        map.put("pageSize", RequestBody.create(null, 4 + ""));
        final String token = (String) SharedPreferencesUtils.getParam(WorkerNightSchoolActivity.this, "Token", "");

        RetrofitService.nightSchoolList(map,token).subscribe(new RxSubscribe<NightSchoolBean>(WorkerNightSchoolActivity.this) {
            @Override
            protected void _onNext(NightSchoolBean nightSchoolBean) {
                final List<NightSchoolBean.DataBean.RowsBean> rows = nightSchoolBean.getData().getRows();
                NightSchoolStudyListAdapter nightSchoolStudyListAdapter = new NightSchoolStudyListAdapter(R.layout.item_night_school, rows);
                nightSchoolStudyListAdapter.openLoadAnimation();
                workNightRec.setAdapter(nightSchoolStudyListAdapter);
                workNightRec.addOnItemTouchListener(new OnItemClickListener() {
                    @Override
                    public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent intent = new Intent(WorkerNightSchoolActivity.this, NightSchoolDetailActivity.class);
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



}
