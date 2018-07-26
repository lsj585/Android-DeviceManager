package com.nd.adhoc.dmsdk.api.security;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.exception.DeviceManagerUnsupportException;

import java.util.List;

/**
 * 阻止应用运行
 */
public interface ISecurity_DisallowRun extends IDmSdkApi {
    /**
     * 添加要被限制运行的应用列表进入黑名单
     * @param context
     * @param packages 需要被禁用的APP列表
     * @throws DeviceManagerSecurityException
     */
    boolean disallowRun(@NonNull Context context,@NonNull List<String> packages);
}
