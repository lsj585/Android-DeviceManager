package com.nd.adhoc.dmsdk.api.manager.security;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.api.IDeviceManager;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerUnsupportException;

import java.util.List;

/**
 * 应用卸载被阻止
 */
public interface ISecurityManager_DisallowUninstall extends IDeviceManager {
    /**
     * 添加某个应用被限制卸载的应用列表进黑名单
     * @param packageName 应用包名
     */
    void addPackageToUninstallList(@NonNull Context context,@NonNull String  packageName) throws DeviceManagerSecurityException, DeviceManagerUnsupportException;
}
