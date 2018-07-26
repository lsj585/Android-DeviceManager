package com.nd.adhoc.dmsdk.api.provider.knox.impl.hardware.bluetooth;

import android.app.enterprise.RestrictionPolicy;
import android.content.Context;
import android.support.annotation.NonNull;
import com.nd.adhoc.dmsdk.api.hardware.IHardwareOperation_Swith;
import com.nd.adhoc.dmsdk.api.provider.knox.utils.Verification;

/**
 * Created by richsjeson on 2018/7/19.
 */

public class BlutoothImpl_Base implements IHardwareOperation_Swith {

    @Override
    public boolean derall(@NonNull Context context, boolean isOpen){
        RestrictionPolicy restrictionPolicy = Verification.isRestrictionPolicyNull(context);
        try {
           return  restrictionPolicy.allowBluetooth(isOpen);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return false;
    }
}
