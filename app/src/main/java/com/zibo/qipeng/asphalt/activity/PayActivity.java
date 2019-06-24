package com.zibo.qipeng.asphalt.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.zibo.qipeng.asphalt.BaseActivity;
import com.zibo.qipeng.asphalt.R;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zongshuo on 2019/6/24
 *
 * @author zongshuo ps: good luck ,ai ni o.
 */
public class PayActivity extends BaseActivity {
    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button2)
    Button button2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button1, R.id.button2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button1:
                break;
            case R.id.button2:
                break;
        }
    }


    /**
     * 支付宝支付订单
     * get_alipay_prepayid
     */
//    private void getAliPayPrePayId(OrderListItem item) {
//        if (!NetworkUtils.isNetworkAvailable(this)) {
//            Toast.makeText(this, "当前无网络连接，请稍后重试", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        HashMap<String, String> params = getNetworkRequestHashMap();
//        String userName = PrefUtils.getString(this,
//                InitApp.USER_PRIVATE_DATA,
//                InitApp.USER_NAME_KEY, "");
//
//        params.put("paytype", "composite");
//        params.put("out_trade_no", item.getPre_pay_id() + "");
//        params.put("body", userName);
//        params.put("subject", "卓创资讯支付宝支付");
//        params.put("total_fee", item.getPrice() + "");
//        params.put("pros", item.getPros());
//        String sig = ((InitApp) getApplication()).getPaySig(params);
//        params.put("sign", sig);
//
//        BaseNetwork.getInstance().sendRequest(BaseNetwork.BASE_URL_TYPE.PAY,
//                "get_alipay_prepayid",
//                BaseNetwork.REQUEST_TYPE.POST, params, new ResponseCallBack() {
//
//                    @Override
//                    public void success(BaseData data) {
//                        if ("0".equals(data.getCode())) {
//                            String obj = String.valueOf(data.getInfo());
//                            aliPay(obj);
//                        } else {
//                            Toast.makeText(PayChooseDetailActivity.this, data.getMsg(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void error(String msg, String code) {
//                    }
//                });
//    }
//
//    private void aliPay(final String info) {
//        if (!NetworkUtils.isNetworkAvailable(this)) {
//            Toast.makeText(this, "当前无网络连接，请稍后重试", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        Runnable payRunnable = new Runnable() {
//
//            @Override
//            public void run() {
//                PayTask alipay = new PayTask(PayActivity.this);
//                Map<String, String> result = alipay.payV2(info, true);
//                String resultStatus = result.get("resultStatus");
//                final String memo = result.get("memo");
//
//                /*
//                 * {resultStatus=9000,
//                 * result=partner="2088811481468205"&seller_id="2088811481468205"&out_trade_no="415267"&subject="卓创资讯支付宝支付"&body="18615144255"&total_fee="0.01"&notify_url="http://mapi.sci99.com/pay/alipay_notify"&service="mobile.securitypay.pay"&payment_type="1"&_input_charset="utf-8"&it_b_pay="30m"&show_url="m.alipay.com"&success="true"&sign_type="RSA"&sign="gqRh7/HVUTKpKAwfu1HeSSTvhqeG140S5uXE5XsvcWvoYqX1xFpNpxUpT+2hKn9u95cIsHsWDrlxGlGEF2Ymnt9hDChGSqQmVmmv9rVUrviseySLhIITLIjkggtsrzHik1ENMdaJoP8ohWDSQ/PKjlGyUJnkxEbB3SJQ4jiebHw=",
//                 * memo=}
//                 * */
//                if ("9000".equals(resultStatus)) {
//                    gotoPaySuccess();
//                } else {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(PayActivity.this, memo, Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }
//            }
//        };
//        // 必须异步调用
//        Thread payThread = new Thread(payRunnable);
//        payThread.start();
//    }
}
