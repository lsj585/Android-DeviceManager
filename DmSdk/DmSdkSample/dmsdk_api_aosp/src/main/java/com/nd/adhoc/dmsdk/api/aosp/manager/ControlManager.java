package com.nd.adhoc.dmsdk.api.aosp.manager;
import android.app.admin.DevicePolicyManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Binder;
import android.os.Build;
import android.os.UserManager;

import com.huawei.android.app.admin.DeviceCameraManager;
import com.huawei.android.app.admin.DeviceRestrictionManager;
import com.nd.adhoc.dmsdk.api.IDeviceControl;


class ControlManager extends BaseManager implements IDeviceControl {


    private DevicePolicyManager devicePolicyManager;

    public void setContext(Context context) {

        super.setContext(context);
        if(devicePolicyManager==null) {
            devicePolicyManager = (DevicePolicyManager)context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        }
    }

    @Override
    public boolean openCamera() {
        boolean isOpenSuccess=false;
        try {
            devicePolicyManager.setCameraDisabled(getComponentName(),true);
            isOpenSuccess=true;
        }catch (SecurityException e){
            e.printStackTrace();
        }
        return isOpenSuccess;
    }

    @Override
    public boolean cloaseCamera() {
        boolean isOpenSuccess=false;
        try {
            devicePolicyManager.setCameraDisabled(getComponentName(),true);
            isOpenSuccess=true;
        }catch (SecurityException e){
            e.printStackTrace();
        }
        return isOpenSuccess;
    }

    @Override
    public boolean openUsb() {
        boolean isOpenSuccess=false;
        try {
            Binder.getCallingUid();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                devicePolicyManager.addUserRestriction(getComponentName(),UserManager.DISALLOW_USB_FILE_TRANSFER);
            }else{
                //抛出异常
            }
            isOpenSuccess=true;
        }catch (SecurityException e){
            e.printStackTrace();
        }
        return isOpenSuccess;
    }

    @Override
    public boolean closeUsb() {
        boolean isOpenSuccess=false;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                devicePolicyManager.clearUserRestriction(getComponentName(),UserManager.DISALLOW_USB_FILE_TRANSFER);
            }else{
                //抛出异常
            }
            isOpenSuccess=true;
        }catch (SecurityException e){
            e.printStackTrace();
        }
        return isOpenSuccess;
    }

    @Override
    public boolean openWifi() {
        boolean isOpenSuccess=false;
        try {

        }catch (SecurityException e){
            e.printStackTrace();
        }
        enableWifi();
        return isOpenSuccess;
    }

    @Override
    public boolean closeWifi() {
        boolean isOpenSuccess=false;
        try {

            isOpenSuccess=true;
        }catch (SecurityException e){
            e.printStackTrace();
        }
        return  isOpenSuccess;
    }

    @Override
    public boolean openNetwork() {
        boolean isOpenSuccess=false;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                devicePolicyManager.addUserRestriction(getComponentName(),UserManager.DISALLOW_DATA_ROAMING);
            }else{
                //抛出异常
            }
            isOpenSuccess=true;
        }catch (SecurityException e){
            e.printStackTrace();
        }
        return  isOpenSuccess;
    }

    @Override
    public boolean closeNetwork() {
        boolean isOpenSuccess=false;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                devicePolicyManager.clearUserRestriction(getComponentName(),UserManager.DISALLOW_DATA_ROAMING);
            }else {
                isOpenSuccess = true;
            }
        }catch (SecurityException e){
            e.printStackTrace();
        }
        return  isOpenSuccess;
    }

    @Override
    public boolean openMicrophone() {
        boolean isOpenSuccess=false;
        try {

            isOpenSuccess=true;
        }catch (SecurityException e){
            e.printStackTrace();
        }
        return  isOpenSuccess;
    }

    @Override
    public boolean closeMicrophone() {
        boolean isOpenSuccess=false;
        try {

            isOpenSuccess=true;
        }catch (SecurityException e){
            e.printStackTrace();
        }
        return  isOpenSuccess;
    }

    @Override
    public boolean openBluetooth() {
        boolean isOpenSuccess=false;
        try {

            isOpenSuccess=true;
        }catch (SecurityException e){
            e.printStackTrace();
        }
        enableBluetooth();
        return isOpenSuccess;
    }

    @Override
    public boolean closeBluetooth() {
        boolean isOpenSuccess=false;
        try {

            isOpenSuccess=true;
        }catch (SecurityException e){
            e.printStackTrace();
        }
        enableBluetooth();
        return isOpenSuccess;
    }

    @Override
    public boolean openSdCard() {
        boolean isOpenSuccess=false;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                devicePolicyManager.addUserRestriction(getComponentName(),UserManager.DISALLOW_MOUNT_PHYSICAL_MEDIA);
            }else{

            }
            isOpenSuccess=true;
        }catch (SecurityException e){
            e.printStackTrace();
        }
        return isOpenSuccess;
    }

    @Override
    public boolean closeSdCard() {
        boolean isOpenSuccess=false;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                devicePolicyManager.clearUserRestriction(getComponentName(),UserManager.DISALLOW_MOUNT_PHYSICAL_MEDIA);
            }else{

            }
            isOpenSuccess=true;
        }catch (SecurityException e){
            e.printStackTrace();
        }
        return isOpenSuccess;
    }

    @Override
    public boolean isOpenCamrea() {
        return true;
//        return deviceCameraManager.isVideoDisabled(getComponentName());
    }

    @Override
    public boolean isOpenUsb() {
//        return deviceRestrictionManager.isUSBDataDisabled(getComponentName());
        return true;
    }

    @Override
    public boolean isOpenBluetooth() {
//        return deviceRestrictionManager.isBluetoothDisabled(getComponentName());
        return true;
    }

    @Override
    public boolean isOpenNetwork() {
//        return deviceRestrictionManager.isDataConnectivityDisabled(getComponentName());
        return true;
    }

    @Override
    public boolean isOpenMicrophone() {
//        return deviceRestrictionManager.isMicrophoneDisabled(getComponentName());
        return true;
    }

    @Override
    public boolean isOpenWifi() {
//        return deviceRestrictionManager.isWifiDisabled(getComponentName());
        return true;
    }

    @Override
    public boolean isOpenSdCard() {
//        return deviceRestrictionManager.isExternalStorageDisabled(getComponentName());
        return true;
    }

    @Override
    public boolean deviceWipeData() {
        return true;
    }

    @Override
    public void release() {

    }


    private void enableWifi() {
        WifiManager wm = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        wm.setWifiEnabled(true);
    }

    private void enableBluetooth() {

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter != null)
        {
            bluetoothAdapter.enable();
        }
    }
}
