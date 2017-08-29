package com.gjr.scroll_scrollviewlistview

/**
 * Created by geng
 * on 2017/8/28.
 */
class DataBuilder {

    companion object {
        fun buildData(page: Int): MutableList<Person> {
            val tmp = ArrayList<Person>()
            (0..20)
                    .map { Person("张三$page-$it", page + it) }
                    .forEach { tmp.add(it) }
            return tmp
        }
    }

}