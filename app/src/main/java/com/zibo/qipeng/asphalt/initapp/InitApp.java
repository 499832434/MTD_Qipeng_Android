package com.zibo.qipeng.asphalt.initapp;

import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by zongshuo on 2018/11/19
 *
 * @author zongshuo ps: good luck ,ai ni o.
 */
public class InitApp extends Application {

    private SQLiteDatabase db;


    public final static String weixinId = "wxfbec1547850539c4";
    public final static String weixinSercet = "96ec7945af8d93a7806137f8701cff4b";
    public final static String qqId = "101567668";
    public final static String qqKey = "2901c8484960b4bc1f86d4d0ba3835ed";


    public static final String HMAC_SHA_1 = "HmacSHA1";
    public static final String DOWNLOAD_TASK_PREF = "DOWNLOAD_TASK_PREF";
    public static final String DOWNLOAD_TASK_ID_KEY = "DOWNLOAD_TASK_ID_KEY";
    public static final String SERVICE_TEL = "4008115599";
    public static final String SERVICE_FORMAT_TEL = "400-811-5599";

    public static final String SIGN_TOMORROW_SCORE_KEY = "SIGN_TOMORROW_SCORE_KEY";//明日签到可获积分
    public static final String SIGN_DAYS_KEY = "SIGN_DAYS_KEY";//累计签到天数
    public static final String LGOIN_DAYS_KEY = "LGOIN_DAYS_KEY";//连续签到天数
    public static final String ADD_SCORE_KEY = "ADD_SCORE_KEY";//今日增加积分
    public static final String TOTAL_SCORE_KEY = "TOTAL_SCORE_KEY";//累计积分
    public static final String BIND_PHONE_KEY = "BIND_PHONE_KEY";//绑定手机号

    public static final String NEWS_DEATIL_DATE_KEY = "NEWS_DEATIL_DATE_KEY";//记录分享日期
    public static final String NEWS_DEATIL_SHARE_KEY = "NEWS_DEATIL_SHARE_KEY";//判断是否显示分享提示
    public static final String NEWS_DEATIL_WARN_KEY = "NEWS_DEATIL_WARN_KEY";//分享提示是否显示过


    private String appPkgName = null;
    public static final String DEVICE_TYPE = "0";
    public static final String PRODUCT_TYPE = "16";
    public static String VERSION = "";

    public static final String PREF_FOUND_SORT = "PREF_FOUND_SORT";//发现排序
    public static final String PREF_IMEI_KEY = "PREF_IMEI_KEY";//IMEI号
    public static final String BIND_TEL = "BIND_TEL";
    public static final String USER_TYPE = "USER_TYPE";//A/B版用户
    public static final String USER_PRIVATE_DATA = "USER_PRIVATE_DATA";
    public static final String USER_PERMISSION_KEY3 = "USER_PERMISSION_KEY3";
    public static final String USER_ID_KEY = "USER_ID_KEY";
    public static final String SCROLL_INFO_KEY = "SCROLL_INFO_KEY";//滚动条提醒信息
    public static final String USER_PRODUCT_TYPE_KEY = "USER_PRODUCT_TYPE_KEY";
    public static final String USER_NAME_KEY = "USER_NAME_KEY";
    public static final String USER_NAME_TEMPORARY_KEY = "USER_NAME_TEMPORARY_KEY";//临时保存账户
    public static final String ACCESS_TOKEN_KEY = "ACCESS_TOKEN_KEY";
    public static final String PREF_FONT_KEY = "FONT_SIZE";
    public static final int PREF_FONT_SIZE_SMALL_CHANGE_VALUE = 0;
    public static final int PREF_FONT_SIZE_MIDDLE_CHANGE_VALUE = 4;
    public static final int PREF_FONT_SIZE_LARGE_CHANGE_VALUE = 8;
    public static final int PREF_FONT_SIZE_SUPER_LARGE_CHANGE_VALUE = 12;
    public static final String INDEX_DATA_KEY = "INDEX_DATA_KEY";
    public static final String PREF_NEW_SIGN_DATE_KEY = "PREF_NEW_SIGN_DATE_KEY";
    public static final String PREF_HAS_SET_OLD_STYLE_KEY = "PREF_HAS_SET_OLD_STYLE_KEY";
    public static final String PREF_FIRST_PUSH_SETTING = "PREF_FIRST_PUSH_SETTING";//推送通知是否显示弹框

//    public static final String PUSH_DISTURB_SET_KEY = "PUSH_DISTURB_SET_KEY";//推送免打扰设置

    public static final String GUIDE_FOLLOW_FIRST_KEY = "GUIDE_FOLLOW_FIRST_KEY";//引导图提示一次(关注)
    public static final String GUIDE_NEW_FIRST_KEY = "GUIDE_NEW_FIRST_KEY";//引导图提示一次(资讯)
    public static final String GUIDE_THREE_KEY = "GUIDE_THREE_KEY";//引导图提示三次

