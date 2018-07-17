package com.nd.adhoc.dmsdk.api.provider.knox.hardware;

import android.app.enterprise.RestrictionPolicy;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.exception.ErrorCode;
import com.nd.adhoc.dmsdk.api.manager.hardware.IMicrophoneManager;
import com.nd.adhoc.dmsdk.api.provider.utils.Verification;
import com.nd.sdp.android.serviceloader.annotation.Service;

@Service(IMicrophoneManager.class)
public class MicrophoneManagerImpl implements IMicrophoneManager {

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
    public boolean close(@NonNull Context context) {
        try {
            turnOff(context, false);
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean isOpen(@NonNull Context context) throws DeviceManagerSecurityException {
        RestrictionPolicy restrictionPolicy = Verification.isRestrictionPolicyNull(context);
        return restrictionPolicy.isMicrophoneEnabled(true);
    }

    @Override
    public void release(@NonNull Context context) {

    }


    private void turnOff(@NonNull Context context, boolean isOpen) throws DeviceManagerSecurityException {
        RestrictionPolicy restrictionPolicy = Verification.isRestrictionPolicyNull(context);
        try {
            boolean isSuccess = restrictionPolicy.setMicrophoneState(isOpen);
            if (!isSuccess) {
                //TODO zyb 此处需要定义ErrorCode中的枚举值为开启失败
                throw new DeviceManagerSecurityException(ErrorCode.DEFAULT_OPERATION_ERROR);
            }
        } catch (SecurityException e) {
            //TODO zyb 此处需要定义ErrorCode中的枚举值为开启失败
            throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }
    }
}
