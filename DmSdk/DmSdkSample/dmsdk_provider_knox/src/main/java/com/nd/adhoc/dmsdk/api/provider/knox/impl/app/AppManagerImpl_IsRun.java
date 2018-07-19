package com.nd.adhoc.dmsdk.api.provider.knox.impl.app;

import android.app.enterprise.ApplicationPolicy;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiFunctionKey;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.app.IApp_IsRun;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.provider.knox.KnoxDeviceManagerFactory;
import com.nd.adhoc.dmsdk.filed.DmSdkConstants;
import com.nd.sdp.android.serviceloader.annotation.Service;

/**
 * 是否运行中
 */
@ApiFunctionKey(DmSdkConstants.MANAGER_APPLICATION_ISRUNNING)
@Service(IDmSdkApi.class)
@ApiImpl(IApp_IsRun.class)
public class AppManagerImpl_IsRun implements IApp_IsRun {

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
