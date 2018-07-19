package com.nd.adhoc.dmsdk.api.provider.knox.impl.hardware.devicelock;

import android.app.enterprise.RestrictionPolicy;
import android.app.enterprise.kioskmode.KioskMode;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.api.provider.knox.utils.Verification;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.exception.ErrorCode;

import java.util.List;

/**
 * @author richsjeson  设备按键解锁和加锁
 */

public class DeviceLockImpl_Base {
    /**
     * 执行打开/关闭 硬件功能的操作
     * @param context
     * @param isOpen
     * @throws DeviceManagerSecurityException
     */
    protected void turnOff(@NonNull Context context, boolean isOpen,@NonNull  List<Integer> pList) throws DeviceManagerSecurityException {
        RestrictionPolicy restrictionPolicy= Verification.isRestrictionPolicyNull(context);
        KioskMode kioskMode=KioskMode.getInstance(context);
        try {
            kioskMode.allowHardwareKeys(pList,isOpen);
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
}
