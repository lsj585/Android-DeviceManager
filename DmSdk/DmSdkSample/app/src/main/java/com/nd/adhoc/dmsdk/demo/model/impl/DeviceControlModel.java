package com.nd.adhoc.dmsdk.demo.model.impl;

import android.app.Instrumentation;
import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;

import com.adhoc.dmsdk.sdk.DeviceManagerSdk;
import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.manager.hardware.IBluetoothManager;
import com.nd.adhoc.dmsdk.api.manager.hardware.ICameraManager;
import com.nd.adhoc.dmsdk.api.manager.hardware.IDeviceLockManager;
import com.nd.adhoc.dmsdk.api.manager.hardware.IMicrophoneManager;
import com.nd.adhoc.dmsdk.api.manager.hardware.IMobileDataManager;
import com.nd.adhoc.dmsdk.api.manager.hardware.ISdCardManager;
import com.nd.adhoc.dmsdk.api.manager.hardware.IUsbMamager;
import com.nd.adhoc.dmsdk.api.manager.hardware.IWifiManager;
import com.nd.adhoc.dmsdk.api.manager.key.IPhysicalKeyManager_Menu;
import com.nd.adhoc.dmsdk.demo.R;
import com.nd.adhoc.dmsdk.demo.bean.HardWareSwitchBean;
import com.nd.adhoc.dmsdk.demo.model.BaseModel;
import com.nd.adhoc.dmsdk.demo.model.IDeviceControlModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据模组层 负责对接SDK
 */
public class DeviceControlModel extends BaseModel<HardWareSwitchBean> implements IDeviceControlModel {


    private List<HardWareSwitchBean> list;

    public DeviceControlModel(Context context) {
        super(context);
    }

    @Override
    public List<HardWareSwitchBean> getList() {
        return createDeviceControlList();
    }

    @Override
    public void update(HardWareSwitchBean hardWareSwitchBean, int position) {

        if (list != null && list.size() > 0) {
            HardWareSwitchBean bean = list.get(position);
            bean.setStatus(hardWareSwitchBean.getStatus());
        }
    }

