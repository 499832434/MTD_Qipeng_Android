package com.zibo.qipeng.asphalt.activity.home;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.zibo.qipeng.asphalt.BaseActivity;
import com.zibo.qipeng.asphalt.R;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_storage);
        ButterKnife.bind(this);
        mImmersionBar.statusBarView(R.id.view_top).init();
        initView();
    }
    private void initView(){

    }
}
