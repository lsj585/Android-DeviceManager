package com.nd.adhoc.dmsdk.api.provider.huawei.impl.security;
import android.content.ComponentName;
import android.content.Context;
import android.support.annotation.NonNull;

import com.huawei.android.app.admin.DeviceApplicationManager;
import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.provider.huawei.HWDeviceManagerFactory;
import com.nd.adhoc.dmsdk.api.security.ISecurity_DisallowRun;
import com.nd.sdp.android.serviceloader.annotation.Service;

import java.util.List;


@Service(IDmSdkApi.class)
@ApiImpl(ISecurity_DisallowRun.class)
public class SecurityImpl_DisallowRun implements ISecurity_DisallowRun {

    @Override
    public void release(@NonNull Context context) {

    }

    @Override
    public boolean disallowRun(@NonNull Context context, @NonNull List<String> packages){
        try {
            DeviceApplicationManager packageManager = HWDeviceManagerFactory.getInstance(context).getDeviceAppManager();
            ComponentName componentName = DeviceManagerContainer.getInstance().getComponentName();
            if (componentName==null && packageManager == null) {
                return false;
            }
            packageManager.addDisallowedRunningApp(componentName,packages);
        } catch (SecurityException  e ) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
