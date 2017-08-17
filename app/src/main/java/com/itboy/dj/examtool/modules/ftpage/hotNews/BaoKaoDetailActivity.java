package com.itboy.dj.examtool.modules.ftpage.hotNews;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;
import com.itboy.dj.examtool.utils.WebViewHelper;

import java.io.UnsupportedEncodingException;

import butterknife.BindView;

public class BaoKaoDetailActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.base_title_name)
    TextView baseTitleName;
    @BindView(R.id.progressBar1)
    ProgressBar progressBar1;
    @BindView(R.id.web)
    WebView web;
    private WebViewHelper helper;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_bao_kao_detail;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        baseTitleName.setText("咨询详情");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        helper = new WebViewHelper(context, web);
        final String token = (String) SharedPreferencesUtils.getParam(BaoKaoDetailActivity.this, "Token", "");
        Intent intent = getIntent();
        String noticeid = intent.getStringExtra("noticeid");
        //Url：http://demo.kspx.ccla.com.cn/kspx-cgi/api/view/exam-{noticeId}.html
        String url = "http://demo.kspx.ccla.com.cn/kspx-cgi/api/view/exam-" + noticeid + ".html?access_token=" + token;
        helper.loadUrl(url);

        helper.setWebViewClient(new WebViewHelper.WebViewClient() {
            @Override
            public void shouldOverrideUrlLoading(WebView view, String url) throws UnsupportedEncodingException {
                Log.d("BaoKaoDetailActivity", url);
            }
        });
        helper.setWebChromeClient(new WebViewHelper.WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {

            }
        }, progressBar1);

    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }


}
