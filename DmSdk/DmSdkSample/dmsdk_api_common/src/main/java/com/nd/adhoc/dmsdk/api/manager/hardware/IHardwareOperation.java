package com.nd.adhoc.dmsdk.api.manager.hardware;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.api.IDeviceManager;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;

/**
 *@
 */
public interface IHardwareOperation extends IDeviceManager {
    /**
     * 开启
     */
     void open(@NonNull Context context) throws DeviceManagerSecurityException;
    /**
     * 关闭
     */
    void close(@NonNull Context context) throws DeviceManagerSecurityException;

    /**
     * 判断是否打开
     * @return
     */
     boolean isOpen(@NonNull Context context) throws DeviceManagerSecurityException;
}
