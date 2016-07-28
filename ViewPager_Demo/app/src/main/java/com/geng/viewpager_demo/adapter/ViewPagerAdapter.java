package com.geng.viewpager_demo.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Author: gengjiarong
 * Date: 2016/7/28
 */
public class ViewPagerAdapter extends PagerAdapter {

    private List<ImageView> mlist;

    public ViewPagerAdapter(List<ImageView> list) {
        mlist = list;
    }

    /**
     * 每次确保，初始化当前的item 和 下一个Item
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        System.out.println("instantiateItem: " + position);
        container.addView(mlist.get(position));
        return mlist.get(position);
    }

    /**
     * 移除 currentPosition - 2 项，每次page更换时会调用；
     * 初始情况下，显示第一个，并不执行销毁，因为没有可销毁的Item
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        System.out.println("destroyItem: " + position);
        container.removeView(mlist.get(position));
    }

    /**
     * 判断 view与具体的 key相关联
     *
     * ViewPager中的源码，在addItem时候：
     *      ii.object = mAdapter.instantiateItem(this, position);
     * 会将初始化的object作为 key,在getItemPosition，destroy时候也是通过这个key来操作某个Item的
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        System.out.println(view == object);
        return view == object;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }
}
