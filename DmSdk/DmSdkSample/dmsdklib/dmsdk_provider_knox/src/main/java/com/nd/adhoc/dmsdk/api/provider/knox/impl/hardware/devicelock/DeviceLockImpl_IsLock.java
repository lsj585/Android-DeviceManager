package com.nd.adhoc.dmsdk.api.provider.knox.impl.hardware.devicelock;

import android.app.enterprise.RestrictionPolicy;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.hardware.devicelock.IDeviceLock_IsLock;
import com.nd.adhoc.dmsdk.api.provider.knox.utils.Verification;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.sdp.android.serviceloader.annotation.Service;

@Service(IDmSdkApi.class)
@ApiImpl(IDeviceLock_IsLock.class)
public class DeviceLockImpl_IsLock implements IDeviceLock_IsLock {

    @Override
    public boolean isOpen(@NonNull Context context) throws DeviceManagerSecurityException {
        RestrictionPolicy restrictionPolicy= Verification.isRestrictionPolicyNull(context);
        return restrictionPolicy.isActivationLockAllowed(true);
    }

    @Override
    public void release(@NonNull Context context) {

    }


}
