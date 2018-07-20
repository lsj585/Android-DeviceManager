package com.nd.adhoc.dmsdk.api.security;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;

/**
 * 允许应用被安装
 */
public interface ISecurity_AllowInstall extends IDmSdkApi {
    /**
     * 从黑名单中移除被限制安装的应用列表
     * @param packageName
     */
    boolean allowInstall(@NonNull Context context, @NonNull String packageName);
}
