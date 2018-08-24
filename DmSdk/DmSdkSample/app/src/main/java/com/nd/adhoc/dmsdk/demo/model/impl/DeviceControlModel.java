package com.nd.adhoc.dmsdk.demo.model.impl;

import android.app.Instrumentation;
import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;

import com.adhoc.dmsdk.sdk.DeviceManagerSdk;
import com.nd.adhoc.dmsdk.api.hardware.bluetooth.IBluetooth_IsOpen;
import com.nd.adhoc.dmsdk.api.hardware.devicelock.IDeviceLock_IsLock;
import com.nd.adhoc.dmsdk.api.hardware.microphone.IMicrophone_IsOpen;
import com.nd.adhoc.dmsdk.api.hardware.mobiledata.IMobileData_IsOpen;
import com.nd.adhoc.dmsdk.api.hardware.mobiledata.IMobileData_Open;
import com.nd.adhoc.dmsdk.api.hardware.sdcard.ISdCard_IsMount;
import com.nd.adhoc.dmsdk.api.hardware.usb.IUsb_IsConnect;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.exception.DeviceManagerUnsupportException;
import com.nd.adhoc.dmsdk.api.hardware.wifi.IWifi_IsOpen;
import com.nd.adhoc.dmsdk.api.key.IPhysicalKey_Menu;
import com.nd.adhoc.dmsdk.demo.R;
import com.nd.adhoc.dmsdk.demo.bean.HardWareSwitchBean;
import com.nd.adhoc.dmsdk.demo.model.BaseModel;
import com.nd.adhoc.dmsdk.demo.model.IDeviceControlModel;
import com.nd.adhoc.dmsdk.filed.DmSdkConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据模组层 负责对接SDK
 */
public class DeviceControlModel extends BaseModel<HardWareSwitchBean> implements IDeviceControlModel {


    private List<HardWareSwitchBean> mList;

    public DeviceControlModel(Context context) {
        super(context);
    }

    @Override
    public List<HardWareSwitchBean> getList() {
        return createDeviceControlList();
    }

    @Override
    public void update(HardWareSwitchBean hardWareSwitchBean, int position) {

        if (mList != null && mList.size() > 0) {
            HardWareSwitchBean bean = mList.get(position);
            bean.setStatus(hardWareSwitchBean.getStatus());
        }
    }

    @Override
    public void delete(HardWareSwitchBean hardWareSwitchBean) {
        if (mList != null && mList.size() > 0) {
            mList.remove(hardWareSwitchBean);
        }
    }

    @Override
    public void delete(int position) {

    }

    @Override
    public HardWareSwitchBean findById(long id) {
        return null;
    }

    @Override
    public void update(int position) {

    }


    @Override
    public boolean updateStatus(int position) {
        switch (position) {
            case 0:
                return deviceWifi();
            case 1:
                return deviceMobileNetwork();
            case 2:
                return deviceBluetooth();
            case 3:
                return deviceCamera();
            case 4:
                return deviceMicrophone();
            case 5:
                return deviceUsb();
            case 6:
                return deviceSdCard();
            case 7:
                return deviceLock();
            case 8:
                return execHomeKey();
            case 9:
                return execBackKey();
            case 10:
                return execMenuKey();
            case 11:
                return execShutdown();
            case 12:
                return execVolumnUp();
            case 13:
                return execVolumnDown();
            case 14:
                //唤醒
                return false;
            case 15:
                //休眠
                return false;
            case 16:
//                //关机
                return false;
            case 17:
                //重启
                return false;
            default:
                return false;
        }
    }

    private boolean deviceMicrophone() {
//        IMicrophon_Open microphoneManager= null;
//        try {
//            microphoneManager = (IMicrophon_Open) DeviceManagerSdk.getInstance().getApi(IMicrophon_Open.class);
//        } catch (DeviceManagerUnsupportException e) {
//            e.printStackTrace();
//            return false;
//        }
////        try {
////            if(!microphoneManager.isOpen(context)){
////                microphoneManager.open(context);
////            }else{
////                microphoneManager.close(context);
////            }
////            return true;
////        } catch (DeviceManagerSecurityException e) {
////            e.printStackTrace();
////        }
        return false;
    }

