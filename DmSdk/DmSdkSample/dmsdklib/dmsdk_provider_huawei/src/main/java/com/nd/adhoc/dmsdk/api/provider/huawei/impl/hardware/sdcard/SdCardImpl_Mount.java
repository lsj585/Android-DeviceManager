package com.nd.adhoc.dmsdk.api.provider.huawei.impl.hardware.sdcard;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.hardware.sdcard.ISdCard_Mount;
import com.nd.sdp.android.serviceloader.annotation.Service;

@Service(IDmSdkApi.class)
@ApiImpl(ISdCard_Mount.class)
public class SdCardImpl_Mount extends SdCardImpl_Base implements ISdCard_Mount {

    @Override
    public boolean open(@NonNull Context context){
        return derall(context,true);
    }
    @Override
    public void release(@NonNull Context context) {

    }
}
