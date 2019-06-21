package com.zibo.qipeng.asphalt.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
public class LoginActivity extends BaseActivity {
    @BindView(R.id.tv_register)
    TextView tv_register;
    @BindView(R.id.b_login)
    Button b_login;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mImmersionBar.statusBarView(R.id.view_top).init();
    }

    @OnClick({R.id.tv_register, R.id.b_login,R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_register:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
            case R.id.b_login:
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
