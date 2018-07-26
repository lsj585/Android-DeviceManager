package com.nd.adhoc.dmsdk.api.hardware.base;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;

/**
 *@
 */
public interface IHardware_IsOpen extends IDmSdkApi {
    /**
     * 判断是否打开
     * @return
     */
     boolean isOpen(@NonNull Context context) throws DeviceManagerSecurityException;
}