    public static final String PREF_CONTACT_TEL_KEY = "PREF_CONTACT_TEL_KEY";
    public static final String PREF_CONTACT_NAME_KEY = "PREF_CONTACT_NAME_KEY";

    //推送相关

    public static final String APP_STATIC_PREF = "APP_STATIC_PREF";
    public static final String PREF_CLIENT_ID = "PREF_CLIENT_ID";


    public static final String RECENT_PRODUCT_LIST = "RECENT_PRODUCT_LIST";



    public static final String START_SCREEN_PICURL = "START_SCREEN_PICURL";

    public static final String IS_LAUNCHER_KEY = "IS_LAUNCHER_KEY";


    public static String INTEGRAL_PUBLIC_KEY = "A6593416ED7774670FDAFD5B58F9A140";//积分公共key
    public static String COMPOSITE_PUBLIC_KEY = "73BAA01A19F097369C418CA4A21DE4FF";//主项目公共key
    public static String PAY_PUBLIC_KEY = "cacc80f3a90abe7979cf6fb2241b0a45";//支付公共key

    private SecretKeySpec signingKey;
    private Mac mac;

    private SecretKeySpec integralSigningKey;
    private Mac integralMac;

    private SecretKeySpec paySigningKey;
    private Mac payMac;



    /**
     * Log or request TAG
     */
    public static final String DEFAULT_REQUEST_TAG = "VolleyDefaultRequestTag";

    /**
     * Global request queue for Volley
     */
    private RequestQueue mRequestQueue;
    public static InitApp initApp;




    public InitApp() {

    }
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        initApp = this;
        initApp();
    }

    public void initApp() {




        initSignatureTools();
        initIntegralSignatureTools();
        initPaySignatureTools();
    }



    public String getAndroidID() {
        String id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        return id == null ? "" : id;
    }






    private static UUID uuid = null;
    public static String DEVICE_TOKEN = "";
    public static final String PREFS_FILE = "device_id.xml";
    public static final String PREFS_DEVICE_ID = "device_id";


//    private void initUUID(Context context) throws UnsupportedEncodingException {
//        initUUID(context, null);
//    }

