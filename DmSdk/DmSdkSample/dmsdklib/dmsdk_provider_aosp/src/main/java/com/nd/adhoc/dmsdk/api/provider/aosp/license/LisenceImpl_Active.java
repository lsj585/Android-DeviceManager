package com.nd.adhoc.dmsdk.api.provider.aosp.license;
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
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.license.ILicense_Active;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.exception.ErrorCode;
import com.nd.adhoc.dmsdk.filed.DmSdkConstants;
import com.nd.adhoc.dmsdk.filed.DmSdkGlobal;
import com.nd.sdp.android.serviceloader.annotation.Service;

/**
 * License 激活 --Knox 入口激活程序 该入口程序不能被
 */
@Service(IDmSdkApi.class)
@ApiImpl(ILicense_Active.class)
public class LisenceImpl_Active implements ILicense_Active {


    private String TAG=getClass().getSimpleName();

    private final String ELM_LICENSE_KEY="ELM_LICENSE_KEY";

    private final String KEL_LICENSE_KEY="KEL_LICENSE_KEY";

    @Override
    public void active(@NonNull Context context) throws DeviceManagerSecurityException {

        verifyMember(context);
        registerLicnese(context);
        activeLicnese(context);
        IntentFilter filter=new IntentFilter();
        filter.addAction(DmSdkConstants.DEVICE_MANAGER_ACTIVE_ACTION);
        filter.addAction(DmSdkConstants.KNOX_LICENSE_ACTIVE_ACTION);
        LocalBroadcastManager.getInstance(context).registerReceiver(receiver,filter);
    }

    @Override
    public void release(@NonNull Context context) {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(receiver);
    }


    private  BroadcastReceiver receiver=new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equalsIgnoreCase(DmSdkConstants.DEVICE_MANAGER_ACTIVE_ACTION)) {

                Intent its=new Intent();
                its.setAction(DmSdkConstants.LICENSE_STATUS_SUCCESS);
                LocalBroadcastManager.getInstance(context).sendBroadcastSync(its);
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
                DmSdkGlobal.ELM_LICENSE_KEY=applicationInfo.metaData.getString(ELM_LICENSE_KEY);
            }else{
                throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_LICENSE_FAILURE);
            }

            if(!TextUtils.isEmpty(metaData.getString(KEL_LICENSE_KEY))){
                DmSdkGlobal.KEL_LICENSE_KEY=applicationInfo.metaData.getString(KEL_LICENSE_KEY);
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
        DeviceManagerContainer container=DeviceManagerContainer.getInstance();
        DevicePolicyManager manager= container.getDevicePolicyManager();
        ComponentName componentName=container.getComponentName();
        if(manager==null ){
            //抛出异常
            throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }

        if(componentName==null){
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
        DeviceManagerContainer container=DeviceManagerContainer.getInstance();
        DevicePolicyManager manager= container.getDevicePolicyManager();
        ComponentName componentName=container.getComponentName();


        if (!manager.isAdminActive(componentName)) {
            //激活
            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Adding app as an admin to test Knox");
//            intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }else{
            Intent intent=new Intent(DmSdkConstants.LICENSE_STATUS_SUCCESS);
            LocalBroadcastManager.getInstance(context).sendBroadcastSync(intent);
        }
    }
}
