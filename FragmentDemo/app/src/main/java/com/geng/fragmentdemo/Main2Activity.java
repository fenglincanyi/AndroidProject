package com.geng.fragmentdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    public static final int RESULT_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }


    public void backTo(View view) {
        Intent intent = new Intent();
        intent.putExtra("result", "我是main 2");
        setResult(RESULT_CODE, intent);
        finish();
    }
}
