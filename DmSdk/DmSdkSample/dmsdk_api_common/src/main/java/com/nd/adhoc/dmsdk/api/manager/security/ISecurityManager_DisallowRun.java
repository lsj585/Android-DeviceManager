package com.nd.adhoc.dmsdk.api.manager.security;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.api.IDeviceManager;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerUnsupportException;

import java.util.List;

/**
 * 阻止应用运行
 */
public interface ISecurityManager_DisallowRun extends IDeviceManager {
    /**
     * 添加要被限制运行的应用列表进入黑名单
     * @param context
     * @param packages 需要被禁用的APP列表
     * @throws DeviceManagerSecurityException
     */
    boolean addPackageToRunList(@NonNull Context context,@NonNull List packages) throws DeviceManagerSecurityException, DeviceManagerUnsupportException;
}
