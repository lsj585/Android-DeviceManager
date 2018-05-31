package com.nd.adhoc.dmsdk.api.provider.knox.license;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.support.annotation.NonNull;
import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.exception.ErrorCode;
import com.nd.adhoc.dmsdk.api.manager.license.ILicenseManager_DeActive;

/**
 * License 激活
 */
public class LisenceManagerImpl_DeActive implements ILicenseManager_DeActive {

    @Override
    public void release(@NonNull Context context) {

    }

    @Override
    public void deActive() throws DeviceManagerSecurityException {

        DeviceManagerContainer container=DeviceManagerContainer.getInstance();
        DevicePolicyManager manager= container.getDevicePolicyManager();
        ComponentName componentName=container.getComponentName();


        if(manager==null ){
            //抛出异常
            throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }

        if(componentName==null){
            //抛出异常
            throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }

        //取消设备激活
        if (manager.isAdminActive(componentName)) {
            manager.removeActiveAdmin(componentName);
        }
    }
}
