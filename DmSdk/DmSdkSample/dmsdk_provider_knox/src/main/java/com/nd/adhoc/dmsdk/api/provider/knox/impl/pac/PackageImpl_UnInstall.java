package com.nd.adhoc.dmsdk.api.provider.knox.impl.pac;

import android.app.enterprise.ApplicationPolicy;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiFunctionKey;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.pac.IPackage_Uninstall;
import com.nd.adhoc.dmsdk.api.provider.knox.KnoxDeviceManagerFactory;
import com.nd.adhoc.dmsdk.filed.DmSdkConstants;
import com.nd.sdp.android.serviceloader.annotation.Service;

@Service(IDmSdkApi.class)
@ApiImpl(IPackage_Uninstall.class)
@ApiFunctionKey(DmSdkConstants.MANAGER_PACKAGE_UNINSTALL)
public class PackageImpl_UnInstall implements IPackage_Uninstall {

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
