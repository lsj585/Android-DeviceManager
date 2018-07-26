package com.nd.adhoc.dmsdk.api.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;

import java.util.List;

/**
 * 获取应用列表
 */
public interface IApp_GetPackageList extends IDmSdkApi {

    @Nullable List<String> getApplicationPackageList(@NonNull Context context) throws DeviceManagerSecurityException;
}
