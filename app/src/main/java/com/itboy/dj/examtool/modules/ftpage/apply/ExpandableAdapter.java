package com.itboy.dj.examtool.modules.ftpage.apply;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itboy.dj.examtool.R;

import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */
//未使用
public class ExpandableAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<GradeGroup> gradeGroups;

    public ExpandableAdapter(Context context, List<GradeGroup> gradeGroups) {
        this.context = context;
        this.gradeGroups = gradeGroups;
    }

    @Override
    public int getGroupCount() {
        return gradeGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return gradeGroups.get(groupPosition).children.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return gradeGroups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return gradeGroups.get(groupPosition).children;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_expandable_lv1, null);

        TextView group_title = (TextView) convertView
                .findViewById(R.id.one);
        group_title.setText(gradeGroups.get(groupPosition).name);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_expandable_lv2_two, null);

        TextView title = (TextView) convertView.findViewById(R.id.two_title);
        TextView time = (TextView) convertView.findViewById(R.id.two_time);
        TextView startEndTime = (TextView) convertView.findViewById(R.id.two_start_end_time);
        TextView object = (TextView) convertView.findViewById(R.id.two_duixiang);
        TextView adress = (TextView) convertView.findViewById(R.id.two_train_adr);
        LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.ln_back);

        ClassListItem classListItem = gradeGroups.get(groupPosition).children.get(childPosition);
        title.setText(classListItem.getTitle());
        time.setText("[ 发布日期:" + classListItem.getTime() + "]");
        startEndTime.setText("报名时间: " + classListItem.getStartTime() + classListItem.getEndTime());
        object.setText("报名对象: " + classListItem.getDuixiang());
        adress.setText("培训地址: " + classListItem.getDuixiang());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
