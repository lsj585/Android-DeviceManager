package com.nd.adhoc.dmsdk.api.provider.huawei.impl.hardware.devicelock;
import android.content.Context;
import android.support.annotation.NonNull;
import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.hardware.devicelock.IDeviceLock_Unlock;
import com.nd.sdp.android.serviceloader.annotation.Service;

@Service(IDmSdkApi.class)
@ApiImpl(IDeviceLock_Unlock.class)
public class DeviceLockImpl_UnLock extends DeviceLockImpl_Base implements IDeviceLock_Unlock {

    @Override
    public boolean close(@NonNull Context context){
        return derall(context,true);
    }
    @Override
    public void release(@NonNull Context context) {
        super.frees(context);
    }
}
