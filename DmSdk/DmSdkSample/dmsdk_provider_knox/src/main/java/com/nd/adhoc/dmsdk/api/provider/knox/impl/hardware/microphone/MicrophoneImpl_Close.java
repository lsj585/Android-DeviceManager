package com.nd.adhoc.dmsdk.api.provider.knox.impl.hardware.microphone;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiFunctionKey;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.hardware.microphone.IMicrophone_Close;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.filed.DmSdkConstants;
import com.nd.sdp.android.serviceloader.annotation.Service;

@ApiFunctionKey(DmSdkConstants.MANAGER_HARDWARE_MICROPHONE)
@Service(IDmSdkApi.class)
@ApiImpl(IMicrophone_Close.class)
public class MicrophoneImpl_Close extends MicrophoneImpl_Base implements IMicrophone_Close {
    @Override
    public boolean close(@NonNull Context context) {
        try {
            turnOff(context, false);
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void release(@NonNull Context context) {

    }
}
