package com.nd.adhoc.dmsdk.test.hardware;

import android.app.Activity;
import android.content.Context;
import android.os.Build;

import com.adhoc.dmsdk.sdk.DeviceManagerSdk;
import com.nd.adhoc.dmsdk.BuildConfig;
import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.exception.DeviceManagerUnsupportException;
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
        shadows = {ShowEnterpriseDeviceManager.class})

public class CameraManagerTest {

    ICamera cameraManager;

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
            cameraManager= (ICamera) DeviceManagerSdk.getInstance().getManager(DeviceManagerContainer.MANAGER_HARDWARE_CAMERA);
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
        Assert.assertNotNull(cameraManager);
        Context context= RuntimeEnvironment.application.getApplicationContext();
        Assert.assertNotNull(context);
        try {
            cameraManager.open(context);
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(context);
    }
    @Test
    public void testIsOpen(){
        testResgisterLicense();
        //开启失败
        Assert.assertNotNull(cameraManager);
        Context context= RuntimeEnvironment.application.getApplicationContext();
        Assert.assertNotNull(context);
        try {
          boolean isSuccess= cameraManager.isOpen(null);
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
        Assert.assertNotNull(cameraManager);
        Context context= RuntimeEnvironment.application.getApplicationContext();
        Assert.assertNotNull(context);
        try {
            cameraManager.close(context);
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(context);
    }
}