    private boolean deviceUsb() {

//        IUsbMamager usbMamager= null;
//        try {
//            usbMamager = (IUsbMamager) DeviceManagerSdk.getInstance().getManager(DmSdkConstants.MANAGER_HARDWARE_USB);
//        } catch (DeviceManagerUnsupportException e) {
//            e.printStackTrace();
//            return false;
//        }
//        try {
//            if(!usbMamager.isOpen(context)){
//                usbMamager.open(context);
//            }else{
//                usbMamager.close(context);
//            }
//            return true;
//        } catch (DeviceManagerSecurityException e) {
//            e.printStackTrace();
//            return false;
//        }
        return false;
    }

    private boolean deviceSdCard() {

//        ISdCard_Mount sdCardManager= null;
//        try {
//            sdCardManager = (ISdCard_Mount) DeviceManagerSdk.getInstance().getManager(DmSdkConstants.MANAGER_HARDWARE_SDCARD);
//        } catch (DeviceManagerUnsupportException e) {
//            e.printStackTrace();
//            return false;
//        }
//        try {
//            if(!sdCardManager.isOpen(context)){
//                sdCardManager.open(context);
//            }else{
//                sdCardManager.close(context);
//            }
//            return true;
//        } catch (DeviceManagerSecurityException e) {
//            e.printStackTrace();
//        }
        return false;
    }

    private boolean deviceLock() {

//        IDeviceLock lockManager= null;
//        try {
//            lockManager = (IDeviceLock) DeviceManagerSdk.getInstance().getManager(DmSdkConstants.MANAGER_HARDWARE_LOCK);
//        } catch (DeviceManagerUnsupportException e) {
//            e.printStackTrace();
//            return false;
//        }
//        try {
//            if(!lockManager.isOpen(context)){
//                lockManager.open(context);
//            }else{
//                lockManager.close(context);
//            }
//            return true;
//        } catch (DeviceManagerSecurityException e) {
//            e.printStackTrace();
//        }
        return false;
    }

    private boolean deviceBluetooth() {

//        IBluetooth_Open bluetoothManager= null;
//        try {
//            bluetoothManager = (IBluetooth_Open) DeviceManagerSdk.getInstance().getManager(DmSdkConstants.MANAGER_HARDWARE_BLUETOOTH);
//        } catch (DeviceManagerUnsupportException e) {
//            e.printStackTrace();
//            return false;
//        }
//        try {
//            if(!bluetoothManager.isOpen(context)){
//                bluetoothManager.open(context);
//            }else{
//                bluetoothManager.close(context);
//            }
//            return true;
//        } catch (DeviceManagerSecurityException e) {
//            e.printStackTrace();
//        }
        return false;
    }

    private boolean deviceCamera() {
//        ICamera cameraManager= null;
//        try {
//            cameraManager = (ICamera) DeviceManagerSdk.getInstance().getManager(DmSdkConstants.MANAGER_HARDWARE_CAMERA);
//        } catch (DeviceManagerUnsupportException e) {
//            e.printStackTrace();
//            return false;
//        }
//        try {
//            if(!cameraManager.isOpen(context)){
//                cameraManager.open(context);
//            }else{
//                cameraManager.close(context);
//            }
//            return true;
//        } catch (DeviceManagerSecurityException e) {
//            e.printStackTrace();
//        }
        return false;
    }

    private boolean deviceMobileNetwork() {

//        IMobileData_Open mobileDataManager= null;
//        try {
//            mobileDataManager = (IMobileData_Open) DeviceManagerSdk.getInstance().getManager(DmSdkConstants.MANAGER_HARDWARE_MOBILEDATA);
//        } catch (DeviceManagerUnsupportException e) {
//            e.printStackTrace();
//            return false;
//        }
//        try {
//            if(!mobileDataManager.isOpen(context)){
//                mobileDataManager.open(context);
//            }else{
//                mobileDataManager.close(context);
//            }
//            return true;
//        } catch (DeviceManagerSecurityException e) {
//            e.printStackTrace();
//        }
        return false;
    }

