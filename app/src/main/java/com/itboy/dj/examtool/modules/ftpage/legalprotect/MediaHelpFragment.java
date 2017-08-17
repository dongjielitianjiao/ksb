package com.itboy.dj.examtool.modules.ftpage.legalprotect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.modules.base.BaseFragment;
import com.itboy.dj.examtool.rxbus.RxBus;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;
import com.itboy.dj.examtool.utils.WebViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscriber;
import rx.Subscription;


public class MediaHelpFragment extends BaseFragment {
    Unbinder unbinder;
    WebViewHelper webViewHelper;
    @BindView(R.id.progressBar1)
    ProgressBar progressBar1;
    @BindView(R.id.web)
    WebView web;
    private Subscription subscribe;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        return R.layout.fragment_media_help;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        webViewHelper = new WebViewHelper(getActivity(), web);
        String token = (String) SharedPreferencesUtils.getParam(getActivity(), "Token", "");
        final String url = "http://demo.kspx.ccla.com.cn/kspx-cgi/api/view/weiquan.html?access_token=" + token + "&type=媒体";
        webViewHelper.loadUrl(url);
        webViewHelper.setWebChromeClient(new WebViewHelper.WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {

            }
        }, progressBar1);

        subscribe = RxBus.getDefault().toObservableSticky(TyPeBean.class).subscribe(new Subscriber<TyPeBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(TyPeBean tyPeBean) {
                switch (tyPeBean.getType()) {
                    case "2":
                        webViewHelper.loadUrl(url);
                        break;


                }
            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (subscribe != null && !subscribe.isUnsubscribed()) {
            subscribe.unsubscribe();
        }
    }


}
