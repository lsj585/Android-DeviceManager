package com.nd.adhoc.dmsdk.api.provider.knox.impl.hardware.devicelock;

import android.app.enterprise.RestrictionPolicy;
import android.app.enterprise.kioskmode.KioskMode;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import com.nd.adhoc.dmsdk.api.provider.knox.impl.hardware.IHardwareOperation_Swith;
import com.nd.adhoc.dmsdk.api.provider.knox.utils.Verification;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.exception.ErrorCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author richsjeson  设备按键解锁和加锁
 */

public class DeviceLockImpl_Base implements IHardwareOperation_Swith {


    protected List<Integer> mAvailableHwKeys;

    public DeviceLockImpl_Base(){
        mAvailableHwKeys= new ArrayList();
        mAvailableHwKeys.add(new Integer(KeyEvent.KEYCODE_BACK));
        mAvailableHwKeys.add(new Integer(KeyEvent.KEYCODE_VOLUME_DOWN));
        mAvailableHwKeys.add(new Integer(KeyEvent.KEYCODE_VOLUME_UP));
        mAvailableHwKeys.add(new Integer(KeyEvent.KEYCODE_HOME));
        mAvailableHwKeys.add(new Integer(KeyEvent.KEYCODE_POWER));
    }


    @Override
    public void derall(@NonNull Context context, boolean isOpen) throws DeviceManagerSecurityException {
        RestrictionPolicy restrictionPolicy= Verification.isRestrictionPolicyNull(context);
        KioskMode kioskMode=KioskMode.getInstance(context);
        try {
            kioskMode.allowHardwareKeys(mAvailableHwKeys,isOpen);
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

    public void frees(@NonNull Context context) {
        mAvailableHwKeys.clear();
        mAvailableHwKeys=null;
    }
}
