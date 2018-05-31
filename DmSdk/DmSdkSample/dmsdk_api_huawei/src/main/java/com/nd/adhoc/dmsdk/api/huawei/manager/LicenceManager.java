package com.nd.adhoc.dmsdk.api.huawei.manager;

import android.annotation.SuppressLint;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.UserManager;
import android.support.v4.content.LocalBroadcastManager;
import com.nd.adhoc.dmsdk.api.ILicenceManager;
import com.nd.adhoc.dmsdk.revicer.Constants;

class LicenceManager extends BaseManager implements ILicenceManager {

    private final String TAG="activateKLM";

    private UserManager mUserManager;

    private DevicePolicyManager devicePolicyManager;

    @Override
    public void setContext(Context context) {
        super.setContext(context);
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(Constants.DEVICE_MANAGER_ACTIVE_ACTION);
        intentFilter.addAction(Constants.KNOX_LICENSE_ACTIVE_ACTION);
        LocalBroadcastManager.getInstance(context).registerReceiver(receiver,intentFilter);
    }

    @Override
    public void activieLicnece() {

        if(devicePolicyManager==null) {
            devicePolicyManager = (DevicePolicyManager)context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        }
        if (!devicePolicyManager.isAdminActive(getComponentName())) {
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

    @Override
    public void release() {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(receiver);
        receiver=null;
    }


    // call ELM license activation
    @SuppressLint("LongLogTag")
    private void activateELM() {

    }


    @SuppressLint("LongLogTag")
    private void activateKLM() {

    }

    private  BroadcastReceiver receiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if(intent.getAction().equalsIgnoreCase(Constants.DEVICE_MANAGER_ACTIVE_ACTION)){
                activateKLM();
            }else if(intent.getAction().equalsIgnoreCase(Constants.KNOX_LICENSE_ACTIVE_ACTION)){
                activateELM();
            }
        }
    };
}
