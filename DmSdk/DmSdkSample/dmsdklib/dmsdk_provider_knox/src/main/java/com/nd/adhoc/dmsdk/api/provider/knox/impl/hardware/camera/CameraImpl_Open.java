package com.nd.adhoc.dmsdk.api.provider.knox.impl.hardware.camera;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.hardware.camera.ICamera_Open;
import com.nd.sdp.android.serviceloader.annotation.Service;

@Service(IDmSdkApi.class)
@ApiImpl(ICamera_Open.class)
public class CameraImpl_Open extends CameraImpl_Base implements ICamera_Open {

    @Override
    public boolean open(@NonNull Context context){
        return derall(context, true);
    }

    @Override
    public void release(@NonNull Context context) {

    }
}
