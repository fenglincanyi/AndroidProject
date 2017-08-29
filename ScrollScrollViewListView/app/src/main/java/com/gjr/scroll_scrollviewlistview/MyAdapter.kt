package com.gjr.scroll_scrollviewlistview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * Created by geng
 * on 2017/8/28.
 */
class MyAdapter(var context: Context, var data: MutableList<Person>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val vh: ViewHolder
        val v: View
        if (convertView == null) {
            v = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
            vh = ViewHolder(v)
            v.tag = vh
        } else {
            v = convertView
            vh = v.tag as ViewHolder
        }

        vh.nameStr.text = data[position].name
        vh.idStr.text = data[position].id.toString()
        return v
    }

    override fun getItem(position: Int): Person {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return data.size
    }

    class ViewHolder(content: View) {
        var nameStr : TextView  = content.findViewById(R.id.nameTV) as TextView
        var idStr : TextView = content.findViewById(R.id.idTv) as TextView
    }
}