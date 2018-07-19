package com.nd.adhoc.dmsdk.api.provider.knox.impl.hardware.microphone;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiFunctionKey;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.hardware.microphone.IMicrophon_Open;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.filed.DmSdkConstants;
import com.nd.sdp.android.serviceloader.annotation.Service;

@ApiFunctionKey(DmSdkConstants.MANAGER_HARDWARE_MICROPHONE)
@Service(IDmSdkApi.class)
@ApiImpl(MicrophoneImpl_Open.class)
public class MicrophoneImpl_Open extends MicrophoneImpl_Base  implements IMicrophon_Open {

    @Override
    public boolean open(@NonNull Context context){
        try {
            turnOff(context, true);
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
