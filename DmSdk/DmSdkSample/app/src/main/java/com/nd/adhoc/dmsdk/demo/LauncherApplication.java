package com.nd.adhoc.dmsdk.demo;

import android.app.Application;

import com.adhoc.dmsdk.sdk.DeviceManagerSdk;

public class LauncherApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DeviceManagerSdk.getInstance().registerSDK(getApplicationContext());
    }
}
