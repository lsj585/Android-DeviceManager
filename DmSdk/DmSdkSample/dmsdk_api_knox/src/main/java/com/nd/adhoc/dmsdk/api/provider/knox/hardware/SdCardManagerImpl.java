package com.nd.adhoc.dmsdk.api.provider.knox.hardware;
import android.app.enterprise.RestrictionPolicy;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.support.annotation.NonNull;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.exception.ErrorCode;
import com.nd.adhoc.dmsdk.api.manager.hardware.ISdCardManager;
import com.nd.adhoc.dmsdk.api.provider.knox.KnoxDeviceManagerFactory;
import com.nd.adhoc.dmsdk.api.provider.utils.Verification;

public class SdCardManagerImpl implements ISdCardManager {

    @Override
    public boolean open(@NonNull Context context){
        try {
            turnOff(context,true);
        }catch (DeviceManagerSecurityException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean close(@NonNull Context context){
        try {
            turnOff(context,false);
        }catch (DeviceManagerSecurityException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean isOpen(@NonNull Context context) throws DeviceManagerSecurityException {
        RestrictionPolicy restrictionPolicy=Verification.isRestrictionPolicyNull(context);
        return restrictionPolicy.isSdCardEnabled();
    }

    @Override
    public void release(@NonNull Context context) {

    }

    /**
     * 开启/关闭
     * @param context
     * @param isOpen
     * @throws DeviceManagerSecurityException
     */
    private void turnOff(@NonNull Context context,boolean isOpen) throws DeviceManagerSecurityException {
        RestrictionPolicy restrictionPolicy= Verification.isRestrictionPolicyNull(context);
        try {
            boolean isSuccess = restrictionPolicy.setSdCardState(isOpen);
            if (!isSuccess) {
                //TODO zyb 此处需要定义ErrorCode中的枚举值为开启失败
                throw new DeviceManagerSecurityException(ErrorCode.DEFAULT_OPERATION_ERROR);
            }
        }catch (SecurityException e){
            //TODO zyb 此处需要定义ErrorCode中的枚举值为开启失败
            throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }
    }
}
