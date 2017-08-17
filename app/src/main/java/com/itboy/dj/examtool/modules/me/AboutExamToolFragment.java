package com.itboy.dj.examtool.modules.me;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.modules.base.BaseFragment;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;
import com.itboy.dj.examtool.utils.WebViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class AboutExamToolFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.progressBar1)
    ProgressBar progressBar1;
    @BindView(R.id.web)
    WebView web;
    WebViewHelper helper;
    private String type;
    private static final String FORUM_TYPE_KEY = "AboutEaxmTools";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getString(FORUM_TYPE_KEY);
            Log.d("AboutExamToolFragment", type);
        }

    }

    public static AboutExamToolFragment newInstance(String type) {
        AboutExamToolFragment fragment = new AboutExamToolFragment();
        Bundle bundle = new Bundle();
        bundle.putString(FORUM_TYPE_KEY, type);
        fragment.setArguments(bundle);
        return fragment;
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
        return R.layout.fragment_about_exam_tool;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {

        helper = new WebViewHelper(context, web);
        //Url：http://demo.kspx.ccla.com.cn/kspx-cgi/api/view/sys-intro/{type}.html
        String token = (String) SharedPreferencesUtils.getParam(getActivity(), "Token", "");
        String url = "http://demo.kspx.ccla.com.cn/kspx-cgi/api/view/sys-intro/" + type + ".html?access_token=" + token;
       // Log.d("AboutExamToolFragment", url);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
