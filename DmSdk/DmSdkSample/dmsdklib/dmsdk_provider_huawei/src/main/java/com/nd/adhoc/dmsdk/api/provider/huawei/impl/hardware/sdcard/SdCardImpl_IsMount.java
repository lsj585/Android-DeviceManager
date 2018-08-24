package com.nd.adhoc.dmsdk.api.provider.huawei.impl.hardware.sdcard;

import android.content.ComponentName;
import android.content.Context;
import android.support.annotation.NonNull;

import com.huawei.android.app.admin.DeviceRestrictionManager;
import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.hardware.sdcard.ISdCard_IsMount;
import com.nd.adhoc.dmsdk.api.provider.huawei.HWDeviceManagerFactory;
import com.nd.sdp.android.serviceloader.annotation.Service;

@Service(IDmSdkApi.class)
@ApiImpl(ISdCard_IsMount.class)
public class SdCardImpl_IsMount implements ISdCard_IsMount {
    @Override
    public boolean isOpen(@NonNull Context context) {
        ComponentName componentName = DeviceManagerContainer.getInstance().getComponentName();
        try {
            DeviceRestrictionManager manager = HWDeviceManagerFactory.getInstance(context).getDeviceRestrictionManager();

            if (componentName == null) {
                return false;
            }
            return manager.isExternalStorageDisabled(componentName);
        } catch (SecurityException | StackOverflowError e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void release(@NonNull Context context) {

    }

}
