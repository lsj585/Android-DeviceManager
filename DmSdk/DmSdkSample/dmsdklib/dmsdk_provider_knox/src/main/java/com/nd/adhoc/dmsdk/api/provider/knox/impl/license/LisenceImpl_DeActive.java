package com.nd.adhoc.dmsdk.api.provider.knox.impl.license;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.license.ILicense_DeActive;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.sdp.android.serviceloader.annotation.Service;
/**
 * License 激活
 */
@Service(IDmSdkApi.class)
@ApiImpl(ILicense_DeActive.class)
public class LisenceImpl_DeActive implements ILicense_DeActive {

    @Override
    public void release(@NonNull Context context) {

    }

    @Override
    public void deActive() throws DeviceManagerSecurityException {

        DeviceManagerContainer container=DeviceManagerContainer.getInstance();
        DevicePolicyManager manager= container.getDevicePolicyManager();
        ComponentName componentName=container.getComponentName();


        if(manager==null ){
            return;
        }

        if(componentName==null){
           return;
        }

        //取消设备激活
        if (manager.isAdminActive(componentName)) {
            manager.removeActiveAdmin(componentName);
        }
    }
}
