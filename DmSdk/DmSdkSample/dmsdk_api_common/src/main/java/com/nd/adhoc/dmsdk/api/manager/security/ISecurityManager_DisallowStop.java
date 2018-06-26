package com.nd.adhoc.dmsdk.api.manager.security;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.api.IDeviceManager;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;

import java.util.List;

/**
 * 阻止应用被进程杀死
 */
public interface ISecurityManager_DisallowStop extends IDeviceManager {


    /**
     * 添加需要被限制停止某个应用的黑名单
     * @param context
     * @param packages 需要被禁用的APP列表
     * @throws DeviceManagerSecurityException
     */
    boolean addPackageToStopList(@NonNull Context context, @NonNull List packages) throws DeviceManagerSecurityException;

}
