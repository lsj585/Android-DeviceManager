package com.nd.adhoc.dmsdk.api.manager.security;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.api.IDeviceManager;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;

import java.util.List;

/**
 * 允许应用被杀死
 */
public interface ISecurityManager_AllowStop extends IDeviceManager {
    /**
     * 从黑名单中移除被限制停止运行应用的应用列表
     * @param pakcages 应用清单
     */
    boolean removePackageToStopList(@NonNull Context context, @NonNull List pakcages) throws DeviceManagerSecurityException;
}
