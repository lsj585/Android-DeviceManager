package com.nd.adhoc.dmsdk.test.hardware;

import android.app.Activity;
import android.content.Context;
import android.os.Build;

import com.adhoc.dmsdk.sdk.DeviceManagerSdk;
import com.nd.adhoc.dmsdk.BuildConfig;
import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerUnsupportException;
import com.nd.adhoc.dmsdk.api.manager.hardware.IBluetoothManager;
import com.nd.adhoc.dmsdk.shadow.ShadowCustomerDevicePolicyManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.M, Build.VERSION_CODES.LOLLIPOP},
        constants = BuildConfig.class,
        shadows = {com.nd.adhoc.dmsdk.shadow.ShowEnterpriseDeviceManager.class,
                ShadowCustomerDevicePolicyManager.class})

public class BloothManagerTest {

    IBluetoothManager bluetoothManager;

    Context context;

    @Before
    public void setup() {
        // runs before every test
        context = RuntimeEnvironment.application.getApplicationContext();
        Assert.assertNotNull(context);

    }

    @Test
    public void testRegister() {
        DeviceManagerSdk.getInstance().registerSDK(context);
        try {
            bluetoothManager = (IBluetoothManager) DeviceManagerSdk.getInstance().getManager(DeviceManagerContainer.MANAGER_HARDWARE_BLUETOOTH);
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
        }
    }

    //    @Test(expected = DeviceManagerSecurityException.class)
    @Test
    public void testResgisterLicense() {
        testRegister();
        Activity activity = Robolectric.setupActivity(Activity.class);
        Assert.assertNotNull(activity);
        DeviceManagerSdk.getInstance().registerLicense(activity);
    }

    //    @Test(expected = DeviceManagerSecurityException.class)
    @Test
    public void testOpen() {
        testResgisterLicense();
        Assert.assertNotNull(bluetoothManager);
        Context context = RuntimeEnvironment.application.getApplicationContext();
        Assert.assertNotNull(context);
        try {
            bluetoothManager.open(context);
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(context);
    }

    //    @Test(expected = DeviceManagerSecurityException.class)
    @Test
    public void testIsOpen() {
        //开启失败
        testResgisterLicense();
        Assert.assertNotNull(bluetoothManager);
        Context context = RuntimeEnvironment.application.getApplicationContext();
        Assert.assertNotNull(context);
        try {
            boolean isSuccess = bluetoothManager.isOpen(null);
            System.out.println(String.format("Afert operation is success:%b", isSuccess));
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(context);
    }

    //    @Test(expected = DeviceManagerSecurityException.class)
    @Test
    public void tesClose() {
        testResgisterLicense();
        Assert.assertNotNull(bluetoothManager);
        Context context = RuntimeEnvironment.application.getApplicationContext();
        Assert.assertNotNull(context);
        try {
            bluetoothManager.close(context);
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
        }

        try {
            bluetoothManager.close(null);
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
        }
    }
}
