package com.nd.adhoc.dmsdk.api.provider.knox.security;

import android.app.admin.DevicePolicyManager;
import android.app.enterprise.ApplicationPolicy;
import android.content.ComponentName;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.api.BaseManager;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.exception.ErrorCode;
import com.nd.adhoc.dmsdk.api.manager.security.ISecurityManager_AllowRun;
import com.nd.adhoc.dmsdk.api.manager.security.ISecurityManager_DisallowRun;
import com.nd.adhoc.dmsdk.api.provider.knox.KnoxDeviceManagerFactory;

import java.util.List;

public class SecurityManagerImpl_AllowRun extends BaseManager implements ISecurityManager_AllowRun {

    public SecurityManagerImpl_AllowRun(@NonNull DevicePolicyManager devicePolicyManager, @NonNull ComponentName componentName) {
        super(devicePolicyManager, componentName);
    }

    public SecurityManagerImpl_AllowRun(@NonNull DevicePolicyManager devicePolicyManager) {
        super(devicePolicyManager);
    }

    @Override
    public void release(@NonNull Context context) {

    }
    @Override
    public void removePackageToRunList(@NonNull Context context, @NonNull List list) throws DeviceManagerSecurityException {
        ApplicationPolicy applicationPolicy=KnoxDeviceManagerFactory.getInstance().getApplicationPolicy(context);
        if(applicationPolicy==null){
            throw  new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }
        //TODO zyb 此处最高异常待定，需要核对API
        try {
            applicationPolicy.removePackagesFromPreventStartBlackList(list);
        }catch (Exception e){
            throw  new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }
    }

}
