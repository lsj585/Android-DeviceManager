package com.nd.adhoc.dmsdk.api.provider.knox.security;
import android.app.enterprise.ApplicationPolicy;
import android.content.Context;
import android.support.annotation.NonNull;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.manager.security.ISecurityManager_AllowUnInstall;
import com.nd.adhoc.dmsdk.api.provider.knox.KnoxDeviceManagerFactory;
import com.nd.sdp.android.serviceloader.annotation.Service;

@Service(ISecurityManager_AllowUnInstall.class)
public class SecurityManagerImpl_AllowUninstall implements ISecurityManager_AllowUnInstall{
    @Override
    public boolean removePackageToUninstallList(@NonNull Context context, @NonNull String packageName) throws DeviceManagerSecurityException {

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
