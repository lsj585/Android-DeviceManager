package com.nd.adhoc.dmsdk.api.provider.knox.impl.hardware.mobiledata;
import android.content.Context;
import android.support.annotation.NonNull;
import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.hardware.mobiledata.IMobileData_Open;
import com.nd.sdp.android.serviceloader.annotation.Service;
@Service(IDmSdkApi.class)
@ApiImpl(IMobileData_Open.class)
public class MobileDataImpl_Open extends MobileDataImpl_Base implements IMobileData_Open {

    @Override
    public boolean open(@NonNull Context context) {
        return derall(context,true);
    }


    @Override
    public void release(@NonNull Context context) {

    }
}
