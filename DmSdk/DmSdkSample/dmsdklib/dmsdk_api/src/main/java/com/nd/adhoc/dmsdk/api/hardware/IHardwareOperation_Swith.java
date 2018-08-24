package com.nd.adhoc.dmsdk.api.hardware;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;

/**
 * Created by richsjeson on 2018/8/22.
 */

public interface IHardwareOperation_Swith {

    /**
     * 硬件开关
     * @param context
     * @param isOpen  ture 开启 false 关闭
     * @throws DeviceManagerSecurityException
     */
    boolean derall(@NonNull Context context, boolean isOpen) throws DeviceManagerSecurityException;
}
