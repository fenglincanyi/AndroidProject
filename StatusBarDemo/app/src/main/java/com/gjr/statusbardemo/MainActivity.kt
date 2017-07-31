package com.gjr.statusbardemo

import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Application主题： @style/Theme.AppCompat.Light.NoActionBar


        val version = Build.VERSION.SDK_INT;
        val flag = false


        if (version >= Build.VERSION_CODES.KITKAT
                && version < Build.VERSION_CODES.LOLLIPOP) {
            if (flag) {
                // 顶部的控件显示在状态栏下面，不被状态栏遮挡
                (findViewById(android.R.id.content) as ViewGroup).getChildAt(0).fitsSystemWindows = true
                // 设置状态栏透明色
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

                // 思路： 在decorView中添加个view(和状态栏一样高)，来改变状态栏样式
                val statusView = View(this)
                val statusBarHeight = getStatusBarHeight()
                val lp = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, statusBarHeight)
                lp.gravity = Gravity.TOP;
                statusView.layoutParams = lp;
                statusView.setBackgroundColor(Color.parseColor("#FF4081"))//#303F9F   #FF4081
                (window.decorView as ViewGroup).addView(statusView)
            } else {
                // 设置状态栏透明色
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

                val mChildView = (findViewById(android.R.id.content) as ViewGroup).getChildAt(0)
                if (mChildView != null) {
                    mChildView.setPadding(
                            mChildView.paddingLeft,
                            0,
                            mChildView.paddingRight,
                            mChildView.paddingBottom
                    )
                }
            }
        } else if (version >= Build.VERSION_CODES.LOLLIPOP) {
            if (flag) {
                window.statusBarColor = Color.parseColor("#FF4081")
            } else {
                // 全屏显示
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.statusBarColor = Color.TRANSPARENT
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
        }


        // 在application 主题中设置 fitsSystemWindows = true, toast 显示会有问题，压扁
        btn.setOnClickListener { Toast.makeText(this, "哈哈哈哈", Toast.LENGTH_LONG).show() }
    }

    private fun getStatusBarHeight(): Int {
        val resId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resId > 0) {
            return resources.getDimensionPixelSize(resId)
        }
        return 0
    }
}