    private boolean deviceWifi() {

//        IWifi_IsOpen wifiManager= null;
//        try {
//            wifiManager = (IWifi_IsOpen) DeviceManagerSdk.getInstance().getManager(DmSdkConstants.MANAGER_HARDWARE_WIFI);
//        } catch (DeviceManagerUnsupportException e) {
//            e.printStackTrace();
//            return false;
//        }
//        try {
//            if(!wifiManager.isOpen(context)){
//                wifiManager.open(context);
//            }else{
//                wifiManager.close(context);
//            }
//            return true;
//        } catch (DeviceManagerSecurityException e) {
//            e.printStackTrace();
//        }
        return false;
    }

    @Override
    public void update(int position, boolean isSuccess) {

        if (mList != null && mList.size() > 0) {
            HardWareSwitchBean bean = mList.get(position);
            //执行成功或失败
            if (isSuccess) {
                bean.setStatus(bean.getStatus() == 0 ? 1 : 0);
                update(bean, position);
            }
        }
    }

    /**
     * HOME键
     *
     * @return
     */
    public boolean execHomeKey() {
        return execKey(KeyEvent.KEYCODE_HOME);
    }

    /**
     * 返回键
     *
     * @return
     */
    public boolean execBackKey() {
        return execKey(KeyEvent.KEYCODE_BACK);
    }

    /**
     * 菜单键
     *
     * @return
     */
    public boolean execMenuKey() throws UnsupportedOperationException {

        IPhysicalKey_Menu keyManagerMenu = null;
        try {
            keyManagerMenu = (IPhysicalKey_Menu) DeviceManagerSdk.getInstance().getApi(IPhysicalKey_Menu.class);
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
            return false;
        }
        keyManagerMenu.exec();
        return execKey(KeyEvent.KEYCODE_MENU);
    }

    /**
     * 电源键
     *
     * @return
     */
    public boolean execShutdown() {
        //KEYCODE_POWER 长按可做关机 短按可做唤醒
        return execKey(KeyEvent.KEYCODE_POWER);
    }

    /**
     * 音量+
     *
     * @return
     */
    public boolean execVolumnUp() {
        return execKey(KeyEvent.KEYCODE_VOLUME_UP);
    }

    /**
     * 音量-
     *
     * @return
     */
    public boolean execVolumnDown() {
        return execKey(KeyEvent.KEYCODE_VOLUME_DOWN);
    }


    private boolean execKey(int key) {
        Instrumentation inst = new Instrumentation();
        inst.sendKeyDownUpSync(key);
        return true;
    }

