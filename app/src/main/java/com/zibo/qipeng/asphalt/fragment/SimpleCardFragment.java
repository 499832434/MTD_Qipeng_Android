package com.zibo.qipeng.asphalt.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zibo.qipeng.asphalt.R;
import com.zibo.qipeng.asphalt.utils.Logger;


@SuppressLint("ValidFragment")
public class SimpleCardFragment extends Fragment {
    private String mTitle="1";
    TextView card_title_tv;

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public static SimpleCardFragment getInstance(String title) {
        Logger.e("ppppp1",title);
        SimpleCardFragment sf = new SimpleCardFragment();
        sf.mTitle = title;
        return sf;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Logger.e("ppppp1","222222"+mTitle);
        View v = inflater.inflate(R.layout.fragment_simple_card, null);
        card_title_tv = v.findViewById(R.id.card_title_tv);
        card_title_tv.setText(mTitle);
        return v;
    }


    public TextView getCard_title_tv() {
        return card_title_tv;
    }
}