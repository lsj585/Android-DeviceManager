package com.nd.adhoc.dmsdk.api.system;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;

import java.util.List;

/**
 * 应用管理
 */
public interface ISystem_ApplicationList extends IDmSdkApi {


    @Nullable List<String> getApplicationPakcageList(@NonNull Context context) throws DeviceManagerSecurityException;
}
