//package com.nd.adhoc.dmsdk.api.knox.manager;
//
//import android.app.enterprise.RestrictionPolicy;
//import android.app.enterprise.SecurityPolicy;
//import android.bluetooth.BluetoothAdapter;
//import android.content.Context;
//import android.net.wifi.WifiManager;
//import android.util.Log;
//
//import com.nd.adhoc.dmsdk.api.IDeviceControl;
//
//import static android.app.enterprise.SecurityPolicy.WIPE_INTERNAL_EXTERNAL_MEMORY;
//
//class ControlManager extends BaseManager implements IDeviceControl {
//
//    private RestrictionPolicy mRestrictioPolicy;
//
//    private SecurityPolicy deviceSecurityPolicy;
//
//    public void setContext(Context context) {
//
//        super.setContext(context);
//        Log.i(this.getClass().getName(), "deviceManager:=" + deviceManager);
//        mRestrictioPolicy = deviceManager.getRestrictionPolicy();
//        deviceSecurityPolicy=deviceManager.getSecurityPolicy();
//    }
//
//    @Override
//    public boolean openCamera() {
//        return  mRestrictioPolicy.setCameraState(true);
//    }
//
//    @Override
//    public boolean cloaseCamera() {
//      return   mRestrictioPolicy.setCameraState(false);
//    }
//
//    @Override
//    public boolean openUsb() {
//        return mRestrictioPolicy.allowUsbHostStorage(true);
//    }
//
//    @Override
//    public boolean closeUsb() {
//        return mRestrictioPolicy.allowUsbHostStorage(false);
//    }
//
//    @Override
//    public boolean openWifi() {
//        boolean isOpenSuccess=mRestrictioPolicy.allowWiFi(true);
//        enableWifi();
//        return isOpenSuccess;
//    }
//
//    @Override
//    public boolean closeWifi() {
//       return mRestrictioPolicy.allowWiFi(false);
//    }
//
//    @Override
//    public boolean openNetwork() {
//        return mRestrictioPolicy.allowUserMobileDataLimit(true);
//    }
//
//    @Override
//    public boolean closeNetwork() {
//        return mRestrictioPolicy.allowUserMobileDataLimit(false);
//    }
//
//    @Override
//    public boolean openMicrophone() {
//       return mRestrictioPolicy.setMicrophoneState(true);
//    }
//
//    @Override
//    public boolean closeMicrophone() {
//        return mRestrictioPolicy.setMicrophoneState(false);
//    }
//
//    @Override
//    public boolean openBluetooth() {
//        boolean isOpenSuccess= mRestrictioPolicy.allowBluetooth(true);
//        enableBluetooth();
//        return isOpenSuccess;
//    }
//
//    @Override
//    public boolean closeBluetooth() {
//       return mRestrictioPolicy.allowBluetooth(false);
//    }
//
//    @Override
//    public boolean openSdCard() {
//        return mRestrictioPolicy.setSdCardState(true);
//    }
//
//    @Override
//    public boolean closeSdCard() {
//        return mRestrictioPolicy.setSdCardState(false);
//    }
//
//    @Override
//    public boolean isOpenCamrea() {
//        return mRestrictioPolicy.isCameraEnabled(true);
//    }
//
//    @Override
//    public boolean isOpenUsb() {
//        return mRestrictioPolicy.isUsbTetheringEnabled();
//    }
//
//    @Override
//    public boolean isOpenBluetooth() {
//        return mRestrictioPolicy.isBluetoothEnabled(true);
//    }
//
//    @Override
//    public boolean isOpenNetwork() {
//        return mRestrictioPolicy.isUserMobileDataLimitAllowed();
//    }
//
//    @Override
//    public boolean isOpenMicrophone() {
//        return mRestrictioPolicy.isMicrophoneEnabled(true);
//    }
//    @Override
//    public boolean isOpenWifi() {
//        return mRestrictioPolicy.isWiFiEnabled(false);
//    }
//
//    @Override
//    public boolean isOpenSdCard() {
//        return mRestrictioPolicy.isSdCardEnabled();
//    }
//
//    @Override
//    public boolean deviceWipeData() {
//        //全部数据擦除
//        return deviceSecurityPolicy.wipeDevice(WIPE_INTERNAL_EXTERNAL_MEMORY);
////        //擦除外置存储的数据
////        deviceSecurityPolicy.wipeDevice(WIPE_EXTERNAL_MEMORY);
////        //擦除手机上的数据
////        deviceSecurityPolicy.wipeDevice(WIPE_INTERNAL_MEMORY);
//    }
//
//    @Override
//    public void release() {
//        mRestrictioPolicy=null;
//        deviceSecurityPolicy=null;
//    }
//
//
//    private void enableWifi() {
//
//        WifiManager wm = (WifiManager) context
//                .getSystemService(Context.WIFI_SERVICE);
//        wm.setWifiEnabled(true);
//    }
//
//    private void enableBluetooth() {
//
//        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//        if (bluetoothAdapter != null)
//        {
//            bluetoothAdapter.enable();
//        }
//    }
//}
