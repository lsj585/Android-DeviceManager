package com.nd.adhoc.dmsdk.api.provider.aosp.security;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.exception.ErrorCode;
import com.nd.adhoc.dmsdk.api.manager.security.ISecurityManager_AllowRun;
import com.nd.adhoc.dmsdk.api.provider.aosp.utils.DeviceControlUtils;
import com.nd.sdp.android.serviceloader.annotation.Service;

import java.util.List;
@Service(ISecurityManager_AllowRun.class)
public class SecurityManagerImpl_AllowRun implements ISecurityManager_AllowRun {

    @Override
    public void release(@NonNull Context context) {

    }

    @Override
    public boolean removePackageToRunList(@NonNull Context context, @NonNull List list) throws DeviceManagerSecurityException {
        DeviceManagerContainer container = DeviceManagerContainer.getInstance();

        if (container == null) {
            return false;
        }

        DevicePolicyManager devicePolicyManager = container.getDevicePolicyManager();

        ComponentName componentName = container.getComponentName();

        DeviceControlUtils.isVerificationNull(context, devicePolicyManager, componentName);
        //清除缓存  Android  P可使用

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            try {
                devicePolicyManager.setPackagesSuspended(componentName, (String[]) list.toArray(), false);
                return true;
            } catch (SecurityException e) {
                e.printStackTrace();
            }
            return false;
        }
        throw new DeviceManagerSecurityException(ErrorCode.DEFAULT_OPERATION_ERROR);
    }

}
