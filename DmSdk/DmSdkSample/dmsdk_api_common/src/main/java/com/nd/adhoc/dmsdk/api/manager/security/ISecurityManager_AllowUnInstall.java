package com.nd.adhoc.dmsdk.api.manager.security;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.api.IDeviceManager;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;

import java.util.List;

/**
 * 允许应用被卸载
 */
public interface ISecurityManager_AllowUnInstall extends IDeviceManager {
    /**
     * 从黑名单中移除被限制卸载应用的应用列表
     * @param packageName 应用包名
     */
    void removePackageToUninstallList(@NonNull Context context, @NonNull String packageName) throws DeviceManagerSecurityException;
}
