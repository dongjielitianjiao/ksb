package com.itboy.dj.examtool.modules.ftpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.adapter.MegagameAdapter;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.modules.ftpage.megagamefragment.MegagameCommentFragment;
import com.itboy.dj.examtool.modules.ftpage.megagamefragment.MegagameIntroduceFragment;
import com.itboy.dj.examtool.widget.tabContainer.TabContainerView1;
import com.itboy.dj.examtool.widget.tabContainer.listener.OnTabSelectedListener;
import com.itboy.dj.examtool.widget.tabContainer.widget.Tab;

import butterknife.BindView;
import butterknife.ButterKnife;


/*大赛详情*/
public class MegagameActivity extends BaseActivity {


    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.base_title_name)
    TextView baseTitleName;
    @BindView(R.id.megame_bot_table)
    TabContainerView1 megameBotTable;
    @BindView(R.id.img)
    ImageView img;
    private String[] itemNames = {"大赛介绍", "大赛评论"};

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_megagame;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
    //    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        baseTitleName.setText("大赛详情");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra("imageUrl");
        Glide.with(MegagameActivity.this).load(imageUrl).into(img);
        String contentid = intent.getStringExtra("contentid");


        MegagameIntroduceFragment megagameIntroduceFragment = MegagameIntroduceFragment.newInstance(contentid, imageUrl);
        MegagameCommentFragment megagameCommentFragment = MegagameCommentFragment.newInstance(contentid);
        megameBotTable.setAdapter(new MegagameAdapter(getSupportFragmentManager(),
                new Fragment[]{megagameIntroduceFragment, megagameCommentFragment}, itemNames));


        megameBotTable.setOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(Tab tab) {

            }
        });

        megameBotTable.setOffscreenPageLimit(itemNames.length);


    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
