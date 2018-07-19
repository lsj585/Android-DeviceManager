package com.nd.adhoc.dmsdk.api.security;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;

/**
 * 允许应用被卸载
 */
public interface ISecurity_AllowUnInstall extends IDmSdkApi {
    /**
     * 从黑名单中移除被限制卸载应用的应用列表
     * @param packageName 应用包名
     */
    boolean removePackageToUninstallList(@NonNull Context context, @NonNull String packageName) throws DeviceManagerSecurityException;
}
