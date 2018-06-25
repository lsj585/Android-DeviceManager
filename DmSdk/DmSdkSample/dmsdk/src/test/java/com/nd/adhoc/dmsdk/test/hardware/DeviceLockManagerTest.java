package com.nd.adhoc.dmsdk.test.hardware;

import android.app.Activity;
import android.content.Context;
import android.os.Build;

import com.adhoc.dmsdk.sdk.DeviceManagerSdk;
import com.nd.adhoc.dmsdk.BuildConfig;
import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerUnsupportException;
import com.nd.adhoc.dmsdk.api.manager.hardware.IDeviceLockManager;
import com.nd.adhoc.dmsdk.shadow.ShdowKioskMode;
import com.nd.adhoc.dmsdk.shadow.ShowEnterpriseDeviceManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.M,Build.VERSION_CODES.LOLLIPOP},
        constants = BuildConfig.class,
        shadows = {ShowEnterpriseDeviceManager.class, ShdowKioskMode.class})

public class DeviceLockManagerTest {

    IDeviceLockManager deviceLockManager;

    Context context;

    @Before
    public void setup() {
        // runs before every test
        context= RuntimeEnvironment.application.getApplicationContext();
        Assert.assertNotNull(context);

    }
    @Test
    public void testRegister(){
        DeviceManagerSdk.getInstance().registerSDK(context);
        try {
            deviceLockManager= (IDeviceLockManager) DeviceManagerSdk.getInstance().getManager(DeviceManagerContainer.MANAGER_HARDWARE_LOCK);
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testResgisterLicense(){
        testRegister();
        Activity activity= Robolectric.setupActivity(Activity.class);
        Assert.assertNotNull(activity);
        DeviceManagerSdk.getInstance().registerLicense(activity);
    }

    @Test
    public void testOpen() {
        testResgisterLicense();
        Assert.assertNotNull(deviceLockManager);
        Context context= RuntimeEnvironment.application.getApplicationContext();
        Assert.assertNotNull(context);
        try {
            deviceLockManager.open(context);
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(context);
    }
    @Test
    public void testIsOpen(){
        testResgisterLicense();
        //开启失败
        Assert.assertNotNull(deviceLockManager);
        Context context= RuntimeEnvironment.application.getApplicationContext();
        Assert.assertNotNull(context);
        try {
          boolean isSuccess= deviceLockManager.isOpen(context);
          System.out.println(String.format("Afert operation is success:%b",isSuccess));
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(context);
    }
//    @Test(expected = DeviceManagerSecurityException.class)
    @Test
    public void tesClose(){
        testResgisterLicense();
        Assert.assertNotNull(deviceLockManager);
        Context context= RuntimeEnvironment.application.getApplicationContext();
        Assert.assertNotNull(context);
        try {
            deviceLockManager.close(context);
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(context);
    }
}
