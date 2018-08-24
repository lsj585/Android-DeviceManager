package com.nd.adhoc.dmsdk.api.provider.knox.impl.hardware.devicelock;

import android.app.enterprise.RestrictionPolicy;
import android.app.enterprise.kioskmode.KioskMode;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import com.nd.adhoc.dmsdk.api.hardware.IHardwareOperation_Swith;
import com.nd.adhoc.dmsdk.api.provider.knox.utils.Verification;

import java.util.ArrayList;
import java.util.List;

/**
 * @author richsjeson  设备按键解锁和加锁
 */

public class DeviceLockImpl_Base implements IHardwareOperation_Swith {


    protected List<Integer> mAvailableHwKeys;

    public DeviceLockImpl_Base(){
        mAvailableHwKeys= new ArrayList();
        mAvailableHwKeys.add(new Integer(KeyEvent.KEYCODE_BACK));
        mAvailableHwKeys.add(new Integer(KeyEvent.KEYCODE_VOLUME_DOWN));
        mAvailableHwKeys.add(new Integer(KeyEvent.KEYCODE_VOLUME_UP));
        mAvailableHwKeys.add(new Integer(KeyEvent.KEYCODE_HOME));
        mAvailableHwKeys.add(new Integer(KeyEvent.KEYCODE_POWER));
    }


    @Override
    public boolean derall(@NonNull Context context, boolean isOpen) {
        RestrictionPolicy restrictionPolicy= Verification.isRestrictionPolicyNull(context);
        KioskMode kioskMode=KioskMode.getInstance(context);
        kioskMode.enableKioskMode(context.getPackageName());
        try {
            kioskMode.allowHardwareKeys(mAvailableHwKeys,isOpen);
            return restrictionPolicy.allowActivationLock(isOpen);
        }catch (SecurityException e){
            e.printStackTrace();
        }
        return false;
    }

    public void frees(@NonNull Context context) {
        mAvailableHwKeys.clear();
        mAvailableHwKeys=null;
    }
}
