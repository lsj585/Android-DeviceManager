package com.nd.adhoc.dmsdk.api.provider.knox.impl.hardware.wifi;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.hardware.wifi.IWifi_Close;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.sdp.android.serviceloader.annotation.Service;


@Service(IDmSdkApi.class)
@ApiImpl(IWifi_Close.class)
public class WifiImpl_Close extends WifiImpl_Base implements IWifi_Close {

    @Override
    public boolean close(@NonNull Context context){
        try {
            derall(context,false);
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
