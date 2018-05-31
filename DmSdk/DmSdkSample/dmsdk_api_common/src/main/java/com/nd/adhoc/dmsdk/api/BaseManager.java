package com.nd.adhoc.dmsdk.api;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.exception.ErrorCode;

public class BaseManager {

    private DevicePolicyManager mDevicePolicyManager;

    private ComponentName mComponentName;


    public BaseManager(@NonNull DevicePolicyManager devicePolicyManager, @NonNull ComponentName componentName) {
        this.mDevicePolicyManager=devicePolicyManager;
        this.mComponentName=componentName;
    }


    public BaseManager(@NonNull DevicePolicyManager devicePolicyManager){

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
