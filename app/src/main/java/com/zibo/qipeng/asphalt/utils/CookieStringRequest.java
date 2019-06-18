package com.zibo.qipeng.asphalt.utils;

import android.text.TextUtils;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zongshuo on 2019/6/4
 *
 * @author zongshuo ps: good luck ,ai ni o.
 */
public class CookieStringRequest extends Request<String> {

    private static final String SET_COOKIE_KEY = "Set-Cookie";
    private static final String COOKIE_KEY = "Cookie";

    private Response.Listener<String> mListener;

    public CookieStringRequest(int method, String url, Response.Listener<String> listener,
                               Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        mListener = listener;
    }

    public CookieStringRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        this(Method.GET, url, listener, errorListener);
    }



    @Override
    protected void deliverResponse(String response) {
        if (mListener != null) {
            mListener.onResponse(response);
        }
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {

            Map<String, String> headers=response.headers;

            Logger.e("Map",headers.toString());
            if(headers!=null&&headers.containsKey(SET_COOKIE_KEY)){
                String cookie=headers.get(SET_COOKIE_KEY);

                if(!TextUtils.isEmpty(cookie)){
                    // TODO: 将cookie存到本地，如Sharepreference
                }
            }

            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {

        Map<String, String> headers=super.getHeaders();

        if(headers==null||headers.equals(Collections.emptyMap())){
            headers=new HashMap<>();
        }

        // TODO: 从本地获取到cookie，并把cookie添加到header中
        String value=getCookie();
        headers.put(COOKIE_KEY,value);
        return headers;
//        return super.getHeaders();
    }

    /**
     * 获取cookie;
     * @return
     */
    private String getCookie() {
        Map<String, String> headers=new HashMap<>();

        headers.put("BAIDUID","47026FF529AFA2570FDD693E6D37ADED:FG=1");
        headers.put("BDUSS","G1RYy10a35RNlcxczBlZWp4Rk5FRzN5RnNybXdLYzRiRlplYlBuMmVlc2dkUjFkSVFBQUFBJCQAAAAAAAAAAAEAAAAI3R0owMFfX19fX19fX19fXwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACDo9Vwg6PVcO");
        headers.put("amisid","s%3AFIe0soBY1mvKEWGZilZHnJyF-He05SkO.bMHXVkYiqMbrUjReCheVRC0Tx8XWTmDLwzSKhQcX%2FbI");
        headers.put("Hm_lpvt_90056b3f84f90da57dc0f40150f005d5","1559619585");
        headers.put("PTOKEN","2a7de686188dee7da855e6d5737da73f");
        headers.put("STOKEN","cdd2cfa7e77890ce42574bca1c55852dd93ab8a515008896e234b701a9ad02cb");
        headers.put("UBI","fi_PncwhpxZ%7ETaMM7SQh8ip5EwvN7QTQLqYy%7E2KdYTEMqwFKcNe6wKgMXqwmZNM8oTprQEWnnMf98RM-78Tf-jjmgBF9N7m%7EmcvWgJqN8hgz%7EbkCCpVLGHjshAeFgRq6JvtxNLOrJLT2MfuE1z0THZEBSciTQ__");
        headers.put("USERNAMETYPE","1");
        headers.put("pplogid","27485P2n9Rncx3Fc3RAHjSUgILd6HPegEtykmFBK3EiDeLiGIInSZEpeW%2FGzZ0sY7NrOeB8ATT5x1VmXu6ESB2EdIg%3D%3D");
      StringBuffer sb=new StringBuffer();
        for(String key:headers.keySet()){
            sb.append(key+"="+headers.get(key));
            sb.append(";");
//            Logger.e("====",key+"="+headers.get(key));
        }
        String str=sb.substring(0,sb.length()-1);
        Logger.e("str",str);
        return str;
    }
}
