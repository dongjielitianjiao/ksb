package com.itboy.dj.examtool.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.api.bean.MsgBean;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/7/25.
 */

public class MenuAdapter extends SwipeMenuAdapter<MenuAdapter.DefaultViewHolder> {

    private List<MsgBean.DataBean.RowsBean>  rowsBeen;

    public MenuAdapter(List<MsgBean.DataBean.RowsBean> rowsBeen) {
        this.rowsBeen = rowsBeen;
    }

    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_list_item, parent, false);
    }

    @Override
    public DefaultViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        DefaultViewHolder viewHolder = new DefaultViewHolder(realContentView);
        viewHolder.mOnItemClickListener = mOnItemClickListener;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DefaultViewHolder holder, int position) {
        holder.setData(rowsBeen.get(position));
    }

    @Override
    public int getItemCount() {
        return rowsBeen == null ? 0 : rowsBeen.size();
    }

    static class DefaultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitle;
        TextView tvContent;
        TextView tvTime;
        OnItemClickListener mOnItemClickListener;

        public DefaultViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvTitle = (TextView) itemView.findViewById(R.id.msg_title);
            tvContent= (TextView) itemView.findViewById(R.id.msg_content);
            tvTime= (TextView) itemView.findViewById(R.id.msg_time);
        }

        public void setData(MsgBean.DataBean.RowsBean rowsBean) {
            this.tvTitle.setText("提莫");
            this.tvContent.setText(rowsBean.getMegContent());
            this.tvTime.setText(rowsBean.getMegTime());
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }
}
