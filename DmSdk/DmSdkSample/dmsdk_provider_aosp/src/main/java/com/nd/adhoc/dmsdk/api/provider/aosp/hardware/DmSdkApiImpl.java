package com.nd.adhoc.dmsdk.api.provider.aosp.hardware;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.api.hardware.base.IHardware_Open;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.exception.ErrorCode;
import com.nd.adhoc.dmsdk.api.provider.aosp.utils.DeviceControlUtils;
import com.nd.sdp.android.serviceloader.annotation.Service;
@Service(ID.class)
public class DmSdkApiImpl implements IHardware_Open {


    @Override
    public boolean open(@NonNull Context context){
        try {
            turnOff(context,true);
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean close(@NonNull Context context){
        try {
            turnOff(context,false);
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
            return false;
        }
        return true;
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
