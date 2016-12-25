package com.geng.animationdemo;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button accelerateBt;
    private Button decelerateBt;
    private Button otherBt;
    private FragmentManager fragmentManager;
    private AccelerateFragment accelerateFragment;
    private DecelerateFragment decelerateFragment;
    private OtherFragment otherFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initListener();
    }

    private void initView() {
        this.otherBt = (Button) findViewById(R.id.otherBt);
        this.decelerateBt = (Button) findViewById(R.id.decelerateBt);
        this.accelerateBt = (Button) findViewById(R.id.accelerateBt);
    }

    private void initData() {
        fragmentManager = getFragmentManager();
        accelerateFragment = new AccelerateFragment();
        decelerateFragment = new DecelerateFragment();
        otherFragment = new OtherFragment();

        fragmentManager
                .beginTransaction()
                .replace(R.id.container, accelerateFragment)
                .commit();
    }

    private void initListener() {
       accelerateBt.setOnClickListener(this);
       decelerateBt.setOnClickListener(this);
       otherBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.accelerateBt:
                if (accelerateFragment.isAdded()) {
                    fragmentManager
                            .beginTransaction()
                            .hide(decelerateFragment)
                            .hide(otherFragment)
                            .show(accelerateFragment)
                            .commit();
                } else {
                    fragmentManager
                            .beginTransaction()
                            .add(R.id.container, accelerateFragment)
                            .commit();
                }
                break;
            case R.id.decelerateBt:
                if (decelerateFragment.isAdded()) {
                    fragmentManager
                            .beginTransaction()
                            .hide(accelerateFragment)
                            .hide(otherFragment)
                            .show(decelerateFragment)
                            .commit();
                } else {
                    fragmentManager
                            .beginTransaction()
                            .add(R.id.container, decelerateFragment)
                            .commit();
                }
                break;
            case R.id.otherBt:
                if (otherFragment.isAdded()) {
                    fragmentManager
                            .beginTransaction()
                            .hide(decelerateFragment)
                            .hide(accelerateFragment)
                            .show(otherFragment)
                            .commit();
                } else {
                    fragmentManager
                            .beginTransaction()
                            .add(R.id.container, otherFragment)
                            .commit();
                }
                break;
        }
    }
}