    private List createDeviceControlList() {

        if (mList == null) {
            mList = new ArrayList<>();
        }
        mList.clear();
        /**
         * 蓝牙模块
         */
        HardWareSwitchBean bluetoothSwitchBean = new HardWareSwitchBean();
        bluetoothSwitchBean.setName(context.getResources().getString(R.string.bluetooth));
        mList.add(bluetoothSwitchBean);
        /**
         * wifi模块
         */
        HardWareSwitchBean wifiBean = new HardWareSwitchBean();
        wifiBean.setName(context.getResources().getString(R.string.wifi));
        mList.add(wifiBean);
        /**
         *3G网络 -- 移动网络
         */
        HardWareSwitchBean t3gBean = new HardWareSwitchBean();
        t3gBean.setName(context.getResources().getString(R.string.t3g));
        t3gBean.setDesiplaySave(true);
        mList.add(t3gBean);

        /**
         * Camera 摄像头
         */
        HardWareSwitchBean cameraBean = new HardWareSwitchBean();
        cameraBean.setName(context.getResources().getString(R.string.camera));
        cameraBean.setDesiplaySave(true);
        mList.add(cameraBean);

        /**
         * 麦克风
         */
        HardWareSwitchBean microphoneBean = new HardWareSwitchBean();
        microphoneBean.setName(context.getResources().getString(R.string.microphone));
        microphoneBean.setDesiplaySave(true);
        mList.add(microphoneBean);
        /**
         * usb
         */
        HardWareSwitchBean usbBean = new HardWareSwitchBean();
        usbBean.setName(context.getResources().getString(R.string.usb));
        usbBean.setDesiplaySave(true);
        mList.add(usbBean);
        /**
         * SD Card
         */
        HardWareSwitchBean sdCardBean = new HardWareSwitchBean();
        sdCardBean.setName(context.getResources().getString(R.string.sd_card));
        sdCardBean.setDesiplaySave(true);
        mList.add(sdCardBean);
        /**
         * Lock
         */
        HardWareSwitchBean locKBean = new HardWareSwitchBean();
        locKBean.setName(context.getResources().getString(R.string.lock));
        locKBean.setDesiplaySave(true);
        mList.add(locKBean);
        Log.i(this.getClass().getName(), String.format("getmList:%d", mList.size()));
        /**
         * HOME键执行
         */
        HardWareSwitchBean homeKeyBean = new HardWareSwitchBean();
        homeKeyBean.setName(context.getResources().getString(R.string.home));
        homeKeyBean.setStatus(0);
        homeKeyBean.setDesiplaySave(false);
        mList.add(homeKeyBean);
//
//
        /**
         * back键执行
         */
        HardWareSwitchBean backKeyBean = new HardWareSwitchBean();
        backKeyBean.setName(context.getResources().getString(R.string.back));
        backKeyBean.setStatus(0);
        backKeyBean.setDesiplaySave(false);
        mList.add(backKeyBean);
//
//
        /**
         * 菜单键执行
         */
        HardWareSwitchBean menuKeyBean = new HardWareSwitchBean();
        menuKeyBean.setName(context.getResources().getString(R.string.menu_key));
        menuKeyBean.setStatus(0);
        menuKeyBean.setDesiplaySave(false);
        mList.add(menuKeyBean);
        Log.i(this.getClass().getName(), String.format("getmList:%d", mList.size()));
//
//
        /**
         * 电源键
         */
        HardWareSwitchBean powerKeyBean = new HardWareSwitchBean();
        powerKeyBean.setName(context.getResources().getString(R.string.power_key));
        powerKeyBean.setStatus(0);
        powerKeyBean.setDesiplaySave(false);
        mList.add(powerKeyBean);
        Log.i(this.getClass().getName(), String.format("getmList:%d", mList.size()));
//
//
        /**
         * 音量键+
         */
        HardWareSwitchBean upKeyBean = new HardWareSwitchBean();
        upKeyBean.setName(context.getResources().getString(R.string.volumn_up));
        upKeyBean.setStatus(0);
        upKeyBean.setDesiplaySave(false);
        mList.add(upKeyBean);
//
//
        /**
         * 音量键-
         */
        HardWareSwitchBean downKeyBean = new HardWareSwitchBean();
        downKeyBean.setName(context.getResources().getString(R.string.volumn_down));
        downKeyBean.setStatus(0);
        downKeyBean.setDesiplaySave(false);
        mList.add(downKeyBean);

        Log.i(this.getClass().getName(), String.format("getmList:%d", mList.size()));
//
        /**
         * wake 设备唤醒
         */
        HardWareSwitchBean wakeKeyBeans = new HardWareSwitchBean();
        wakeKeyBeans.setName(context.getResources().getString(R.string.weak));
        wakeKeyBeans.setStatus(0);
        wakeKeyBeans.setDesiplaySave(false);
        mList.add(wakeKeyBeans);
//
        /**
         * wake 设备休眠
         */
        HardWareSwitchBean dormancyBean = new HardWareSwitchBean();
        dormancyBean.setName(context.getResources().getString(R.string.dormancy));
        dormancyBean.setStatus(0);
        dormancyBean.setDesiplaySave(false);
        mList.add(dormancyBean);
//
        /**
         * wake 关机
         */
        HardWareSwitchBean powerOffbean = new HardWareSwitchBean();
        powerOffbean.setName(context.getResources().getString(R.string.power_off));
        powerOffbean.setStatus(0);
        powerOffbean.setDesiplaySave(false);
        mList.add(powerOffbean);
//
//
        /**
         * wake 重启
         */
        HardWareSwitchBean rebootBean = new HardWareSwitchBean();
        rebootBean.setName(context.getResources().getString(R.string.reboot));
        rebootBean.setStatus(0);
        rebootBean.setDesiplaySave(false);
        mList.add(rebootBean);
        updateWifi(wifiBean);
        updateMobileData(t3gBean);
        updateBluetooth(bluetoothSwitchBean);
        updateMicrophone(microphoneBean);
        updateSdCard(sdCardBean);
        updateLock(locKBean);
        updateUsb(usbBean);
        return mList;
    }

