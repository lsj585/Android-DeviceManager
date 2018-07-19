package com.nd.adhoc.dmsdk.api.provider.knox.impl.hardware.devicelock;

import android.app.enterprise.RestrictionPolicy;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.KeyEvent;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiFunctionKey;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.hardware.devicelock.IDeviceLock_IsLock;
import com.nd.adhoc.dmsdk.api.provider.knox.utils.Verification;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.filed.DmSdkConstants;
import com.nd.sdp.android.serviceloader.annotation.Service;

import java.util.ArrayList;
import java.util.List;
@ApiFunctionKey(DmSdkConstants.MANAGER_HARDWARE_LOCK)
@Service(IDmSdkApi.class)
@ApiImpl(IDeviceLock_IsLock.class)
public class DeviceLockImpl_IsLock implements IDeviceLock_IsLock {

    private List<Integer> mAvailableHwKeys;

    @Override
    public boolean isOpen(@NonNull Context context) throws DeviceManagerSecurityException {
        RestrictionPolicy restrictionPolicy= Verification.isRestrictionPolicyNull(context);
        return restrictionPolicy.isActivationLockAllowed(true);
    }

    @Override
    public void release(@NonNull Context context) {
        mAvailableHwKeys.clear();
        mAvailableHwKeys=null;
    }

    /**
     * 设备锁定时，不允许按键响应
     */
    public List<Integer> getKeyList(){
        if(mAvailableHwKeys==null) {
            mAvailableHwKeys= new ArrayList();
            mAvailableHwKeys.add(new Integer(KeyEvent.KEYCODE_BACK));
            mAvailableHwKeys.add(new Integer(KeyEvent.KEYCODE_VOLUME_DOWN));
            mAvailableHwKeys.add(new Integer(KeyEvent.KEYCODE_VOLUME_UP));
            mAvailableHwKeys.add(new Integer(KeyEvent.KEYCODE_HOME));
            mAvailableHwKeys.add(new Integer(KeyEvent.KEYCODE_POWER));
        }
        return mAvailableHwKeys;
    }
}
