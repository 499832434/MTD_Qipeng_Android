package com.zibo.qipeng.asphalt.activity.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.NaviPara;
import com.amap.api.maps.model.animation.Animation;
import com.amap.api.maps.model.animation.ScaleAnimation;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zibo.qipeng.asphalt.BaseActivity;
import com.zibo.qipeng.asphalt.R;
import com.zibo.qipeng.asphalt.adapter.home.HomeRecommendAdapter;

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
public class HomeProductActivity extends BaseActivity implements AMap.OnMarkerClickListener, AMap.OnInfoWindowClickListener {


    @BindView(R.id.rv_home_recommend)
    RecyclerView rv_home_recommend;
    List<String> list = new ArrayList<>();
    HomeRecommendAdapter adapter;
    @BindView(R.id.map)
    MapView map;
    @BindView(R.id.b_cat)
    Button b_cat;
    @BindView(R.id.b_buy)
    Button b_buy;
    private AMap aMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_product);
        ButterKnife.bind(this);
        map.onCreate(savedInstanceState);// 此方法必须重写
        init();
        initView();

    }


    private void initView() {

        LatLng latLng = new LatLng(37.524283, 117.911697);
        MarkerOptions options = new MarkerOptions();
        options.position(latLng);
        options.title("位置:");
        options.snippet("山东卓创有限公司");

//        TextView textView = new TextView(getApplicationContext());
//        textView.setText("山东卓创有限公司");
//        textView.setGravity(Gravity.CENTER);
//        textView.setTextColor(Color.BLACK);
//        textView.setBackgroundResource(R.mipmap.custom_info_bubble);
//        options.icon(BitmapDescriptorFactory.fromView(textView));


        Marker marker = aMap.addMarker(options);
        Animation markerAnimation = new ScaleAnimation(0, 1, 0, 1); //初始化生长效果动画
        markerAnimation.setDuration(1000);  //设置动画时间 单位毫秒
        marker.setAnimation(markerAnimation);
        marker.startAnimation();
        marker.showInfoWindow();
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));







        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }

        adapter = new HomeRecommendAdapter(HomeProductActivity.this, R.layout.item_home_recommend, list);
        rv_home_recommend.setAdapter(adapter);
        rv_home_recommend.setLayoutManager(new GridLayoutManager(HomeProductActivity.this, 2));
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(HomeProductActivity.this, HomeAsphaltActivity.class));
            }
        });
    }


    /**
     * 初始化AMap对象
     */
    private void init() {
//        LatLng latLng=new LatLng(37.524283, 117.911697);
//        CameraPosition LUJIAZUI = new CameraPosition.Builder().target(latLng).zoom(18).bearing(0).tilt(30).build();
//        AMapOptions aOptions = new AMapOptions();
//        aOptions.zoomGesturesEnabled(false);// 禁止通过手势缩放地图
//        aOptions.scrollGesturesEnabled(false);// 禁止通过手势移动地图
//        aOptions.tiltGesturesEnabled(false);// 禁止通过手势倾斜地图
//        aOptions.camera(LUJIAZUI);

        if (aMap == null) {
            aMap = map.getMap();
            aMap.setOnMarkerClickListener(this);
            aMap.setOnInfoWindowClickListener(this);
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        map.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        map.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        map.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        map.onSaveInstanceState(outState);
    }

    @OnClick({R.id.b_cat, R.id.b_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.b_cat:
                break;
            case R.id.b_buy:
                break;
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        // 构造导航参数
        NaviPara naviPara = new NaviPara();
        // 设置终点位置
        naviPara.setTargetPoint(marker.getPosition());
        // 设置导航策略，这里是避免拥堵
        naviPara.setNaviStyle(AMapUtils.DRIVING_AVOID_CONGESTION);
        try {
            // 调起高德地图导航
            AMapUtils.openAMapNavi(naviPara, getApplicationContext());
        } catch (com.amap.api.maps.AMapException e) {
            // 如果没安装会进入异常，调起下载页面
            AMapUtils.getLatestAMapApp(getApplicationContext());
        }
//        aMap.clear();
        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        // 构造导航参数
        NaviPara naviPara = new NaviPara();
        // 设置终点位置
        naviPara.setTargetPoint(marker.getPosition());
        // 设置导航策略，这里是避免拥堵
        naviPara.setNaviStyle(AMapUtils.DRIVING_AVOID_CONGESTION);
        try {
            // 调起高德地图导航
            AMapUtils.openAMapNavi(naviPara, getApplicationContext());
        } catch (com.amap.api.maps.AMapException e) {
            // 如果没安装会进入异常，调起下载页面
            AMapUtils.getLatestAMapApp(getApplicationContext());
        }
    }
}
