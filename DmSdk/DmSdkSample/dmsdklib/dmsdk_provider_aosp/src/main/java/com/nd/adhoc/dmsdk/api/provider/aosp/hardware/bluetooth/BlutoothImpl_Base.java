package com.nd.adhoc.dmsdk.api.provider.aosp.hardware.bluetooth;
import android.content.Context;
import android.os.UserManager;
import android.support.annotation.NonNull;
import com.nd.adhoc.dmsdk.api.provider.aosp.utils.DeviceControlUtils;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;

/**
 * Created by richsjeson on 2018/7/19.
 */

public class BlutoothImpl_Base {
    /**
     * 执行打开/关闭 硬件功能的操作
     * @param context
     * @param isOpen
     * @throws DeviceManagerSecurityException
     */
    protected void turnOff(@NonNull Context context, boolean isOpen) throws DeviceManagerSecurityException {
        DeviceControlUtils.operation(context,isOpen, UserManager.DISALLOW_BLUETOOTH);
    }
}
