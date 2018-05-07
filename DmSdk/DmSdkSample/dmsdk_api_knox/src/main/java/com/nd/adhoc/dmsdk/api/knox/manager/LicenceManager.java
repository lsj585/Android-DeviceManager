package com.nd.adhoc.dmsdk.api.knox.manager;

import android.annotation.SuppressLint;
import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.UserHandle;
import android.util.Log;

import com.nd.adhoc.dmsdk.api.ILicenceManager;
import com.nd.adhoc.dmsdk.api.knox.IKnoxDmApiManager;
import com.samsung.android.knox.license.EnterpriseLicenseManager;
import com.samsung.android.knox.license.KnoxEnterpriseLicenseManager;

class LicenceManager extends BaseManager implements ILicenceManager {

    private final String TAG="activateKLM";

    private final String KEL_LICENSE_KEY = "KLM06-2SWKZ-NSL3A-93911-SSMAW-OCUCU";
    private final String ELM_LICENSE_KEY = "2597D84D978FDADB00DD43BC30ACA35FE9396F88B671F069F72D50705367D711AAEC116B0485165465DC6D80087C727FFD8F3476269A6652A91E9A71792D2EB7";


    private IntentFilter mIntentFilter;

    private DevicePolicyManager devicePolicyManager;
    @Override
    public void activieLicnece() {

        if(devicePolicyManager==null) {
            devicePolicyManager = (DevicePolicyManager) getContext().getSystemService(Context.DEVICE_POLICY_SERVICE);
        }
        ComponentName mDeviceAdmin = new ComponentName(getContext(), com.nd.adhoc.dmsdk.revicer.AdminReciver.class);

//        if (!devicePolicyManager.isAdminActive(mDeviceAdmin)) {
            //激活
            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mDeviceAdmin);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Adding app as an admin to test Knox");
            // Start the add device admin activity
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
            getContext().startActivity(intent);
//        }
        activateKLM();
        activateELM();
    }

    @Override
    public void release() {

    }


    // call ELM license activation
    @SuppressLint("LongLogTag")
    private void activateELM() {
        try {
            EnterpriseLicenseManager elmManager = EnterpriseLicenseManager.getInstance(context);
            elmManager.activateLicense(ELM_LICENSE_KEY, context.getPackageName());
            Log.i(TAG, "activateELM");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @SuppressLint("LongLogTag")
    private void activateKLM() {

        try {
            KnoxEnterpriseLicenseManager elmManager = KnoxEnterpriseLicenseManager.getInstance(context);
            elmManager.activateLicense(KEL_LICENSE_KEY, context.getPackageName());
            Log.i(TAG, "activateKLM");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
