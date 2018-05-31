package com.nd.adhoc.dmsdk.api.provider.knox.license;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.admin.DevicePolicyManager;
import android.app.enterprise.license.EnterpriseLicenseManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;

import com.nd.adhoc.dmsdk.api.BaseManager;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.exception.ErrorCode;
import com.nd.adhoc.dmsdk.api.manager.license.ILicenseManager_Active;
import com.nd.adhoc.dmsdk.revicer.Constants;
import com.sec.enterprise.knox.license.KnoxEnterpriseLicenseManager;

/**
 * License 激活 --Knox 入口激活程序 该入口程序不能被
 */
class LisenceManagerImpl_Active extends BaseManager implements ILicenseManager_Active {


    private String TAG=getClass().getSimpleName();

    private final String ELM_LICENSE_KEY="ELM_LICENSE_KEY";

    private final String KEL_LICENSE_KEY="KEL_LICENSE_KEY";

    public LisenceManagerImpl_Active(DevicePolicyManager devicePolicyManager, ComponentName componentName) {

        super(devicePolicyManager, componentName);
    }

    public LisenceManagerImpl_Active(DevicePolicyManager devicePolicyManager) {

        super(devicePolicyManager);
    }

    @Override
    public void active(@NonNull Context context) throws DeviceManagerSecurityException {

        verifyMember(context);
        registerLicnese(context);
        activeLicnese(context);
    }

    @Override
    public void release(@NonNull Context context) {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(receiver);
    }


    private  BroadcastReceiver receiver=new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            if(intent.getAction().equalsIgnoreCase(Constants.DEVICE_MANAGER_ACTIVE_ACTION)){
                activateKLM(context);
            }else if(intent.getAction().equalsIgnoreCase(Constants.KNOX_LICENSE_ACTIVE_ACTION)){
                activateELM(context);
            }
        }


        // call ELM license activation
        @SuppressLint("LongLogTag")
        private void activateELM(Context context) {
            try {
                EnterpriseLicenseManager elmManager = EnterpriseLicenseManager.getInstance(context);
                elmManager.activateLicense(Constants.ELM_LICENSE_KEY, context.getPackageName());
                Log.i(TAG, "activateELM");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        @SuppressLint("LongLogTag")
        private void activateKLM(Context context) {
            try {
                KnoxEnterpriseLicenseManager kepManager = KnoxEnterpriseLicenseManager.getInstance(context);
                kepManager.activateLicense(Constants.KEL_LICENSE_KEY, context.getPackageName());
                Log.i(TAG, "activateKLM");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * 激活Mainifest.xml中配置的ELM_LICENSE_KEY和KEL_LICENSE_KEY
     * @param context
     */
    private void registerLicnese(@NonNull  Context context) throws DeviceManagerSecurityException {

        String packageName=context.getPackageName();
        PackageManager packageManager=context.getPackageManager();
        try {
            ApplicationInfo applicationInfo=packageManager.getApplicationInfo(packageName,PackageManager.GET_META_DATA);
            Bundle metaData=applicationInfo.metaData;
            if(metaData==null){
                //抛出异常
                throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_LICENSE_FAILURE);
            }
            //验证ELM_LICENSE_KEY 是否配置在Mainifest.xml中
            if(!TextUtils.isEmpty(metaData.getString(ELM_LICENSE_KEY))){
                Constants.ELM_LICENSE_KEY=applicationInfo.metaData.getString(ELM_LICENSE_KEY);
            }else{
                throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_LICENSE_FAILURE);
            }

            if(!TextUtils.isEmpty(metaData.getString(KEL_LICENSE_KEY))){
                Constants.KEL_LICENSE_KEY=applicationInfo.metaData.getString(KEL_LICENSE_KEY);
            }else{
                throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_LICENSE_FAILURE);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 校验成员变量是否为NULL；
     * @param context
     */
    private void verifyMember(@NonNull  Context context) throws DeviceManagerSecurityException {
        if(context==null){
            //抛出异常
            throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_LICENSE_FAILURE);
        }

        if(getDevicePolicyManager()==null ){
            //抛出异常
            throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }

        if(getComponentName()==null){
            //抛出异常
            throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }
    }

    /**
     * 激活License
     *
     * @param context
     */
    private void activeLicnese(@NonNull Context context){

        if (!getDevicePolicyManager().isAdminActive(getComponentName())) {
            //激活
            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, getComponentName());
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Adding app as an admin to test Knox");
            context.startActivity(intent);
        }else{
            Intent intent=new Intent(Constants.LICENSE_STATUS_SUCCESS);
            LocalBroadcastManager.getInstance(context).sendBroadcastSync(intent);
        }
    }
}
