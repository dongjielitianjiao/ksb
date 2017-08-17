package com.itboy.dj.examtool.api;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.utils.networkDeal.NetStateUtils;
import com.itboy.dj.examtool.widget.LToast;

import rx.Subscriber;

/**
 * Created by Jam on 16-7-21
 * Description: 自定义Subscribe
 */
public abstract class RxSubscribe<T> extends Subscriber<T> {
    private Context mContext;
    private Dialog dialog;
    private String msg;
    private ProgressBar progressBar;
    int[] colors = {
            android.graphics.Color.parseColor("#D55400"),
            android.graphics.Color.parseColor("#2B3E51"),
            android.graphics.Color.parseColor("#00BD9C"),
            android.graphics.Color.parseColor("#227FBB"),
            android.graphics.Color.parseColor("#7F8C8D"),
            android.graphics.Color.parseColor("#FFCC5C"),
            android.graphics.Color.parseColor("#D55400"),
            android.graphics.Color.parseColor("#1AAF5D"),
    };

    public RxSubscribe() {

    }

    protected boolean showDialog() {
        return true;
    }

    /**
     * @param context context
     * @param msg     dialog message
     */
    public RxSubscribe(Context context, String msg) {
        this.mContext = context;
        this.msg = msg;
    }

    /**
     * @param context context
     */
    public RxSubscribe(Context context) {
        this(context, "请稍后...");
    }

    @Override
    public void onCompleted() {
        if (showDialog())
            dialog.dismiss();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (showDialog()) {
            dialog = new Dialog(mContext);
            dialog.getContext().getTheme().applyStyle(R.style.dialog, true); //去掉dialog边框
            //给progressBar设置样式
            progressBar = new ProgressBar(mContext);
            DoubleBounce doubleBounce = new DoubleBounce();
            doubleBounce.setBounds(0, 0, 100, 100);
            doubleBounce.setColor(colors[7]);
            progressBar.setIndeterminateDrawable(doubleBounce);
            dialog.setCancelable(true);
            dialog.setContentView(progressBar);
            dialog.setCanceledOnTouchOutside(true);
            //点击取消的时候取消订阅
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    if (!isUnsubscribed()) {
                        unsubscribe();
                    }
                }
            });
            dialog.show();
        }


    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onError(Throwable e) {

        if (false == NetStateUtils.isNetworkConnected(mContext)) { //这里自行替换判断网络的代码
            LToast.show(mContext, "网络不可用");
            _onError("网络不可用");
        } else if (e instanceof ServerException) {
            LToast.show(mContext, e.getMessage());
            _onError(e.getMessage());
        } else {
            LToast.show(mContext, "请求失败，请稍后再试...");
            _onError("请求失败，请稍后再试...");


        }
        if (showDialog())
            dialog.dismiss();
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String message);

}
