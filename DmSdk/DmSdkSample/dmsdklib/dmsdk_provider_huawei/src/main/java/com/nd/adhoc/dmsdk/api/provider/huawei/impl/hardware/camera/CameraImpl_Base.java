package com.nd.adhoc.dmsdk.api.provider.huawei.impl.hardware.camera;

import android.content.ComponentName;
import android.content.Context;
import android.support.annotation.NonNull;

import com.huawei.android.app.admin.DeviceCameraManager;
import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.api.hardware.IHardwareOperation_Swith;
import com.nd.adhoc.dmsdk.api.provider.huawei.HWDeviceManagerFactory;

/**
 * @author richsjeson
 */

public class CameraImpl_Base implements IHardwareOperation_Swith {
    @Override
    public boolean derall(@NonNull Context context, boolean isOpen) {
        ComponentName componentName = DeviceManagerContainer.getInstance().getComponentName();
        try {
            DeviceCameraManager cameraManager = HWDeviceManagerFactory.getInstance(context).getDeviceCameraManager();
            if (componentName == null) {
                return false;
            }
            return cameraManager.setVideoDisabled(componentName, !isOpen);
        } catch (SecurityException |StackOverflowError e) {
            e.printStackTrace();
        }
        return false;
    }
}
