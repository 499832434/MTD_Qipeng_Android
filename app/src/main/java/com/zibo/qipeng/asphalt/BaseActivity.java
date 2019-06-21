package com.zibo.qipeng.asphalt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gyf.barlibrary.ImmersionBar;
import com.zibo.qipeng.asphalt.initapp.InitApp;
import com.zibo.qipeng.asphalt.utils.PrefUtils;

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


    /**
     * 获取用户的登录信息
     */
    public String getUserInfo(int flag) {
        switch (flag) {
            case 0://用户id
                return PrefUtils.getString(BaseActivity.this, InitApp.USER_PRIVATE_DATA, InitApp.USER_ID_KEY, "");
        }
        return "";
    }
}
