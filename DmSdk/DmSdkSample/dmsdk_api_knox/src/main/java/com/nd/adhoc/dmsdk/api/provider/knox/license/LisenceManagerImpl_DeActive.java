package com.nd.adhoc.dmsdk.api.provider.knox.license;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.api.BaseManager;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.exception.ErrorCode;
import com.nd.adhoc.dmsdk.api.manager.license.ILicenseManager_DeActive;

/**
 * License 激活
 */
class LisenceManagerImpl_DeActive extends BaseManager implements ILicenseManager_DeActive {


    public LisenceManagerImpl_DeActive(DevicePolicyManager devicePolicyManager, ComponentName componentName) {
        super(devicePolicyManager, componentName);
    }

    public LisenceManagerImpl_DeActive(DevicePolicyManager devicePolicyManager) {
        super(devicePolicyManager);
    }



    @Override
    public void release(@NonNull Context context) {

    }

    @Override
    public void deActive() throws DeviceManagerSecurityException {

        if(getDevicePolicyManager()==null ){
            //抛出异常
            throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }

        if(getComponentName()==null){
            //抛出异常
            throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }

        //取消设备激活
        if (getDevicePolicyManager().isAdminActive(getComponentName())) {
            getDevicePolicyManager().removeActiveAdmin(getComponentName());
        }
    }
}
