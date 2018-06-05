package com.nd.adhoc.dmsdk.revicer;
import android.app.enterprise.license.EnterpriseLicenseManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

import com.sec.enterprise.knox.license.KnoxEnterpriseLicenseManager;

public class ActiveLicenseReceiver extends BroadcastReceiver {

    private void showToast(Context context, int msg_res) {
        Toast.makeText(context.getApplicationContext(), context.getResources().getString(msg_res), Toast.LENGTH_SHORT).show();
    }

    private void showToast(Context context, String msg) {
        Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        } else {
            String action = intent.getAction();
            if (action == null) {
                return;
            }else if(action.equalsIgnoreCase(KnoxEnterpriseLicenseManager.ACTION_LICENSE_STATUS)){

                int errorCode = intent.getIntExtra(KnoxEnterpriseLicenseManager.EXTRA_LICENSE_ERROR_CODE, -1);
                if (errorCode == KnoxEnterpriseLicenseManager.ERROR_NONE) {
                    // ELM activated successfully
                    onSuccess(context.getApplicationContext(), Constants.KNOX_LICENSE_ACTIVE_ACTION);
                    return;
                } else {
                    onFailure(context.getApplicationContext());
                }
            }else if(action.equalsIgnoreCase(EnterpriseLicenseManager.ACTION_LICENSE_STATUS)){
                int errorCode = intent.getIntExtra(EnterpriseLicenseManager.EXTRA_LICENSE_ERROR_CODE, -1);
                if (errorCode == EnterpriseLicenseManager.ERROR_NONE) {
                    // ELM activated successfully
                    onSuccess(context.getApplicationContext(), Constants.LICENSE_STATUS_SUCCESS);
                    return;
                } else {
                    onFailure(context.getApplicationContext());
                }
            }
        }
    }

    /**
     * 跳转页面；至需要跳转的页面
     * @param context
     */
    private void onSuccess(Context context,String action){
        Intent intent=new Intent();
        intent.setAction(action);
        LocalBroadcastManager.getInstance(context).sendBroadcastSync(intent);
    }

    private void onFailure(Context context){
        Intent intent=new Intent();
        intent.setAction(Constants.LICENSE_STATUS_FAILURE);
        LocalBroadcastManager.getInstance(context).sendBroadcastSync(intent);
    }
}
