package com.zibo.qipeng.asphalt.activity.home;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.animation.Animation;
import com.amap.api.maps.model.animation.ScaleAnimation;
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
public class HomeProductActivity extends BaseActivity {


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
        Marker marker = aMap.addMarker(options);
        Animation markerAnimation = new ScaleAnimation(0, 1, 0, 1); //初始化生长效果动画
        markerAnimation.setDuration(1000);  //设置动画时间 单位毫秒
        marker.setAnimation(markerAnimation);
        marker.startAnimation();
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));


        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }

        adapter = new HomeRecommendAdapter(HomeProductActivity.this, R.layout.item_home_recommend, list);
        rv_home_recommend.setAdapter(adapter);
        rv_home_recommend.setLayoutManager(new GridLayoutManager(HomeProductActivity.this, 2));
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
}
