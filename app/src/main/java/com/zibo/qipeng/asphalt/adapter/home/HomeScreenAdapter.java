package com.zibo.qipeng.asphalt.adapter.home;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zibo.qipeng.asphalt.R;
import com.zibo.qipeng.asphalt.activity.home.HomeProductActivity;
import com.zibo.qipeng.asphalt.entity.Screen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zongshuo on 2019/6/18
 *
 * @author zongshuo ps: good luck ,ai ni o.
 */
public class HomeScreenAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<Screen> screenlist=new ArrayList<>();





    public HomeScreenAdapter(Context context, List<Screen> screenlist) {
        this.context = context;
        this.screenlist = screenlist;
    }

    @Override
    public int getGroupCount() {
        return screenlist.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
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
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_home_screen_head,null);
            holder = new ViewHolder();
            holder.tv_title = convertView.findViewById(R.id.tv_title);
            holder.iv_flag = convertView.findViewById(R.id.iv_flag);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_title.setText(screenlist.get(groupPosition).getTitle());
        return convertView;

    }


    @Override
    public View getChildView(final int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder1 holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_home_screen_child,null);
            holder = new ViewHolder1();
            holder.rv_home_screen_child= convertView.findViewById(R.id.rv_home_screen_child);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder1) convertView.getTag();
        }

        HomeScreenChildAdapter adapter = new HomeScreenChildAdapter(context, R.layout.item_home_screen_child_child,screenlist.get(groupPosition).getContentList() );
        holder.rv_home_screen_child.setAdapter(adapter);
        holder.rv_home_screen_child.setLayoutManager(new GridLayoutManager(context, 3));

        return convertView;
    }



    class ViewHolder {
        TextView tv_title;
        ImageView iv_flag;
    }

    class ViewHolder1 {
        RecyclerView rv_home_screen_child;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }



}
