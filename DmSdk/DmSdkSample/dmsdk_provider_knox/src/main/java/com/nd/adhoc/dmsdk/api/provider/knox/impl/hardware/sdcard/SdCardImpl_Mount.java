package com.nd.adhoc.dmsdk.api.provider.knox.impl.hardware.sdcard;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.hardware.sdcard.ISdCard_Mount;
import com.nd.sdp.android.serviceloader.annotation.Service;

@Service(IDmSdkApi.class)
@ApiImpl(ISdCard_Mount.class)
public class SdCardImpl_Mount extends SdCardImpl_Base implements ISdCard_Mount {

    @Override
    public boolean open(@NonNull Context context){
        try {
            derall(context,true);
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
