package com.geng.tablistdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geng
 * on 2017/1/3.
 */
public class ContentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;

    public ContentPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        fragmentList = new ArrayList<>();
        fragmentList.addAll(fragments);
    }

    @Override
    public Fragment getItem(int position) {

        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList == null ? 0 : fragmentList.size();
    }
}
