package com.zibo.qipeng.asphalt.fragment.account;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zibo.qipeng.asphalt.MainActivity;
import com.zibo.qipeng.asphalt.R;
import com.zibo.qipeng.asphalt.adapter.account.AccountOrderAdapter;
import com.zibo.qipeng.asphalt.adapter.home.HomeStorageComAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zongshuo on 2019/6/21
 *
 * @author zongshuo ps: good luck ,ai ni o.
 */
public class OrderFragment extends Fragment {
    MainActivity mActivity;
    View currentView;
    @BindView(R.id.rv_order)
    RecyclerView rv_order;
    Unbinder unbinder;
    List<String> list = new ArrayList<>();
    AccountOrderAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        currentView = inflater.inflate(R.layout.fragment_order, null);
        unbinder = ButterKnife.bind(this, currentView);
        initView();
        return currentView;
    }

    private void initView() {
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(mActivity);
        layoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        rv_order.setLayoutManager(layoutManager1);

        adapter = new AccountOrderAdapter(mActivity, R.layout.item_order_list, list);
        rv_order.setAdapter(adapter);

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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
