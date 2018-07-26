package com.nd.adhoc.dmsdk.shadow;

import android.app.enterprise.RestrictionPolicy;

import org.robolectric.annotation.Implements;

//@Implements(RestrictionPolicy.class)
public class ShadowRestrictionPolicy{

    void __constructor__() {

        System.out.println("Hello RestrictionPolicy");
    }
}
