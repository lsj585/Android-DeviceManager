package com.nd.adhoc.dmsdk.api.provider.knox.security;
import android.app.enterprise.ApplicationPolicy;
import android.content.Context;
import android.support.annotation.NonNull;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.exception.ErrorCode;
import com.nd.adhoc.dmsdk.api.manager.security.ISecurityManager_AllowWipeData;
import com.nd.adhoc.dmsdk.api.provider.knox.KnoxDeviceManagerFactory;

import java.util.List;

public class SecurityManagerImpl_AllowWipeData  implements  ISecurityManager_AllowWipeData{
    @Override
    public void release(@NonNull Context context) {

    }

    @Override
    public boolean removePackageToClearDataList(@NonNull Context context, @NonNull List packages) throws DeviceManagerSecurityException {
        ApplicationPolicy applicationPolicy= KnoxDeviceManagerFactory.getInstance().getApplicationPolicy(context);
        if(applicationPolicy==null){
            return false;
        }
        //TODO zyb 此处最高异常待定，需要核对API
        try {
            return applicationPolicy.removePackagesFromClearDataWhiteList(packages);
        }catch (SecurityException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removePackageToClearCacheList(@NonNull Context context, @NonNull List packages) throws DeviceManagerSecurityException {
        ApplicationPolicy applicationPolicy= KnoxDeviceManagerFactory.getInstance().getApplicationPolicy(context);
        if(applicationPolicy==null){
            throw  new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }
        //TODO zyb 此处最高异常待定，需要核对API
        try {
            return applicationPolicy.removePackagesFromClearCacheWhiteList(packages);
        }catch (SecurityException e){
            e.printStackTrace();
        }
        return false;
    }
}
