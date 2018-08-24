package com.nd.adhoc.dmsdk.api.provider.huawei.impl.hardware.camera;

import android.content.ComponentName;
import android.content.Context;
import android.support.annotation.NonNull;

import com.huawei.android.app.admin.DeviceCameraManager;
import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.hardware.camera.ICamera_IsOpen;
import com.nd.adhoc.dmsdk.api.provider.huawei.HWDeviceManagerFactory;
import com.nd.sdp.android.serviceloader.annotation.Service;

@Service(IDmSdkApi.class)
@ApiImpl(ICamera_IsOpen.class)
public class CameraImpl_IsOpen implements ICamera_IsOpen {
    @Override
    public boolean isOpen(@NonNull Context context) {
        ComponentName componentName = DeviceManagerContainer.getInstance().getComponentName();
        try {
            DeviceCameraManager cameraManager = HWDeviceManagerFactory.getInstance(context).getDeviceCameraManager();
            if (componentName == null) {
                return false;
            }
            return cameraManager.isVideoDisabled(componentName);
        } catch (SecurityException | StackOverflowError e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void release(@NonNull Context context) {

    }
}
