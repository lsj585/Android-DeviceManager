package com.nd.adhoc.dmsdk.api.provider.huawei.impl.security;
import android.content.ComponentName;
import android.content.Context;
import android.support.annotation.NonNull;

import com.huawei.android.app.admin.DeviceApplicationManager;
import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.provider.huawei.HWDeviceManagerFactory;
import com.nd.adhoc.dmsdk.api.security.ISecurity_AllowRun;
import com.nd.sdp.android.serviceloader.annotation.Service;
import java.util.List;

@Service(IDmSdkApi.class)
@ApiImpl(ISecurity_AllowRun.class)
public class SecurityImpl_AllowRun implements ISecurity_AllowRun {

    @Override
    public void release(@NonNull Context context) {

    }
    @Override
    public boolean allowRun(@NonNull Context context, @NonNull List<String> packages) {
        try {
            DeviceApplicationManager packageManager = HWDeviceManagerFactory.getInstance(context).getDeviceAppManager();
            ComponentName componentName = DeviceManagerContainer.getInstance().getComponentName();
            if (componentName==null && packageManager == null) {
                return false;
            }
            packageManager.removeDisallowedRunningApp(componentName,packages);
        } catch (SecurityException  e ) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
