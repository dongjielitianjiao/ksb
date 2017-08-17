package com.itboy.dj.examtool.widget.popwindow;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.itboy.dj.examtool.R;

import java.util.List;

/**
 * Created by Administrator on 2017/1/16.
 */

public class ChooseExamWindow extends BasicPopupWindow {
    private GridView listView;
    private List<String> list;
    private TikuApter adapter;
    private View mView;

    public ChooseExamWindow(Activity context, Handler handler, int id, List<String> list) {
        super(context, handler, id);
        this.list = list;
        initView();

    }

    public void synData(List<String> list) {
        adapter.synData(list);
    }

        //position 当前位置  tag 是否选中状态 ：-1 不选中， 0选中
    public void setCheckItem(int position,int tag) {

        if(listView!=null&&listView.getChildAt(position)!=null){
            TextView viewById = (TextView) listView.getChildAt(position).findViewById(R.id.exam_choose_text);
            if(tag==0){
                viewById.setTextColor(listView.getContext().getResources().getColor(R.color.color_white));
                viewById.setBackground(listView.getContext().getResources().getDrawable(R.drawable.exam_choose_select));
            }else {
                viewById.setTextColor(listView.getContext().getResources().getColor(R.color.color_black));
                viewById.setBackground(listView.getContext().getResources().getDrawable(R.drawable.exam_choose_normal));
            }

            adapter.notifyDataSetChanged();
        }

    }

    private void initView() {
        listView = (GridView) conentView.findViewById(R.id.content_exam);
        mView = conentView.findViewById(R.id.view);
        adapter = new TikuApter(context, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Message message = new Message();
                message.obj = position;
                handler.sendMessage(message);
                dismiss();
            }
        });

        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }


    class TikuApter extends BaseAdapter {
        private Activity context;
        private List<String> list;

        public TikuApter(Activity context, List<String> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        private void synData(List<String> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(context, R.layout.item_exam_oval_choose, null);
                holder.tv = (TextView) convertView.findViewById(R.id.exam_choose_text);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tv.setText(list.get(position));

            return convertView;
        }
    }

    class ViewHolder {
        TextView tv;
    }

}
