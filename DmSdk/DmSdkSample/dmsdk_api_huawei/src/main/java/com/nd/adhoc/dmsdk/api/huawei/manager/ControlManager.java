package com.nd.adhoc.dmsdk.api.huawei.manager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.nd.adhoc.dmsdk.api.IDeviceControl;


class ControlManager extends BaseManager implements IDeviceControl {



    public void setContext(Context context) {

        super.setContext(context);


    }

    @Override
    public boolean openCamera() {
        return  true;
    }

    @Override
    public boolean cloaseCamera() {
        return  true;
    }

    @Override
    public boolean openUsb() {
        return  true;
    }

    @Override
    public boolean closeUsb() {
        return  true;
    }

    @Override
    public boolean openWifi() {
        boolean isOpenSuccess=true;
        enableWifi();
        return isOpenSuccess;
    }

    @Override
    public boolean closeWifi() {
        return  true;
    }

    @Override
    public boolean openNetwork() {
        return  true;
    }

    @Override
    public boolean closeNetwork() {
        return  true;
    }

    @Override
    public boolean openMicrophone() {
        return  true;
    }

    @Override
    public boolean closeMicrophone() {
        return  true;
    }

    @Override
    public boolean openBluetooth() {
        boolean isOpenSuccess=true;
        enableBluetooth();
        return isOpenSuccess;
    }

    @Override
    public boolean closeBluetooth() {
        return  true;
    }

    @Override
    public boolean openSdCard() {
        return  true;
    }

    @Override
    public boolean closeSdCard() {
        return  true;
    }

    @Override
    public boolean isOpenCamrea() {
        return true;
    }

    @Override
    public boolean isOpenUsb() {
        return true;
    }

    @Override
    public boolean isOpenBluetooth() {
        return true;
    }

    @Override
    public boolean isOpenNetwork() {
        return true;
    }

    @Override
    public boolean isOpenMicrophone() {
        return true;
    }
    @Override
    public boolean isOpenWifi() {
        return true;
    }

    @Override
    public boolean isOpenSdCard() {
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
