package com.zibo.qipeng.asphalt.fragment.map;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.NaviPara;
import com.amap.api.maps.model.animation.Animation;
import com.amap.api.maps.model.animation.ScaleAnimation;
import com.zibo.qipeng.asphalt.MainActivity;
import com.zibo.qipeng.asphalt.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zongshuo on 2019/6/21
 *
 * @author zongshuo ps: good luck ,ai ni o.
 */
public class MapFragment extends Fragment implements AMap.OnMarkerClickListener {
    View currentView;
    @BindView(R.id.map)
    MapView map;
    Unbinder unbinder;
    private AMap aMap;
    MainActivity mActivity;
    private double[] coords = {116.357126,39.838522,
            116.292735,39.475891,117.258593,38.975045,
            116.798661,38.412723,116.357126,37.524283,
            117.911697,37.406963
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        currentView = inflater.inflate(R.layout.fragment_map, null);
        unbinder = ButterKnife.bind(this, currentView);
        map.onCreate(savedInstanceState);// 此方法必须重写
        init();
        initView();
        return currentView;
    }


    /**
     * 初始化AMap对象
     */
    private void init() {

        if (aMap == null) {
            aMap = map.getMap();
            aMap.setOnMarkerClickListener(this);
        }

    }


    private void initView(){
//        LatLng latLng = new LatLng(37.524283, 117.911697);
//        MarkerOptions options = new MarkerOptions();
//        options.position(latLng);
//        TextView textView = new TextView(mActivity.getApplicationContext());
//        textView.setText("山东卓创有限公司");
//        textView.setGravity(Gravity.CENTER);
//        textView.setTextColor(Color.BLACK);
//        textView.setBackgroundResource(R.mipmap.custom_info_bubble);
//        options.icon(BitmapDescriptorFactory.fromView(textView));
//        aMap.addMarker(options);
//        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
        readLatLngs();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        map.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        map.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        map.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        map.onSaveInstanceState(outState);
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

    private void readLatLngs() {
        for (int i = 0; i < coords.length; i += 2) {
            LatLng latLng = new LatLng(coords[i + 1], coords[i]);
            MarkerOptions options = new MarkerOptions();
            options.position(latLng);
            TextView textView = new TextView(mActivity.getApplicationContext());
            textView.setText("山东卓创有限公司"+i);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.BLACK);
            textView.setBackgroundResource(R.mipmap.custom_info_bubble);
            options.icon(BitmapDescriptorFactory.fromView(textView));
            aMap.addMarker(options);
        }
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(39.838522,116.357126), 6));
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
            AMapUtils.openAMapNavi(naviPara, mActivity.getApplicationContext());
        } catch (com.amap.api.maps.AMapException e) {
            // 如果没安装会进入异常，调起下载页面
            AMapUtils.getLatestAMapApp(mActivity.getApplicationContext());
        }
//        aMap.clear();
        return false;
    }
}
