package com.nd.adhoc.dmsdk.api.provider.knox.license;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.nd.adhoc.dmsdk.api.BaseManager;
import com.nd.adhoc.dmsdk.api.manager.license.LicenseManager_Active;
import com.nd.adhoc.dmsdk.api.manager.license.LicenseManager_DeActive;
import com.nd.adhoc.dmsdk.revicer.ReciverConstants;

/**
 * License 激活
 */
public class LisenceManagerImpl_DeActive extends BaseManager implements LicenseManager_DeActive {


    public LisenceManagerImpl_DeActive(DevicePolicyManager devicePolicyManager, ComponentName componentName) {
        super(devicePolicyManager, componentName);
    }

    public LisenceManagerImpl_DeActive(DevicePolicyManager devicePolicyManager) {
        super(devicePolicyManager);
    }



    @Override
    public void release(Context context) {

    }

    @Override
    public void deActive(Context context) {

        if(getDevicePolicyManager()==null ){
            //抛出异常
            return;
        }

        if(getComponentName()==null){
            //抛出异常
            return;
        }

        //取消设备激活
        if (getDevicePolicyManager().isAdminActive(getComponentName())) {
            getDevicePolicyManager().removeActiveAdmin(getComponentName());
        }
    }
}
