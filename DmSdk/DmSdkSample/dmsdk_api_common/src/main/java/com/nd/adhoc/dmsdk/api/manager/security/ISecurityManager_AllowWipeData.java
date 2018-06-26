package com.nd.adhoc.dmsdk.api.manager.security;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.api.IDeviceManager;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;

import java.util.List;

/**ISecurityManager_AllowWipeData
 * 允许应用被安装
 */
public interface ISecurityManager_AllowWipeData extends IDeviceManager {
    /**
     * 从黑名单中移除被限制清理应用数据的应用列表
     * @param packages 应用清单
     */
    boolean removePackageToClearDataList(@NonNull Context context, @NonNull List packages) throws DeviceManagerSecurityException;

    /**
     * 从黑名单中移除被限制清理应用缓存的应用列表
     * @param packages 应用清单
     */
    boolean removePackageToClearCacheList(@NonNull Context context, @NonNull List packages) throws DeviceManagerSecurityException;
}
