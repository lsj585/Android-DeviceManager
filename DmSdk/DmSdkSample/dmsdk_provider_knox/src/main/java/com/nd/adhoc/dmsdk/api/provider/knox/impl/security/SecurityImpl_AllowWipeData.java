package com.nd.adhoc.dmsdk.api.provider.knox.impl.security;
import android.app.enterprise.ApplicationPolicy;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiFunctionKey;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.security.ISecurity_AllowWipeData;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.exception.ErrorCode;
import com.nd.adhoc.dmsdk.api.provider.knox.KnoxDeviceManagerFactory;
import com.nd.adhoc.dmsdk.filed.DmSdkConstants;
import com.nd.sdp.android.serviceloader.annotation.Service;

import java.util.List;
@Service(IDmSdkApi.class)
@ApiImpl(ISecurity_AllowWipeData.class)
@ApiFunctionKey(DmSdkConstants.MANAGER_SECURITY_ALLOWWIPEDATA)
public class SecurityImpl_AllowWipeData implements ISecurity_AllowWipeData {

    @Override
    public void release(@NonNull Context context) {

    }

    @Override
    public boolean allowCleadData(@NonNull Context context, @NonNull List<String> packages) throws DeviceManagerSecurityException {
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
    public boolean allowClearCache(@NonNull Context context, @NonNull List<String> packages) throws DeviceManagerSecurityException {
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
