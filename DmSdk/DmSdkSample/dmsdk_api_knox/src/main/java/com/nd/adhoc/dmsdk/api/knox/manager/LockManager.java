package com.nd.adhoc.dmsdk.api.knox.manager;
import android.app.enterprise.RestrictionPolicy;
import android.app.enterprise.kioskmode.KioskMode;
import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;

import com.nd.adhoc.dmsdk.api.IDeviceLock;

import java.util.ArrayList;
import java.util.List;

class LockManager extends BaseManager implements IDeviceLock {

//    private DeviceInventory deviceInventoryPolicy ;

    private List availableHwKeys;

    private RestrictionPolicy restrictionPolicy;

    private KioskMode kioskMode;

    public void setContext(Context context) {

        super.setContext(context);
        Log.i(this.getClass().getName(), "deviceManager:=" + deviceManager);
//        deviceInventoryPolicy=deviceManager.getDeviceInventory();
        restrictionPolicy=deviceManager.getRestrictionPolicy();
        kioskMode=KioskMode.getInstance(context);
        getKeyList();
    }


    @Override
    public boolean lock() {
        kioskMode.allowHardwareKeys(availableHwKeys,true);
        return restrictionPolicy.allowActivationLock(true);
    }

    @Override
    public boolean unlock() {
        kioskMode.allowHardwareKeys(availableHwKeys,false);
        return restrictionPolicy.allowActivationLock(false);
    }

    /**
     *
     * @return
     */
    public boolean isLock() {
        return restrictionPolicy.isActivationLockAllowed(true);
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
