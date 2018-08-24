package com.nd.adhoc.dmsdk.demo.model.manager.factory;

import com.nd.adhoc.dmsdk.demo.model.manager.KManager_Back;
import com.nd.adhoc.dmsdk.demo.model.manager.KManager_Home;
import com.nd.adhoc.dmsdk.demo.model.manager.KManager_Power;
import com.nd.adhoc.dmsdk.demo.model.manager.KManager_VDown;
import com.nd.adhoc.dmsdk.demo.model.manager.KManager_VUp;
import com.nd.adhoc.dmsdk.demo.model.manager.KManager_Menu;
import com.nd.adhoc.dmsdk.demo.model.manager.Manager_Bluetooth;
import com.nd.adhoc.dmsdk.demo.model.manager.Manager_Camera;
import com.nd.adhoc.dmsdk.demo.model.manager.Manager_DeviceLock;
import com.nd.adhoc.dmsdk.demo.model.manager.Manager_Microphone;
import com.nd.adhoc.dmsdk.demo.model.manager.Manager_MobileData;
import com.nd.adhoc.dmsdk.demo.model.manager.Manager_SdCard;
import com.nd.adhoc.dmsdk.demo.model.manager.Manager_Usb;
import com.nd.adhoc.dmsdk.demo.model.manager.Manager_Wifi;
import com.nd.adhoc.dmsdk.demo.model.manager.base.IDeviceManager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author richsjeson
 * 设备操作工厂
 */

public class DeviceManagerFactory {


    public static final int DEVICE_MANAGER_WIFI=0;
    public static final int DEVICE_MANAGER_BLUETOOTH=1;
    public static final int DEVICE_MANAGER_MOBILE=2;
    public static final int DEVICE_MANAGER_CAMERA=3;
    public static final int DEVICE_MANAGER_MICROPHONE=4;
    public static final int DEVICE_MANAGER_USB=5;
    public static final int DEVICE_MANAGER_SDCARD=6;
    public static final int DEVICE_MANAGER_DEVICE_LOCK=7;
    public static final int DEVICE_MANAGER_EXEC_KEY_HOME=8;
    public static final int DEVICE_MANAGER_EXEC_KEY_BACK=9;
    public static final int DEVICE_MANAGER_EXEC_KEY_MENU=10;
    public static final int DEVICE_MANAGER_EXEC_KEY_POWER=11;
    public static final int DEVICE_MANAGER_EXEC_KEY_VOLUMN_UP=12;
    public static final int DEVICE_MANAGER_EXEC_KEY_VOLUMN_DOWN=13;

    private Map<Integer,IDeviceManager> mManagers;


    public DeviceManagerFactory(){

        mManagers=new ConcurrentHashMap<>();
        mManagers.put(DEVICE_MANAGER_WIFI,new Manager_Wifi());
        mManagers.put(DEVICE_MANAGER_MOBILE,new Manager_MobileData());
        mManagers.put(DEVICE_MANAGER_BLUETOOTH,new Manager_Bluetooth());
        mManagers.put(DEVICE_MANAGER_CAMERA,new Manager_Camera());
        mManagers.put(DEVICE_MANAGER_MICROPHONE,new Manager_Microphone());
        mManagers.put(DEVICE_MANAGER_USB,new Manager_Usb());
        mManagers.put(DEVICE_MANAGER_SDCARD,new Manager_SdCard());
        mManagers.put(DEVICE_MANAGER_DEVICE_LOCK,new Manager_DeviceLock());
        mManagers.put(DEVICE_MANAGER_EXEC_KEY_HOME,new KManager_Home());
        mManagers.put(DEVICE_MANAGER_EXEC_KEY_BACK,new KManager_Back());
        mManagers.put(DEVICE_MANAGER_EXEC_KEY_MENU,new KManager_Menu());
        mManagers.put(DEVICE_MANAGER_EXEC_KEY_VOLUMN_UP,new KManager_VUp());
        mManagers.put(DEVICE_MANAGER_EXEC_KEY_VOLUMN_DOWN,new KManager_VDown());
        mManagers.put(DEVICE_MANAGER_EXEC_KEY_POWER,new KManager_Power());
    }

    public IDeviceManager getManager(int position){
        return mManagers.get(position);
    }

    public int getManagerCount(){
        return mManagers.size();
    }

    public void release() {
        mManagers.clear();
        mManagers=null;
    }
}
