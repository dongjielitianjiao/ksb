package com.itboy.dj.examtool.modules.me;

import android.view.View;
import android.widget.RelativeLayout;

import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.modules.base.BaseActivity;

import butterknife.BindView;
/*未用这个界面*/
public class MyStudyActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.back)
    RelativeLayout back;


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {

        back.setOnClickListener(this);
    }

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
}
