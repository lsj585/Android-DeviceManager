package com.nd.adhoc.dmsdk.api.provider.aosp.power;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.power.IPower_Reboot;
import com.nd.sdp.android.serviceloader.annotation.Service;

/**
 * 重启
 */
@Service(IDmSdkApi.class)
@ApiImpl(IPower_Reboot.class)
public class PowerImpl_Reboot implements IPower_Reboot {


    @Override
    public void exec(){

        DeviceManagerContainer container = DeviceManagerContainer.getInstance();

        DevicePolicyManager devicePolicyManager = container.getDevicePolicyManager();

        ComponentName componentName = container.getComponentName();

        if (container.getComponentName() == null) {
            return ;
        }

        if (devicePolicyManager == null) {
            return ;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            try {
                devicePolicyManager.reboot(componentName);
            }catch (RuntimeException e){
                e.printStackTrace();
            }
            return;
        }
        return;
    }

    @Override
    public void release(@NonNull Context context) {

    }
}
