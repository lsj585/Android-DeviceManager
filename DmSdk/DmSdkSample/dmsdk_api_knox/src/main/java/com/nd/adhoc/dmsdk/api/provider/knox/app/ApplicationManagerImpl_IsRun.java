package com.nd.adhoc.dmsdk.api.provider.knox.app;

import android.app.enterprise.ApplicationPolicy;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.exception.ErrorCode;
import com.nd.adhoc.dmsdk.api.manager.app.IApplicationManager_IsRun;
import com.nd.adhoc.dmsdk.api.provider.knox.KnoxDeviceManagerFactory;

/**
 * 是否运行中
 */
public class ApplicationManagerImpl_IsRun implements IApplicationManager_IsRun{

    @Override
    public void release(@NonNull Context context) {

    }
    @Override
    public boolean isRunning(@NonNull Context context, @NonNull String packageName) throws DeviceManagerSecurityException {
        ApplicationPolicy applicationPolicy=KnoxDeviceManagerFactory.getInstance().getApplicationPolicy(context);
        if(applicationPolicy==null){
            return false;
        }
        //TODO zyb 此处最高异常待定，需要核对API
        try {
            return applicationPolicy.isApplicationRunning(packageName);
        }catch (SecurityException e) {
            e.printStackTrace();
        }
        return false;
    }
}
