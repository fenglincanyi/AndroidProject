package com.geng.viewpager_demo.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;


import com.geng.viewpager_demo.R;
import com.geng.viewpager_demo.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager vpMain;
    private List<ImageView> mImageViews;
    private int[] imgs;
    private ViewPagerAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initListener();
    }

    private void initView() {
        vpMain = (ViewPager) findViewById(R.id.vp_main);
    }

    private void initData() {
        imgs = new int[]{
                R.mipmap.img1,
                R.mipmap.img2,
                R.mipmap.img3,
                R.mipmap.img4,
                R.mipmap.img5
        };

        mImageViews = new ArrayList<>();
        ImageView imageView;
        for (int img : imgs) {
            imageView = new ImageView(this);
            imageView.setImageResource(img);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            mImageViews.add(imageView);
        }

        mAdapter = new ViewPagerAdapter(mImageViews);
        vpMain.setAdapter(mAdapter);
    }

    private void initListener() {
        vpMain.addOnPageChangeListener(this);
    }

    /**
     * 滑动监听
     *
     * @param position             当前显示的item的 index
     * @param positionOffset       总是：后边的view相对于前面的view显示所占比
     * @param positionOffsetPixels 后边的view相对于前面的view像素偏移量
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        System.out.println("onPageScrolled:  " + position + " | " + positionOffset + " | " + positionOffsetPixels);
    }

    /**
     * 当前选中的项
     *
     * @param position 当前选中view的 index
     */
    @Override
    public void onPageSelected(int position) {
//        System.out.println("Selected:  " + position);
    }

    /**
     * 监听PageView的状态
     *
     * @param state 0：SCROLL_STATE_IDLE 静止
     *              1：SCROLL_STATE_DRAGGING 拖拽
     *              2：SCROLL_STATE_SETTLING 落到最终位置
     */
    @Override
    public void onPageScrollStateChanged(int state) {
//        System.out.println("state:  " + state);

    }
}
