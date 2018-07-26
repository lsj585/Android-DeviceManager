package com.adhoc.dmsdk.sdk;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.license.ILicense_DeActive;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.exception.DeviceManagerUnsupportException;
import com.nd.adhoc.dmsdk.api.license.ILicense_Active;
import com.nd.adhoc.dmsdk.revicer.AdminReciver;
import com.nd.sdp.android.serviceloader.AnnotationServiceLoader;
import com.nd.sdp.android.serviceloader.ServiceLoader;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SDK入口类 管理对象池
 */
public class DeviceManagerSdk {


    private static DeviceManagerSdk instance;

    private Map<Class, IDmSdkApi> maps = new ConcurrentHashMap();

    private DevicePolicyManager mDevicePolicyManager;

    private ComponentName mComponetName;

    private DeviceManagerSdk() {
    }


    public synchronized static DeviceManagerSdk getInstance() {

        if (instance == null) {
            instance = new DeviceManagerSdk();
        }
        return instance;
    }


    public void registerSDK(@NonNull Context context) {
        initManager();
        mDevicePolicyManager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        mComponetName = new ComponentName(context, AdminReciver.class);
        //sdk初始化时，注入DevicePolicyManager和componentName
        DeviceManagerContainer.getInstance().init(mDevicePolicyManager, mComponetName);
    }


    private void initManager() {
        ServiceLoader serviceLoader = AnnotationServiceLoader.load(IDmSdkApi.class);
        Iterator<IDmSdkApi> iterator = serviceLoader.iterator();
        while(iterator.hasNext()){
            IDmSdkApi deviceManager=iterator.next();
            ApiImpl apiImpl=deviceManager.getClass().getAnnotation(ApiImpl.class);
            if(apiImpl!=null){
                maps.put(apiImpl.value(),deviceManager);
            }
        }
        Log.i(this.getClass().getName(),"maps:"+maps.size());
    }

    /**
     * 从对象池中获取管理器
     *
     * @param manager
     * @return
     */
    public IDmSdkApi getApi(Class manager) throws DeviceManagerUnsupportException {
        IDmSdkApi dmSdkApi= maps.get(manager);
        if(dmSdkApi==null){
            throw new DeviceManagerUnsupportException();
        }
        return dmSdkApi;
    }

    /**
     * 销毁管理器实例
     *
     * @param
     */
    public void destoryManager(Context context,Class manager) {
        try {
            IDmSdkApi dmSdkApi=getApi(manager);
            maps.remove(manager);
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
        }

    }

    /**
     * 释放
     */
    public void release() {
    }

    public boolean isResgisterSDK() {
        if (maps.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 激活license 设备管理器
     *
     * @param context
     */
    public void registerLicense(@NonNull Context context) {

        ILicense_Active licenseManagerActive = null;
        try {
            licenseManagerActive = (ILicense_Active) getApi(ILicense_Active.class);
        } catch (DeviceManagerUnsupportException | UnsupportedOperationException e) {
            e.printStackTrace();
        }
        if (licenseManagerActive == null) {
            return;
        }
        try {
            licenseManagerActive.active(context);
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
        }

    }

    /**
     * 取消激活设备管理器
     */
    public void unRegisterLicense() {

        ILicense_DeActive licenseManagerActive = null;
        try {
            licenseManagerActive = (ILicense_DeActive) getApi(ILicense_DeActive.class);
        } catch (DeviceManagerUnsupportException | UnsupportedOperationException e) {
            e.printStackTrace();
        }
        if (licenseManagerActive == null) {
            return;
        }
        try {
            licenseManagerActive.deActive();
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
        }
    }
}
