package com.nd.adhoc.dmsdk.api.provider.knox.system;

import android.app.admin.DevicePolicyManager;
import android.app.enterprise.ApplicationPolicy;
import android.content.ComponentName;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.api.BaseManager;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.exception.ErrorCode;
import com.nd.adhoc.dmsdk.api.manager.system.ISystemManager_ApplicationList;
import com.nd.adhoc.dmsdk.api.provider.knox.KnoxDeviceManagerFactory;

import java.util.Arrays;
import java.util.List;

class SystemManagerImpl_GetApplicationList extends BaseManager implements ISystemManager_ApplicationList {

    public SystemManagerImpl_GetApplicationList(@NonNull DevicePolicyManager devicePolicyManager, @NonNull ComponentName componentName) {
        super(devicePolicyManager, componentName);
    }

    public SystemManagerImpl_GetApplicationList(@NonNull DevicePolicyManager devicePolicyManager) {
        super(devicePolicyManager);
    }

    @Override
    public List getApplicationPakcageList(@NonNull Context context) throws DeviceManagerSecurityException {
        try {
            ApplicationPolicy applicationPolicy = KnoxDeviceManagerFactory.getInstance().getApplicationPolicy(context);
            if(applicationPolicy != null){
                return Arrays.asList(applicationPolicy.getInstalledApplicationsIDList());
            }else{
                throw  new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
            }

        }catch (RuntimeException e){
            e.printStackTrace();
            //TODO zyb 此处最高异常待定，需要核对API
            throw  new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }
    }

    @Override
    public void release(@NonNull Context context) {

    }
}
