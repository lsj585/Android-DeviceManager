package com.nd.adhoc.dmsdk.api.provider.knox.impl.hardware.bluetooth;

import android.app.enterprise.RestrictionPolicy;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.api.provider.knox.utils.Verification;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.exception.ErrorCode;

/**
 * Created by richsjeson on 2018/7/19.
 */

public class BlutoothImpl_Base {
    /**
     * 执行打开/关闭 硬件功能的操作
     * @param context
     * @param isOpen
     * @throws DeviceManagerSecurityException
     */
    protected void turnOff(@NonNull Context context, boolean isOpen) throws DeviceManagerSecurityException {
        RestrictionPolicy restrictionPolicy = Verification.isRestrictionPolicyNull(context);
        try {
            boolean isSuccess = restrictionPolicy.allowBluetooth(isOpen);
            if (!isSuccess) {
                //TODO zyb 此处需要定义ErrorCode中的枚举值为开启失败
                throw new DeviceManagerSecurityException(ErrorCode.DEFAULT_OPERATION_ERROR);
            }
        } catch (SecurityException e) {
            e.printStackTrace();
            //TODO zyb 此处需要定义ErrorCode中的枚举值为开启失败
            throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }
    }
}
