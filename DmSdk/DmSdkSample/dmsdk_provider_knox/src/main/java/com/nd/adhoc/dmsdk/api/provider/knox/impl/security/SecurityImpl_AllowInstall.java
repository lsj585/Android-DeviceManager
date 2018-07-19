package com.nd.adhoc.dmsdk.api.provider.knox.impl.security;
import android.app.enterprise.ApplicationPolicy;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiFunctionKey;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.security.ISecurity_AllowInstall;
import com.nd.adhoc.dmsdk.api.provider.knox.KnoxDeviceManagerFactory;
import com.nd.adhoc.dmsdk.filed.DmSdkConstants;
import com.nd.sdp.android.serviceloader.annotation.Service;

@Service(IDmSdkApi.class)
@ApiImpl(ISecurity_AllowInstall.class)
@ApiFunctionKey(DmSdkConstants.MANAGER_SECURITY_ALLOWINSTALL)

public class SecurityImpl_AllowInstall implements ISecurity_AllowInstall {

    @Override
    public boolean removePackageToInstallList(@NonNull Context context, @NonNull String packageName){

        ApplicationPolicy applicationPolicy= KnoxDeviceManagerFactory.getInstance().getApplicationPolicy(context);
        if(applicationPolicy==null){
            return false;
        }
        //TODO zyb 此处最高异常待定，需要核对API
        try {
            applicationPolicy.setApplicationUninstallationDisabled(packageName);
            return true;
        }catch (SecurityException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void release(@NonNull Context context) {

    }
}
