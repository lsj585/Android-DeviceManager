package com.nd.adhoc.dmsdk.api.provider.aosp.hardware.camera;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.support.annotation.NonNull;
import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.api.hardware.IHardwareOperation_Swith;
import com.nd.adhoc.dmsdk.api.provider.aosp.utils.DeviceControlUtils;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;

/**
 * @author richsjeson
 */

public class CameraImpl_Base implements IHardwareOperation_Swith {
    /**
     * 执行打开/关闭 硬件功能的操作
     * @param context
     * @param isOpen
     * @throws DeviceManagerSecurityException
     */
    @Override
    public boolean derall(@NonNull Context context, boolean isOpen) {
        DeviceManagerContainer container = DeviceManagerContainer.getInstance();

        DevicePolicyManager devicePolicyManager = container.getDevicePolicyManager();

        ComponentName componentName = container.getComponentName();

        DeviceControlUtils.isVerificationNull(context,devicePolicyManager,componentName);
        try {
            devicePolicyManager.setCameraDisabled(componentName, isOpen);
        } catch (SecurityException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
