package com.example.geng.resumeanimation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import java.util.Calendar;

/**
 * Created by geng
 * on 2017/4/9.
 */
public class RangeSeekBar extends View {

    private Paint paint;
    private ViewDragHelper dragHelper;
    private Canvas canvas;
    private float x;

    public RangeSeekBar(Context context) {
        this(context, null);
    }

    public RangeSeekBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RangeSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawProgreess(canvas);
    }

    private void drawProgreess(Canvas canvas) {
        this.canvas = canvas;
        paint.setColor(Color.parseColor("#44333333"));
        paint.setStrokeWidth(14f);
        canvas.drawLine(40, 100, getWidth()-40, 100, paint);
        paint.setColor(Color.parseColor("#ff0000"));
        canvas.drawLine(x==0? 60: x+20, 100, getWidth()-40, 100, paint);
    }

    public void setMoveX(float tmpx){
        x = tmpx;
        invalidate();
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        System.out.println(event.getX());
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_MOVE:
//                x = event.getX();
//                invalidate();
//                break;
//        }
//        return true;
//    }
}
