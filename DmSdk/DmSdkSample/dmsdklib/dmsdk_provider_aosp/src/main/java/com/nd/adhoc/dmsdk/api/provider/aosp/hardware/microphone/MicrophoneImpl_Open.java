package com.nd.adhoc.dmsdk.api.provider.aosp.hardware.microphone;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.hardware.microphone.IMicrophone_Open;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.sdp.android.serviceloader.annotation.Service;
@Service(IDmSdkApi.class)
@ApiImpl(MicrophoneImpl_Open.class)
public class MicrophoneImpl_Open extends MicrophoneImpl_Base  implements IMicrophone_Open {

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
