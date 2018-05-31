package com.nd.adhoc.dmsdk.api.manager.security;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.api.IDeviceManager;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;

import java.util.List;

/**
 * 允许应用运行
 */
public interface ISecurityManager_AllowRun extends IDeviceManager {
    /**
     * 从黑名单中移除被限制运行的应用列表
     * @param list
     */
    void removePackageToRunList(@NonNull Context context, @NonNull List list) throws DeviceManagerSecurityException;
}
