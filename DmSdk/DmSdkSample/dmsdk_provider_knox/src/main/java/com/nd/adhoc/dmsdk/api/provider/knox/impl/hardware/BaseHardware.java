package com.nd.adhoc.dmsdk.api.provider.knox.impl.hardware;

import android.app.enterprise.RestrictionPolicy;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.api.provider.knox.utils.Verification;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.exception.ErrorCode;

/**
 *@author richsjeson
 */

public class BaseHardware {
    /**
     * 开启或关闭硬件能力
     * @param context
     * @param isOpen
     * @throws DeviceManagerSecurityException
     */
    protected void  turnOff(@NonNull Context context, boolean isOpen) throws DeviceManagerSecurityException {
        RestrictionPolicy restrictionPolicy= Verification.isRestrictionPolicyNull(context);
        try {
            boolean isSuccess = restrictionPolicy.setWiFiState(isOpen);
            if (!isSuccess) {
                //TODO zyb 此处需要定义ErrorCode中的枚举值为开启失败
                throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
            }
        }catch (SecurityException e){
            //TODO zyb 此处需要定义ErrorCode中的枚举值为开启失败
            throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }
    }
}
