package com.nd.adhoc.dmsdk.api.provider.knox.license;

import android.annotation.SuppressLint;
import android.app.admin.DevicePolicyManager;
import android.app.enterprise.license.EnterpriseLicenseManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.nd.adhoc.dmsdk.api.BaseManager;
import com.nd.adhoc.dmsdk.api.manager.license.ILicenseManager_Active;
import com.nd.adhoc.dmsdk.revicer.ReciverConstants;
import com.sec.enterprise.knox.license.KnoxEnterpriseLicenseManager;

/**
 * License 激活 --Knox 入口激活程序 该入口程序不能被
 */
public class LisenceManagerImpl_ActiveI extends BaseManager implements ILicenseManager_Active {


    private String TAG=getClass().getSimpleName();

    public LisenceManagerImpl_ActiveI(DevicePolicyManager devicePolicyManager, ComponentName componentName) {

        super(devicePolicyManager, componentName);
    }

    public LisenceManagerImpl_ActiveI(DevicePolicyManager devicePolicyManager) {

        super(devicePolicyManager);
    }

    @Override
    public void active(Context context) {

        if(getDevicePolicyManager()==null ){
            //抛出异常
            return;
        }

        if(getComponentName()==null){
            //抛出异常
            return;
        }

        if (!getDevicePolicyManager().isAdminActive(getComponentName())) {
            //激活
            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, getComponentName());
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Adding app as an admin to test Knox");
            context.startActivity(intent);
        }else{
            Intent intent=new Intent(ReciverConstants.LICENSE_STATUS_SUCCESS);
            LocalBroadcastManager.getInstance(context).sendBroadcastSync(intent);
        }
    }

    @Override
    public void release(Context context) {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(receiver);
    }


    private  BroadcastReceiver receiver=new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            if(intent.getAction().equalsIgnoreCase(ReciverConstants.DEVICE_MANAGER_ACTIVE_ACTION)){
                activateKLM(context);
            }else if(intent.getAction().equalsIgnoreCase(ReciverConstants.KNOX_LICENSE_ACTIVE_ACTION)){
                activateELM(context);
            }
        }


        // call ELM license activation
        @SuppressLint("LongLogTag")
        private void activateELM(Context context) {
            try {
                EnterpriseLicenseManager elmManager = EnterpriseLicenseManager.getInstance(context);
                elmManager.activateLicense(ReciverConstants.ELM_LICENSE_KEY, context.getPackageName());
                Log.i(TAG, "activateELM");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        @SuppressLint("LongLogTag")
        private void activateKLM(Context context) {
            try {
                KnoxEnterpriseLicenseManager kepManager = KnoxEnterpriseLicenseManager.getInstance(context);
                kepManager.activateLicense(ReciverConstants.KEL_LICENSE_KEY, context.getPackageName());
                Log.i(TAG, "activateKLM");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}
