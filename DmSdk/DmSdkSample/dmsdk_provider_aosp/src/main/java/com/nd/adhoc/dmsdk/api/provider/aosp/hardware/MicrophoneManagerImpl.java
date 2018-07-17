package com.nd.adhoc.dmsdk.api.provider.aosp.hardware;
import android.content.Context;
import android.os.UserManager;
import android.support.annotation.NonNull;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.manager.hardware.IMicrophoneManager;
import com.nd.adhoc.dmsdk.api.provider.aosp.utils.DeviceControlUtils;
import com.nd.sdp.android.serviceloader.annotation.Service;

@Service(IMicrophoneManager.class)
public class MicrophoneManagerImpl implements IMicrophoneManager {

    @Override
    public boolean open(@NonNull Context context){
        try {
            turnOff(context,true);
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean close(@NonNull Context context){
        try {
            turnOff(context,false);
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean isOpen(@NonNull Context context) throws DeviceManagerSecurityException {
        return DeviceControlUtils.isOpen(context,UserManager.DISALLOW_UNMUTE_MICROPHONE);
    }

    @Override
    public void release(@NonNull Context context) {

    }


    private void turnOff(@NonNull Context context,boolean isOpen) throws DeviceManagerSecurityException {
        DeviceControlUtils.operation(context,isOpen,UserManager.DISALLOW_UNMUTE_MICROPHONE);
    }
}
