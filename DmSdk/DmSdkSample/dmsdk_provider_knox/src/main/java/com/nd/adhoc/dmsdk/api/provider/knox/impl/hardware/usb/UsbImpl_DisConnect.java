package com.nd.adhoc.dmsdk.api.provider.knox.impl.hardware.usb;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiFunctionKey;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.hardware.usb.IUsb_DisConnect;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.filed.DmSdkConstants;
import com.nd.sdp.android.serviceloader.annotation.Service;

@ApiFunctionKey(DmSdkConstants.MANAGER_HARDWARE_USB)
@Service(IDmSdkApi.class)
@ApiImpl(IUsb_DisConnect.class)
public class UsbImpl_DisConnect extends UsbImpl_Base implements IUsb_DisConnect {
    @Override
    public boolean close(@NonNull Context context){
        try {
            turnOff(context,false);
        }catch (DeviceManagerSecurityException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    public void release(@NonNull Context context) {

    }
}
