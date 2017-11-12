package com.geng.dragsortbyrecyclerview;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;

/**
 * Created by gengjiarong
 * on 2017/11/10.
 */

public class AnimationHelper {

    public static void showHideAnimation(View dot, boolean isShow) {
        ScaleAnimation sa;
        AlphaAnimation aa;
        if (isShow) {
            sa = new ScaleAnimation(0f, 1f, 0f, 1f,Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 1f);
            aa = new AlphaAnimation(0f, 1f);
        } else {
            sa = new ScaleAnimation(1f, 0f, 1f, 0f, Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0f);
            aa = new AlphaAnimation(1f, 0f);
        }

        AnimationSet set = new AnimationSet(false);
        set.addAnimation(sa);
        set.addAnimation(aa);
        set.setDuration(200);
        set.setFillAfter(true);

        dot.setAnimation(set);

        set.start();
    }
}
