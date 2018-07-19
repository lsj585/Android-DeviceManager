package com.nd.adhoc.dmsdk.api.provider.aosp.security;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.api.security.ISecurity_DisallowUninstall;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.exception.DeviceManagerUnsupportException;
import com.nd.adhoc.dmsdk.exception.ErrorCode;
import com.nd.adhoc.dmsdk.api.provider.aosp.utils.DeviceControlUtils;
import com.nd.sdp.android.serviceloader.annotation.Service;

@Service(ISecurity_DisallowUninstall.class)
public class SecurityImpl_DisallowUninstall implements ISecurity_DisallowUninstall {

    @Override
    public boolean addPackageToUninstallList(@NonNull Context context, @NonNull String packageName) throws DeviceManagerSecurityException, DeviceManagerUnsupportException {
        DeviceManagerContainer container = DeviceManagerContainer.getInstance();

        if (container == null) {
            return false;
        }

        DevicePolicyManager devicePolicyManager = container.getDevicePolicyManager();

        ComponentName componentName = container.getComponentName();

        DeviceControlUtils.isVerificationNull(context, devicePolicyManager, componentName);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                devicePolicyManager.setUninstallBlocked(componentName, packageName, true);
                return true;
            } catch (SecurityException e) {
                e.printStackTrace();
            }
            return false;
        }
        throw new DeviceManagerUnsupportException(ErrorCode.ERROR_CODE_UN_SUPPORT);

    }

    @Override
    public void release(@NonNull Context context) {

    }
}
