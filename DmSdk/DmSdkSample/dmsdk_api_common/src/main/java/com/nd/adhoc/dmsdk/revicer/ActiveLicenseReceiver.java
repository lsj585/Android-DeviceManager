package com.nd.adhoc.dmsdk.revicer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

public class ActiveLicenseReceiver extends BroadcastReceiver {

    final String EDM_ACTION_LICENSE_STATUS = "edm.intent.action.knox_license.status";

    final String EDM_EXTRA_LICENSE_ERROR_CODE = "edm.intent.extra.knox_license.errorcode";

    final String KLM_ACTION_LICENSE_STATUS = "edm.intent.action.license.status";

    final String KLM_EXTRA_LICENSE_ERROR_CODE = "edm.intent.extra.license.errorcode";

    final int ERROR_NONE = 0;

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
        }
        String action = intent.getAction();
        if (action == null) {
            return;
        }

        if (action.equalsIgnoreCase(EDM_ACTION_LICENSE_STATUS)) {

            int errorCode = intent.getIntExtra(EDM_EXTRA_LICENSE_ERROR_CODE, -1);
            if (errorCode == ERROR_NONE) {
                // ELM activated successfully
                onSuccess(context.getApplicationContext(), Constants.KNOX_LICENSE_ACTIVE_ACTION);
                return;
            }
            onFailure(context.getApplicationContext());
        }


        if (action.equalsIgnoreCase(KLM_ACTION_LICENSE_STATUS)) {
            int errorCode = intent.getIntExtra(KLM_EXTRA_LICENSE_ERROR_CODE, -1);
            if (errorCode == ERROR_NONE) {
                // ELM activated successfully
                onSuccess(context.getApplicationContext(), Constants.LICENSE_STATUS_SUCCESS);
                return;
            }
            onFailure(context.getApplicationContext());
        }
    }

    /**
     * 跳转页面；至需要跳转的页面
     *
     * @param context
     */
    private void onSuccess(Context context, String action) {
        Intent intent = new Intent();
        intent.setAction(action);
        LocalBroadcastManager.getInstance(context).sendBroadcastSync(intent);
    }

    private void onFailure(Context context) {
        Intent intent = new Intent();
        intent.setAction(Constants.LICENSE_STATUS_FAILURE);
        LocalBroadcastManager.getInstance(context).sendBroadcastSync(intent);
    }
}
