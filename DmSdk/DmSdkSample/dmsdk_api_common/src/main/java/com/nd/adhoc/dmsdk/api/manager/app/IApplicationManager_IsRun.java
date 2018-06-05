package com.nd.adhoc.dmsdk.api.manager.app;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.api.IDeviceManager;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;

import java.util.List;

/**
 * 获取应用列表
 */
public interface IApplicationManager_IsRun extends IDeviceManager {
    /**
     * 判断是否在运行中
     * @param context
     * @param packageName 包名
     * @return
     * @throws DeviceManagerSecurityException
     */
    boolean isRunning(@NonNull Context context,@NonNull String packageName) throws DeviceManagerSecurityException;
}
