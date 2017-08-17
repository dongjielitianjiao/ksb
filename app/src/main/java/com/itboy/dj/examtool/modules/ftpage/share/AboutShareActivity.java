package com.itboy.dj.examtool.modules.ftpage.share;

import android.util.Log;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;
import com.itboy.dj.examtool.utils.WebViewHelper;

import butterknife.BindView;

public class AboutShareActivity extends BaseActivity {
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
        return R.layout.activity_about_share;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        baseTitleName.setText("关于分享");
        helper = new WebViewHelper(context, web);
        String token = (String) SharedPreferencesUtils.getParam(AboutShareActivity.this, "Token", "");
        String url = "http://demo.kspx.ccla.com.cn/kspx-cgi/api/view/share.html?access_token=" + token;
        //String url1="http://demo.kspx.ccla.com.cn/kspx-cgi/api/view/weiquan.html?access_token="+token;
        //String url2="http://demo.kspx.ccla.com.cn/kspx-cgi/api/view/yuanzhu.html?access_token="+token;
        Log.d("ShareCodeActivity", url);
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


}
