package com.nd.adhoc.dmsdk.api.provider.huawei.impl.app;
import android.content.ComponentName;
import android.content.Context;
import android.support.annotation.NonNull;

import com.huawei.android.app.admin.DevicePackageManager;
import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.app.IApp_ClearData;
import com.nd.adhoc.dmsdk.api.provider.huawei.HWDeviceManagerFactory;
import com.nd.sdp.android.serviceloader.annotation.Service;

@Service(IDmSdkApi.class)
@ApiImpl(IApp_ClearData.class)
public class AppImpl_ClearData implements IApp_ClearData {

    @Override
    public void release(@NonNull Context context) {

    }

    @Override
    public boolean clearData(@NonNull Context context, String packageName){
        DevicePackageManager manager=HWDeviceManagerFactory.getInstance(context).getDevicePackageManager();
        ComponentName componentName=DeviceManagerContainer.getInstance().getComponentName();
        if(componentName==null && manager==null){
            return false;
        }
        try {
            manager.clearPackageData(componentName,packageName);
        }catch (SecurityException | IllegalArgumentException  e){
            e.printStackTrace();
            return false;
        }
        return true;

    }
}
