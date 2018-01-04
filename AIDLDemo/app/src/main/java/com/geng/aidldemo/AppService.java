package com.geng.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geng on 2018/1/3.
 */

/**
 * 服务端
 */
public class AppService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;// 返回Stub
    }


    // 实现 aidl 定义的接口
    private final IAppAidlInterface.Stub mBinder = new IAppAidlInterface.Stub() {

        @Override
        public String setData(String packageName, int tag) throws RemoteException {
            System.out.println("AppService被调用： " + packageName + ", " + tag);
            // 。。。模拟各种操作
            return "AppService_" + packageName + "_" + tag;
        }

        @Override
        public List<PeopleBean> queryInfo(String name, int age) throws RemoteException {
            List<PeopleBean> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                PeopleBean peopleBean = new PeopleBean();
                peopleBean.name = "小米";
                peopleBean.sex = "女";
                peopleBean.age = i;
                list.add(peopleBean);
            }
            // ... 各种操作，忽略

            return list;
        }
    };
}
