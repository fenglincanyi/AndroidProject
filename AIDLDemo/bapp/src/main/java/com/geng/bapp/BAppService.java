package com.geng.bapp;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.geng.aidldemo.IAppAidlInterface;
import com.geng.aidldemo.PeopleBean;

import java.util.List;

/**
 * Created by geng on 2018/1/3.
 */

/**
 * 客户端
 */
public class BAppService extends Service {

    private boolean isBinded;
    private IAppAidlInterface mIBAppService;

    private ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            isBinded = true;
            mIBAppService = IAppAidlInterface.Stub.asInterface(service);

            // 调用远程app 的方法
            try {
                String result = mIBAppService.setData(getPackageName(), 1000);
                System.out.println("BAppService 调用 AppService 结果： "+ result);

                List<PeopleBean> list = mIBAppService.queryInfo("小米", 3);
                System.out.println("查询people结果：");
                for (PeopleBean p : list) {
                    System.out.println(p.toString());
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBinded = false;
            mIBAppService = null;
        }
    };


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String source = intent.getStringExtra("app_source");
        System.out.println("App B onStartCommand: " + source);

        if (isBinded) {
            unbindService(sc);
        }

        // 绑定远程app的AppService
        Intent in = new Intent();
        in.setComponent(new ComponentName(source, source + ".AppService"));
        bindService(in, sc, BIND_AUTO_CREATE);
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
