package com.nd.adhoc.dmsdk.api.provider.aosp.hardware.camera;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.support.annotation.NonNull;
import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.hardware.camera.ICamera_IsOpen;
import com.nd.adhoc.dmsdk.api.provider.aosp.utils.DeviceControlUtils;
import com.nd.sdp.android.serviceloader.annotation.Service;

@Service(IDmSdkApi.class)
@ApiImpl(ICamera_IsOpen.class)

public class CameraImpl_IsOpen implements ICamera_IsOpen {
    @Override
    public boolean isOpen(@NonNull Context context) {
        DeviceManagerContainer container = DeviceManagerContainer.getInstance();

        DevicePolicyManager devicePolicyManager = container.getDevicePolicyManager();

        ComponentName componentName = container.getComponentName();

        DeviceControlUtils.isVerificationNull(context,devicePolicyManager,componentName);

        try {
            return devicePolicyManager.getCameraDisabled(componentName);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void release(@NonNull Context context) {

    }
}
