package com.nd.adhoc.dmsdk.api.provider.aosp.hardware.bluetooth;
import android.content.Context;
import android.os.UserManager;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.api.hardware.IHardwareOperation_Swith;
import com.nd.adhoc.dmsdk.api.provider.aosp.utils.DeviceControlUtils;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;

/**
 * @author richsjeson
 */

public class BlutoothImpl_Base implements IHardwareOperation_Swith {
    /**
     * 执行打开/关闭 硬件功能的操作
     * @param context
     * @param isOpen
     * @throws DeviceManagerSecurityException
     */
    public boolean derall(@NonNull Context context, boolean isOpen) {
        try {
            DeviceControlUtils.operation(context,isOpen, UserManager.DISALLOW_BLUETOOTH);
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
