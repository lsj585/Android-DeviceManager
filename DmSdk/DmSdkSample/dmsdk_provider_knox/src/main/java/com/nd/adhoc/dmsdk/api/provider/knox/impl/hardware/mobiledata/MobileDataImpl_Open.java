package com.nd.adhoc.dmsdk.api.provider.knox.impl.hardware.mobiledata;
import android.content.Context;
import android.support.annotation.NonNull;
import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.hardware.mobiledata.IMobileData_Open;
import com.nd.sdp.android.serviceloader.annotation.Service;
@Service(IDmSdkApi.class)
@ApiImpl(IMobileData_Open.class)
public class MobileDataImpl_Open extends MobileDataImpl_Base implements IMobileData_Open {

    @Override
    public boolean open(@NonNull Context context) {
        try {
            derall(context,true);
        }catch (DeviceManagerSecurityException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    public void release(@NonNull Context context) {

    }
}
