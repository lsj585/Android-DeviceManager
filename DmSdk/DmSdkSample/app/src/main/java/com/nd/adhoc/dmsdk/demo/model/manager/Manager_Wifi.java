package com.nd.adhoc.dmsdk.demo.model.manager;

import android.content.Context;
import android.support.annotation.NonNull;

import com.adhoc.dmsdk.sdk.DeviceManagerSdk;
import com.nd.adhoc.dmsdk.api.hardware.wifi.IWifi_Close;
import com.nd.adhoc.dmsdk.api.hardware.wifi.IWifi_IsOpen;
import com.nd.adhoc.dmsdk.api.hardware.wifi.IWifi_Open;
import com.nd.adhoc.dmsdk.demo.model.manager.base.IDeviceManager;
import com.nd.adhoc.dmsdk.exception.DeviceManagerUnsupportException;


/**
 * @author richsjeson
 */

public class Manager_Wifi implements IDeviceManager {

    public boolean execute(@NonNull Context context) {

        if(!isOpen(context)){
            return openWifi(context);
        }else{
            return closeWifi(context);
        }
    }


    public boolean isOpen(@NonNull Context context) {

        IWifi_IsOpen wifiManager;
        try {
            wifiManager = (IWifi_IsOpen) DeviceManagerSdk.getInstance().getApi(IWifi_IsOpen.class);
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
            return false;
        }
        return wifiManager.isOpen(context);
    }

    private boolean openWifi(@NonNull Context context){
        IWifi_Open wifiOpen;
        try {
            wifiOpen = (IWifi_Open) DeviceManagerSdk.getInstance().getApi(IWifi_Open.class);
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
            return false;
        }
        return wifiOpen.open(context);
    }


    private boolean closeWifi(@NonNull Context context){
        IWifi_Close wifiClose;
        try {
            wifiClose = (IWifi_Close) DeviceManagerSdk.getInstance().getApi(IWifi_Close.class);
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
            return false;
        }
        return wifiClose.close(context);
    }
}
