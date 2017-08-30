package com.gjr.seekprogresslayout;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by geng
 * on 2017/8/30.
 */
public class SeekProgressLayout extends RelativeLayout {


    private ImageView leftImg;
    private ImageView rightImg;
    private View line;

    private ViewDragHelper dragHelper;
    private int left, right;


    public SeekProgressLayout(Context context) {
        this(context, null);
    }

    public SeekProgressLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SeekProgressLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
        initDragHelper();
    }

    private void initView() {
        leftImg = new ImageView(getContext());
        rightImg = new ImageView(getContext());
        leftImg.setImageResource(R.mipmap.ic_launcher);
        rightImg.setImageResource(R.mipmap.ic_launcher);

        line = new View(getContext());
        LayoutParams llp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 10);
        llp.addRule(CENTER_VERTICAL);
        line.setBackgroundColor(Color.parseColor("#ff0000"));
        addView(line, llp);


        leftImg.setBackgroundColor(Color.parseColor("#ff0000"));
        rightImg.setBackgroundColor(Color.parseColor("#ff00ff"));

        addView(leftImg);
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(ALIGN_PARENT_RIGHT);
        addView(rightImg, lp);
    }


    private void initDragHelper() {
        dragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {// 决定哪个 子View 可以滑动（true）
                return child == leftImg || child == rightImg;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                // 中间所剩距离百分比
//                System.out.println((int)((rightImg.getLeft() - leftImg.getRight()) *1.0f / (getWidth() - leftImg.getWidth() * 2) *100));
                if (child == leftImg) {
                    if (left <= 0) {
                        return 0;
                    } else if (left >= rightImg.getLeft() - leftImg.getWidth()) {
                        return rightImg.getLeft() - leftImg.getWidth();
                    }
                } else if (child == rightImg) {
                    if (left >= getWidth() - rightImg.getWidth()) {
                        return getWidth() - rightImg.getWidth();
                    } else if (left <= leftImg.getRight()) {
                        return leftImg.getRight();
                    }
                }
                return left;
            }

//            @Override
//            public int clampViewPositionVertical(View child, int top, int dy) {
//              默认实现，不允许上下动
                /*
                 * Restrict the motion of the dragged child view along the vertical axis.
                 * The default implementation does not allow vertical motion; the extending
                 * class must override this method and provide the desired clamping.
                 */
//                return 0;
//            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = MotionEventCompat.getActionMasked(ev);
        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            dragHelper.cancel();
            return false;
        }
        return dragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        dragHelper.processTouchEvent(event);
        left = leftImg.getLeft();
        right = rightImg.getLeft();

        line.setLeft(leftImg.getLeft());
        line.setRight(rightImg.getRight());
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        line.setLeft(left);
        leftImg.setLeft(left);
        leftImg.setRight(leftImg.getMeasuredWidth()+left);
        if (right > 0) {
            line.setRight(right);
            rightImg.setLeft(right);
            rightImg.setRight(leftImg.getMeasuredWidth()+right);
        }
    }
}
