package com.gjr.scroll_scrollviewlistview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.AbsListView
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    var page: Int = 1
    val adapter = MyAdapter2(DataBuilder.buildData(page))
//    val adapter = MyAdapter(this,DataBuilder.buildData(page))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        initData()
    }


    private fun initData() {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        // 为了减少RecycleView滑动时的卡顿（并不如丝般顺滑）
        recyclerView.isNestedScrollingEnabled = false // 默认是 true， 在构造方法中



//        listView.adapter = adapter
//        listView.setOnScrollListener(object : AbsListView.OnScrollListener {
//            override fun onScrollStateChanged(view: AbsListView, scrollState: Int) {
//                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE
//                        && (view.lastVisiblePosition == adapter.data.size - 1)) {
//                    adapter.data.addAll(DataBuilder.buildData(++page))
//                    adapter.notifyDataSetChanged()
//                }
//            }
//
//            override fun onScroll(view: AbsListView, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
//            }
//        })
    }
}
