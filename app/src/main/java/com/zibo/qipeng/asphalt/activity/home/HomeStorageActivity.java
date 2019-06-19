package com.zibo.qipeng.asphalt.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zibo.qipeng.asphalt.BaseActivity;
import com.zibo.qipeng.asphalt.R;
import com.zibo.qipeng.asphalt.adapter.home.HomeRecommendAdapter;
import com.zibo.qipeng.asphalt.adapter.home.HomeStorageAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zongshuo on 2019/6/18
 *
 * @author zongshuo ps: good luck ,ai ni o.
 */
public class HomeStorageActivity extends BaseActivity {
    @BindView(R.id.rv_storage)
    RecyclerView rv_storage;
    List<String> list = new ArrayList<>();
    HomeStorageAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_storage);
        ButterKnife.bind(this);
        mImmersionBar.statusBarView(R.id.view_top).init();
        initView();
    }


    private void initView(){

        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }


        adapter = new HomeStorageAdapter(HomeStorageActivity.this, R.layout.item_home_storage, list);
        rv_storage.setAdapter(adapter);
        rv_storage.setLayoutManager(new GridLayoutManager(HomeStorageActivity.this, 2));
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(HomeStorageActivity.this, HomeStorageDetailActivity.class));
            }
        });
    }
}
