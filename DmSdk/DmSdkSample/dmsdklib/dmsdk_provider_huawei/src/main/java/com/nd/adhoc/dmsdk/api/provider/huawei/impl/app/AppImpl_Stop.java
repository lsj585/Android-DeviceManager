package com.nd.adhoc.dmsdk.api.provider.huawei.impl.app;

import android.content.ComponentName;
import android.content.Context;
import android.support.annotation.NonNull;

import com.huawei.android.app.admin.DeviceApplicationManager;
import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.app.IApp_Stop;
import com.nd.adhoc.dmsdk.api.provider.huawei.HWDeviceManagerFactory;
import com.nd.sdp.android.serviceloader.annotation.Service;

@Service(IDmSdkApi.class)
@ApiImpl(IApp_Stop.class)
public class AppImpl_Stop implements IApp_Stop {

    @Override
    public void release(@NonNull Context context) {

    }

    @Override
    public boolean stopApp(@NonNull Context context, String packageName) {
        DeviceApplicationManager manager = HWDeviceManagerFactory.getInstance(context).getDeviceAppManager();
        ComponentName componentName = DeviceManagerContainer.getInstance().getComponentName();
        if (manager == null && componentName == null) {
            return false;
        }
        try {
            manager.killApplicationProcess(componentName, packageName);
        }catch (SecurityException | IllegalArgumentException  e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
