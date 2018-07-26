package com.nd.adhoc.dmsdk.test.app;

import android.app.Activity;
import android.content.Context;
import android.os.Build;

import com.adhoc.dmsdk.sdk.DeviceManagerSdk;
import com.nd.adhoc.dmsdk.BuildConfig;
import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.exception.DeviceManagerUnsupportException;
import com.nd.adhoc.dmsdk.api.pac.IPackage_Install;
import com.nd.adhoc.dmsdk.api.pac.IPackage_Uninstall;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.io.FileNotFoundException;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.M, Build.VERSION_CODES.LOLLIPOP},
        constants = BuildConfig.class,
        shadows = {com.nd.adhoc.dmsdk.shadow.ShowEnterpriseDeviceManager.class})
public class TestAppManager {

    Context context;

    String TEST_PACKAGE_NAME="com.baidu.com";


    @Before
    public void setup() {
        // runs before every test
        context = RuntimeEnvironment.application.getApplicationContext();
        Assert.assertNotNull(context);

    }
    @Test
    public void testRegister(){
        DeviceManagerSdk.getInstance().registerSDK(context);
    }
    @Test
    public void testResgisterLicense(){
        testRegister();
        Activity activity= Robolectric.setupActivity(Activity.class);
        Assert.assertNotNull(activity);
        DeviceManagerSdk.getInstance().registerLicense(activity);
    }

    /**
     * 卸载APP的单元测试
     */
    @Test
    public void testUnInstallApp(){
        testResgisterLicense();
        IPackage_Uninstall uninstall = null;
        try {
            uninstall = (IPackage_Uninstall) DeviceManagerSdk.getInstance().getManager(DeviceManagerContainer.MANAGER_PACKAGE_UNINSTALL);
           if(uninstall!=null) {
               uninstall.uninstall(context, TEST_PACKAGE_NAME);
           }
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInstallApp(){

        testResgisterLicense();
        IPackage_Install uninstall = null;
        try {
            uninstall = (IPackage_Install) DeviceManagerSdk.getInstance().getManager(DeviceManagerContainer.MANAGER_PACKAGE_INSTALL);

            Mockito.when(uninstall).then(new Answer<Object>() {


                @Override
                public Object answer(InvocationOnMock invocation) throws Throwable {
                    return null;
                }
            });

            if(uninstall!=null) {
                uninstall.install(context, TEST_PACKAGE_NAME);
            }

            if(uninstall!=null) {
                uninstall.install(context, "");
            }


        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
