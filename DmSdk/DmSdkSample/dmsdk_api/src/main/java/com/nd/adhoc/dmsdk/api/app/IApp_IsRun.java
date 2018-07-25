package com.nd.adhoc.dmsdk.api.app;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;

/**
 * 获取应用列表
 */
public interface IApp_IsRun extends IDmSdkApi {
    /**
     * 判断是否在运行中
     * @param context
     * @param packageName 包名
     * @return
     * @throws DeviceManagerSecurityException
     */
    boolean isRunning(@NonNull Context context,@NonNull String packageName);
}
