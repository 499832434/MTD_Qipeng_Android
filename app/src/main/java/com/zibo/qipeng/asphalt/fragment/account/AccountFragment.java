package com.zibo.qipeng.asphalt.fragment.account;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.flyco.tablayout.SlidingTabLayout;
import com.zibo.qipeng.asphalt.MainActivity;
import com.zibo.qipeng.asphalt.R;
import com.zibo.qipeng.asphalt.activity.account.VipSetActivity;
import com.zibo.qipeng.asphalt.activity.home.HomeStorageDetailActivity;
import com.zibo.qipeng.asphalt.fragment.SimpleCardFragment;
import com.zibo.qipeng.asphalt.fragment.home.stroage.StroageCommodityFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by zongshuo on 2019/6/21
 *
 * @author zongshuo ps: good luck ,ai ni o.
 */
public class AccountFragment extends Fragment {
    MainActivity mActivity;
    View currentView;
    @BindView(R.id.iv_photo)
    ImageView iv_photo;
    @BindView(R.id.iv_set)
    ImageView iv_set;
    @BindView(R.id.stl_order)
    SlidingTabLayout stl_order;
    @BindView(R.id.vp_order)
    ViewPager vp_order;
    Unbinder unbinder;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles = {"全部订单", "未发货","已发货"};
    private MyPagerAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        currentView = inflater.inflate(R.layout.fragment_account, null);
        unbinder = ButterKnife.bind(this, currentView);
        initView();
        return currentView;
    }

    private void initView() {


        mFragments.add(new OrderFragment());
        mFragments.add(SimpleCardFragment.getInstance("未发货"));
        mFragments.add(SimpleCardFragment.getInstance("已发货"));

        mAdapter = new MyPagerAdapter(getFragmentManager());
        vp_order.setAdapter(mAdapter);
        stl_order.setViewPager(vp_order);
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

    @OnClick({R.id.iv_photo, R.id.iv_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_photo:
                break;
            case R.id.iv_set:
                startActivity(new Intent(mActivity,VipSetActivity.class));
                break;
        }
    }
}
