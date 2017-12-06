package com.geng.annoationprocessordemo;

import com.geng.annotation.Setting;

/**
 * Created by gengjiarong
 * on 2017/12/3.
 */

public class SettingTest {

    @Setting(strValue = "哈哈哈")
    public String msg;

    @Setting(intValue = 1)
    public int code;
}
