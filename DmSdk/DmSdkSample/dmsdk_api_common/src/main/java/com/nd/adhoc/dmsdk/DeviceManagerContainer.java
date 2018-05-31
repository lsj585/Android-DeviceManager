package com.nd.adhoc.dmsdk;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.support.annotation.NonNull;

/**
 * 设备管理器工厂
 */
public class DeviceManagerContainer {


    private static DeviceManagerContainer mDeviceManagrFactory;

    private DevicePolicyManager mDevicePolicyManager;

    private ComponentName mComponentName;

    public  static DeviceManagerContainer getInstance() {
        //使用同步锁，避免在多个线程中使用，导致产生多个实体类。
        if (mDeviceManagrFactory == null) {
            synchronized (DeviceManagerContainer.class){
                if(mDeviceManagrFactory==null){
                    mDeviceManagrFactory = new DeviceManagerContainer();
                }
            }
        }
        return mDeviceManagrFactory;
    }





    public void init(@NonNull DevicePolicyManager devicePolicyManager, @NonNull ComponentName componentName) {
        this.mDevicePolicyManager=devicePolicyManager;
        this.mComponentName=componentName;
    }


    public void init(@NonNull DevicePolicyManager devicePolicyManager){

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

    public void release(){

        if(mDeviceManagrFactory==null){
            mDeviceManagrFactory=null;
        }
    }
}
