package com.geng.aidldemo;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendB(View view) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.geng.bapp", "com.geng.bapp.BAppService"));
        intent.putExtra("app_source", getPackageName());
        startService(intent);
    }
}
