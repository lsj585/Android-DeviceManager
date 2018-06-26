package com.nd.adhoc.dmsdk.api.manager.security;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.api.IDeviceManager;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerUnsupportException;

import java.util.List;

/**
 * 阻止应用被安装
 */
public interface ISecurityManager_DisallowInstall extends IDeviceManager {
    /**
     * 加入黑名单中移除被限制安装应用的应用列表
     * @param packageName 应用包名
     */
    boolean addPackageToInstallList(@NonNull Context context,@NonNull String packageName) throws DeviceManagerSecurityException, DeviceManagerUnsupportException;
}
