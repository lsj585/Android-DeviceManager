package com.nd.adhoc.dmsdk.api.provider.huawei.impl.hardware.sdcard;

import android.content.ComponentName;
import android.content.Context;
import android.support.annotation.NonNull;

import com.huawei.android.app.admin.DeviceRestrictionManager;
import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.api.hardware.IHardwareOperation_Swith;
import com.nd.adhoc.dmsdk.api.provider.huawei.HWDeviceManagerFactory;

public class SdCardImpl_Base implements IHardwareOperation_Swith {

    @Override
    public boolean derall(@NonNull Context context, boolean isOpen) {
        ComponentName componentName = DeviceManagerContainer.getInstance().getComponentName();
        try {
            DeviceRestrictionManager manager = HWDeviceManagerFactory.getInstance(context).getDeviceRestrictionManager();
            if (componentName == null) {
                return false;
            }

            manager.setExternalStorageDisabled(componentName, !isOpen);
        } catch (SecurityException | StackOverflowError e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
