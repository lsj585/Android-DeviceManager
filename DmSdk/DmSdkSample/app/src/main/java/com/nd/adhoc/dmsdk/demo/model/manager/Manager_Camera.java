package com.nd.adhoc.dmsdk.demo.model.manager;

import android.content.Context;
import android.support.annotation.NonNull;

import com.adhoc.dmsdk.sdk.DeviceManagerSdk;
import com.nd.adhoc.dmsdk.api.hardware.camera.ICamera_Close;
import com.nd.adhoc.dmsdk.api.hardware.camera.ICamera_IsOpen;
import com.nd.adhoc.dmsdk.api.hardware.camera.ICamera_Open;
import com.nd.adhoc.dmsdk.demo.model.manager.base.IDeviceManager;
import com.nd.adhoc.dmsdk.exception.DeviceManagerUnsupportException;


/**
 * @author richsjeson
 */

public class Manager_Camera implements IDeviceManager {

    public boolean execute(@NonNull Context context) {

        if(!isOpen(context)){
            return openCamera(context);
        }else{
            return closeCamera(context);
        }
    }


    public boolean isOpen(@NonNull Context context) {

        ICamera_IsOpen cameraIsOpen;
        try {
            cameraIsOpen = (ICamera_IsOpen) DeviceManagerSdk.getInstance().getApi(ICamera_IsOpen.class);
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
            return false;
        }
        return cameraIsOpen.isOpen(context);
    }

    private boolean openCamera(@NonNull Context context){
        ICamera_Open cameraOpen;
        try {
            cameraOpen = (ICamera_Open) DeviceManagerSdk.getInstance().getApi(ICamera_Open.class);
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
            return false;
        }
        return cameraOpen.open(context);
    }


    private boolean closeCamera(@NonNull Context context){
        ICamera_Close cameraClose;
        try {
            cameraClose = (ICamera_Close) DeviceManagerSdk.getInstance().getApi(ICamera_Close.class);
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
            return false;
        }
        return cameraClose.close(context);
    }

}
