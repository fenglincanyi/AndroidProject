package com.gjr.scroll_scrollviewlistview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.list_item.view.*


/**
 * Created by geng
 * on 2017/8/29.
 */
class MyAdapter2(var data: MutableList<Person>): RecyclerView.Adapter<MyAdapter2.MyVH>() {

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVH {
        return MyVH(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: MyVH, position: Int) {
        holder.nameTv.text = data[position].name
        holder.idTv.text = data[position].id.toString()
    }


    class MyVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameTv : TextView = itemView.nameTV
        var idTv : TextView = itemView.idTv
    }
}


