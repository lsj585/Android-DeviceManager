package com.nd.adhoc.dmsdk.api.provider.huawei.impl.hardware.mobiledata;

import android.content.ComponentName;
import android.content.Context;
import android.support.annotation.NonNull;

import com.huawei.android.app.admin.DeviceRestrictionManager;
import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.hardware.mobiledata.IMobileData_IsOpen;
import com.nd.adhoc.dmsdk.api.provider.huawei.HWDeviceManagerFactory;
import com.nd.sdp.android.serviceloader.annotation.Service;

@Service(IDmSdkApi.class)
@ApiImpl(IMobileData_IsOpen.class)
public class MobileDataImpl_IsOpen implements IMobileData_IsOpen {

    @Override
    public boolean isOpen(@NonNull Context context) {
        ComponentName componentName = DeviceManagerContainer.getInstance().getComponentName();
        try {
            DeviceRestrictionManager manager = HWDeviceManagerFactory.getInstance(context).getDeviceRestrictionManager();
            if (componentName == null) {
                return false;
            }

            return manager.isDataConnectivityDisabled(componentName);
        } catch (SecurityException | StackOverflowError e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void release(@NonNull Context context) {

    }

}