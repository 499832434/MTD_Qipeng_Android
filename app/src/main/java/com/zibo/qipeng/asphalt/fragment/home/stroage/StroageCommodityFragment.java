package com.zibo.qipeng.asphalt.fragment.home.stroage;

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
import com.zibo.qipeng.asphalt.activity.home.HomeProductActivity;
import com.zibo.qipeng.asphalt.activity.home.HomeStorageDetailActivity;
import com.zibo.qipeng.asphalt.adapter.home.HomeAdapter;
import com.zibo.qipeng.asphalt.adapter.home.HomeStorageComAdapter;

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
public class StroageCommodityFragment extends Fragment {
    View currentView;
    @BindView(R.id.rv_storage_com)
    RecyclerView rv_storage_com;
    Unbinder unbinder;
    private HomeStorageDetailActivity mActivity;
    List<String> list = new ArrayList<>();
    HomeStorageComAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        currentView = inflater.inflate(R.layout.fragment_stroage_commodity, null);
        unbinder = ButterKnife.bind(this, currentView);
        initView();
        adapter.notifyDataSetChanged();
        return currentView;
    }

    private void initView() {
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(mActivity);
        layoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        rv_storage_com.setLayoutManager(layoutManager1);

        adapter = new HomeStorageComAdapter(mActivity, R.layout.item_storage_com, list);
        rv_storage_com.setAdapter(adapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (HomeStorageDetailActivity) activity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (HomeStorageDetailActivity) context;
    }

}
