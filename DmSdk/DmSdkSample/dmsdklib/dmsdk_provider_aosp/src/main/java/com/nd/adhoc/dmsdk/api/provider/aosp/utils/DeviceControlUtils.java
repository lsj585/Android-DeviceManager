package com.nd.adhoc.dmsdk.api.provider.aosp.utils;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;

/**
 * 校验类
 *
 * @author richsjeosn
 */
public class DeviceControlUtils {
    /**
     * @param context
     * @param isOpen
     * @param key     用户策略的KEY 类似UserManager.DISALLOW_BLUETOOTH
     * @throws DeviceManagerSecurityException
     */
    public static void operation(@NonNull Context context, boolean isOpen, @NonNull String key) throws DeviceManagerSecurityException {

        if (TextUtils.isEmpty(key)) {
            return;
        }
        DeviceManagerContainer container = DeviceManagerContainer.getInstance();

        if (container == null) {
            return;
        }

        DevicePolicyManager devicePolicyManager = container.getDevicePolicyManager();
        ComponentName componentName = container.getComponentName();

        isVerificationNull(context, devicePolicyManager, componentName);

        if (isOpen) {
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    //key
                    devicePolicyManager.addUserRestriction(componentName, key);
                } else {
                    return;
                }
            } catch (SecurityException e) {
                e.printStackTrace();
            }
            return;
        }
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //key
                devicePolicyManager.clearUserRestriction(componentName, key);
            } else {
               return ;
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param context
     * @param key
     * @param key     用户策略的KEY 类似UserManager.DISALLOW_BLUETOOTH
     * @return true 表示操作成功 false表示操作失败
     * @throws DeviceManagerSecurityException
     */
    public static boolean isOpen(@NonNull Context context, @NonNull String key) throws DeviceManagerSecurityException {

        if (TextUtils.isEmpty(key)) {
            return false;
        }

        DeviceManagerContainer container = DeviceManagerContainer.getInstance();

        if (container == null) {
            return false;
        }

        DevicePolicyManager devicePolicyManager = container.getDevicePolicyManager();

        ComponentName componentName = container.getComponentName();

        isVerificationNull(context, devicePolicyManager, componentName);

        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                Bundle bundle = devicePolicyManager.getUserRestrictions(componentName);
                if (bundle != null) {
                    return bundle.getBoolean(key);
                }
                return false;
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return false;

    }

    public static void isVerificationNull(@NonNull Context context, @Nullable DevicePolicyManager devicePolicyManager, @Nullable ComponentName componentName) {

        if (componentName == null) {
            return;
        }

        if (devicePolicyManager == null) {
            return;
        }

    }

}
