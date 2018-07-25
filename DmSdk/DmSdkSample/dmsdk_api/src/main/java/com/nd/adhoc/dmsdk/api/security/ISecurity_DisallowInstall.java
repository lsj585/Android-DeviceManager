package com.nd.adhoc.dmsdk.api.security;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.exception.DeviceManagerUnsupportException;

/**
 * 阻止应用被安装
 */
public interface ISecurity_DisallowInstall extends IDmSdkApi {
    /**
     * 加入黑名单中移除被限制安装应用的应用列表
     * @param packageName 应用包名
     */
    boolean disallowInstall(@NonNull Context context,@NonNull String packageName);
}
