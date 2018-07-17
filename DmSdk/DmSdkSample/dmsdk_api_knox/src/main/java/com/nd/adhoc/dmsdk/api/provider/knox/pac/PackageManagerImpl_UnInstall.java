package com.nd.adhoc.dmsdk.api.provider.knox.pac;

import android.app.enterprise.ApplicationPolicy;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.nd.adhoc.dmsdk.api.manager.pac.IPackageManager_Uninstall;
import com.nd.adhoc.dmsdk.api.provider.knox.KnoxDeviceManagerFactory;
import com.nd.sdp.android.serviceloader.annotation.Service;

@Service(IPackageManager_Uninstall.class)
public class PackageManagerImpl_UnInstall implements IPackageManager_Uninstall {

    @Override
    public void release(@NonNull Context context) {

    }

    @Override
    public boolean uninstall(@NonNull Context context, @NonNull String packageName){

        if (TextUtils.isEmpty(packageName)) {
           return false;
        }
        ApplicationPolicy applicationPolicy = KnoxDeviceManagerFactory.getInstance().getApplicationPolicy(context);
        if (applicationPolicy == null) {
            return false;
        }
        try {
            return applicationPolicy.uninstallApplication(packageName, false);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return false;
    }
}
