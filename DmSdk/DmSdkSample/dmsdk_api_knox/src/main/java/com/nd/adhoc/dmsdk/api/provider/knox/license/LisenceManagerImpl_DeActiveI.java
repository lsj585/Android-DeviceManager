package com.nd.adhoc.dmsdk.api.provider.knox.license;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;

import com.nd.adhoc.dmsdk.api.BaseManager;
import com.nd.adhoc.dmsdk.api.manager.license.ILicenseManager_DeActive;

/**
 * License 激活
 */
public class LisenceManagerImpl_DeActiveI extends BaseManager implements ILicenseManager_DeActive {


    public LisenceManagerImpl_DeActiveI(DevicePolicyManager devicePolicyManager, ComponentName componentName) {
        super(devicePolicyManager, componentName);
    }

    public LisenceManagerImpl_DeActiveI(DevicePolicyManager devicePolicyManager) {
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
