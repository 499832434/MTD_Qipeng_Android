package com.zibo.qipeng.asphalt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.zibo.qipeng.asphalt.entity.TabEntity;
import com.zibo.qipeng.asphalt.fragment.SimpleCardFragment;
import com.zibo.qipeng.asphalt.fragment.home.HomeFragment;
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
            R.mipmap.ckd, R.mipmap.ckd,
            R.mipmap.ckd, R.mipmap.ckd, R.mipmap.ckd,};
    private int[] mIconUnselectIds = {
            R.mipmap.sy, R.mipmap.dt,
            R.mipmap.tz,R.mipmap.gwc,R.mipmap.wd};



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        checkPermission();

        initView();
//        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Logger.e("aaa","5555");
//                getList(1,108);
//            }
//        });
    }


    private void checkPermission() {
        XXPermissions.with(this)
                //.constantRequest() //可设置被拒绝后继续申请，直到用户授权或者永久拒绝
                //.permission(Permission.SYSTEM_ALERT_WINDOW, Permission.REQUEST_INSTALL_PACKAGES) //支持请求6.0悬浮窗权限8.0请求安装权限
                .permission(Permission.READ_EXTERNAL_STORAGE,
                        Permission.WRITE_EXTERNAL_STORAGE) //不指定权限则自动获取清单中的危险权限
                .request(new OnPermission() {

                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {

                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {

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
        vp_main.setOffscreenPageLimit(3);

        mFragments.add(new HomeFragment());
        mFragments.add(SimpleCardFragment.getInstance("地图"));
        mFragments.add(SimpleCardFragment.getInstance("投资"));
        mFragments.add(SimpleCardFragment.getInstance("购物车"));
        mFragments.add(SimpleCardFragment.getInstance("我的"));

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        tl_main.setTabData(mTabEntities);
        tl_main.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
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
}
