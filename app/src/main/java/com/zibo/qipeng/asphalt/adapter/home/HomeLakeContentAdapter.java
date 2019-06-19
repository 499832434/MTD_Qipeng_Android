package com.zibo.qipeng.asphalt.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qmuiteam.qmui.layout.QMUILinearLayout;
import com.zibo.qipeng.asphalt.R;
import com.zibo.qipeng.asphalt.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zongshuo on 2019/6/18
 *
 * @author zongshuo ps: good luck ,ai ni o.
 */
public class HomeLakeContentAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private Context context;

    private List<String> data = new ArrayList<>();

    public HomeLakeContentAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
        this.data = data;
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        //可链式调用赋值
//        helper.setText(R.id.tv_product, item);
        QMUILinearLayout layoutForTest=helper.getView(R.id.layout_for_test);
        layoutForTest.setRadiusAndShadow(DensityUtil.dip2px(context, 3), DensityUtil.dip2px(context, 15), 1.5f);
    }

}
