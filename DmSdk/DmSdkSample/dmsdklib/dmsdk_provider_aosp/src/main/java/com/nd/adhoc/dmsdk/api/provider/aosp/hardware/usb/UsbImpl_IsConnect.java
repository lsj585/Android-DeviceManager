package com.nd.adhoc.dmsdk.api.provider.aosp.hardware.usb;
import android.content.Context;
import android.os.UserManager;
import android.support.annotation.NonNull;
import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.hardware.usb.IUsb_IsConnect;
import com.nd.adhoc.dmsdk.api.provider.aosp.utils.DeviceControlUtils;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.sdp.android.serviceloader.annotation.Service;

@Service(IDmSdkApi.class)
@ApiImpl(IUsb_IsConnect.class)
public class UsbImpl_IsConnect implements IUsb_IsConnect {
    @Override
    public boolean isOpen(@NonNull Context context){
        try {
            return DeviceControlUtils.isOpen(context, UserManager.DISALLOW_USB_FILE_TRANSFER);
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public void release(@NonNull Context context) {

    }
}
