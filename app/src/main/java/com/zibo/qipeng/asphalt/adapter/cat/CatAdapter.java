package com.zibo.qipeng.asphalt.adapter.cat;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zongshuo on 2019/6/18
 *
 * @author zongshuo ps: good luck ,ai ni o.
 */
public class CatAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private Context context;

    private List<String> data = new ArrayList<>();

    public CatAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
        this.data = data;
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        //可链式调用赋值
//        helper.setText(R.id.tv_product, item);

    }

}
