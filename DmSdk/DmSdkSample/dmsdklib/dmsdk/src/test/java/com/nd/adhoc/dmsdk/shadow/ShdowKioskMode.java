package com.nd.adhoc.dmsdk.shadow;

import android.app.enterprise.kioskmode.KioskMode;
import android.content.Context;

import org.mockito.Mockito;
import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;

@Implements(KioskMode.class)

public class ShdowKioskMode {

    @Implementation
    public static KioskMode getInstance(Context context) {
        return Mockito.mock(KioskMode.class);
    }
}
