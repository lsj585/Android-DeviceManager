package com.nd.adhoc.dmsdk.api.provider.knox.impl.hardware.usb;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.hardware.usb.IUsb_DisConnect;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.sdp.android.serviceloader.annotation.Service;

@Service(IDmSdkApi.class)
@ApiImpl(IUsb_DisConnect.class)
public class UsbImpl_DisConnect extends UsbImpl_Base implements IUsb_DisConnect {
    @Override
    public boolean close(@NonNull Context context){
        return derall(context,false);
    }


    @Override
    public void release(@NonNull Context context) {

    }
}
