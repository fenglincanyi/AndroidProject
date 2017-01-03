package com.geng.tablistdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by geng
 * on 2017/1/3.
 */
public class ContentFragment extends Fragment {

    private TextView contentTv;
    private LinearLayout fragment_container;

    private String contentStr;
    private String colorValue;

    public ContentFragment(String content, String value){
        contentStr = content;
        colorValue =  value;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        contentTv = (TextView) view.findViewById(R.id.content_tv);
        fragment_container = (LinearLayout) view.findViewById(R.id.fragment_container);
        setContentTv(contentStr, colorValue);
    }

    public void setContentTv(String content, String color) {
        contentTv.setText(content);
        fragment_container.setBackgroundColor(Color.parseColor(color));
    }
}
