package com.nd.adhoc.dmsdk.api.provider.aosp.hardware;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.exception.ErrorCode;
import com.nd.adhoc.dmsdk.api.manager.hardware.IDeviceLockManager;
import com.nd.adhoc.dmsdk.api.provider.aosp.utils.DeviceControlUtils;

import static android.app.admin.DevicePolicyManager.WIPE_RESET_PROTECTION_DATA;

public class DeviceLockManagerImpl implements IDeviceLockManager {


    @Override
    public void open(@NonNull Context context) throws DeviceManagerSecurityException {
        turnOff(context,true);
    }

    @Override
    public void close(@NonNull Context context) throws DeviceManagerSecurityException {
        turnOff(context,false);
    }

    @Override
    public boolean isOpen(@NonNull Context context) throws DeviceManagerSecurityException {
        throw new DeviceManagerSecurityException(ErrorCode.DEFAULT_ERROR_CODE);
    }

    @Override
    public void release(@NonNull Context context) {

    }


    private void turnOff(@NonNull Context context,boolean isOpen) throws DeviceManagerSecurityException {

        DeviceManagerContainer container = DeviceManagerContainer.getInstance();

        if(container==null){
            throw  new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }

        DevicePolicyManager devicePolicyManager = container.getDevicePolicyManager();

        ComponentName componentName = container.getComponentName();

        DeviceControlUtils.isVerificationNull(context,devicePolicyManager,componentName);

        try {
            devicePolicyManager.lockNow();
        }catch (SecurityException e){
            throw  new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }

    }

}
