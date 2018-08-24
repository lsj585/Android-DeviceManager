package com.nd.adhoc.dmsdk.api.provider.huawei.impl.hardware.sdcard;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.hardware.sdcard.ISdCard_UnMount;
import com.nd.adhoc.dmsdk.api.provider.huawei.impl.hardware.sdcard.SdCardImpl_Base;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.sdp.android.serviceloader.annotation.Service;

@Service(IDmSdkApi.class)
@ApiImpl(ISdCard_UnMount.class)
public class SdCardImpl_UnMount extends SdCardImpl_Base implements ISdCard_UnMount {

    @Override
    public boolean close(@NonNull Context context){
        return derall(context,false);
    }

    @Override
    public void release(@NonNull Context context) {

    }
}
