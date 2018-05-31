package com.nd.adhoc.dmsdk.api.provider.knox.app;

import android.app.admin.DevicePolicyManager;
import android.app.enterprise.ApplicationPolicy;
import android.content.ComponentName;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.api.BaseManager;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.exception.ErrorCode;
import com.nd.adhoc.dmsdk.api.manager.app.IApplicationManager_GetPackageList;
import com.nd.adhoc.dmsdk.api.manager.app.IApplicationManager_WipeData;
import com.nd.adhoc.dmsdk.api.provider.knox.KnoxDeviceManagerFactory;

import java.util.Arrays;
import java.util.List;

public class ApplicationManagerImpl_GetPackageList extends BaseManager implements IApplicationManager_GetPackageList{

    public ApplicationManagerImpl_GetPackageList(@NonNull DevicePolicyManager devicePolicyManager, @NonNull ComponentName componentName) {
        super(devicePolicyManager, componentName);
    }

    public ApplicationManagerImpl_GetPackageList(@NonNull DevicePolicyManager devicePolicyManager) {
        super(devicePolicyManager);
    }

    @Override
    public void release(@NonNull Context context) {

    }

    @Override
    public List<String> getApplicationPackageList(@NonNull Context context) throws DeviceManagerSecurityException {
        ApplicationPolicy applicationPolicy=KnoxDeviceManagerFactory.getInstance().getApplicationPolicy(context);
        if(applicationPolicy==null){
            throw  new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }
        //TODO zyb 此处最高异常待定，需要核对API
        try {
            String[] packageList=applicationPolicy.getInstalledApplicationsIDList();
            if(packageList != null){
                return Arrays.asList(packageList);
            }
            return  null;
        }catch (Exception e){
            throw  new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }
    }
}
