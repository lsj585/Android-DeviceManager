package com.nd.adhoc.dmsdk.api.provider.knox.hardware;
import android.app.enterprise.RestrictionPolicy;
import android.app.enterprise.kioskmode.KioskMode;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.exception.ErrorCode;
import com.nd.adhoc.dmsdk.api.manager.hardware.IDeviceLockManager;
import com.nd.adhoc.dmsdk.api.provider.utils.Verification;
import com.nd.sdp.android.serviceloader.annotation.Service;

import java.util.ArrayList;
import java.util.List;

@Service(IDeviceLockManager.class)
public class DeviceLockManagerImpl  implements IDeviceLockManager {

    private List availableHwKeys;

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
        RestrictionPolicy restrictionPolicy= Verification.isRestrictionPolicyNull(context);
        return restrictionPolicy.isActivationLockAllowed(true);
    }

    @Override
    public void release(@NonNull Context context) {
        availableHwKeys.clear();
        availableHwKeys=null;
    }


    private void turnOff(@NonNull Context context,boolean isOpen) throws DeviceManagerSecurityException {
        RestrictionPolicy restrictionPolicy= Verification.isRestrictionPolicyNull(context);
        KioskMode kioskMode=KioskMode.getInstance(context);
        getKeyList();
        try {
            kioskMode.allowHardwareKeys(availableHwKeys,isOpen);
            boolean isSuccess=restrictionPolicy.allowActivationLock(isOpen);
            if(!isSuccess){
                //TODO zyb 此处需要定义ErrorCode中的枚举值为开启失败
                throw  new DeviceManagerSecurityException(ErrorCode.DEFAULT_OPERATION_ERROR);
            }
        }catch (SecurityException e){
            //TODO zyb 此处需要定义ErrorCode中的枚举值为开启失败
            throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }


    }
    /**
     * 设备锁定时，不允许按键响应
     */
    public List<Integer> getKeyList(){
        if(availableHwKeys==null) {
            availableHwKeys= new ArrayList();
            availableHwKeys.add(new Integer(KeyEvent.KEYCODE_BACK));
            availableHwKeys.add(new Integer(KeyEvent.KEYCODE_VOLUME_DOWN));
            availableHwKeys.add(new Integer(KeyEvent.KEYCODE_VOLUME_UP));
            availableHwKeys.add(new Integer(KeyEvent.KEYCODE_HOME));
            availableHwKeys.add(new Integer(KeyEvent.KEYCODE_POWER));
        }
        return availableHwKeys;
    }
}
