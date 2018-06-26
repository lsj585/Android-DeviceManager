package com.nd.adhoc.dmsdk.api.provider.knox.system;
import android.app.enterprise.ApplicationPolicy;
import android.content.Context;
import android.support.annotation.NonNull;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.exception.ErrorCode;
import com.nd.adhoc.dmsdk.api.manager.system.ISystemManager_ApplicationList;
import com.nd.adhoc.dmsdk.api.provider.knox.KnoxDeviceManagerFactory;

import java.util.Arrays;
import java.util.List;

public class SystemManagerImpl_GetApplicationList implements ISystemManager_ApplicationList {

    @Override
    public List getApplicationPakcageList(@NonNull Context context) throws DeviceManagerSecurityException {
        try {
            ApplicationPolicy applicationPolicy = KnoxDeviceManagerFactory.getInstance().getApplicationPolicy(context);
            if(applicationPolicy != null){
                return Arrays.asList(applicationPolicy.getInstalledApplicationsIDList());
            }else{
                throw  new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
            }

        }catch (SecurityException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void release(@NonNull Context context) {

    }
}
