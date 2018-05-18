package com.nd.adhoc.dmsdk.api.huawei.manager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;

import com.huawei.android.app.admin.DeviceCameraManager;
import com.huawei.android.app.admin.DeviceRestrictionManager;
import com.nd.adhoc.dmsdk.api.IDeviceControl;


class ControlManager extends BaseManager implements IDeviceControl {


    private DeviceRestrictionManager deviceRestrictionManager;

    private DeviceCameraManager deviceCameraManager;


    public void setContext(Context context) {

        super.setContext(context);
        deviceRestrictionManager=new DeviceRestrictionManager();
        deviceCameraManager=new DeviceCameraManager();

    }

    @Override
    public boolean openCamera() {
        boolean isOpenSuccess=false;
        try {
            deviceCameraManager.setVideoDisabled(getComponentName(), true);
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
            deviceCameraManager.setVideoDisabled(getComponentName(), false);
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
            deviceRestrictionManager.setUSBDataDisabled(getComponentName(), true);
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
            deviceRestrictionManager.setUSBDataDisabled(getComponentName(), false);
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
            deviceRestrictionManager.setWifiDisabled(getComponentName(), true);
            isOpenSuccess=true;
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
            deviceRestrictionManager.setWifiDisabled(getComponentName(), false);
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
            deviceRestrictionManager.setDataConnectivityDisabled(getComponentName(), true);
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
            deviceRestrictionManager.setDataConnectivityDisabled(getComponentName(), false);
            isOpenSuccess=true;
        }catch (SecurityException e){
            e.printStackTrace();
        }
        return  isOpenSuccess;
    }

    @Override
    public boolean openMicrophone() {
        boolean isOpenSuccess=false;
        try {
            deviceRestrictionManager.setMicrophoneDisabled(getComponentName(), true);
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
            deviceRestrictionManager.setMicrophoneDisabled(getComponentName(), false);
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
            deviceRestrictionManager.setBluetoothDisabled(getComponentName(), true);
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
            deviceRestrictionManager.setBluetoothDisabled(getComponentName(), false);
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
            deviceRestrictionManager.setExternalStorageDisabled(getComponentName(), true);
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
            deviceRestrictionManager.setExternalStorageDisabled(getComponentName(), false);
            isOpenSuccess=true;
        }catch (SecurityException e){
            e.printStackTrace();
        }
        return isOpenSuccess;
    }

    @Override
    public boolean isOpenCamrea() {
        return deviceCameraManager.isVideoDisabled(getComponentName());
    }

    @Override
    public boolean isOpenUsb() {
        return deviceRestrictionManager.isUSBDataDisabled(getComponentName());
    }

    @Override
    public boolean isOpenBluetooth() {
        return deviceRestrictionManager.isBluetoothDisabled(getComponentName());
    }

    @Override
    public boolean isOpenNetwork() {
        return deviceRestrictionManager.isDataConnectivityDisabled(getComponentName());
    }

    @Override
    public boolean isOpenMicrophone() {
        return deviceRestrictionManager.isMicrophoneDisabled(getComponentName());
    }
    @Override
    public boolean isOpenWifi() {
        return deviceRestrictionManager.isWifiDisabled(getComponentName());
    }

    @Override
    public boolean isOpenSdCard() {
        return deviceRestrictionManager.isExternalStorageDisabled(getComponentName());
    }

    @Override
    public boolean deviceWipeData() {
        return true;
    }

    @Override
    public void release() {
        deviceRestrictionManager=null;
        deviceCameraManager=null;
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
