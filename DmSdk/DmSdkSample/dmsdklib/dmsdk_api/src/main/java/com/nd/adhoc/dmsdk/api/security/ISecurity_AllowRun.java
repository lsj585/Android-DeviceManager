package com.nd.adhoc.dmsdk.api.security;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;

import java.util.List;

/**
 * 允许应用运行
 */
public interface ISecurity_AllowRun extends IDmSdkApi {
    /**
     * 从黑名单中移除被限制运行的应用列表
     * @param list
     */
    boolean allowRun(@NonNull Context context, @NonNull List<String> list);
}
