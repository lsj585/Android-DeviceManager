package com.nd.adhoc.dmsdk.api.manager.app;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.api.IDeviceManager;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;

/**
 * 停止应用
 */
public interface IApplicationManager_Stop extends IDeviceManager {

    /**
     * 停止某个应用
     * @param packageName 包名
     */
    boolean stopApp(@NonNull Context context, @NonNull String packageName) throws DeviceManagerSecurityException;
}
