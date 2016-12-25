package com.geng.animationdemo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;


public class AccelerateFragment extends Fragment implements View.OnClickListener{

    private ImageView deceiv;
    private TranslateAnimation translateAnimation;
    private Spinner spinner1;
    private Spinner spinner2;

    public AccelerateFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_accelerate, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinner1 = (Spinner) view.findViewById(R.id.accelerateSp);
        spinner2 = (Spinner) view.findViewById(R.id.timeSp);
        deceiv = (ImageView)view.findViewById(R.id.deceiv);

        translateAnimation = new TranslateAnimation(0, 0, 0, 1500);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(500);
        translateAnimation.setInterpolator(new AccelerateInterpolator());

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0://accelerate_interpolator
                        translateAnimation.setInterpolator(new AccelerateInterpolator());
                        break;
                    case 1://accelerate_decelerate_interpolator
                        translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
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
