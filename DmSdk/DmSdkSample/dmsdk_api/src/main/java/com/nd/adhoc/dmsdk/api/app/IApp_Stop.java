package com.nd.adhoc.dmsdk.api.app;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;

/**
 * 停止应用
 */
public interface IApp_Stop extends IDmSdkApi {

    /**
     * 停止某个应用
     * @param packageName 包名
     */
    boolean stopApp(@NonNull Context context, @NonNull String packageName);
}
