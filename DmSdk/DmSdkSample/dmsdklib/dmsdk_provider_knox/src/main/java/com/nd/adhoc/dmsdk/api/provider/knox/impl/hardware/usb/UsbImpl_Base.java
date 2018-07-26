package com.nd.adhoc.dmsdk.api.provider.knox.impl.hardware.usb;

import android.app.enterprise.RestrictionPolicy;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.api.hardware.IHardwareOperation_Swith;
import com.nd.adhoc.dmsdk.api.provider.knox.utils.Verification;


public class UsbImpl_Base implements IHardwareOperation_Swith{


    @Override
    public boolean derall(@NonNull Context context, boolean isOpen) {

        RestrictionPolicy restrictionPolicy = Verification.isRestrictionPolicyNull(context);
        try {
            return restrictionPolicy.isUsbMassStorageEnabled(isOpen);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return false;
    }
}
