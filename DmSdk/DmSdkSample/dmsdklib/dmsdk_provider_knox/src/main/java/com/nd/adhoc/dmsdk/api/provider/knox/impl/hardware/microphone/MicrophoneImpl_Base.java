package com.nd.adhoc.dmsdk.api.provider.knox.impl.hardware.microphone;

import android.app.enterprise.RestrictionPolicy;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.api.hardware.IHardwareOperation_Swith;
import com.nd.adhoc.dmsdk.api.provider.knox.utils.Verification;

public class MicrophoneImpl_Base implements IHardwareOperation_Swith {
    @Override
    public boolean derall(@NonNull Context context, boolean isOpen) {
        RestrictionPolicy restrictionPolicy = Verification.isRestrictionPolicyNull(context);
        try {
            return restrictionPolicy.setMicrophoneState(isOpen);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return false;
    }
}
