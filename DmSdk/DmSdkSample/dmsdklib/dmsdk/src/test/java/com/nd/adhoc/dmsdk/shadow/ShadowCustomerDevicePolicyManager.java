package com.nd.adhoc.dmsdk.shadow;
import android.content.ComponentName;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.shadows.ShadowDevicePolicyManager;

@Implements(ShadowDevicePolicyManager.class)
public class ShadowCustomerDevicePolicyManager {
    @Implementation
    public void enforceActiveAdmin(ComponentName admin) {
//        System.out.println("enforceActiveAdmin");
    }
}
