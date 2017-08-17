package com.itboy.dj.examtool.modules.ftpage.megagamefragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.modules.base.BaseFragment;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;
import com.itboy.dj.examtool.utils.WebViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MegagameIntroduceFragment extends BaseFragment {


    @BindView(R.id.web)
    WebView web;
    Unbinder unbinder;

    private WebViewHelper webViewHelper;
    private String mArgumentId;
    private String mArgumentImgUrl;
    public static final String ARGUMENT_ME_INTRODUCE = "id";
    public static final String ARGUMENT_ME_INTRODUCE_IMG = "img";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null)
            mArgumentId = bundle.getString(ARGUMENT_ME_INTRODUCE);
        mArgumentImgUrl = bundle.getString(ARGUMENT_ME_INTRODUCE_IMG);

    }

    public static MegagameIntroduceFragment newInstance(String id, String imgUrl) {
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT_ME_INTRODUCE, id);
        bundle.putString(ARGUMENT_ME_INTRODUCE_IMG, imgUrl);
        MegagameIntroduceFragment megagameIntroduceFragment = new MegagameIntroduceFragment();
        megagameIntroduceFragment.setArguments(bundle);
        return megagameIntroduceFragment;
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
        return R.layout.fragment_megagame_introduce;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        webViewHelper = new WebViewHelper(getActivity(), web);
        final String token = (String) SharedPreferencesUtils.getParam(getActivity(), "Token", "");
        String url = "http://demo.kspx.ccla.com.cn/kspx-cgi/api/view/dasai-" + mArgumentId + ".html?access_token=" + token;
        Log.d("MegagameIntroduceFragme", url + "-----" + mArgumentImgUrl);
        webViewHelper.loadUrl(url);

    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


}
