package com.geng.dragsortbyrecyclerview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gengjiarong
 * on 2017/11/10.
 */

public class DataHelper {

    private static String[] coclors = {
            "#9575CD", "#7986CB", "#64B5F6", "#C8E6C9", "#C8E6C9",
            "#DCE775", "#FFF176", "#FFB74D", "#FF8A65", "#607D8B"
    };

//    private static String[] items = {
//            "租房", "电影", "旅游", "娱乐", "出行",
//            "理财", "公益", "新闻", "体育", "购物"
//    };

    private static String[] items = {
            "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10"
    };

    public static List<ItemModel> buildData() {
        List<ItemModel> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ItemModel model = new ItemModel(coclors[i], items[i]);
            list.add(model);
        }
        return list;
    }
}
