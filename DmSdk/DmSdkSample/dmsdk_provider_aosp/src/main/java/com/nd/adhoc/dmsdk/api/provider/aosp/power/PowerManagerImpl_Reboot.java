package com.nd.adhoc.dmsdk.api.provider.aosp.power;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.exception.ErrorCode;
import com.nd.adhoc.dmsdk.api.manager.power.IPowerManager_Reboot;
import com.nd.sdp.android.serviceloader.annotation.Service;

/**
 * 重启
 */
@Service(IPowerManager_Reboot.class)
public class PowerManagerImpl_Reboot implements IPowerManager_Reboot {


    @Override
    public void exec() throws DeviceManagerSecurityException {

        DeviceManagerContainer container = DeviceManagerContainer.getInstance();

        DevicePolicyManager devicePolicyManager = container.getDevicePolicyManager();

        ComponentName componentName = container.getComponentName();

        if (container.getComponentName() == null) {
            throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }

        if (devicePolicyManager == null) {
            throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            try {
                devicePolicyManager.reboot(componentName);
            }catch (RuntimeException e){
                e.printStackTrace();
            }
            return;
        }
        throw new UnsupportedOperationException("不支持此API的操作");
    }

    @Override
    public void release(@NonNull Context context) {

    }
}
