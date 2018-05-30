package com.nd.adhoc.dmsdk.api;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;

public class BaseManager {

    private DevicePolicyManager mDevicePolicyManager;

    private ComponentName mComponentName;


    public BaseManager( DevicePolicyManager devicePolicyManager,ComponentName componentName){

        this.mDevicePolicyManager=devicePolicyManager;
        this.mComponentName=componentName;
    }


    public BaseManager( DevicePolicyManager devicePolicyManager){

        this.mDevicePolicyManager=devicePolicyManager;
    }


    public DevicePolicyManager getDevicePolicyManager() {
        if(mDevicePolicyManager==null){
            return null;
        }
        return mDevicePolicyManager;
    }

    public ComponentName getComponentName() {
        if(mComponentName==null){
            return null;
        }
        return mComponentName;
    }
}
