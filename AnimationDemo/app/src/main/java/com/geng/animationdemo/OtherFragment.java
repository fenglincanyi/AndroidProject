package com.geng.animationdemo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;


public class OtherFragment extends Fragment implements View.OnClickListener {

    private ImageView deceiv;
    private TranslateAnimation translateAnimation;
    private Spinner spinner1;
    private Spinner spinner2;

    public OtherFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_other, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinner1 = (Spinner) view.findViewById(R.id.decelerateSp);
        spinner2 = (Spinner) view.findViewById(R.id.timeSp);
        deceiv = (ImageView) view.findViewById(R.id.deceiv);

        translateAnimation = new TranslateAnimation(0, 0, 0, ScreenUtil.dip2px(getActivity(), 390f));
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(500);
        translateAnimation.setInterpolator(new LinearInterpolator());

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0://linear_interpolator
                        translateAnimation.setInterpolator(new LinearInterpolator());
                        break;
                    case 1://cycle_interpolator
                        translateAnimation.setInterpolator(new CycleInterpolator(5));// 反复弹动 5 times
                        break;
                    case 2://anticipate_interpolator
                        translateAnimation.setInterpolator(new AnticipateInterpolator());
                        break;
                    case 3://anticipate_overshoot_interpolator
                        translateAnimation.setInterpolator(new AnticipateOvershootInterpolator());
                        break;
                    case 4://overshoot_interpolator
                        translateAnimation.setInterpolator(new OvershootInterpolator());
                        break;
                    case 5://bounce_interpolator
                        translateAnimation.setInterpolator(new BounceInterpolator());
                        break;
//                    case 6://path_interpolator
//                        The end points (0, 0) and (1, 1) are assumed
//                        translateAnimation.setInterpolator(new PathInterpolator();// 二阶的贝塞尔曲线
//                        break;
                    case 6://fast_out_linear_in_interpolator
                        translateAnimation.setInterpolator(new FastOutLinearInInterpolator());
                        break;
                    case 7://fast_out_linear_slow_in_interpolator
                        translateAnimation.setInterpolator(new FastOutSlowInInterpolator());
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:// 0.5s
                        translateAnimation.setDuration(500);
                        break;
                    case 1:// 1s
                        translateAnimation.setDuration(1000);
                        break;
                    case 2:// 1.5s
                        translateAnimation.setDuration(1500);
                        break;
                    case 3:// 2s
                        translateAnimation.setDuration(2000);
                        break;
                    case 4:// 2.5s
                        translateAnimation.setDuration(2500);
                        break;
                    case 5:// 3s
                        translateAnimation.setDuration(3000);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        view.findViewById(R.id.deceBt).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.deceBt:
                deceiv.clearAnimation();
                deceiv.startAnimation(translateAnimation);
                break;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            if (translateAnimation != null) {
                translateAnimation.cancel();
            }
            if (deceiv != null) {
                deceiv.clearAnimation();
            }
        }
    }
}
