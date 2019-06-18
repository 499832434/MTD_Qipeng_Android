package com.zibo.qipeng.asphalt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gyf.barlibrary.ImmersionBar;

/**
 * Created by zongshuo on 2019/5/27
 *
 * @author zongshuo ps: good luck ,ai ni o.
 */
public class BaseActivity extends AppCompatActivity {
    protected ImmersionBar mImmersionBar;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化沉浸式
        if (isImmersionBarEnabled())
            initImmersionBar();
    }


    protected boolean isImmersionBarEnabled() {
        return true;
    }

    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.navigationBarColor(R.color.white).statusBarDarkFont(true, 0.2f).init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();  //在BaseActivity里销毁
    }
}
