package com.zibo.qipeng.asphalt.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zibo.qipeng.asphalt.BaseActivity;
import com.zibo.qipeng.asphalt.R;
import com.zibo.qipeng.asphalt.adapter.home.HomeLakeAdapter;
import com.zibo.qipeng.asphalt.adapter.home.HomeStorageComAdapter;
import com.zibo.qipeng.asphalt.view.CustomTitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zongshuo on 2019/6/19
 *
 * @author zongshuo ps: good luck ,ai ni o.
 */
public class HomeLakeActivity extends BaseActivity {
    @BindView(R.id.ctb)
    CustomTitleBar ctb;
    @BindView(R.id.rv_home_lake)
    RecyclerView rv_home_lake;
    List<String> list = new ArrayList<>();
    HomeLakeAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_lake);
        ButterKnife.bind(this);
        mImmersionBar.statusBarView(R.id.view_top).init();
        initView();
    }

    private void initView(){
        ctb.setFlagClickListener(-1, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        for (int i = 0; i < 5; i++) {
            list.add("" + i);
        }


        LinearLayoutManager layoutManager1 = new LinearLayoutManager(HomeLakeActivity.this);
        layoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        rv_home_lake.setLayoutManager(layoutManager1);

        adapter = new HomeLakeAdapter(HomeLakeActivity.this, R.layout.item_home_lake, list);
        rv_home_lake.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(HomeLakeActivity.this, HomeLakeContentActivity.class));
            }
        });
    }
}
