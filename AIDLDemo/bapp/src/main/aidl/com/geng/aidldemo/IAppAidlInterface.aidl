// IAppAidlInterface.aidl
package com.geng.aidldemo;
// 手动导入包
import com.geng.aidldemo.PeopleBean;

// Declare any non-default types here with import statements

interface IAppAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);

    String setData(String packageName, int tag);

    List<PeopleBean> queryInfo(String name, int age);

}
