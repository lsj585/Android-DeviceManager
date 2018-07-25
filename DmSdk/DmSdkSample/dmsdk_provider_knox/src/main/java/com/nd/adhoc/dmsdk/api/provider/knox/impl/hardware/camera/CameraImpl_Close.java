package com.nd.adhoc.dmsdk.api.provider.knox.impl.hardware.camera;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.hardware.camera.ICamera_Close;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.sdp.android.serviceloader.annotation.Service;

@Service(IDmSdkApi.class)
@ApiImpl(ICamera_Close.class)
public class CameraImpl_Close extends CameraImpl_Base implements ICamera_Close {

    @Override
    public boolean close(@NonNull Context context){
        try {
            changer(context,false);
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
