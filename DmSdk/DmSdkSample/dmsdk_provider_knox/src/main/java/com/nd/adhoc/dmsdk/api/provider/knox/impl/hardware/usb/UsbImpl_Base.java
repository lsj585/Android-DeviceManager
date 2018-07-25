package com.nd.adhoc.dmsdk.api.provider.knox.impl.hardware.usb;

import android.app.enterprise.RestrictionPolicy;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.api.provider.knox.impl.hardware.IHardwareOperation_Swith;
import com.nd.adhoc.dmsdk.api.provider.knox.utils.Verification;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.exception.ErrorCode;

public class UsbImpl_Base implements IHardwareOperation_Swith{


    @Override
    public void derall(@NonNull Context context, boolean isOpen) throws DeviceManagerSecurityException {

        RestrictionPolicy restrictionPolicy = Verification.isRestrictionPolicyNull(context);
        try {
            boolean isSuccess = restrictionPolicy.isUsbMassStorageEnabled(isOpen);
            if (!isSuccess) {
                //TODO zyb 此处需要定义ErrorCode中的枚举值为开启失败
                throw new DeviceManagerSecurityException(ErrorCode.DEFAULT_OPERATION_ERROR);
            }
        } catch (SecurityException e) {
            //TODO zyb 此处需要定义ErrorCode中的枚举值为开启失败
            throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }
    }
}
