package com.nd.adhoc.dmsdk.api.provider.knox;

import android.app.enterprise.ApplicationPolicy;
import android.app.enterprise.EnterpriseDeviceManager;
import android.app.enterprise.RestrictionPolicy;
import android.app.enterprise.SecurityPolicy;
import android.app.enterprise.kioskmode.KioskMode;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * TODO ZYB 工厂类中的全局初始化需要验证
 */
public class KnoxDeviceManagerFactory {

    private static KnoxDeviceManagerFactory mDeviceManagrFactory;

    private EnterpriseDeviceManager mEnterpriseDeviceManager;

    private ApplicationPolicy mApplicationPolicy;

    private RestrictionPolicy mRestrictionPolicy;

    private SecurityPolicy mSecurityPolicy;

    private KnoxDeviceManagerFactory() {

    }

    public  static KnoxDeviceManagerFactory getInstance() {
        //使用同步锁，避免在多个线程中使用，导致产生多个实体类。
        if (mDeviceManagrFactory == null) {
            synchronized (KnoxDeviceManagerFactory.class){
                if(mDeviceManagrFactory==null){
                    mDeviceManagrFactory = new KnoxDeviceManagerFactory();
                }
            }
        }
        return mDeviceManagrFactory;
    }

    public EnterpriseDeviceManager getEnterpriseDeviceManager(@NonNull Context context) {
        if (mEnterpriseDeviceManager == null) {
            return mEnterpriseDeviceManager = new EnterpriseDeviceManager(context);
        }
        return mEnterpriseDeviceManager;
    }

    public ApplicationPolicy getApplicationPolicy(@NonNull Context context) {

        if (mApplicationPolicy != null) {
            return mApplicationPolicy;
        }
        if (mEnterpriseDeviceManager == null) {
            mEnterpriseDeviceManager = getEnterpriseDeviceManager(context);
        }
        mApplicationPolicy = mEnterpriseDeviceManager.getApplicationPolicy();
        return mApplicationPolicy;
    }

    public RestrictionPolicy getRestrictionPolicy(@NonNull Context context) {
        if (mRestrictionPolicy != null) {
            return mRestrictionPolicy;
        }
        if (mEnterpriseDeviceManager == null) {
            mEnterpriseDeviceManager = getEnterpriseDeviceManager(context);
        }
        mRestrictionPolicy = mEnterpriseDeviceManager.getRestrictionPolicy();
        return mRestrictionPolicy;
    }


    public SecurityPolicy getSecurityPolicy(@NonNull Context context) {
        if (mSecurityPolicy != null) {
            return mSecurityPolicy;
        }
        if (mEnterpriseDeviceManager == null) {
            mEnterpriseDeviceManager = getEnterpriseDeviceManager(context);
        }
        mSecurityPolicy = mEnterpriseDeviceManager.getSecurityPolicy();
        return mSecurityPolicy;

    }
}
