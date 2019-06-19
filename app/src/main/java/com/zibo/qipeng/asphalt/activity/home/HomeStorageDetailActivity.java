package com.zibo.qipeng.asphalt.activity.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.zibo.qipeng.asphalt.BaseActivity;
import com.zibo.qipeng.asphalt.R;
import com.zibo.qipeng.asphalt.fragment.SimpleCardFragment;
import com.zibo.qipeng.asphalt.fragment.home.stroage.StroageCommodityFragment;
import com.zibo.qipeng.asphalt.view.CustomTitleBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zongshuo on 2019/6/19
 *
 * @author zongshuo ps: good luck ,ai ni o.
 */
public class HomeStorageDetailActivity extends BaseActivity {
    @BindView(R.id.ctb)
    CustomTitleBar ctb;
    @BindView(R.id.stl_storage)
    SlidingTabLayout stl_storage;
    @BindView(R.id.vp_storage)
    ViewPager vp_storage;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles = {"商品", "仓库简介","地理位置"};
    private MyPagerAdapter mAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_storage_detail);
        ButterKnife.bind(this);
        mImmersionBar.statusBarView(R.id.view_top).init();
        initView();
    }

    private void initView() {
        ctb.setFlagClickListener(-1, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ctb.setFlagClickListener(0, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mFragments.add(new StroageCommodityFragment());
        mFragments.add(SimpleCardFragment.getInstance("仓库简介"));
        mFragments.add(SimpleCardFragment.getInstance("地理位置"));

        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        vp_storage.setAdapter(mAdapter);
        stl_storage.setViewPager(vp_storage);
    }


    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
