package com.zibo.qipeng.asphalt.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zibo.qipeng.asphalt.R;

import butterknife.BindView;


/**
 * Created by zongshuo on 2018/12/13
 *
 * @author zongshuo ps: good luck ,ai ni o.
 */
public class CustomTitleBar extends LinearLayout {


    ImageView ivBack;
    TextView tvTitle;
    ImageView ivFirst;



    private String text;
    @SuppressLint("ResourceAsColor")
    public CustomTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        View titleBar = LayoutInflater.from(context).inflate(R.layout.custom_title_bar_view, null);
        ivBack=titleBar.findViewById(R.id.iv_back);
        ivFirst=titleBar.findViewById(R.id.iv_first);
        tvTitle=titleBar.findViewById(R.id.tv_title);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomTitleBar);
        text = ta.getString(R.styleable.CustomTitleBar_text);
        tvTitle.setText(text);
        ta.recycle();

        addView(titleBar, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }




    /**
     * 添加点击事件 -1:返回 0:标题下拉按钮 1:点赞 2:收藏 3:分享 4:字号 5:日历 6:搜索 7:文字
     */
    public void setFlagClickListener(int flag, OnClickListener listener) {
        switch (flag) {
            case -1:
                ivBack.setVisibility(VISIBLE);
                ivBack.setOnClickListener(listener);
                break;
            case 0:
                ivFirst.setVisibility(VISIBLE);
                ivFirst.setOnClickListener(listener);
                break;
        }
    }




}
