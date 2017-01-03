package com.geng.tablistdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView redlineview;
    private ViewPager containervp;
    private FragmentManager fm;
    private List<Fragment> fragmentList;
    private LinearLayout.LayoutParams layoutParams;
    private int widthLen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
        initData();
    }

    private void initView() {
        this.containervp = (ViewPager) findViewById(R.id.container_vp);
        this.redlineview = (TextView) findViewById(R.id.red_line_view);
        this.tv4 = (TextView) findViewById(R.id.tv4);
        this.tv3 = (TextView) findViewById(R.id.tv3);
        this.tv2 = (TextView) findViewById(R.id.tv2);
        this.tv1 = (TextView) findViewById(R.id.tv1);
    }

    private void initListener() {
        tv4.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv1.setOnClickListener(this);
    }

    private void initData() {
        widthLen = ScreenUtil.getScreenW(this) / 4;
        layoutParams = new LinearLayout.LayoutParams(widthLen, 5);
        redlineview.setLayoutParams(layoutParams);

        fm = getSupportFragmentManager();
        fragmentList = new ArrayList<>();
        ContentFragment fragment1 = new ContentFragment("tab1", "#ff0000");
        ContentFragment fragment2 = new ContentFragment("tab2", "#00ff00");
        ContentFragment fragment3 = new ContentFragment("tab3", "#0000ff");
        ContentFragment fragment4 = new ContentFragment("tab4", "#ff00ff");
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        fragmentList.add(fragment4);
        ContentPagerAdapter pagerAdaper = new ContentPagerAdapter(fm, fragmentList);
        containervp.setAdapter(pagerAdaper);
        containervp.addOnPageChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv1:
                containervp.setCurrentItem(0);
                break;
            case R.id.tv2:
                containervp.setCurrentItem(1);
                break;
            case R.id.tv3:
                containervp.setCurrentItem(2);
                break;
            case R.id.tv4:
                containervp.setCurrentItem(3);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        layoutParams.leftMargin = (int) (position * widthLen + positionOffset * widthLen);
        redlineview.setLayoutParams(layoutParams);
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
