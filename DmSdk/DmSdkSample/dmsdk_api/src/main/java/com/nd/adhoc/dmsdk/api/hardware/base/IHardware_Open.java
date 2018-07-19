package com.nd.adhoc.dmsdk.api.hardware.base;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;

/**
 *@
 */
public interface IHardware_Open extends IDmSdkApi {
    /**
     * 开启
     */
    boolean open(@NonNull Context context) throws DeviceManagerSecurityException;
}
