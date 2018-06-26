package com.nd.adhoc.dmsdk.api.provider.aosp.utils;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.UserManager;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.exception.ErrorCode;

/**
 * 校验类
 * @author richsjeosn
 */
public class DeviceControlUtils {
    /**
     *
     * @param context
     * @param isOpen
     * @param key 用户策略的KEY 类似UserManager.DISALLOW_BLUETOOTH
     * @throws DeviceManagerSecurityException
     */
    public static void operation(@NonNull Context context,boolean isOpen,@NonNull String key) throws DeviceManagerSecurityException {

        if(TextUtils.isEmpty(key)){
            throw  new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }
        DeviceManagerContainer container = DeviceManagerContainer.getInstance();

        if(container==null){
            throw  new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }

        DevicePolicyManager devicePolicyManager = container.getDevicePolicyManager();
        ComponentName componentName = container.getComponentName();

        isVerificationNull(context,devicePolicyManager,componentName);

        if(isOpen){
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    //key
                    devicePolicyManager.addUserRestriction(componentName, key);
                }else{
                    throw  new DeviceManagerSecurityException(ErrorCode.DEFAULT_ERROR_CODE);
                }
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }else{
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    //key
                    devicePolicyManager.clearUserRestriction(componentName,key);
                }else{
                    throw  new DeviceManagerSecurityException(ErrorCode.DEFAULT_ERROR_CODE);
                }
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param context
     * @param key
     * @param key 用户策略的KEY 类似UserManager.DISALLOW_BLUETOOTH
     * @return true 表示操作成功 false表示操作失败
     * @throws DeviceManagerSecurityException
     */
    public   static boolean isOpen(@NonNull Context context,@NonNull  String key) throws DeviceManagerSecurityException {

        if(TextUtils.isEmpty(key)){
            throw  new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }

        DeviceManagerContainer container = DeviceManagerContainer.getInstance();

        if(container==null){
            throw  new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }

        DevicePolicyManager devicePolicyManager = container.getDevicePolicyManager();

        ComponentName componentName = container.getComponentName();

        isVerificationNull(context,devicePolicyManager,componentName);

        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                Bundle bundle= devicePolicyManager.getUserRestrictions(componentName);
                if(bundle != null) {
                    return bundle.getBoolean(key);
                }else{
                    throw new DeviceManagerSecurityException(ErrorCode.DEFAULT_ERROR_CODE);
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
            throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }
        return false;

    }

    public static void isVerificationNull(@NonNull Context context,DevicePolicyManager devicePolicyManager , ComponentName componentName ) throws DeviceManagerSecurityException {

        if(context==null){
            //抛出异常
            throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }

        if (componentName == null) {
            throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }

        if (devicePolicyManager == null) {
            throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }

    }

}
