package com.nd.adhoc.dmsdk.api.provider.huawei.impl.hardware.devicelock;

import android.content.ComponentName;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.KeyEvent;

import com.huawei.android.app.admin.DeviceRestrictionManager;
import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.api.hardware.IHardwareOperation_Swith;
import com.nd.adhoc.dmsdk.api.provider.huawei.HWDeviceManagerFactory;;

import java.util.ArrayList;
import java.util.List;

/**
 * @author richsjeson  设备按键解锁和加锁
 */

public class DeviceLockImpl_Base implements IHardwareOperation_Swith {

    public DeviceLockImpl_Base() {

    }


    @Override
    public boolean derall(@NonNull Context context, boolean isOpen) {
        ComponentName componentName = DeviceManagerContainer.getInstance().getComponentName();
        try {
            DeviceRestrictionManager manager = HWDeviceManagerFactory.getInstance(context).getDeviceRestrictionManager();
            if (componentName == null) {
                return false;
            }

            manager.setBackButtonDisabled(componentName, !isOpen);
            manager.setHomeButtonDisabled(componentName, !isOpen);
            manager.setTaskButtonDisabled(componentName, !isOpen);
            manager.setPowerDisabled(componentName, !isOpen);
            manager.setShutdownMenuDisabled(componentName, !isOpen);
            manager.setVolumeAdjustDisabled(componentName, !isOpen);
            manager.setVoiceOutgoingDisabled(componentName, !isOpen);
        } catch (SecurityException | StackOverflowError e) {
            e.printStackTrace();
        }
        return false;
    }

    public void frees(@NonNull Context context) {

    }
}
