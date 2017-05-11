package com.example.bwei.todaynews.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.igexin.sdk.GTServiceManager;

/**
 * Created by muhanxi on 17/5/10.
 */

public class PushService extends Service {
    public static final String TAG = PushService.class.getName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, TAG + " call -> onCreate -------");

        GTServiceManager.getInstance().onCreate(this);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, TAG + " call -> onStartCommand -------");

        super.onStartCommand(intent, flags, startId);
        return GTServiceManager.getInstance().onStartCommand(this, intent, flags, startId);
    }
    @Override
    public IBinder onBind(Intent intent) {
        return GTServiceManager.getInstance().onBind(intent);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        GTServiceManager.getInstance().onDestroy();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        GTServiceManager.getInstance().onLowMemory();
    }

}
