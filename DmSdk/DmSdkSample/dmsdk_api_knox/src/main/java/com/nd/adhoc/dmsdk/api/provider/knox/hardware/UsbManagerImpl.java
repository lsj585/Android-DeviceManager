package com.nd.adhoc.dmsdk.api.provider.knox.hardware;
import android.app.enterprise.RestrictionPolicy;
import android.content.Context;
import android.support.annotation.NonNull;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.exception.ErrorCode;
import com.nd.adhoc.dmsdk.api.manager.hardware.IUsbMamager;
import com.nd.adhoc.dmsdk.api.provider.knox.KnoxDeviceManagerFactory;
import com.nd.adhoc.dmsdk.api.provider.utils.Verification;

public class UsbManagerImpl  implements IUsbMamager {

    @Override
    public void open(@NonNull Context context) throws DeviceManagerSecurityException {
        turnOff(context,true);
    }

    @Override
    public void close(@NonNull Context context) throws DeviceManagerSecurityException {
        turnOff(context,false);
    }

    @Override
    public boolean isOpen(@NonNull Context context) throws DeviceManagerSecurityException {
        RestrictionPolicy restrictionPolicy= Verification.isRestrictionPolicyNull(context);
        return restrictionPolicy.setUsbMassStorage(true);
    }

    @Override
    public void release(@NonNull Context context) {

    }

    private void turnOff(@NonNull Context context,boolean isOpen) throws DeviceManagerSecurityException {
        RestrictionPolicy restrictionPolicy= Verification.isRestrictionPolicyNull(context);
        try {
            boolean isSuccess=restrictionPolicy.isUsbMassStorageEnabled(isOpen);
            if(!isSuccess){
                //TODO zyb 此处需要定义ErrorCode中的枚举值为开启失败
                throw  new DeviceManagerSecurityException(ErrorCode.DEFAULT_OPERATION_ERROR);
            }
        }catch (SecurityException e){
            //TODO zyb 此处需要定义ErrorCode中的枚举值为开启失败
            throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }
    }
}
