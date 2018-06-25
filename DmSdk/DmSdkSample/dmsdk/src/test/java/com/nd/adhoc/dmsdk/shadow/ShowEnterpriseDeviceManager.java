package com.nd.adhoc.dmsdk.shadow;

import android.app.enterprise.ApplicationPolicy;
import android.app.enterprise.EnterpriseDeviceManager;
import android.app.enterprise.RestrictionPolicy;
import android.content.Context;

import junit.framework.Assert;

import org.mockito.Mockito;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@Implements(EnterpriseDeviceManager.class)
public class ShowEnterpriseDeviceManager {


    public void __constructor__(Context context) {

        System.out.println("Hello ");
    }

    @Implementation
    public RestrictionPolicy getRestrictionPolicy() {
//        Class cls=RestrictionPolicy.class;
//        try {
//            Constructor constructor=cls.getConstructor();
//            constructor.setAccessible(false);
//            RestrictionPolicy policy= (RestrictionPolicy) constructor.newInstance();
//            return policy;
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        return  null;
        RestrictionPolicy policy=Mockito.mock(RestrictionPolicy.class);
        Assert.assertNotNull(policy);
        return Mockito.mock(RestrictionPolicy.class);
    }

    @Implementation
    public ApplicationPolicy getApplicationPolicy() {
        ApplicationPolicy policy=Mockito.mock(ApplicationPolicy.class);
        Assert.assertNotNull(policy);
        return Mockito.mock(ApplicationPolicy.class);
    }
}
