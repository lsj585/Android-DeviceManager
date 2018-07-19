package com.nd.adhoc.dmsdk.api.security;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.exception.DeviceManagerUnsupportException;

/**
 * 应用卸载被阻止
 */
public interface ISecurity_DisallowUninstall extends IDmSdkApi {
    /**
     * 添加某个应用被限制卸载的应用列表进黑名单
     * @param packageName 应用包名
     */
    boolean addPackageToUninstallList(@NonNull Context context,@NonNull String  packageName) throws DeviceManagerSecurityException, DeviceManagerUnsupportException;
}
