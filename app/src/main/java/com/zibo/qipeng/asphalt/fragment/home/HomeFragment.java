package com.zibo.qipeng.asphalt.fragment.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.zibo.qipeng.asphalt.MainActivity;
import com.zibo.qipeng.asphalt.R;
import com.zibo.qipeng.asphalt.activity.home.HomeAsphaltActivity;
import com.zibo.qipeng.asphalt.activity.home.HomeLakeActivity;
import com.zibo.qipeng.asphalt.activity.home.HomeProductActivity;
import com.zibo.qipeng.asphalt.activity.home.HomeStorageActivity;
import com.zibo.qipeng.asphalt.adapter.home.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by zongshuo on 2019/6/17
 *
 * @author zongshuo ps: good luck ,ai ni o.
 */
public class HomeFragment extends Fragment {
    View currentView;
    @BindView(R.id.chart)
    LineChart chart;
    Unbinder unbinder;
    @BindView(R.id.rv_home)
    RecyclerView rv_home;
    @BindView(R.id.ll_logistics)
    LinearLayout ll_logistics;
    @BindView(R.id.ll_storage)
    LinearLayout ll_storage;
    @BindView(R.id.ll_lake)
    LinearLayout ll_lake;
    @BindView(R.id.ll_asphalt)
    LinearLayout ll_asphalt;
    private MainActivity mActivity;
    ArrayList<ILineDataSet> sets;
    List<String> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        currentView = inflater.inflate(R.layout.fragment_home, null);
        unbinder = ButterKnife.bind(this, currentView);
        initData();
        initView();
        return currentView;
    }

    private void initData() {
        ArrayList<Entry> values1 = new ArrayList<>();

        for (int i = 0; i < 24; i++) {
            values1.add(new Entry(i, (int) (Math.random() * 65) + 40, "abc"));
        }

//        values1.add(new Entry(3/11, (int) (Math.random() * 65) + 40));
//        values1.add(new Entry(i, (int) (Math.random() * 65) + 40));
//        values1.add(new Entry(i, (int) (Math.random() * 65) + 40));
//        values1.add(new Entry(i, (int) (Math.random() * 65) + 40));
//        values1.add(new Entry(i, (int) (Math.random() * 65) + 40));
//        values1.add(new Entry(i, (int) (Math.random() * 65) + 40));


        LineDataSet d1 = new LineDataSet(values1, "分布图");
        d1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        d1.setLineWidth(2.5f);
        d1.setDrawCircles(false);
        d1.setHighLightColor(Color.rgb(244, 117, 117));
        d1.setDrawValues(true);
        sets = new ArrayList<>();
        sets.add(d1);


        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
    }

    private void initView() {

        Typeface mTf = Typeface.createFromAsset(mActivity.getAssets(), "OpenSans-Regular.ttf");


        chart.getDescription().setEnabled(false);
        chart.setDrawGridBackground(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTf);
        xAxis.setDrawAxisLine(true);
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        xAxis.setAvoidFirstLastClipping(true);
//        xAxis.setTextColor(Color.parseColor("#4B9BF7"));
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int intValue = (int) value;
                return "03/11";
            }
        });

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTypeface(mTf);
//        leftAxis.setLabelCount(6, false);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        leftAxis.setAxisMaximum(120f); //
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
//        leftAxis.setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                int intValue = (int) value;
//                return "aaa";
//            }
//        });


        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setTypeface(mTf);
//        rightAxis.setLabelCount(6, false);
        rightAxis.setAxisMinimum(0f); //
        rightAxis.setAxisMaximum(10f); // this rep// this replaces setStartAtZero(true)
        rightAxis.setDrawGridLines(false);
//        rightAxis.enableGridDashedLine(10f, 10f, 0f);

        // set data
        chart.setData(new LineData(sets));


        chart.animateX(750);


        rv_home.setNestedScrollingEnabled(false);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(mActivity);
        layoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        rv_home.setLayoutManager(layoutManager1);

        Drawable drawable = ContextCompat.getDrawable(mActivity, R.drawable.custom_divider);
        DividerItemDecoration decoration = new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL);
        decoration.setDrawable(drawable);
        rv_home.addItemDecoration(decoration);

        HomeAdapter adapter = new HomeAdapter(mActivity, R.layout.item_home_list, list);
        rv_home.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mActivity, HomeProductActivity.class));
            }
        });
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

    @OnClick({R.id.ll_logistics, R.id.ll_storage, R.id.ll_lake, R.id.ll_asphalt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_logistics:
                break;
            case R.id.ll_storage:
                startActivity(new Intent(mActivity,HomeStorageActivity.class));
                break;
            case R.id.ll_lake:
                startActivity(new Intent(mActivity,HomeLakeActivity.class));
                break;
            case R.id.ll_asphalt:
                startActivity(new Intent(mActivity,HomeAsphaltActivity.class));
                break;
        }
    }
}
