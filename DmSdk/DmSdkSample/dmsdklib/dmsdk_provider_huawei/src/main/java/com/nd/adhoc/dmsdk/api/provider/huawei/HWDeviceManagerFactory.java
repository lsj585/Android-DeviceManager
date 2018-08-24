package com.nd.adhoc.dmsdk.api.provider.huawei;
import android.content.Context;
import android.support.annotation.NonNull;

import com.huawei.android.app.admin.DeviceApplicationManager;
import com.huawei.android.app.admin.DeviceCameraManager;
import com.huawei.android.app.admin.DevicePackageManager;
import com.huawei.android.app.admin.DeviceRestrictionManager;

/**
 * TODO ZYB 工厂类中的全局初始化需要验证
 */
public class HWDeviceManagerFactory {

    private static HWDeviceManagerFactory mDeviceManagrFactory;

    private DevicePackageManager mPackageManager;

    private DeviceApplicationManager mAppManager;

    private DeviceRestrictionManager mRestrictionManager;

    private DeviceCameraManager mDeviceCameraManager;


    private HWDeviceManagerFactory(@NonNull Context pContext) {
        if (mDeviceManagrFactory == null) {
            mDeviceManagrFactory = new HWDeviceManagerFactory(pContext);
        }
        mPackageManager=new DevicePackageManager();
        mAppManager=new DeviceApplicationManager();
        mRestrictionManager=new DeviceRestrictionManager();
        mDeviceCameraManager=new DeviceCameraManager();
    }

    public  static HWDeviceManagerFactory getInstance(@NonNull Context pContext) {
        //使用同步锁，避免在多个线程中使用，导致产生多个实体类。
        if (mDeviceManagrFactory == null) {
            synchronized (HWDeviceManagerFactory.class){
                if(mDeviceManagrFactory==null){
                    mDeviceManagrFactory = new HWDeviceManagerFactory(pContext);
                }
            }
        }
        return mDeviceManagrFactory;
    }


    public DevicePackageManager getDevicePackageManager(){
        return mPackageManager;
    }

    public DeviceApplicationManager getDeviceAppManager(){
        return mAppManager;
    }

    public DeviceRestrictionManager getDeviceRestrictionManager(){
        return mRestrictionManager;
    }

    public DeviceCameraManager getDeviceCameraManager(){
        return mDeviceCameraManager;
    }


}
