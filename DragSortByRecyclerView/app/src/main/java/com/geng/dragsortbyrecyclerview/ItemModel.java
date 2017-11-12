package com.geng.dragsortbyrecyclerview;

import java.io.Serializable;

/**
 * Created by gengjiarong
 * on 2017/11/10.
 */

public class ItemModel implements Serializable {

    private static final long serialVersionUID = 5141519720626858886L;

    public String icon;
    public String iconText;

    public ItemModel(String icon, String iconText) {
        this.icon = icon;
        this.iconText = iconText;
    }
}
