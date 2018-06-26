package com.nd.adhoc.dmsdk.api.manager.security;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.api.IDeviceManager;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;

import java.util.List;

/**
 * 允许应用被安装
 */
public interface ISecurityManager_AllowInstall extends IDeviceManager {
    /**
     * 从黑名单中移除被限制安装的应用列表
     * @param packageName
     */
    boolean removePackageToInstallList(@NonNull Context context, @NonNull String packageName) throws DeviceManagerSecurityException;
}
