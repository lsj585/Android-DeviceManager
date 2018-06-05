package com.nd.adhoc.dmsdk.demo.ui;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.adhoc.dmsdk.sdk.DeviceManagerSdk;
import com.nd.adhoc.dmsdk.demo.R;
import com.nd.adhoc.dmsdk.demo.presenter.impl.ActiveLicensePresenter;
import com.nd.adhoc.dmsdk.demo.view.BaseView;
import com.nd.adhoc.dmsdk.revicer.Constants;


/**
 * 该DEMO演示的直接调用api层；sdk层未设计
 * <p>
 * 业务流程，激活设备后进入主页
 * <p>
 * 接入knox sdk需要配置以下条件：
 * 1.加入权限，在mainfest.xml中设置
 * <p>
 * <!-- Permissions TODO List permissions used by application -->
 * <uses-permission android:name="com.samsung.android.knox.permission.KNOX_HW_CONTROL" />
 * <p>
 * 2.广播接收
 * <p>
 * <receiver android:name="com.nd.adhoc.dmsdk.revicer.AdminReciver"
 *          android:permission="android.permission.BIND_DEVICE_ADMIN">
 *          <meta-data android:name="android.app.device_admin"
 *                          android:resource="@xml/device_admin_receiver" />
 *       <intent-filter>
 *              <action android:name="android.app.action.DEVICE_ADMIN_ENABLED" />
 *       </intent-filter>
 * </receiver>
 * <p>
 * <receiver android:name="com.nd.adhoc.dmsdk.revicer.ActiveLicenseReceiver">
 *          <intent-filter>
 *              <action android:name="com.samsung.android.knox.intent.action.LICENSE_STATUS" />
 *              <action android:name="com.samsung.android.knox.intent.action.KNOX_LICENSE_STATUS"/>
 *          </intent-filter>
 * </receiver>
 * <p>
 * 3.在res目录创建xml目录，加入device_admin_receiver：
 */
public class LauncherActivity extends AppCompatActivity implements View.OnClickListener,BaseView {

    private int LOAD_NUMBER = 1;
    /**
     * 激活设备
     */
    private Button btnActiveDevice;

    final String DEFAULT_DEVICE_LAUNCH="com.nd.adhoc.default_device_Launcher";
    final String DEFAULT_DEVICE_CATEGORY="com.nd.adhoc.default_device_CATEGORY";

    private ActiveLicensePresenter presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launcher_activity);
        btnActiveDevice = findViewById(R.id.btn_activeDevice);
        btnActiveDevice.setOnClickListener(this);
        presenter=new ActiveLicensePresenter(this,this);
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.LICENSE_STATUS_SUCCESS);
        filter.addAction(Constants.LICENSE_STATUS_FAILURE);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter);
    }
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_activeDevice) {
            presenter.activeLicense();
        }
    }


    private  BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(final Context context, Intent intent) {

            if (intent.getAction().equals(Constants.LICENSE_STATUS_FAILURE)) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.active_device_error), Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (intent.getAction().equals(Constants.LICENSE_STATUS_SUCCESS)) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(!DeviceManagerSdk.getInstance().isResgisterSDK()){
                            DeviceManagerSdk.getInstance().registerSDK(context.getApplicationContext());
                        }
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.active_device_success), Toast.LENGTH_SHORT).show();
                        Intent intentAction=new Intent();
                        intentAction.setAction(DEFAULT_DEVICE_LAUNCH);
                        intentAction.addCategory(DEFAULT_DEVICE_CATEGORY);
                        startActivity(intentAction);
                        finish();
                    }
                });
            }
        }
    };

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
        super.onDestroy();
    }
}
