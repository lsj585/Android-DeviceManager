package com.nd.adhoc.dmsdk.api.provider.aosp.hardware.microphone;
import android.content.Context;
import android.support.annotation.NonNull;
import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.hardware.microphone.IMicrophone_Close;
import com.nd.sdp.android.serviceloader.annotation.Service;

@Service(IDmSdkApi.class)
@ApiImpl(IMicrophone_Close.class)
public class MicrophoneImpl_Close extends MicrophoneImpl_Base implements IMicrophone_Close {
    @Override
    public boolean close(@NonNull Context context) {
        return derall(context, false);
    }

    @Override
    public void release(@NonNull Context context) {

    }
}
