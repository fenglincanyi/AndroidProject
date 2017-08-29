package com.gjr.scroll_scrollviewlistview

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.AbsListView
import android.widget.LinearLayout
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import org.jetbrains.anko.startActivity


class MainActivity : AppCompatActivity() {

    var page: Int = 1
    val adapter = MyAdapter(this, DataBuilder.buildData(page))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handleScroll()
        initData()
        enterBtn.setOnClickListener {
            startActivity<Main2Activity>()
        }
    }

    private fun handleScroll() {
        listView.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                v.parent.parent.requestDisallowInterceptTouchEvent(true)
                return false
            }
        })
    }

    private fun initData() {
        listView.adapter = adapter
        listView.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScrollStateChanged(view: AbsListView, scrollState: Int) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE
                        && (view.lastVisiblePosition == adapter.data.size - 1)) {
                    adapter.data.addAll(DataBuilder.buildData(++page))
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onScroll(view: AbsListView, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
            }
        })
    }
}
