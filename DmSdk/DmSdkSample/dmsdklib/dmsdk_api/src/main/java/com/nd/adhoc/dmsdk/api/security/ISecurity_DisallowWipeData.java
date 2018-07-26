package com.nd.adhoc.dmsdk.api.security;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;

import java.util.List;

/**
 * 应用缓存清理
 */
public interface ISecurity_DisallowWipeData extends IDmSdkApi {
    /**
     * 添加某个应用被限制清理数据的进入黑名单
     * @param packages 应用清单
     */
    boolean disallowClearData(@NonNull Context context, @NonNull List<String> packages) ;

    /**
     * 添加某个应用被限制清理缓存的进入黑名单
     * @param packages 应用清单
     */
    boolean disallowClearCacheData(@NonNull Context context, @NonNull List<String> packages);
}
