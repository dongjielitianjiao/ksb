package com.itboy.dj.examtool.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;


import com.itboy.dj.examtool.BaseApplication;

import java.io.UnsupportedEncodingException;

/**
 * 自定义webview
 * 功能：
 * 1、显示加载进度
 * 2、显示加载错误页面
 * Created by Administrator on 2016/7/26.
 */
public class WebViewHelper implements Runnable {
    //上下文对象
    private Context mContext;
    //WebView对象
    private WebView mWebView;
    //SVProgressHUD对象，显示进度

    //加载的url地址
    private String url;


    public WebViewHelper(Context context, WebView webView) {
        this.mContext = context;
        this.mWebView = webView;
        initSetting();
    }

    /**
     * 初始化WebView设置
     */
    private void initSetting() {
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        //去掉竖直方向上的scrollbar
        mWebView.setVerticalScrollBarEnabled(false);
        //去掉水平方向上的scrollbar
        mWebView.setHorizontalScrollBarEnabled(false);
        //屏蔽掉长按时间，因为webview长按时会调用系统的复制控件
        mWebView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //默认返回false，表示不处理长按事件，可复制
                return true;
            }
        });
    }

    /**
     * 重新加载
     */
    public void reload() {
        if (NetUtil.isNetworkAvailable(BaseApplication.getContext())) {
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.loadUrl(url);
        } else {
            mWebView.loadUrl("file:///android_asset/network.html");
            mWebView.addJavascriptInterface(new WebAppInterface(), "Android");
        }
    }

    /**
     * 停止加载
     */
    public void stopLoading() {
        mWebView.stopLoading();
    }

    /**
     * 加载网页
     *
     * @param url 网页地址
     */
    public void loadUrl(String url) {
        this.url = url;
        if (NetUtil.isNetworkAvailable(BaseApplication.getContext())) {
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.loadUrl(url);
        } else {
            mWebView.loadUrl("file:///android_asset/network.html");
            mWebView.addJavascriptInterface(new WebAppInterface(), "Android");
        }
    }

    public void setWebChromeClient(final WebChromeClient client, final ProgressBar bar) {
        mWebView.setWebChromeClient(new android.webkit.WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                client.onReceivedTitle(view, title);
                super.onReceivedTitle(view, title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                if (newProgress == 100) {
                    bar.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    bar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    bar.setProgress(newProgress);//设置进度值
                }
                super.onProgressChanged(view, newProgress);

            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                result.confirm();
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                result.confirm();
                return super.onJsConfirm(view, url, message, result);
            }
        });
    }


    public void setWebViewClient(final WebViewClient client) {
        mWebView.setWebViewClient(new android.webkit.WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                try {
                    client.shouldOverrideUrlLoading(view, url);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

            }

            @Override
            public void onPageFinished(WebView view, String url) {

            }

            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);

                if (!NetUtil.isNetworkAvailable(BaseApplication.getContext())) {
                    view.loadUrl("file:///android_asset/network.html");
                } else {
                    view.loadUrl("file:///android_asset/fail.html");
                }
            }

            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(view, error.getErrorCode(), error.getDescription().toString(), request.getUrl().toString());
            }
        });
    }

    @Override
    public void run() {

    }


    public interface WebChromeClient {
        /**
         * 接受标题
         *
         * @param view
         * @param title
         */
        void onReceivedTitle(WebView view, String title);

    }

    public interface WebViewClient {
        /**
         * 加载url
         *
         * @param view
         * @param url
         */
        void shouldOverrideUrlLoading(WebView view, String url) throws UnsupportedEncodingException;
    }


    public class WebAppInterface {
        /**
         * js调用，重新加载页面
         */
        @JavascriptInterface
        public void reload() {
            mWebView.post(new Runnable() {
                @Override
                public void run() {
                    //LogUtil.d(url);
                    loadUrl(url);
                }
            });

        }
    }
}

