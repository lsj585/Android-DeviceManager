package com.nd.adhoc.dmsdk.demo.model.manager;

import android.content.Context;
import android.support.annotation.NonNull;

import com.adhoc.dmsdk.sdk.DeviceManagerSdk;
import com.nd.adhoc.dmsdk.api.hardware.sdcard.ISdCard_IsMount;
import com.nd.adhoc.dmsdk.api.hardware.sdcard.ISdCard_Mount;
import com.nd.adhoc.dmsdk.api.hardware.sdcard.ISdCard_UnMount;
import com.nd.adhoc.dmsdk.demo.model.manager.base.IDeviceManager;
import com.nd.adhoc.dmsdk.exception.DeviceManagerUnsupportException;


/**
 * @author richsjeson
 */

public class Manager_SdCard implements IDeviceManager {


    public boolean execute(@NonNull Context context) {

        if (!isOpen(context)) {
            return openSdCard(context);
        } else {
            return closeSdCard(context);
        }
    }


    public boolean isOpen(@NonNull Context context) {

        ISdCard_IsMount sdCardIsMount;
        try {
            sdCardIsMount = (ISdCard_IsMount) DeviceManagerSdk.getInstance().getApi(ISdCard_IsMount.class);
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
            return false;
        }
        return sdCardIsMount.isOpen(context);
    }

    private boolean openSdCard(@NonNull Context context) {
        ISdCard_Mount sdCardMount;
        try {
            sdCardMount = (ISdCard_Mount) DeviceManagerSdk.getInstance().getApi(ISdCard_Mount.class);
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
            return false;
        }
        return sdCardMount.open(context);
    }


    private boolean closeSdCard(@NonNull Context context) {
        ISdCard_UnMount sdCardUnMount;
        try {
            sdCardUnMount = (ISdCard_UnMount) DeviceManagerSdk.getInstance().getApi(ISdCard_UnMount.class);
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
            return false;
        }
        return sdCardUnMount.close(context);
    }

}
