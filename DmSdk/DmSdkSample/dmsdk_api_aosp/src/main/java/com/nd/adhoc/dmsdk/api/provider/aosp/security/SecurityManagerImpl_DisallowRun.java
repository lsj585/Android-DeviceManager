package com.nd.adhoc.dmsdk.api.provider.aosp.security;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerUnsupportException;
import com.nd.adhoc.dmsdk.api.exception.ErrorCode;
import com.nd.adhoc.dmsdk.api.manager.security.ISecurityManager_DisallowRun;
import com.nd.adhoc.dmsdk.api.provider.aosp.utils.DeviceControlUtils;

import java.util.List;

public class SecurityManagerImpl_DisallowRun implements ISecurityManager_DisallowRun {
    @Override
    public void release(@NonNull Context context) {

    }

    @Override
    public void addPackageToRunList(@NonNull Context context, @NonNull List list) throws DeviceManagerSecurityException {

        DeviceManagerContainer container = DeviceManagerContainer.getInstance();

        if (container == null) {
            throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }

        DevicePolicyManager devicePolicyManager = container.getDevicePolicyManager();

        ComponentName componentName = container.getComponentName();

        DeviceControlUtils.isVerificationNull(context, devicePolicyManager, componentName);
        //清除缓存  Android  P可使用
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                devicePolicyManager.setPackagesSuspended(componentName, (String[]) list.toArray(), true);
            }else{
                throw new DeviceManagerUnsupportException(ErrorCode.ERROR_CODE_UN_SUPPORT);
            }
        } catch (SecurityException e) {
            e.printStackTrace();
            throw new DeviceManagerSecurityException(ErrorCode.DEFAULT_OPERATION_ERROR);
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
        }
    }
}
