package com.nd.adhoc.dmsdk.api.provider.huawei.impl.pac;
import android.content.ComponentName;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.huawei.android.app.admin.DevicePackageManager;
import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.pac.IPackage_Uninstall;
import com.nd.adhoc.dmsdk.api.provider.huawei.HWDeviceManagerFactory;
import com.nd.sdp.android.serviceloader.annotation.Service;

@Service(IDmSdkApi.class)
@ApiImpl(IPackage_Uninstall.class)
public class PackageImpl_UnInstall implements IPackage_Uninstall {

    @Override
    public void release(@NonNull Context context) {

    }

    @Override
    public boolean uninstall(@NonNull Context context, @NonNull String packageName){

        if (TextUtils.isEmpty(packageName)) {
           return false;
        }
        try {
            DevicePackageManager packageManager = HWDeviceManagerFactory.getInstance(context).getDevicePackageManager();
            ComponentName componentName = DeviceManagerContainer.getInstance().getComponentName();
            if (componentName==null && packageManager == null) {
                return false;
            }
            packageManager.uninstallPackage(componentName,packageName,true);
        } catch (SecurityException  e ) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
