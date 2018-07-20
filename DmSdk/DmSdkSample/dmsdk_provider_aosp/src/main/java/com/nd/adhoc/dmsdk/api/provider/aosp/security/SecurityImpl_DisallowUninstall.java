package com.nd.adhoc.dmsdk.api.provider.aosp.security;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.security.ISecurity_DisallowUninstall;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.provider.aosp.utils.DeviceControlUtils;
import com.nd.sdp.android.serviceloader.annotation.Service;

@Service(IDmSdkApi.class)
@ApiImpl(ISecurity_DisallowUninstall.class)
public class SecurityImpl_DisallowUninstall implements ISecurity_DisallowUninstall {
    @Override
    public void release(@NonNull Context context) {

    }

    @Override
    public boolean disallowUninstall(@NonNull Context context, @NonNull String packageName) {
        DeviceManagerContainer container = DeviceManagerContainer.getInstance();

        if (container == null) {
            return false;
        }

        DevicePolicyManager devicePolicyManager = container.getDevicePolicyManager();

        ComponentName componentName = container.getComponentName();

        try {
            DeviceControlUtils.isVerificationNull(context, devicePolicyManager, componentName);
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
            return false;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                devicePolicyManager.setUninstallBlocked(componentName, packageName, true);
                return true;
            } catch (SecurityException e) {
                e.printStackTrace();
            }
            return false;
        }
       return false;
    }
}
