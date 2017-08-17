package com.itboy.dj.examtool.widget.popwindow;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.itboy.dj.examtool.R;

import java.util.List;

/**
 * Created by Administrator on 2017/1/16.
 */

public class ErrorReviewWindow extends BasicPopupWindow {
    private RecyclerView recyclerView;
    private List<String> list;

    private ErrorAdapter errorAdapter;
    private View mView;

    public ErrorReviewWindow(Activity context, Handler handler, int id, List<String> list) {
        super(context, handler, id);
        this.list = list;
        initView();

    }


    private void initView() {
        recyclerView = (RecyclerView) conentView.findViewById(R.id.error_rec);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);

        errorAdapter = new ErrorAdapter();
        recyclerView.setAdapter(errorAdapter);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                Message message = new Message();
                message.obj = position;
                handler.sendMessage(message);
                dismiss();
            }
        });

        mView = conentView.findViewById(R.id.view);
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }


    class ErrorAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public ErrorAdapter() {
            super(R.layout.error_adapter, list);
        }

     /*  public ErrorAdapter(int layoutResId, List<String> data) {
           super(layoutResId, data);
       }*/

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.exam_choose_text, item);
        }
    }


}
