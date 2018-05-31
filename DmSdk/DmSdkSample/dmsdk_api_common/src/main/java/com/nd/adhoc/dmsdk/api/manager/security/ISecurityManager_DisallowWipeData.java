package com.nd.adhoc.dmsdk.api.manager.security;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.api.IDeviceManager;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;

import java.util.List;

/**
 * 应用缓存清理
 */
public interface ISecurityManager_DisallowWipeData extends IDeviceManager{
    /**
     * 添加某个应用被限制清理数据的进入黑名单
     * @param packages 应用清单
     */
    void addPackageToClearDataList(@NonNull Context context, @NonNull List packages) throws DeviceManagerSecurityException;

    /**
     * 添加某个应用被限制清理缓存的进入黑名单
     * @param packages 应用清单
     */
    void addPackageToClearCacheList(@NonNull Context context, @NonNull List packages) throws DeviceManagerSecurityException;
}
