package com.example.geng.resumeanimation;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private TextView tip;

    private Scroller scroller;
    private ProgressBar pb;
    private LinearLayout ll;
    private TextView left_tx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        RangeSeekBar seekBar = new RangeSeekBar(this);
//        pb = (ProgressBar) findViewById(R.id.progess);
//        ll = (LinearLayout) findViewById(R.id.left);
//        left_tx = (TextView) findViewById(R.id.left_tx);

//        scroller = new Scroller(this);

//        seekBar = (SeekBar) findViewById(R.id.seekBar1);
//        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                System.out.println(seekBar.getWidth());
//            }
//
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//                System.out.println("onStart");
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                System.out.println("onStop");
//            }
//        });
    }
//

}
