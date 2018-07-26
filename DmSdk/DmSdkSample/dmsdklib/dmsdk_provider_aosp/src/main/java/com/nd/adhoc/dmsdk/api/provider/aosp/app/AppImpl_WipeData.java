package com.nd.adhoc.dmsdk.api.provider.aosp.app;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.app.IApp_WipeData;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.provider.aosp.utils.DeviceControlUtils;
import com.nd.sdp.android.serviceloader.annotation.Service;

@Service(IDmSdkApi.class)
@ApiImpl(IApp_WipeData.class)
public class AppImpl_WipeData implements IApp_WipeData {

    @Override
    public void release(@NonNull Context context) {

    }

    @Override
    public boolean clearData(@NonNull Context context,String packageName) {

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
        return false;
    }
}
