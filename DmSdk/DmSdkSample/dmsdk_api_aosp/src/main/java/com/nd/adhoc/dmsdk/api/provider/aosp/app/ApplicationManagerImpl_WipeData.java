package com.nd.adhoc.dmsdk.api.provider.aosp.app;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerUnsupportException;
import com.nd.adhoc.dmsdk.api.exception.ErrorCode;
import com.nd.adhoc.dmsdk.api.manager.app.IApplicationManager_WipeData;
import com.nd.adhoc.dmsdk.api.provider.aosp.utils.DeviceControlUtils;

public class ApplicationManagerImpl_WipeData implements IApplicationManager_WipeData {

    @Override
    public void release(@NonNull Context context) {

    }

    @Override
    public boolean clearData(@NonNull Context context,String packageName) throws DeviceManagerSecurityException, DeviceManagerUnsupportException {

        DeviceManagerContainer container = DeviceManagerContainer.getInstance();

        if(container==null){
            return false;
        }

        DevicePolicyManager devicePolicyManager = container.getDevicePolicyManager();

        ComponentName componentName = container.getComponentName();

        DeviceControlUtils.isVerificationNull(context,devicePolicyManager,componentName);
        //清除缓存  Android  P可使用
        if (Build.VERSION.SDK_INT >= 28) {
            try{
                devicePolicyManager.clearApplicationUserData(componentName,packageName,context.getMainExecutor(),null);
                return true;
            }catch (SecurityException e){
                e.printStackTrace();
            }
            return false;
        }
        throw new DeviceManagerUnsupportException(ErrorCode.ERROR_CODE_UN_SUPPORT);

    }
}
