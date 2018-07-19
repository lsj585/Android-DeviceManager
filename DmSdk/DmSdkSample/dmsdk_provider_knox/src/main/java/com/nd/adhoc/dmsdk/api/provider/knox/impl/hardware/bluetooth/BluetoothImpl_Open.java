package com.nd.adhoc.dmsdk.api.provider.knox.impl.hardware.bluetooth;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiFunctionKey;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.hardware.bluetooth.IBluetooth_Open;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.filed.DmSdkConstants;
import com.nd.sdp.android.serviceloader.annotation.Service;

@ApiFunctionKey(DmSdkConstants.MANAGER_HARDWARE_BLUETOOTH)
@Service(IDmSdkApi.class)
@ApiImpl(IBluetooth_Open.class)
public class BluetoothImpl_Open extends BlutoothImpl_Base implements IBluetooth_Open {

    @Override
    public boolean open(@NonNull Context context) {
        try {
            turnOff(context, true);
        }catch (DeviceManagerSecurityException e){
            e.printStackTrace();
            return false;
        }
        enableBluetooth();
        return true;
    }

    @Override
    public void release(@NonNull Context context) {

    }

    /**
     * 开启蓝牙开关
     */
    private void enableBluetooth() {

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter != null)
        {
            bluetoothAdapter.enable();
        }
    }
}
