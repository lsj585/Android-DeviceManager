package com.nd.adhoc.dmsdk.api.provider.aosp.hardware.microphone;
import android.content.Context;
import android.os.UserManager;
import android.support.annotation.NonNull;
import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.hardware.microphone.IMicrophone_IsOpen;
import com.nd.adhoc.dmsdk.api.provider.aosp.utils.DeviceControlUtils;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.sdp.android.serviceloader.annotation.Service;

@Service(IDmSdkApi.class)
@ApiImpl(IMicrophone_IsOpen.class)
public class MicrophoneImpl_IsOpen implements IMicrophone_IsOpen {

    @Override
    public boolean isOpen(@NonNull Context context) {
        try {
            return DeviceControlUtils.isOpen(context, UserManager.DISALLOW_UNMUTE_MICROPHONE);
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void release(@NonNull Context context) {

    }
}
