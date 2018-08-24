package com.nd.adhoc.dmsdk.api.provider.huawei.impl.license;

import android.annotation.SuppressLint;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.license.ILicense_Active;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.filed.DmSdkConstants;
import com.nd.sdp.android.serviceloader.annotation.Service;

/**
 * License 激活 --Knox 入口激活程序 该入口程序不能被
 */
@Service(IDmSdkApi.class)
@ApiImpl(ILicense_Active.class)
public class LisenceImpl_Active implements ILicense_Active {


    private final static String TAG=LisenceImpl_Active.class.getSimpleName();

    private final static String ELM_LICENSE_KEY="ELM_LICENSE_KEY";

    private final static String KEL_LICENSE_KEY="KEL_LICENSE_KEY";

    @Override
    public void active(@NonNull Context context) throws DeviceManagerSecurityException {

        verifyMember(context);
        activeLicnese(context);
    }

    @Override
    public void release(@NonNull Context context) {

    }

    /**
     * 校验成员变量是否为NULL；
     * @param context
     */
    private void verifyMember(@NonNull  Context context) throws DeviceManagerSecurityException {
        DeviceManagerContainer container=DeviceManagerContainer.getInstance();
        ComponentName componentName=container.getComponentName();
        if(componentName==null){
            //抛出异常
            throw new DeviceManagerSecurityException();
        }
    }

    /**
     * 激活License
     *
     * @param context
     */
    private void activeLicnese(@NonNull Context context){
        DeviceManagerContainer container=DeviceManagerContainer.getInstance();
        DevicePolicyManager manager= container.getDevicePolicyManager();
        ComponentName componentName=container.getComponentName();

        if (!manager.isAdminActive(componentName)) {
            //激活
            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Adding app as an admin to test Knox");

            if (context.getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
                context.startActivity(intent);
            }
        }else{
            Intent intent=new Intent(DmSdkConstants.LICENSE_STATUS_SUCCESS);
            context.sendBroadcast(intent);
        }
    }
}
