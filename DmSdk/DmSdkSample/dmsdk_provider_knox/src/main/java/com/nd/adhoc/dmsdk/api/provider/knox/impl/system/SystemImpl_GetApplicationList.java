package com.nd.adhoc.dmsdk.api.provider.knox.impl.system;
import android.app.enterprise.ApplicationPolicy;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiFunctionKey;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.exception.ErrorCode;
import com.nd.adhoc.dmsdk.api.system.ISystem_ApplicationList;
import com.nd.adhoc.dmsdk.api.provider.knox.KnoxDeviceManagerFactory;
import com.nd.adhoc.dmsdk.filed.DmSdkConstants;
import com.nd.sdp.android.serviceloader.annotation.Service;

import java.util.Arrays;
import java.util.List;


@Service(IDmSdkApi.class)
@ApiImpl(ISystem_ApplicationList.class)
@ApiFunctionKey(DmSdkConstants.MANAGER_SECURITY_ALLOWINSTALL)
public class SystemImpl_GetApplicationList implements ISystem_ApplicationList {

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
