package com.zibo.qipeng.asphalt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.zibo.qipeng.asphalt.activity.login.LoginActivity;
import com.zibo.qipeng.asphalt.entity.TabEntity;
import com.zibo.qipeng.asphalt.fragment.SimpleCardFragment;
import com.zibo.qipeng.asphalt.fragment.account.AccountFragment;
import com.zibo.qipeng.asphalt.fragment.cat.CatFragment;
import com.zibo.qipeng.asphalt.fragment.home.HomeFragment;
import com.zibo.qipeng.asphalt.fragment.home.stroage.StroageCommodityFragment;
import com.zibo.qipeng.asphalt.fragment.map.MapFragment;
import com.zibo.qipeng.asphalt.initapp.InitApp;
import com.zibo.qipeng.asphalt.utils.CookieStringRequest;
import com.zibo.qipeng.asphalt.utils.Logger;
import com.zibo.qipeng.asphalt.utils.SDFileHelper;
import com.zibo.qipeng.asphalt.view.CustomViewPager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    StringBuffer sb = new StringBuffer();
    @BindView(R.id.vp_main)
    CustomViewPager vp_main;
    @BindView(R.id.tl_main)
    CommonTabLayout tl_main;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles = {"首页", "地图","投资","购物车", "我的"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private int[] mIconSelectIds = {
            R.mipmap.sy, R.mipmap.dt,
            R.mipmap.tz,R.mipmap.gwc,R.mipmap.wd};
    private int[] mIconUnselectIds = {
            R.mipmap.sy, R.mipmap.dt,
            R.mipmap.tz,R.mipmap.gwc,R.mipmap.wd};



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getList(1,94);
            }
        });
    }




    private void getList(final int page, final int end) {
        HashMap<String, String> params = new HashMap<>();
        params.put("_id", "12297");
        params.put("_key", "a8fd7e");
        params.put("tab", "reply");
        params.put("page", page + "");
        params.put("perPage", "100");
//        params.put("class_id", "4");
        String url = InitApp.getUrlByParameter(ApiConstants.BASE_MAIN_URL_API, params, false);
        Logger.e("getListRL", url);

        CookieStringRequest request = new CookieStringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Logger.e("getList", response);
                        try {
                            JSONObject object = new JSONObject(response);
                            JSONObject obj = object.getJSONObject("data");
                            JSONArray array = obj.getJSONArray("rows");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object1 = array.getJSONObject(i);
                                sb.append(object1.getString("encode_qid") + "   " + object1.getString("title"));
                                sb.append("\r\n");
//                                Logger.e("==="+i,object1.getString("encode_qid")+"   "+object1.getString("title"));
                            }
                            Logger.e("StringBuffer", sb.toString());


                            int abc = page;
                            if (abc < end) {
                                abc++;
                                getList(abc, end);
                            } else {
                                SDFileHelper.writeData(sb.toString());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );
        InitApp.initApp.addToRequestQueue(request);
    }


    private void initView() {


        tl_main = findViewById(R.id.tl_main);
        vp_main = findViewById(R.id.vp_main);
        vp_main.setOffscreenPageLimit(5);

        mFragments.add(new HomeFragment());
        mFragments.add(new MapFragment());
        mFragments.add(SimpleCardFragment.getInstance("投资"));
        mFragments.add(new CatFragment());
        mFragments.add(new AccountFragment());

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        tl_main.setTabData(mTabEntities);
        tl_main.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if(position==3||position==4){
                    if(TextUtils.isEmpty(getUserInfo(0))){
                        startActivity(new Intent(MainActivity.this,LoginActivity.class));
                        vp_main.setCurrentItem(position);
                        vp_main.setCurrentItem(0);
                        return;
                    }
                }
                vp_main.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        vp_main.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                tl_main.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        vp_main.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));


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

    private long mExitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "连续点击返回键将退出客户端", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