//    @SuppressLint("MissingPermission")
//    private void initUUID(Context context, String encoding) throws UnsupportedEncodingException {
//        String deviceId = null;
//        try {
//            deviceId = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
//        } catch (Exception ex) {
//            Log.e(getClass().getSimpleName(), ex.getMessage());
//        }
//        if (TextUtils.isEmpty(deviceId)) {
//            PrefUtils.putString(context, InitApp.USER_PRIVATE_DATA, InitApp.PREF_IMEI_KEY, "");
//        } else {
//            PrefUtils.putString(context, InitApp.USER_PRIVATE_DATA, InitApp.PREF_IMEI_KEY, deviceId);
//        }
//        try {
//            if (encoding != null) {//encoding == null判断反
//                uuid = deviceId != null ? UUID.nameUUIDFromBytes(deviceId.getBytes(encoding)) : null;
//            } else {
//                uuid = deviceId != null ? UUID.nameUUIDFromBytes(deviceId.getBytes()) : null;
//            }
//            Logger.e("deviceId", deviceId);
//            Logger.e("uuid", uuid.toString());
//        } catch (Exception ex) {
//            Log.e(getClass().getSimpleName(), ex.getMessage());
//        }
//        if (uuid == null) {
//            final String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
//            if (androidId != null) {
//                if (encoding != null) {//encoding == null判断反
//                    uuid = UUID.nameUUIDFromBytes(androidId.getBytes(encoding));
//                } else {
//                    uuid = UUID.nameUUIDFromBytes(androidId.getBytes());
//                }
//
//            } else {
//                uuid = UUID.fromString("null");
//            }
//        }
//    }



    private void initSignatureTools() {
        try {
            signingKey = new SecretKeySpec(COMPOSITE_PUBLIC_KEY.getBytes("UTF-8"), HMAC_SHA_1);
        } catch (UnsupportedEncodingException e) {
            signingKey = new SecretKeySpec(COMPOSITE_PUBLIC_KEY.getBytes(), HMAC_SHA_1);
        }
        try {
            mac = Mac.getInstance(HMAC_SHA_1);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            mac.init(signingKey);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    private void initIntegralSignatureTools() {
        try {
            integralSigningKey = new SecretKeySpec(INTEGRAL_PUBLIC_KEY.getBytes("UTF-8"), HMAC_SHA_1);
        } catch (UnsupportedEncodingException e) {
            integralSigningKey = new SecretKeySpec(INTEGRAL_PUBLIC_KEY.getBytes(), HMAC_SHA_1);
        }
        try {
            integralMac = Mac.getInstance(HMAC_SHA_1);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            integralMac.init(integralSigningKey);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }


    private void initPaySignatureTools() {
        try {
            paySigningKey = new SecretKeySpec(PAY_PUBLIC_KEY.getBytes("UTF-8"), HMAC_SHA_1);
        } catch (UnsupportedEncodingException e) {
            paySigningKey = new SecretKeySpec(PAY_PUBLIC_KEY.getBytes(), HMAC_SHA_1);
        }
        try {
            payMac = Mac.getInstance(HMAC_SHA_1);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            payMac.init(paySigningKey);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }


    public synchronized String getSig(Map<String, String> params) {
        List<String> paramsList = new ArrayList<String>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            paramsList.add(entry.getKey() + entry.getValue());
        }
        byte[] rawHmac = null;
        try {
            rawHmac = mac.doFinal(sort(paramsList).getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            rawHmac = mac.doFinal(sort(paramsList).getBytes());
        }
        String result = Base64.encodeToString(rawHmac, Base64.NO_WRAP);
        return result;
    }

    public static String getUrlByParameter(String subjectUrl, HashMap<String, String> map, Boolean isSign) {
        try {
            StringBuffer sb = new StringBuffer(subjectUrl);
            int i = 0;
            for (String key : map.keySet()) {
                if (i == 0) {
                    sb.append(key + "=" + URLEncoder.encode(map.get(key), "utf-8"));
                } else {
                    sb.append("&" + key + "=" + URLEncoder.encode(map.get(key), "utf-8"));
                }
                i++;
            }
            if (isSign) {
                sb.append("&sign=" + URLEncoder.encode(initApp.getSig(map), "utf-8"));
            }
            return sb.toString();
        } catch (Exception e) {
            return "";
        }
    }



    public synchronized String getIntegralSig(Map<String, String> params) {
        List<String> paramsList = new ArrayList<String>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            paramsList.add(entry.getKey() + entry.getValue());
        }
        byte[] rawHmac = null;
        try {
            rawHmac = integralMac.doFinal(sort(paramsList).getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            rawHmac = integralMac.doFinal(sort(paramsList).getBytes());
        }
        String result = Base64.encodeToString(rawHmac, Base64.NO_WRAP);
        return result;
    }

    public static String getIntegralUrlByParameter(String subjectUrl, HashMap<String, String> map, Boolean isSign) {
        try {
            StringBuffer sb = new StringBuffer(subjectUrl);
            int i = 0;
            for (String key : map.keySet()) {
                if (i == 0) {
                    sb.append(key + "=" + URLEncoder.encode(map.get(key), "utf-8"));
                } else {
                    sb.append("&" + key + "=" + URLEncoder.encode(map.get(key), "utf-8"));
                }
                i++;
            }
            if (isSign) {
                sb.append("&sign=" + URLEncoder.encode(initApp.getIntegralSig(map), "utf-8"));
            }
            return sb.toString();
        } catch (Exception e) {
            return "";
        }
    }


    public synchronized String getPaySig(Map<String, String> params) {
        List<String> paramsList = new ArrayList<String>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            paramsList.add(entry.getKey() + entry.getValue());
        }
        byte[] rawHmac = null;
        try {
            rawHmac = payMac.doFinal(sort(paramsList).getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            rawHmac = payMac.doFinal(sort(paramsList).getBytes());
        }
        String result = Base64.encodeToString(rawHmac, Base64.NO_WRAP);
        return result;
    }

    public static String getPayUrlByParameter(String subjectUrl, HashMap<String, String> map, Boolean isSign) {
        try {
            StringBuffer sb = new StringBuffer(subjectUrl);
            int i = 0;
            for (String key : map.keySet()) {
                if (i == 0) {
                    sb.append(key + "=" +URLEncoder.encode(map.get(key), "utf-8"));
                } else {
                    sb.append("&" + key + "=" +URLEncoder.encode(map.get(key), "utf-8"));
                }
                i++;
            }
            if (isSign) {
                sb.append("&sign=" + URLEncoder.encode(initApp.getPaySig(map), "utf-8"));
            }
            return sb.toString();
        } catch (Exception e) {
            return "";
        }
    }


    private String sort(List<String> reqParams) {
        Collections.sort(reqParams);
        StringBuilder sb = new StringBuilder();
        for (String item : reqParams) {
            sb.append(item);
        }
        return sb.toString();
    }

    public <T> void addToRequestQueue(Request<T> req) {
        // set the default tag if tag is empty
        req.setTag(DEFAULT_REQUEST_TAG);

        RetryPolicy retryPolicy = new DefaultRetryPolicy(18000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        req.setRetryPolicy(retryPolicy);

        getRequestQueue().add(req);
    }

    public RequestQueue getRequestQueue() {
        // lazy initialize the request queue, the queue instance will be
        // created when it is accessed for the first time
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }





}
