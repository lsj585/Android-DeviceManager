package com.nd.adhoc.dmsdk.api.provider.knox.impl.hardware.camera;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiFunctionKey;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.hardware.camera.ICamera_Open;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.filed.DmSdkConstants;
import com.nd.sdp.android.serviceloader.annotation.Service;
@ApiFunctionKey(DmSdkConstants.MANAGER_HARDWARE_CAMERA)
@Service(IDmSdkApi.class)
@ApiImpl(ICamera_Open.class)
public class CameraImpl_Open extends CameraImpl_Base implements ICamera_Open {

    @Override
    public boolean open(@NonNull Context context){
        try {
            turnOff(context, true);
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
