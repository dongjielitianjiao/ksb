package com.itboy.dj.examtool.modules.ftpage.hotNews;

import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;
import com.itboy.dj.examtool.utils.WebViewHelper;

import butterknife.BindView;

public class NewsDetailActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.base_title_name)
    TextView baseTitleName;
    @BindView(R.id.web)
    WebView web;
    @BindView(R.id.progressBar1)
    ProgressBar progressBar1;
    private WebViewHelper helper;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        //13252104544
        baseTitleName.setText("咨询详情");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        helper = new WebViewHelper(context, web);
        final String token = (String) SharedPreferencesUtils.getParam(NewsDetailActivity.this, "Token", "");
        Intent intent = getIntent();
        String contentid = intent.getStringExtra("contentid");
        String url = "http://demo.kspx.ccla.com.cn/kspx-cgi/api/view/content-" + contentid + ".html?access_token=" + token;
        helper.loadUrl(url);
        helper.setWebChromeClient(new WebViewHelper.WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {

            }
        }, progressBar1);


    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

  /*  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }*/
}
