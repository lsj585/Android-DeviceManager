package com.nd.adhoc.dmsdk.api.provider.huawei.impl.hardware.mobiledata;
import android.content.ComponentName;
import android.content.Context;
import android.support.annotation.NonNull;

import com.huawei.android.app.admin.DeviceRestrictionManager;
import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.api.hardware.IHardwareOperation_Swith;
import com.nd.adhoc.dmsdk.api.provider.huawei.HWDeviceManagerFactory;

public class MobileDataImpl_Base implements IHardwareOperation_Swith {
    @Override
    public boolean derall(@NonNull Context context, boolean isOpen) {
        ComponentName componentName = DeviceManagerContainer.getInstance().getComponentName();
        DeviceRestrictionManager manager = HWDeviceManagerFactory.getInstance(context).getDeviceRestrictionManager();
        if(componentName==null){
            return false;
        }
        try {
            manager.setDataConnectivityDisabled(componentName,!isOpen);
        } catch (SecurityException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
