package com.nd.adhoc.dmsdk.api.provider.knox.impl.hardware.devicelock;

import android.app.enterprise.RestrictionPolicy;
import android.app.enterprise.kioskmode.KioskMode;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.KeyEvent;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiFunctionKey;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.hardware.devicelock.IDeviceLock_Lock;
import com.nd.adhoc.dmsdk.api.provider.knox.utils.Verification;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.exception.ErrorCode;
import com.nd.adhoc.dmsdk.filed.DmSdkConstants;
import com.nd.sdp.android.serviceloader.annotation.Service;

import java.util.ArrayList;
import java.util.List;

@Service(IDmSdkApi.class)
@ApiImpl(IDeviceLock_Lock.class)
public class DeviceLockImpl_Lock extends DeviceLockImpl_Base implements  IDeviceLock_Lock {


    @Override
    public boolean open(@NonNull Context context){
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
        super.frees(context);
    }
}
