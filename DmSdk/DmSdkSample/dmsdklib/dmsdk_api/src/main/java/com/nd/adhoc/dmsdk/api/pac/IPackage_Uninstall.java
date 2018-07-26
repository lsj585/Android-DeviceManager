package com.nd.adhoc.dmsdk.api.pac;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;

/**
 * 安装包管理---静默安装和卸载
 */
public interface IPackage_Uninstall extends IDmSdkApi {
    /**
     * 卸载应用
     * @param packageName 需要被卸载的应用的包名
     */
    boolean uninstall(@NonNull Context context, @NonNull String packageName) throws DeviceManagerSecurityException;

}