    private void updateUsb(HardWareSwitchBean usbBean) {

        IUsb_IsConnect usbManager= null;
        try {
            usbManager = (IUsb_IsConnect) DeviceManagerSdk.getInstance().getApi(IUsb_IsConnect.class);
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
        }
        boolean isOpen = usbManager.isOpen(context);
        usbBean.setStatus(isOpen == true ? 1 : 0);
        usbBean.setDesiplaySave(true);
    }
    private void updateLock(HardWareSwitchBean locKBean) {
        IDeviceLock_IsLock lockManager= null;
        try {
            lockManager = (IDeviceLock_IsLock) DeviceManagerSdk.getInstance().getApi(IDeviceLock_IsLock.class);
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
        }
        boolean isOpen = lockManager.isOpen(context);
        locKBean.setStatus(isOpen == true ? 1 : 0);
        locKBean.setDesiplaySave(true);

    }

    private void updateSdCard(HardWareSwitchBean sdCardBean) {
        ISdCard_IsMount sdCardManager= null;
        try {
            sdCardManager = (ISdCard_IsMount) DeviceManagerSdk.getInstance().getApi(ISdCard_IsMount.class);
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
        }
        boolean isOpen = sdCardManager.isOpen(context);
        sdCardBean.setStatus(isOpen == true ? 1 : 0);
        sdCardBean.setDesiplaySave(true);
    }

    private void updateMicrophone(HardWareSwitchBean microphoneBean) {

        IMicrophone_IsOpen microphoneManager = null;
        try {
            microphoneManager = (IMicrophone_IsOpen) DeviceManagerSdk.getInstance().getApi(IMicrophone_IsOpen.class);
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
        }
        boolean isOpen = microphoneManager.isOpen(context);
        microphoneBean.setStatus(isOpen == true ? 1 : 0);
        microphoneBean.setDesiplaySave(true);
    }


    @Override
    public void release() {
        mList = null;
    }

    /**
     * 添加WIFI
     *
     * @param wifiBean
     */
    private void updateWifi(HardWareSwitchBean wifiBean) {
        IWifi_IsOpen wifiManager = null;
        try {
            wifiManager = (IWifi_IsOpen) DeviceManagerSdk.getInstance().getApi(IWifi_IsOpen.class);
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
        }

        if (wifiManager == null) {
            return;
        }
        boolean isOpenWifi = wifiManager.isOpen(context);
        wifiBean.setStatus(isOpenWifi == true ? 1 : 0);
        wifiBean.setDesiplaySave(true);
    }

    /**
     * 添加移动网络模块
     *
     * @param t3gBean
     */
    private void updateMobileData(HardWareSwitchBean t3gBean) {
        IMobileData_IsOpen mobileDataManager = null;
        try {
            mobileDataManager = (IMobileData_IsOpen) DeviceManagerSdk.getInstance().getApi(IMobileData_IsOpen.class);
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
        }
        if (mobileDataManager == null) {
            return;
        }
        t3gBean.setStatus(mobileDataManager.isOpen(context) == true ? 1 : 0);
    }

    private void updateBluetooth(HardWareSwitchBean bluetoothSwitchBean) {
        IBluetooth_IsOpen bluetoothIsOpen = null;
        try {
            bluetoothIsOpen = (IBluetooth_IsOpen) DeviceManagerSdk.getInstance().getApi(IBluetooth_IsOpen.class);
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
        }

        if (bluetoothIsOpen == null) {
            return;
        }
        boolean isOpenWifi = bluetoothIsOpen.isOpen(context);
        bluetoothSwitchBean.setStatus(isOpenWifi == true ? 1 : 0);
        bluetoothSwitchBean.setDesiplaySave(true);

    }

}
