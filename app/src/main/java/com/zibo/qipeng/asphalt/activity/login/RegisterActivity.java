package com.zibo.qipeng.asphalt.activity.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.zibo.qipeng.asphalt.BaseActivity;
import com.zibo.qipeng.asphalt.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zongshuo on 2019/6/21
 *
 * @author zongshuo ps: good luck ,ai ni o.
 */
public class RegisterActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.b_register)
    Button bRegister;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        mImmersionBar.statusBarView(R.id.view_top).init();
    }

    @OnClick({R.id.iv_back, R.id.b_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.b_register:
                break;
        }
    }
}
