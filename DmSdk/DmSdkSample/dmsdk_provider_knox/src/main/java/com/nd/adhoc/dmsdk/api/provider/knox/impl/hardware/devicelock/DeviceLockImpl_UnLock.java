package com.nd.adhoc.dmsdk.api.provider.knox.impl.hardware.devicelock;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.KeyEvent;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.hardware.devicelock.IDeviceLock_Unlock;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.sdp.android.serviceloader.annotation.Service;

import java.util.ArrayList;
import java.util.List;

@Service(IDmSdkApi.class)
@ApiImpl(IDeviceLock_Unlock.class)
public class DeviceLockImpl_UnLock extends DeviceLockImpl_Base implements IDeviceLock_Unlock {

    @Override
    public boolean close(@NonNull Context context){
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
        super.frees(context);
    }
}
