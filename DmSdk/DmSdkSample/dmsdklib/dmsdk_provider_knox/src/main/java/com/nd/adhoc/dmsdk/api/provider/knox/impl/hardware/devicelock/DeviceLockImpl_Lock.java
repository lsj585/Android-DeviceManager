package com.nd.adhoc.dmsdk.api.provider.knox.impl.hardware.devicelock;
import android.content.Context;
import android.support.annotation.NonNull;
import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.hardware.devicelock.IDeviceLock_Lock;
import com.nd.sdp.android.serviceloader.annotation.Service;


@Service(IDmSdkApi.class)
@ApiImpl(IDeviceLock_Lock.class)
public class DeviceLockImpl_Lock extends DeviceLockImpl_Base implements IDeviceLock_Lock {


    @Override
    public boolean open(@NonNull Context context) {

        return derall(context, true);
    }

    @Override
    public void release(@NonNull Context context) {
        super.frees(context);
    }
}
