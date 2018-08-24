package com.nd.adhoc.dmsdk.demo.model.manager;
import android.content.Context;
import android.support.annotation.NonNull;
import com.adhoc.dmsdk.sdk.DeviceManagerSdk;
import com.nd.adhoc.dmsdk.api.hardware.usb.IUsb_Connect;
import com.nd.adhoc.dmsdk.api.hardware.usb.IUsb_DisConnect;
import com.nd.adhoc.dmsdk.api.hardware.usb.IUsb_IsConnect;
import com.nd.adhoc.dmsdk.demo.model.manager.base.IDeviceManager;
import com.nd.adhoc.dmsdk.exception.DeviceManagerUnsupportException;


/**
 * @author richsjeson
 */

public class Manager_Usb implements IDeviceManager {

    public boolean execute(@NonNull Context context) {

        if (!isOpen(context)) {
            return openUsb(context);
        } else {
            return closeUsb(context);
        }
    }


    public boolean isOpen(@NonNull Context context) {

        IUsb_IsConnect usbIsConnect;
        try {
            usbIsConnect = (IUsb_IsConnect) DeviceManagerSdk.getInstance().getApi(IUsb_IsConnect.class);
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
            return false;
        }
        return usbIsConnect.isOpen(context);
    }

    private boolean openUsb(@NonNull Context context) {
        IUsb_Connect usbConnect;
        try {
            usbConnect = (IUsb_Connect) DeviceManagerSdk.getInstance().getApi(IUsb_Connect.class);
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
            return false;
        }
        return usbConnect.open(context);
    }


    private boolean closeUsb(@NonNull Context context) {
        IUsb_DisConnect usbDisConnect;
        try {
            usbDisConnect = (IUsb_DisConnect) DeviceManagerSdk.getInstance().getApi(IUsb_DisConnect.class);
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
            return false;
        }
        return usbDisConnect.close(context);
    }

}
