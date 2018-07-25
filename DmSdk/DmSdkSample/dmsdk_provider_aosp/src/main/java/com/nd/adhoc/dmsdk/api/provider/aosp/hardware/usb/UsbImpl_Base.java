package com.nd.adhoc.dmsdk.api.provider.aosp.hardware.usb;
import android.content.Context;
import android.os.UserManager;
import android.support.annotation.NonNull;
import com.nd.adhoc.dmsdk.api.provider.aosp.utils.DeviceControlUtils;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;

public class UsbImpl_Base {

    public void turnOff(@NonNull Context context, boolean isOpen) throws DeviceManagerSecurityException {
        DeviceControlUtils.operation(context,isOpen,UserManager.DISALLOW_USB_FILE_TRANSFER);
    }
}
