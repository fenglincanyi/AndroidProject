package com.geng.fragmentdemo;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, MessageFragment.OnFragmentInteractionListener, FragmentManager.OnBackStackChangedListener {

    private TextView tvFlag1;
    private TextView tvFlag2;
    private TextView tvFlag3;
    private TextView tvMsg;

    private android.support.v4.app.FragmentManager fragmentManager;
    private Tab1Fragment fragment1;
    private Tab2Fragment fragment2;
    private MessageFragment messageFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListener();
        initData();
    }

    private void initViews() {
        tvFlag1 = (TextView) findViewById(R.id.tv_Flag1);
        tvFlag2 = (TextView) findViewById(R.id.tv_Flag2);
        tvFlag3 = (TextView) findViewById(R.id.tv_Flag3);
        tvMsg = (TextView) findViewById(R.id.tv_Msg);
    }

    private void initListener() {
        tvFlag1.setOnClickListener(this);
        tvFlag2.setOnClickListener(this);
        tvFlag3.setOnClickListener(this);
    }

    private void initData() {
        fragmentManager = getSupportFragmentManager();
        fragmentManager.addOnBackStackChangedListener(this);
        fragment1 = new Tab1Fragment();
        fragment2 = new Tab2Fragment();
        messageFragment = new MessageFragment();
//        messageFragment = MessageFragment.newInstance("测试", "MessageFragment");
    }

    @Override
    public void onClick(View v) {
//        transaction = fragmentManager.beginTransaction();
        switch (v.getId()) {
            case R.id.tv_Flag1:
                tvFlag1.setBackgroundColor(Color.parseColor("#44000000"));
                tvFlag2.setBackgroundColor(Color.parseColor("#ffffff"));
                tvFlag3.setBackgroundColor(Color.parseColor("#ffffff"));

                fragmentManager
                        .beginTransaction()
                        .replace(R.id.fl_Container, fragment1)
                        .addToBackStack("f1")
                        .commit();

//                if (fragment1.isAdded()) {
//                    fragmentManager
//                            .beginTransaction()
//                            .hide(fragment2)
//                            .show(fragment1)
//                            .commit();
//                } else {
//                    fragmentManager
//                            .beginTransaction()
//                            .add(R.id.fl_Container, fragment1)
//                            .commit();
//                }

                break;
            case R.id.tv_Flag2:
                tvFlag1.setBackgroundColor(Color.parseColor("#ffffff"));
                tvFlag2.setBackgroundColor(Color.parseColor("#44000000"));
                tvFlag3.setBackgroundColor(Color.parseColor("#ffffff"));

                fragmentManager
                        .beginTransaction()
                        .replace(R.id.fl_Container, fragment2)
                        .addToBackStack("f2")
                        .commit();

//                if (fragment2.isAdded()) {
//                    fragmentManager
//                            .beginTransaction()
//                            .hide(fragment1)
//                            .show(fragment2)
//                            .commit();
//                } else {
//                    fragmentManager
//                            .beginTransaction()
//                            .add(R.id.fl_Container, fragment2)
//                            .commit();
//                }
                break;
            case R.id.tv_Flag3:
                tvFlag1.setBackgroundColor(Color.parseColor("#ffffff"));
                tvFlag2.setBackgroundColor(Color.parseColor("#ffffff"));
                tvFlag3.setBackgroundColor(Color.parseColor("#44000000"));

                fragmentManager
                        .beginTransaction()
                        .replace(R.id.fl_Container, messageFragment)
                        .commit();

                break;
        }
    }

    @Override
    public void onBackStackChanged() {
        int count = fragmentManager.getBackStackEntryCount();
        Log.e("stack", "stack情况，count： " + count);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        tvMsg.setText(String.format("%s", "收到fragment消息： " + uri.toString()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Toast.makeText(this, "activity 收到："+data.getStringExtra("result"), Toast.LENGTH_LONG).show();
        }
    }
}
