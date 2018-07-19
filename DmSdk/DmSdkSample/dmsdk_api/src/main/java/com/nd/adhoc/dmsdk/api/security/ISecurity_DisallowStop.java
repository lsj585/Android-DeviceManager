package com.nd.adhoc.dmsdk.api.security;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;

import java.util.List;

/**
 * 阻止应用被进程杀死
 */
public interface ISecurity_DisallowStop extends IDmSdkApi {


    /**
     * 添加需要被限制停止某个应用的黑名单
     * @param context
     * @param packages 需要被禁用的APP列表
     * @throws DeviceManagerSecurityException
     */
    boolean addPackageToStopList(@NonNull Context context, @NonNull List packages) throws DeviceManagerSecurityException;

}
