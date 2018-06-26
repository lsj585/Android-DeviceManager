package com.nd.adhoc.dmsdk.api.provider.knox.security;
import android.app.enterprise.ApplicationPolicy;
import android.content.Context;
import android.support.annotation.NonNull;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.exception.ErrorCode;
import com.nd.adhoc.dmsdk.api.manager.security.ISecurityManager_AllowRun;
import com.nd.adhoc.dmsdk.api.provider.knox.KnoxDeviceManagerFactory;

import java.util.List;

public class SecurityManagerImpl_AllowRun implements ISecurityManager_AllowRun {

    @Override
    public void release(@NonNull Context context) {

    }
    @Override
    public boolean removePackageToRunList(@NonNull Context context, @NonNull List list) {
        ApplicationPolicy applicationPolicy=KnoxDeviceManagerFactory.getInstance().getApplicationPolicy(context);
        if(applicationPolicy==null){
           return false;
        }
        //TODO zyb 此处最高异常待定，需要核对API
        try {
            return applicationPolicy.removePackagesFromPreventStartBlackList(list);
        }catch (SecurityException e){
            e.printStackTrace();
        }
        return false;

    }

}
