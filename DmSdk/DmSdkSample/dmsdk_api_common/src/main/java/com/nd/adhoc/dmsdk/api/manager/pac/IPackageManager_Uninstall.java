package com.nd.adhoc.dmsdk.api.manager.pac;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.api.IDeviceManager;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;

import java.io.FileNotFoundException;

/**
 * 安装包管理---静默安装和卸载
 */
public interface IPackageManager_Uninstall extends IDeviceManager {
    /**
     * 卸载应用
     * @param packageName 需要被卸载的应用的包名
     */
    void uninstall(@NonNull Context context, @NonNull String packageName) throws DeviceManagerSecurityException;

}
