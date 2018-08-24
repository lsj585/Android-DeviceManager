package com.nd.adhoc.dmsdk.api.provider.aosp.security;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.security.ISecurity_AllowRun;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.provider.aosp.utils.DeviceControlUtils;
import com.nd.sdp.android.serviceloader.annotation.Service;

import java.util.List;
@Service(IDmSdkApi.class)
@ApiImpl(ISecurity_AllowRun.class)
public class SecurityImpl_AllowRun implements ISecurity_AllowRun {

    @Override
    public void release(@NonNull Context context) {

    }
    @Override
    public boolean allowRun(@NonNull Context context, @NonNull List<String> list) {
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
        return false;
    }
}
