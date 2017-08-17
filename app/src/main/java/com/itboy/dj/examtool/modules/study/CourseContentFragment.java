package com.itboy.dj.examtool.modules.study;

import android.os.Bundle;
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

/*课程内容*/
public class CourseContentFragment extends BaseFragment {


    @BindView(R.id.progressBar1)
    ProgressBar progressBar1;
    @BindView(R.id.web)
    WebView web;
    private String mArgument;
    private String mArgumentType;
    public static final String ARGUMENT_QWE = "contentargument";
    public static final String TYPE = "contentargumenttpye";
    private WebViewHelper helper;
    Unbinder unbinder;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null)
            mArgument = bundle.getString(ARGUMENT_QWE);
        mArgumentType = bundle.getString(TYPE);
    }

    public static CourseContentFragment newInstance(String argument, String type) {
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT_QWE, argument);
        bundle.putString(TYPE, type);
        CourseContentFragment courseContentFragment = new CourseContentFragment();
        courseContentFragment.setArguments(bundle);
        return courseContentFragment;
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
        return R.layout.fragment_course_content;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void updateViews(boolean isRefresh) {
        helper = new WebViewHelper(context, web);
        final String token = (String) SharedPreferencesUtils.getParam(getActivity(), "Token", "");
        String url = null;
        if ("contentCourse".equals(mArgumentType)) {
            //http://demo.kspx.ccla.com.cn/kspx-cgi/api/view/course-{courseId}.html
            url = "http://demo.kspx.ccla.com.cn/kspx-cgi/api/view/course-" + mArgument + ".html?access_token=" + token;
          //  Log.d("CourseContentFragment", url);
            helper.loadUrl(url);
        } else if ("contentnight".equals(mArgumentType)) {
            //http://demo.kspx.ccla.com.cn/kspx-cgi/api/view/yexiao-{courseId}.htm
            url = "http://demo.kspx.ccla.com.cn/kspx-cgi/api/view/yexiao-" + mArgument + ".html?access_token=" + token;
        //    Log.d("CourseContentFragment", url);
            helper.loadUrl(url);
        }

        helper.setWebChromeClient(new WebViewHelper.WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {

            }
        }, progressBar1);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