    @Override
    public void delete(HardWareSwitchBean hardWareSwitchBean) {
        if (list != null && list.size() > 0) {
            list.remove(hardWareSwitchBean);
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
    public boolean updateStatus(int position) throws Exception{
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
        IMicrophoneManager microphoneManager= (IMicrophoneManager) DeviceManagerSdk.getInstance().getManager(DeviceManagerContainer.MANAGER_HARDWARE_MICROPHONE);
        try {
            if(!microphoneManager.isOpen(context)){
                microphoneManager.open(context);
            }else{
                microphoneManager.close(context);
            }
            return true;
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean deviceUsb() {

        IUsbMamager usbMamager= (IUsbMamager) DeviceManagerSdk.getInstance().getManager(DeviceManagerContainer.MANAGER_HARDWARE_USB);
        try {
            if(!usbMamager.isOpen(context)){
                usbMamager.open(context);
            }else{
                usbMamager.close(context);
            }
            return true;
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean deviceSdCard() {

        ISdCardManager sdCardManager= (ISdCardManager) DeviceManagerSdk.getInstance().getManager(DeviceManagerContainer.MANAGER_HARDWARE_SDCARD);
        try {
            if(!sdCardManager.isOpen(context)){
                sdCardManager.open(context);
            }else{
                sdCardManager.close(context);
            }
            return true;
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean deviceLock() {

        IDeviceLockManager lockManager= (IDeviceLockManager) DeviceManagerSdk.getInstance().getManager(DeviceManagerContainer.MANAGER_HARDWARE_LOCK);
        try {
            if(!lockManager.isOpen(context)){
                lockManager.open(context);
            }else{
                lockManager.close(context);
            }
            return true;
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean deviceBluetooth() {

        IBluetoothManager bluetoothManager= (IBluetoothManager) DeviceManagerSdk.getInstance().getManager(DeviceManagerContainer.MANAGER_HARDWARE_BLUETOOTH);
        try {
            if(!bluetoothManager.isOpen(context)){
                bluetoothManager.open(context);
            }else{
                bluetoothManager.close(context);
            }
            return true;
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean deviceCamera() {
        ICameraManager cameraManager= (ICameraManager) DeviceManagerSdk.getInstance().getManager(DeviceManagerContainer.MANAGER_HARDWARE_CAMERA);
        try {
            if(!cameraManager.isOpen(context)){
                cameraManager.open(context);
            }else{
                cameraManager.close(context);
            }
            return true;
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean deviceMobileNetwork() {

        IMobileDataManager mobileDataManager= (IMobileDataManager) DeviceManagerSdk.getInstance().getManager(DeviceManagerContainer.MANAGER_HARDWARE_MOBILEDATA);
        try {
            if(!mobileDataManager.isOpen(context)){
                mobileDataManager.open(context);
            }else{
                mobileDataManager.close(context);
            }
            return true;
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean deviceWifi() {

        IWifiManager wifiManager= (IWifiManager) DeviceManagerSdk.getInstance().getManager(DeviceManagerContainer.MANAGER_HARDWARE_WIFI);
        try {
            if(!wifiManager.isOpen(context)){
                wifiManager.open(context);
            }else{
                wifiManager.close(context);
            }
            return true;
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void update(int position, boolean isSuccess) {

        if (list != null && list.size() > 0) {
            HardWareSwitchBean bean = list.get(position);
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

        IPhysicalKeyManager_Menu keyManagerMenu = (IPhysicalKeyManager_Menu) DeviceManagerSdk.getInstance().getManager(DeviceManagerContainer.MANAGER_KEY_MENU);
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

        if (list == null) {
            list = new ArrayList<HardWareSwitchBean>();
        }
        list.clear();

        IWifiManager wifiManager= (IWifiManager) DeviceManagerSdk.getInstance().getManager(DeviceManagerContainer.MANAGER_HARDWARE_WIFI);
        IMobileDataManager mobileDataManager= (IMobileDataManager) DeviceManagerSdk.getInstance().getManager(DeviceManagerContainer.MANAGER_HARDWARE_MOBILEDATA);
        IBluetoothManager bluetoothManager= (IBluetoothManager) DeviceManagerSdk.getInstance().getManager(DeviceManagerContainer.MANAGER_HARDWARE_BLUETOOTH);
        ICameraManager cameraManager= (ICameraManager) DeviceManagerSdk.getInstance().getManager(DeviceManagerContainer.MANAGER_HARDWARE_CAMERA);
        IMicrophoneManager microphoneManager= (IMicrophoneManager) DeviceManagerSdk.getInstance().getManager(DeviceManagerContainer.MANAGER_HARDWARE_MICROPHONE);
        IUsbMamager usbMamager= (IUsbMamager) DeviceManagerSdk.getInstance().getManager(DeviceManagerContainer.MANAGER_HARDWARE_USB);
        ISdCardManager sdCardManager= (ISdCardManager) DeviceManagerSdk.getInstance().getManager(DeviceManagerContainer.MANAGER_HARDWARE_SDCARD);
        IDeviceLockManager lockManager= (IDeviceLockManager) DeviceManagerSdk.getInstance().getManager(DeviceManagerContainer.MANAGER_HARDWARE_LOCK);


        /**
         * wifi模块
         */
        HardWareSwitchBean wifiBean = new HardWareSwitchBean();
        wifiBean.setName(context.getResources().getString(R.string.wifi));
        try {
            wifiBean.setStatus(wifiManager.isOpen(context) == true ? 1 : 0);
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
        }
        wifiBean.setDesiplaySave(true);
        list.add(wifiBean);

        /**
         *3G网络 -- 移动网络
         */
        HardWareSwitchBean t3gBean = new HardWareSwitchBean();
        t3gBean.setName(context.getResources().getString(R.string.t3g));
        try {
            t3gBean.setStatus(mobileDataManager.isOpen(context) == true ? 1 : 0);
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
        }
        t3gBean.setDesiplaySave(true);
        list.add(t3gBean);

        /**
         * 蓝牙
         */
        HardWareSwitchBean bluetoothBean = new HardWareSwitchBean();
        bluetoothBean.setName(context.getResources().getString(R.string.bluetooth));
        try {
            bluetoothBean.setStatus(bluetoothManager.isOpen(context) == true ? 1 : 0);
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
        }
        bluetoothBean.setDesiplaySave(true);
        list.add(bluetoothBean);


        /**
         * Camera 摄像头
         */
        HardWareSwitchBean cameraBean = new HardWareSwitchBean();
        cameraBean.setName(context.getResources().getString(R.string.camera));
        try {
            cameraBean.setStatus(cameraManager.isOpen(context) == true ? 1 : 0);
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
        }
        cameraBean.setDesiplaySave(true);
        list.add(cameraBean);


        /**
         * 麦克风
         */
        HardWareSwitchBean microphoneBean = new HardWareSwitchBean();
        microphoneBean.setName(context.getResources().getString(R.string.microphone));
        try {
            microphoneBean.setStatus(microphoneManager.isOpen(context)==true ? 1 : 0);
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
        }  ;
        microphoneBean.setDesiplaySave(true);
        list.add(microphoneBean);
        /**
         * usb
         */
        HardWareSwitchBean usbBean = new HardWareSwitchBean();
        usbBean.setName(context.getResources().getString(R.string.usb));
        usbBean.setDesiplaySave(true);
        try {
            usbBean.setStatus(usbMamager.isOpen(context) == true ? 1 : 0);
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
        }
        list.add(usbBean);


        /**
         * SD Card
         */
        HardWareSwitchBean sdCardBean = new HardWareSwitchBean();
        sdCardBean.setName(context.getResources().getString(R.string.sd_card));
        try {
            sdCardBean.setStatus(sdCardManager.isOpen(context) == true ? 1 : 0);
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
        }
        sdCardBean.setDesiplaySave(true);
        list.add(sdCardBean);
        Log.i(this.getClass().getName(), String.format("getList:%d", list.size()));


        /**
         * Lock
         */
        HardWareSwitchBean locKBean = new HardWareSwitchBean();
        locKBean.setName(context.getResources().getString(R.string.lock));
        try {
            locKBean.setStatus(lockManager.isOpen(context) == true ? 1 : 0);
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
        }
        locKBean.setDesiplaySave(true);
        list.add(locKBean);
        Log.i(this.getClass().getName(), String.format("getList:%d", list.size()));

        /**
         * HOME键执行
         */
        HardWareSwitchBean homeKeyBean = new HardWareSwitchBean();
        homeKeyBean.setName(context.getResources().getString(R.string.home));
        homeKeyBean.setStatus(0);
        homeKeyBean.setDesiplaySave(false);
        list.add(homeKeyBean);


        /**
         * back键执行
         */
        HardWareSwitchBean backKeyBean = new HardWareSwitchBean();
        backKeyBean.setName(context.getResources().getString(R.string.back));
        backKeyBean.setStatus(0);
        backKeyBean.setDesiplaySave(false);
        list.add(backKeyBean);


        /**
         * 菜单键执行
         */
        HardWareSwitchBean menuKeyBean = new HardWareSwitchBean();
        menuKeyBean.setName(context.getResources().getString(R.string.menu_key));
        menuKeyBean.setStatus(0);
        menuKeyBean.setDesiplaySave(false);
        list.add(menuKeyBean);
        Log.i(this.getClass().getName(), String.format("getList:%d", list.size()));


        /**
         * 电源键
         */
        HardWareSwitchBean powerKeyBean= new HardWareSwitchBean();
        powerKeyBean.setName(context.getResources().getString(R.string.power_key));
        powerKeyBean.setStatus(0);
        powerKeyBean.setDesiplaySave(false);
        list.add(powerKeyBean);
        Log.i(this.getClass().getName(), String.format("getList:%d", list.size()));


        /**
         * 音量键+
         */
        HardWareSwitchBean upKeyBean = new HardWareSwitchBean();
        upKeyBean.setName(context.getResources().getString(R.string.volumn_up));
        upKeyBean.setStatus(0);
        upKeyBean.setDesiplaySave(false);
        list.add(upKeyBean);


        /**
         * 音量键-
         */
        HardWareSwitchBean downKeyBean = new HardWareSwitchBean();
        downKeyBean.setName(context.getResources().getString(R.string.volumn_down));
        downKeyBean.setStatus(0);
        downKeyBean.setDesiplaySave(false);
        list.add(downKeyBean);

        Log.i(this.getClass().getName(), String.format("getList:%d", list.size()));

        /**
         * wake 设备唤醒
         */
        HardWareSwitchBean wakeKeyBeans = new HardWareSwitchBean();
        wakeKeyBeans.setName(context.getResources().getString(R.string.weak));
        wakeKeyBeans.setStatus(0);
        wakeKeyBeans.setDesiplaySave(false);
        list.add(wakeKeyBeans);

        /**
         * wake 设备休眠
         */
        HardWareSwitchBean dormancyBean = new HardWareSwitchBean();
        dormancyBean.setName(context.getResources().getString(R.string.dormancy));
        dormancyBean.setStatus(0);
        dormancyBean.setDesiplaySave(false);
        list.add(dormancyBean);

        /**
         * wake 关机
         */
        HardWareSwitchBean powerOffbean = new HardWareSwitchBean();
        powerOffbean.setName(context.getResources().getString(R.string.power_off));
        powerOffbean.setStatus(0);
        powerOffbean.setDesiplaySave(false);
        list.add(powerOffbean);


        /**
         * wake 重启
         */
        HardWareSwitchBean rebootBean = new HardWareSwitchBean();
        rebootBean.setName(context.getResources().getString(R.string.reboot));
        rebootBean.setStatus(0);
        rebootBean.setDesiplaySave(false);
        list.add(rebootBean);

        Log.i(this.getClass().getName(), String.format("getList:%d", list.size()));
        return list;
    }

    @Override
    public void release() {
        list = null;
    }

}
