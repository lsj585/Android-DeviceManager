package com.nd.adhoc.dmsdk.api.app;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;

/**
 * 运行应用
 */
public interface IApp_Run extends IDmSdkApi {
    /**
     * 启动某个应用
     * @param packageName 包名
     * @param clsName apk所对应的launcher_activity的类名
     */
    boolean startApp(@NonNull Context context, @NonNull String packageName,@NonNull String clsName);
}
