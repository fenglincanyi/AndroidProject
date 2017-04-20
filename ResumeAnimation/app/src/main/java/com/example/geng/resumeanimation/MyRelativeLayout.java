package com.example.geng.resumeanimation;

import android.content.Context;
import android.graphics.Color;
import android.graphics.RectF;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by geng
 * on 2017/4/10.
 */
public class MyRelativeLayout extends RelativeLayout {

    private LinearLayout leftLL;
    private LinearLayout rightLL;
    private TextView leftTv;
    private TextView rightTv;
    private ImageView leftIV;
    private ImageView rightIV;
    private RangeSeekBar rangeSeekBar;

    private ViewDragHelper dragHelper;
    private View bgView;
    private View progreeView;
    private LayoutParams leftLP;
    private LayoutParams rightLP;
    private boolean isStop;
    private float currentX;
    private float lastX;
    private boolean isDragLeft;
    private boolean isDragRight;

    public MyRelativeLayout(Context context) {
        this(context, null);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initChildView(context);
        addChildView();
        dragHelper = ViewDragHelper.create(this, 1f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                if (child == rangeSeekBar) {
                    return false;
                }
                return true;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                final int leftBound = getPaddingLeft();
                final int rightBound = getWidth() - leftLL.getWidth() - leftBound;

                final int newLeft = Math.min(Math.max(left, leftBound), rightBound);

                rangeSeekBar.setMoveX(currentX);
                return newLeft;
            }
        });
    }

    private void initChildView(Context context) {
//        bgView = new View(context);
//        progreeView = new View(context);
//        leftLP = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 10);
//        leftLP.setMargins(20, 20, 20, 0);
//        bgView.setLayoutParams(leftLP);
//        RelativeLayout.LayoutParams rightLP = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 10);
//        rightLP.setMargins(20, 20, 20, 0);
//        progreeView.setLayoutParams(rightLP);
//        bgView.setBackgroundColor(Color.parseColor("#44333333"));
//        progreeView.setBackgroundColor(Color.parseColor("#ff0000"));
        rangeSeekBar = new RangeSeekBar(context);

        leftLL = new LinearLayout(context);
        leftLL.setGravity(CENTER_HORIZONTAL);
        leftLL.setOrientation(LinearLayout.VERTICAL);

        rightLL = new LinearLayout(context);
        rightLL.setGravity(CENTER_HORIZONTAL);
        rightLL.setOrientation(LinearLayout.VERTICAL);


        leftTv = new TextView(context);
        rightTv = new TextView(context);
        leftTv.setText("不限");
        rightTv.setText("不限");
//        leftTv.
        leftTv.setGravity(CENTER_HORIZONTAL);
        rightTv.setGravity(CENTER_HORIZONTAL);

        leftIV = new ImageView(context);
        rightIV = new ImageView(context);
        leftIV.setImageResource(R.mipmap.ic_launcher);
        rightIV.setImageResource(R.mipmap.ic_launcher);
    }

    private void addChildView() {
        setGravity(CENTER_VERTICAL);

        leftLL.addView(leftTv);
        leftLL.addView(leftIV);
        rightLL.addView(rightTv);
        rightLL.addView(rightIV);

//        addView(bgView);
//        addView(progreeView);
        addView(rangeSeekBar);
        addView(leftLL);
        addView(rightLL);

        RelativeLayout.LayoutParams ll = (LayoutParams) rightLL.getLayoutParams();
        ll.addRule(ALIGN_PARENT_RIGHT);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return dragHelper.shouldInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        currentX = event.getRawX();
//        if (event.getX)
//        if ((rightLL.getLeft() - leftLL.getLeft()) < (leftLL.getRight() - leftLL.getLeft() - 16)) {
//            isStop = true;
//            return true;
//        }
//        RectF rectLeft = calcViewScreenLocation(leftLL);
//        RectF rectRight = calcViewScreenLocation(rightLL);
//        System.out.println(rectLeft.contains(event.getRawX(), event.getRawY()) + "---");
//        System.out.println(rectRight.contains(event.getRawX(), event.getRawY()) + "+++");

//        if (rectLeft.contains(event.getRawX(), event.getRawY())) {
//            leftLP.width = (int) (720-event.getRawX());
//            bgView.setLayoutParams(leftLP);





        if (lastX != 0 && lastX <= currentX) {
            return true;
        }
        dragHelper.processTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                RectF rectLeft = calcViewScreenLocation(leftLL);
//                RectF rectRight = calcViewScreenLocation(rightLL);
//                if (rectLeft.contains(event.getRawX(), event.getRawY())) {
//                    isDragLeft = true;
//                    isDragRight = false;
//                } else if (rectRight.contains(event.getRawX(), event.getRawY())) {
//                    isDragLeft = false;
//                    isDragRight = true;
//                }
                break;
            case MotionEvent.ACTION_UP:
                if (isDragLeft) {

                } else if (isDragRight) {

                }
                lastX = currentX;
                break;
        }
//        RectF rectLeft = calcViewScreenLocation(leftLL);
//        if (rectLeft.contains(event.getRawX(), event.getRawY())) {

//        }


//        RelativeLayout.LayoutParams params = (LayoutParams) progreeView.getLayoutParams();
//        params.width = (int) (event.getRawX()-40);
//        params.height = 10;
//        progreeView.setLayoutParams(params);
//        progreeView.setTranslationX();
//        progreeView.setScaleX();


//        } else if (rectRight.contains(event.getRawX(), event.getRawY())) {
//            dragHelper.processTouchEvent(event);
//        }


//        boolean isok = false;
//        rightLP = new LayoutParams((int) (event.getRawX()/10), 0);
//        progreeView.setLayoutParams(rightLP);
//        switch (event.getAction()) {
//
//            case MotionEvent.ACTION_DOWN:
//                isok = (rectLeft.contains(event.getRawX(), event.getRawY()) || rectRight.contains(event.getRawX(), event.getRawY()));
//                break;
//            case MotionEvent.ACTION_MOVE:
//                if (isok) {
//
//                    dragHelper.processTouchEvent(event);
//                }
//                break;
//        }


//        float currentX = event.getX();
//        float currentY = event.getY();
//        System.out.println(currentX+"---"+currentY);
//        float leftX = leftLL.getX() + leftLL.getWidth();
//        float leftY = leftLL.getY() + leftLL.getBottom();
//        float rightX = rightLL.getX() + rightLL.getWidth();
//        float rightY = rightLL.getY() + rightLL.getBottom();
//        System.out.println((currentX>=leftX && currentX<=rightX)+"  x");
//        System.out.println((currentY>=leftY && currentX<=rightY)+"  y");
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_MOVE:
////                if (currentX>=leftX && currentX<=rightX) {
////                    leftLL.scrollBy((int) (currentX-leftLL.getX()), 0);
////                }
//
//
////                RectF rect = calcViewScreenLocation(view);
////                boolean isInViewRect = rect.contains(x, y);
//                if ()
//                break;
//        }


        return true;
    }

    private RectF calcViewScreenLocation(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        return new RectF(location[0], location[1], location[0] + view.getWidth(),
                location[1] + view.getHeight());
    }

}