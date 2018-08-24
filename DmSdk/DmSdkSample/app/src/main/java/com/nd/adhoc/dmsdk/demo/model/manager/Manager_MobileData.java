package com.nd.adhoc.dmsdk.demo.model.manager;

import android.content.Context;
import android.support.annotation.NonNull;

import com.adhoc.dmsdk.sdk.DeviceManagerSdk;
import com.nd.adhoc.dmsdk.api.hardware.microphone.IMicrophone_Open;
import com.nd.adhoc.dmsdk.api.hardware.mobiledata.IMobileData_Close;
import com.nd.adhoc.dmsdk.api.hardware.mobiledata.IMobileData_IsOpen;
import com.nd.adhoc.dmsdk.demo.model.manager.base.IDeviceManager;
import com.nd.adhoc.dmsdk.exception.DeviceManagerUnsupportException;


/**
 * @author richsjeson
 */

public class Manager_MobileData implements IDeviceManager {


    public boolean execute(@NonNull Context context) {

        if (!isOpen(context)) {
            return openMobile(context);
        } else {
            return closeMobile(context);
        }
    }


    public boolean isOpen(@NonNull Context context) {

        IMobileData_IsOpen mobileIsOpen;
        try {
            mobileIsOpen = (IMobileData_IsOpen) DeviceManagerSdk.getInstance().getApi(IMobileData_IsOpen.class);
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
            return false;
        }
        return mobileIsOpen.isOpen(context);
    }

    private boolean openMobile(@NonNull Context context) {
        IMicrophone_Open mobileOpen;
        try {
            mobileOpen = (IMicrophone_Open) DeviceManagerSdk.getInstance().getApi(IMicrophone_Open.class);
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
            return false;
        }
        return mobileOpen.open(context);
    }


    private boolean closeMobile(@NonNull Context context) {
        IMobileData_Close mobileClose;
        try {
            mobileClose = (IMobileData_Close) DeviceManagerSdk.getInstance().getApi(IMobileData_Close.class);
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
            return false;
        }
        return mobileClose.close(context);
    }

}
