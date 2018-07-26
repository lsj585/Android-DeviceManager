package com.nd.adhoc.dmsdk.api.security;

import android.content.Context;
import android.support.annotation.NonNull;
import com.nd.adhoc.dmsdk.IDmSdkApi;

/**
 * 应用卸载被阻止
 */
public interface ISecurity_DisallowUninstall extends IDmSdkApi {
    /**
     * 添加某个应用被限制卸载的应用列表进黑名单
     * @param packageName 应用包名
     */
    boolean disallowUninstall(@NonNull Context context,@NonNull String  packageName);
}
