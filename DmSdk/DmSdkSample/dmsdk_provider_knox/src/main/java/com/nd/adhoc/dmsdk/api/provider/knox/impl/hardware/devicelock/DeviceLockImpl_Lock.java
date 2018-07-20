package com.nd.adhoc.dmsdk.api.provider.knox.impl.hardware.devicelock;

import android.app.enterprise.RestrictionPolicy;
import android.app.enterprise.kioskmode.KioskMode;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.KeyEvent;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiFunctionKey;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.hardware.devicelock.IDeviceLock_Lock;
import com.nd.adhoc.dmsdk.api.provider.knox.utils.Verification;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.exception.ErrorCode;
import com.nd.adhoc.dmsdk.filed.DmSdkConstants;
import com.nd.sdp.android.serviceloader.annotation.Service;

import java.util.ArrayList;
import java.util.List;

@Service(IDmSdkApi.class)
@ApiImpl(IDeviceLock_Lock.class)
public class DeviceLockImpl_Lock extends DeviceLockImpl_Base implements  IDeviceLock_Lock {

    private List<Integer> mAvailableHwKeys;

    @Override
    public boolean open(@NonNull Context context){
        try {
            turnOff(context,true);
        }catch (DeviceManagerSecurityException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void release(@NonNull Context context) {
        mAvailableHwKeys.clear();
        mAvailableHwKeys=null;
    }


    private void turnOff(@NonNull Context context,boolean isOpen) throws DeviceManagerSecurityException {
        RestrictionPolicy restrictionPolicy= Verification.isRestrictionPolicyNull(context);
        KioskMode kioskMode=KioskMode.getInstance(context);
        List<Integer> availableHwKeys=getKeyList();
        try {
            kioskMode.allowHardwareKeys(availableHwKeys,isOpen);
            boolean isSuccess=restrictionPolicy.allowActivationLock(isOpen);
            if(!isSuccess){
                //TODO zyb 此处需要定义ErrorCode中的枚举值为开启失败
                throw  new DeviceManagerSecurityException(ErrorCode.DEFAULT_OPERATION_ERROR);
            }
        }catch (SecurityException e){
            //TODO zyb 此处需要定义ErrorCode中的枚举值为开启失败
            throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }


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
