package com.nd.adhoc.dmsdk.demo.model.manager;

import android.content.Context;
import android.support.annotation.NonNull;

import com.adhoc.dmsdk.sdk.DeviceManagerSdk;
import com.nd.adhoc.dmsdk.api.hardware.microphone.IMicrophone_Close;
import com.nd.adhoc.dmsdk.api.hardware.microphone.IMicrophone_IsOpen;
import com.nd.adhoc.dmsdk.api.hardware.microphone.IMicrophone_Open;
import com.nd.adhoc.dmsdk.demo.model.manager.base.IDeviceManager;
import com.nd.adhoc.dmsdk.exception.DeviceManagerUnsupportException;


/**
 * @author richsjeson
 */

public class Manager_Microphone implements IDeviceManager {

    public boolean execute(@NonNull Context context) {

        if(!isOpen(context)){
            return openMicrophone(context);
        }else{
            return closeMicrophone(context);
        }
    }


    public boolean isOpen(@NonNull Context context) {

        IMicrophone_IsOpen micIsOpen;
        try {
            micIsOpen = (IMicrophone_IsOpen) DeviceManagerSdk.getInstance().getApi(IMicrophone_IsOpen.class);
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
            return false;
        }
        return micIsOpen.isOpen(context);
    }

    private boolean openMicrophone(@NonNull Context context){
        IMicrophone_Open micOpen;
        try {
            micOpen = (IMicrophone_Open) DeviceManagerSdk.getInstance().getApi(IMicrophone_Open.class);
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
            return false;
        }
        return micOpen.open(context);
    }


    private boolean closeMicrophone(@NonNull Context context){
        IMicrophone_Close micClose;
        try {
            micClose = (IMicrophone_Close) DeviceManagerSdk.getInstance().getApi(IMicrophone_Close.class);
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
            return false;
        }
        return micClose.close(context);
    }

}
