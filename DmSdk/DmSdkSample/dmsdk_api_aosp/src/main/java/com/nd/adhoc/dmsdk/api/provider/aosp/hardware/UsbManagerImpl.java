package com.nd.adhoc.dmsdk.api.provider.aosp.hardware;
import android.content.Context;
import android.os.UserManager;
import android.support.annotation.NonNull;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.manager.hardware.IUsbMamager;
import com.nd.adhoc.dmsdk.api.provider.aosp.utils.DeviceControlUtils;

public class UsbManagerImpl  implements IUsbMamager {

    @Override
    public boolean open(@NonNull Context context){
        try {
            turnOff(context,true);
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean close(@NonNull Context context) {
        try {
            turnOff(context,false);
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean isOpen(@NonNull Context context) throws DeviceManagerSecurityException {
        return DeviceControlUtils.isOpen(context,UserManager.DISALLOW_USB_FILE_TRANSFER);
    }

    @Override
    public void release(@NonNull Context context) {

    }

    private void turnOff(@NonNull Context context,boolean isOpen) throws DeviceManagerSecurityException {
        DeviceControlUtils.operation(context,isOpen,UserManager.DISALLOW_USB_FILE_TRANSFER);
    }
}
