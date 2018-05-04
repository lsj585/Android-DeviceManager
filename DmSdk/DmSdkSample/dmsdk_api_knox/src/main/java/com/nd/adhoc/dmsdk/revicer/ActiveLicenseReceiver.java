package com.nd.adhoc.dmsdk.revicer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;
import com.samsung.android.knox.license.EnterpriseLicenseManager;

public class ActiveLicenseReceiver extends BroadcastReceiver {

    private void showToast(Context context, int msg_res) {
        Toast.makeText(context, context.getResources().getString(msg_res), Toast.LENGTH_SHORT).show();
    }

    private void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        } else {
            String action = intent.getAction();
            if (action == null) {
                return;
            }else if(action.equalsIgnoreCase("edm.intent.action.license.status")){
                onSuccess(context);
            }
//            else if (action.equals(EnterpriseLicenseManager.ACTION_LICENSE_STATUS)) {
//
//                // ELM activation result Intent is obtained
////                int errorCode = intent.getIntExtra(EnterpriseLicenseManager.EXTRA_LICENSE_ERROR_CODE, DEFAULT_ERROR_CODE);
////                int resultType = intent.getIntExtra(EnterpriseLicenseManager.EXTRA_LICENSE_RESULT_TYPE, DEFAULT_ERROR_CODE);
////
////                if (resultType == 800) {
////                    if (errorCode == EnterpriseLicenseManager.ERROR_NONE) {
////                        // ELM activated successfully
////                        Toast.makeText(context, R.string.elm_activated_succesfully, Toast.LENGTH_SHORT).show();
////                        Log.d("SampleLicenseReceiver", context.getString(R.string.elm_activated_succesfully));
////                        return;
////                    } else {
////                        // ELM activation failed
////                        switch (errorCode) {
////                            case EnterpriseLicenseManager.ERROR_INTERNAL:
////                                msg_res = R.string.err_elm_internal;
////                                break;
////                            case EnterpriseLicenseManager.ERROR_INTERNAL_SERVER:
////                                msg_res = R.string.err_elm_internal_server;
////                                break;
////                            case EnterpriseLicenseManager.ERROR_INVALID_LICENSE:
////                                msg_res = R.string.err_elm_licence_invalid_license;
////                                break;
////                            case EnterpriseLicenseManager.ERROR_INVALID_PACKAGE_NAME:
////                                msg_res = R.string.err_elm_invalid_package_name;
////                                break;
////                            case EnterpriseLicenseManager.ERROR_LICENSE_TERMINATED:
////                                msg_res = R.string.err_elm_licence_terminated;
////                                break;
////                            case EnterpriseLicenseManager.ERROR_NETWORK_DISCONNECTED:
////                                msg_res = R.string.err_elm_network_disconnected;
////                                break;
////                            case EnterpriseLicenseManager.ERROR_NETWORK_GENERAL:
////                                msg_res = R.string.err_elm_network_general;
////                                break;
////                            case EnterpriseLicenseManager.ERROR_NOT_CURRENT_DATE:
////                                msg_res = R.string.err_elm_not_current_date;
////                                break;
////                            case EnterpriseLicenseManager.ERROR_NULL_PARAMS:
////                                msg_res = R.string.err_elm_null_params;
////                                break;
////                            case EnterpriseLicenseManager.ERROR_SIGNATURE_MISMATCH:
////                                msg_res = R.string.err_elm_sig_mismatch;
////                                break;
////                            case EnterpriseLicenseManager.ERROR_UNKNOWN:
////                                msg_res = R.string.err_elm_unknown;
////                                break;
////                            case EnterpriseLicenseManager.ERROR_USER_DISAGREES_LICENSE_AGREEMENT:
////                                msg_res = R.string.err_elm_user_disagrees_license_agreement;
////                                break;
////                            case EnterpriseLicenseManager.ERROR_VERSION_CODE_MISMATCH:
////                                msg_res = R.string.err_elm_ver_code_mismatch;
////                                break;
////                            default:
////                                // Unknown error code
////                                String errorStatus = intent.getStringExtra(EnterpriseLicenseManager.EXTRA_LICENSE_STATUS);
////                                String msg = context.getResources().getString(R.string.err_elm_code_unknown, Integer.toString(errorCode), errorStatus);
////                                showToast(context, msg);
////                                Log.d("SampleLicenseReceiver", msg);
////                                return;
////                        }
//
//                // Display ELM error message
//                showToast(context, "intent");
////                        Log.d("SampleLicenseReceiver", context.getString(msg_res));
//                return;
//            }
        }
    }

    /**
     * 跳转页面；至需要跳转的页面
     * @param context
     */
    private void onSuccess(Context context){
        Intent intent=new Intent();
        intent.setAction(ReciverConstants.LICENSE_STATUS_SUCCESS);
        LocalBroadcastManager.getInstance(context).sendBroadcastSync(intent);
    }

    private void onFailure(Context context){
        Intent intent=new Intent();
        intent.setAction(ReciverConstants.LICENSE_STATUS_FAILURE);
        LocalBroadcastManager.getInstance(context).sendBroadcastSync(intent);
    }
}
