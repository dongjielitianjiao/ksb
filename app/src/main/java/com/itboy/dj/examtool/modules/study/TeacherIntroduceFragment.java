package com.itboy.dj.examtool.modules.study;

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

/*讲师介绍*/
public class TeacherIntroduceFragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.progressBar1)
    ProgressBar progressBar1;
    @BindView(R.id.web)
    WebView web;
    private String mArgument;
    private String mArgumentType;
    public static final String ARGUMENT_QWE = "argument";
    public static final String TYPE = "argumenttpye";
    private WebViewHelper helper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null)
            mArgument = bundle.getString(ARGUMENT_QWE);
             mArgumentType = bundle.getString(TYPE);
        //  Log.d("TeacherIntroduceFragmen", mArgument);
    }

    public static TeacherIntroduceFragment newInstance(String argument, String type) {
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT_QWE, argument);
        bundle.putString(TYPE, type);
        TeacherIntroduceFragment teacherIntroduceFragment = new TeacherIntroduceFragment();
        teacherIntroduceFragment.setArguments(bundle);
        return teacherIntroduceFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*    View view = inflater.inflate(R.layout.fragment_teacher_introduce, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;*/
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_teacher_introduce;
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
        //Url：http://demo.kspx.ccla.com.cn/kspx-cgi/api/view/course-{courseId}/teacher.html
        //Url：http://demo.kspx.ccla.com.cn/kspx-cgi/api/view/yexiao-{courseId}/teacher.htm
        /*http://demo.kspx.ccla.com.cn/kspx-cgi/api/view/course-64/teacher.html?access_token=74e991ed081a47d6a8ec53fb5d6a4a64WD0BADy5JOeK7MWk5meaFuIaq6e1pvFulDf6PtYAhlSiwFI4n5tAyh9XoHenPbw7giqKR702a78abcedc461cb0c7a2206a89e93d*/

        String url = null;
        if ("kecheng".equals(mArgumentType)) {
            url = "http://demo.kspx.ccla.com.cn/kspx-cgi/api/view/course-" + mArgument + "/teacher.html?access_token=" + token;
        } else if ("yexiao".equals(mArgumentType)) {
            url = "http://demo.kspx.ccla.com.cn/kspx-cgi/api/view/yexiao-" + mArgument + "/teacher.html?access_token=" + token;

        }
        Log.d("TeacherIntroduceFragmen", url);

        helper.loadUrl(url);
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
