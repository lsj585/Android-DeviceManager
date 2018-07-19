package com.nd.adhoc.dmsdk.api.security;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;

import java.util.List;

/**ISecurity_AllowWipeData
 * 允许应用被安装
 */
public interface ISecurity_AllowWipeData extends IDmSdkApi {
    /**
     * 从黑名单中移除被限制清理应用数据的应用列表
     * @param packages 应用清单
     */
    boolean allowCleadData(@NonNull Context context, @NonNull List<String> packages) throws DeviceManagerSecurityException;

    /**
     * 从黑名单中移除被限制清理应用缓存的应用列表
     * @param packages 应用清单
     */
    boolean allowClearCache(@NonNull Context context, @NonNull List<String> packages) throws DeviceManagerSecurityException;
}
