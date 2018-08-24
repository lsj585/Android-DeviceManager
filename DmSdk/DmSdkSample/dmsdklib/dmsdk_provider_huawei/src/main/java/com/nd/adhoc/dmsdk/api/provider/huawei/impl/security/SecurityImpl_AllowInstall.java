package com.nd.adhoc.dmsdk.api.provider.huawei.impl.security;
import android.content.ComponentName;
import android.content.Context;
import android.support.annotation.NonNull;

import com.huawei.android.app.admin.DevicePackageManager;
import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.provider.huawei.HWDeviceManagerFactory;
import com.nd.adhoc.dmsdk.api.security.ISecurity_AllowInstall;
import com.nd.sdp.android.serviceloader.annotation.Service;

import java.util.ArrayList;
import java.util.List;

@Service(IDmSdkApi.class)
@ApiImpl(ISecurity_AllowInstall.class)

public class SecurityImpl_AllowInstall implements ISecurity_AllowInstall {

    @Override
    public void release(@NonNull Context context) {

    }

    @Override
    public boolean allowInstall(@NonNull Context context, @NonNull String packageName) {
        try {
            DevicePackageManager packageManager = HWDeviceManagerFactory.getInstance(context).getDevicePackageManager();
            ComponentName componentName = DeviceManagerContainer.getInstance().getComponentName();
            if (componentName==null && packageManager == null) {
                return false;
            }
            List<String> packages=new ArrayList<>();
            packages.add(packageName);
            packageManager.addInstallPackageWhiteList(componentName,packages);
        } catch (SecurityException  e ) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
