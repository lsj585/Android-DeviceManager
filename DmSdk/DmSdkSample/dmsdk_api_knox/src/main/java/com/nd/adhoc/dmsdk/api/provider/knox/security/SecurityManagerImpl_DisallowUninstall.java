package com.nd.adhoc.dmsdk.api.provider.knox.security;
import android.app.enterprise.ApplicationPolicy;
import android.content.Context;
import android.support.annotation.NonNull;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.exception.ErrorCode;
import com.nd.adhoc.dmsdk.api.manager.security.ISecurityManager_DisallowUninstall;
import com.nd.adhoc.dmsdk.api.provider.knox.KnoxDeviceManagerFactory;

public class SecurityManagerImpl_DisallowUninstall  implements ISecurityManager_DisallowUninstall {

    @Override
    public boolean addPackageToUninstallList(@NonNull Context context, @NonNull String packageName) throws DeviceManagerSecurityException {

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
