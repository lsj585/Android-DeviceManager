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
import com.nd.adhoc.dmsdk.api.manager.security.ISecurityManager_DisallowUninstall;
import com.nd.adhoc.dmsdk.api.provider.aosp.utils.DeviceControlUtils;

public class SecurityManagerImpl_DisallowUninstall implements ISecurityManager_DisallowUninstall {

    @Override
    public void addPackageToUninstallList(@NonNull Context context, @NonNull String packageName) throws DeviceManagerSecurityException, DeviceManagerUnsupportException {
        DeviceManagerContainer container = DeviceManagerContainer.getInstance();

        if (container == null) {
            throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }

        DevicePolicyManager devicePolicyManager = container.getDevicePolicyManager();

        ComponentName componentName = container.getComponentName();

        DeviceControlUtils.isVerificationNull(context, devicePolicyManager, componentName);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                devicePolicyManager.setUninstallBlocked(componentName, packageName, true);
            }catch (SecurityException e){
                e.printStackTrace();
            }
        }else{
            throw   new DeviceManagerUnsupportException(ErrorCode.ERROR_CODE_UN_SUPPORT);
        }
    }

    @Override
    public void release(@NonNull Context context) {

    }
}
