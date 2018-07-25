package com.nd.adhoc.dmsdk.api.app;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.exception.DeviceManagerUnsupportException;

/**
 * 应用数据清理
 */
public interface IApp_WipeData extends IDmSdkApi {
    /**
     * 移除数据
     * @param packageName 包名
     */
    boolean clearData(@NonNull Context context,  @NonNull String packageName);
}
