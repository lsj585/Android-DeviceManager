package com.nd.adhoc.dmsdk.api.provider.knox.hardware;
import android.app.enterprise.RestrictionPolicy;
import android.app.enterprise.kioskmode.KioskMode;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.exception.ErrorCode;
import com.nd.adhoc.dmsdk.api.manager.hardware.IDeviceLockManager;
import com.nd.adhoc.dmsdk.api.provider.knox.KnoxDeviceManagerFactory;

import java.util.ArrayList;
import java.util.List;

public class DeviceLockManagerImpl  implements IDeviceLockManager {

    private List availableHwKeys;

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
        RestrictionPolicy restrictionPolicy= verifyIsNull(context);
        return restrictionPolicy.isActivationLockAllowed(true);
    }

    @Override
    public void release(@NonNull Context context) {
        availableHwKeys.clear();
        availableHwKeys=null;
    }


    private void turnOff(@NonNull Context context,boolean isOpen) throws DeviceManagerSecurityException {
        RestrictionPolicy restrictionPolicy= verifyIsNull(context);
        KioskMode kioskMode=KioskMode.getInstance(context);
        try {
            kioskMode.allowHardwareKeys(availableHwKeys,isOpen);
            boolean isSuccess=restrictionPolicy.allowActivationLock(isOpen);
            if(!isSuccess){
                //TODO zyb 此处需要定义ErrorCode中的枚举值为开启失败
                throw  new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
            }
        }catch (SecurityException e){
            //TODO zyb 此处需要定义ErrorCode中的枚举值为开启失败
            throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }


    }

    /**
     * 校验从工厂中获取到数值是否为空
     * @return
     * @throws DeviceManagerSecurityException
     */
    private RestrictionPolicy verifyIsNull(@NonNull Context context) throws DeviceManagerSecurityException {

        if(context==null){
            throw  new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }

        RestrictionPolicy restrictionPolicy= KnoxDeviceManagerFactory.getInstance().getRestrictionPolicy(context);
        if(restrictionPolicy==null){
            throw  new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }
        return restrictionPolicy;
    }

    /**
     * 设备锁定时，不允许按键响应
     */
    public void getKeyList(){
        if(availableHwKeys==null) {
            availableHwKeys= new ArrayList();
            availableHwKeys.add(new Integer(KeyEvent.KEYCODE_BACK));
            availableHwKeys.add(new Integer(KeyEvent.KEYCODE_VOLUME_DOWN));
            availableHwKeys.add(new Integer(KeyEvent.KEYCODE_VOLUME_UP));
            availableHwKeys.add(new Integer(KeyEvent.KEYCODE_HOME));
            availableHwKeys.add(new Integer(KeyEvent.KEYCODE_POWER));
        }
    }
}
