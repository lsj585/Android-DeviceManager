package com.nd.adhoc.dmsdk.demo.model.impl;

import android.app.Instrumentation;
import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;

import com.nd.adhoc.dmsdk.api.knox.manager.DeviceApiManager;
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

    private DeviceApiManager mDeviceManager;

    public DeviceControlModel(Context context) {
        super(context);
        mDeviceManager = new DeviceApiManager(context);
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
    public boolean updateStatus(int position) {
        switch (position) {
            case 0:
                return mDeviceManager.deviceWifi();
            case 1:
                return mDeviceManager.deviceMobileNetwork();
            case 2:
                return mDeviceManager.deviceBluetooth();
            case 3:
                return mDeviceManager.deviceCamera();
            case 4:
                return mDeviceManager.deviceMicrophone();
            case 5:
                return mDeviceManager.deviceUsb();
            case 6:
                return mDeviceManager.deviceSdCard();
            case 7:
                return mDeviceManager.deviceLock();
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
            default:
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
    public boolean execMenuKey() {
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
        /**
         * wifi模块
         */
        HardWareSwitchBean wifiBean = new HardWareSwitchBean();
        wifiBean.setName(context.getResources().getString(R.string.wifi));
        wifiBean.setStatus(mDeviceManager.isOpenWifi() == true ? 1 : 0);
        wifiBean.setId(1);
        list.add(wifiBean);

        /**
         *3G网络 -- 移动网络
         */
        HardWareSwitchBean t3gBean = new HardWareSwitchBean();
        t3gBean.setName(context.getResources().getString(R.string.t3g));
        t3gBean.setStatus(mDeviceManager.isOpenNetwork() == true ? 1 : 0);
        t3gBean.setId(2);
        list.add(t3gBean);

        /**
         * 蓝牙
         */
        HardWareSwitchBean bluetoothBean = new HardWareSwitchBean();
        bluetoothBean.setName(context.getResources().getString(R.string.bluetooth));
        bluetoothBean.setStatus(mDeviceManager.isOpenBluetooth() == true ? 1 : 0);
        bluetoothBean.setId(3);
        list.add(bluetoothBean);


        /**
         * Camera 摄像头
         */
        HardWareSwitchBean cameraBean = new HardWareSwitchBean();
        cameraBean.setName(context.getResources().getString(R.string.camera));
        cameraBean.setStatus(mDeviceManager.isOpenCamrea() == true ? 1 : 0);
        cameraBean.setId(4);
        list.add(cameraBean);


        /**
         * 麦克风
         */
        HardWareSwitchBean microphoneBean = new HardWareSwitchBean();
        microphoneBean.setName(context.getResources().getString(R.string.microphone));
        microphoneBean.setStatus(mDeviceManager.isOpenMicrophone() == true ? 1 : 0);
        microphoneBean.setId(5);
        list.add(microphoneBean);
        /**
         * usb
         */
        HardWareSwitchBean usbBean = new HardWareSwitchBean();
        usbBean.setName(context.getResources().getString(R.string.usb));
        usbBean.setId(6);
        usbBean.setStatus(mDeviceManager.isOpenUsb() == true ? 1 : 0);
        list.add(usbBean);


        /**
         * SD Card
         */
        HardWareSwitchBean sdCardBean = new HardWareSwitchBean();
        sdCardBean.setName(context.getResources().getString(R.string.sd_card));
        sdCardBean.setStatus(mDeviceManager.isOpenUsb() == true ? 1 : 0);
        sdCardBean.setId(7);
        list.add(sdCardBean);
        Log.i(this.getClass().getName(), String.format("getList:%d", list.size()));


        /**
         * Lock
         */
        HardWareSwitchBean locKBean = new HardWareSwitchBean();
        locKBean.setName(context.getResources().getString(R.string.lock));
        locKBean.setStatus(mDeviceManager.isLock() == true ? 1 : 0);
        locKBean.setId(8);
        list.add(locKBean);
        Log.i(this.getClass().getName(), String.format("getList:%d", list.size()));

        /**
         * HOME键执行
         */
        HardWareSwitchBean homeKeyBean = new HardWareSwitchBean();
        homeKeyBean.setName(context.getResources().getString(R.string.home));
        homeKeyBean.setStatus(0);
        homeKeyBean.setId(9);
        list.add(homeKeyBean);


        /**
         * back键执行
         */
        HardWareSwitchBean backKeyBean = new HardWareSwitchBean();
        backKeyBean.setName(context.getResources().getString(R.string.back));
        backKeyBean.setStatus(0);
        backKeyBean.setId(10);
        list.add(backKeyBean);


        /**
         * 菜单键执行
         */
        HardWareSwitchBean menuKeyBean = new HardWareSwitchBean();
        menuKeyBean.setName(context.getResources().getString(R.string.menu_key));
        menuKeyBean.setStatus(0);
        menuKeyBean.setId(11);
        list.add(menuKeyBean);
        Log.i(this.getClass().getName(), String.format("getList:%d", list.size()));


        /**
         * 电源键
         */
        HardWareSwitchBean powerKeyBean= new HardWareSwitchBean();
        powerKeyBean.setName(context.getResources().getString(R.string.power_key));
        powerKeyBean.setStatus(0);
        powerKeyBean.setId(12);
        list.add(powerKeyBean);
        Log.i(this.getClass().getName(), String.format("getList:%d", list.size()));


        /**
         * 音量键+
         */
        HardWareSwitchBean upKeyBean = new HardWareSwitchBean();
        upKeyBean.setName(context.getResources().getString(R.string.volumn_up));
        upKeyBean.setStatus(0);
        upKeyBean.setId(13);
        list.add(upKeyBean);


        /**
         * 音量键-
         */
        HardWareSwitchBean downKeyBean = new HardWareSwitchBean();
        downKeyBean.setName(context.getResources().getString(R.string.volumn_down));
        downKeyBean.setStatus(0);
        downKeyBean.setId(14);
        list.add(downKeyBean);

        Log.i(this.getClass().getName(), String.format("getList:%d", list.size()));
        return list;
    }

    @Override
    public void release() {
        mDeviceManager.release();
        list = null;
    }

}
