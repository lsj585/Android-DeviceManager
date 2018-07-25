package com.nd.adhoc.dmsdk.api.provider.knox.utils;

import android.app.enterprise.RestrictionPolicy;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.exception.ErrorCode;
import com.nd.adhoc.dmsdk.api.provider.knox.KnoxDeviceManagerFactory;

/**
 * 校验类
 * @author richsjeosn
 */
public class Verification {
    /**
     * 校验RestrictionPolicy是否为null，如果为Null，则抛出异常给上层捕获，如果不为Null,则返回当前对象
     * @param context
     * @return RestrictionPolicy
     * @throws DeviceManagerSecurityException
     */
    public static RestrictionPolicy isRestrictionPolicyNull(@NonNull Context context) throws DeviceManagerSecurityException {
        if(context==null){
            //抛出异常
            throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }
        RestrictionPolicy restrictionPolicy= KnoxDeviceManagerFactory.getInstance().getRestrictionPolicy(context);
        if(restrictionPolicy==null){
            throw  new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }
        return restrictionPolicy;
    }
}
