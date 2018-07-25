package com.nd.adhoc.dmsdk.api.provider.knox.impl.hardware;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;

/**
 *@author richsjeson
 * 硬件开关
 */

public interface IHardwareOperation_Swith {

     /**
      * 硬件开关
      * @param context
      * @param isOpen  ture 开启 false 关闭
      * @throws DeviceManagerSecurityException
      */
     void derall(@NonNull Context context, boolean isOpen) throws DeviceManagerSecurityException;
}
