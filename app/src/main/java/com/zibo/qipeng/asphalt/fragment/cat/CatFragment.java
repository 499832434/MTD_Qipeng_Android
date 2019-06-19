package com.zibo.qipeng.asphalt.fragment.cat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zibo.qipeng.asphalt.MainActivity;
import com.zibo.qipeng.asphalt.R;
import com.zibo.qipeng.asphalt.activity.home.HomeLakeActivity;
import com.zibo.qipeng.asphalt.activity.home.HomeLakeContentActivity;
import com.zibo.qipeng.asphalt.adapter.cat.CatAdapter;
import com.zibo.qipeng.asphalt.adapter.home.HomeLakeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zongshuo on 2019/6/19
 *
 * @author zongshuo ps: good luck ,ai ni o.
 */
public class CatFragment extends Fragment {
    View currentView;
    @BindView(R.id.rv_cat)
    RecyclerView rv_cat;
    Unbinder unbinder;
    private MainActivity mActivity;
    List<String> list = new ArrayList<>();
    CatAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        currentView = inflater.inflate(R.layout.fragment_cat, null);
        unbinder = ButterKnife.bind(this, currentView);
        initView();
        return currentView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private void initView(){

        for (int i = 0; i < 5; i++) {
            list.add("" + i);
        }


        LinearLayoutManager layoutManager1 = new LinearLayoutManager(mActivity);
        layoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        rv_cat.setLayoutManager(layoutManager1);

        adapter = new CatAdapter(mActivity, R.layout.item_cat_list, list);
        rv_cat.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (MainActivity) activity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (MainActivity) context;
    }

}
