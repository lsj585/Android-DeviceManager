package com.nd.adhoc.dmsdk.api.provider.aosp.hardware.microphone;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.UserManager;
import android.support.annotation.NonNull;
import com.nd.adhoc.dmsdk.api.hardware.IHardwareOperation_Swith;
import com.nd.adhoc.dmsdk.api.provider.aosp.utils.DeviceControlUtils;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;

public class MicrophoneImpl_Base implements IHardwareOperation_Swith {


    public void turnOff(@NonNull Context context, boolean isOpen) throws DeviceManagerSecurityException {



    }

    @Override
    public boolean derall(@NonNull Context context, boolean isOpen) {
        try {
            DeviceControlUtils.operation(context,isOpen, UserManager.DISALLOW_UNMUTE_MICROPHONE);
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
