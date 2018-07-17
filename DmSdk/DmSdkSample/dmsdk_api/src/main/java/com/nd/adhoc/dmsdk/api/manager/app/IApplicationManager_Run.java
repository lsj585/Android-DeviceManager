package com.nd.adhoc.dmsdk.api.manager.app;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.api.IDeviceManager;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;

/**
 * 运行应用
 */
public interface IApplicationManager_Run extends IDeviceManager {
    /**
     * 启动某个应用
     * @param packageName 包名
     * @param clsName apk所对应的launcher_activity的类名
     */
    boolean startApp(@NonNull Context context, @NonNull String packageName,@NonNull String clsName) throws DeviceManagerSecurityException;
}
