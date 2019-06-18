package com.zibo.qipeng.asphalt.activity.home;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zibo.qipeng.asphalt.BaseActivity;
import com.zibo.qipeng.asphalt.R;
import com.zibo.qipeng.asphalt.adapter.home.HomeAsphaltAdapter;
import com.zibo.qipeng.asphalt.adapter.home.HomeScreenAdapter;
import com.zibo.qipeng.asphalt.entity.Screen;
import com.zibo.qipeng.asphalt.view.PinnedHeaderExpandableListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zongshuo on 2019/6/18
 *
 * @author zongshuo ps: good luck ,ai ni o.
 */
public class HomeAsphaltActivity extends BaseActivity {
    @BindView(R.id.rv_home_asphalt)
    RecyclerView rv_home_asphalt;
    List<String> list = new ArrayList<>();
    HomeAsphaltAdapter adapter;
    @BindView(R.id.tv_multiple)
    TextView tv_multiple;
    @BindView(R.id.tv_sales)
    TextView tv_sales;
    @BindView(R.id.tv_unit)
    TextView tv_unit;
    @BindView(R.id.tv_screen)
    TextView tv_screen;
    @BindView(R.id.dl_screen)
    DrawerLayout dl_screen;
    @BindView(R.id.phel_screen)
    PinnedHeaderExpandableListView phel_screen;
    HomeScreenAdapter adapter1;

    List<Screen> screenlist=new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_asphalt);
        ButterKnife.bind(this);
        mImmersionBar.statusBarView(R.id.view_top).init();

        initView();

    }

    private void initView() {
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(HomeAsphaltActivity.this);
        layoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        rv_home_asphalt.setLayoutManager(layoutManager1);

        Drawable drawable = ContextCompat.getDrawable(HomeAsphaltActivity.this, R.drawable.custom_divider);
        DividerItemDecoration decoration = new DividerItemDecoration(HomeAsphaltActivity.this, DividerItemDecoration.VERTICAL);
        decoration.setDrawable(drawable);
        rv_home_asphalt.addItemDecoration(decoration);

        adapter = new HomeAsphaltAdapter(HomeAsphaltActivity.this, R.layout.item_home_asphalt_list, list);
        rv_home_asphalt.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(HomeAsphaltActivity.this, HomeProductActivity.class));
            }
        });


        for(int i=0;i<10;i++){
            Screen screen=new Screen();
            screen.setTitle("标题"+i);
            List<String> list=new ArrayList<>();
            list.add("内容"+i);
            list.add("内容"+i);
            list.add("内容"+i);
            screen.setContentList(list);
            screenlist.add(screen);
        }
        phel_screen.setGroupIndicator(null);
//        phel_screen.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//            @Override
//            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
//                return true;
//            }
//        });
        adapter1=new HomeScreenAdapter(HomeAsphaltActivity.this,screenlist);
        phel_screen.setAdapter(adapter1);
        for (int i = 0; i < screenlist.size(); i++) {
            phel_screen.expandGroup(i);
        }
    }

    @OnClick({R.id.tv_multiple, R.id.tv_sales, R.id.tv_unit, R.id.tv_screen})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_multiple:
                break;
            case R.id.tv_sales:
                break;
            case R.id.tv_unit:
                break;
            case R.id.tv_screen:
                if (dl_screen.isDrawerOpen(Gravity.RIGHT)) {
                    dl_screen.closeDrawer(Gravity.RIGHT);
                } else {
                    dl_screen.openDrawer(Gravity.RIGHT);
                }
                break;
        }
    }
}
