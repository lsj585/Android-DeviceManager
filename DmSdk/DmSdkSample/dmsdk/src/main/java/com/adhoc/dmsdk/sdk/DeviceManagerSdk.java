package com.adhoc.dmsdk.sdk;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.api.IDeviceManager;
import com.nd.adhoc.dmsdk.api.manager.security.ISecurityManager_AllowUnInstall;
import com.nd.adhoc.dmsdk.api.manager.security.ISecurityManager_AllowWipeData;
import com.nd.adhoc.dmsdk.revicer.AdminReciver;

import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SDK入口类 管理对象池
 */
public class DeviceManagerSdk {


    private static DeviceManagerSdk instance;

    private Map<String, RealObject> maps = new ConcurrentHashMap();

    private DevicePolicyManager mDevicePolicyManager;

    private ComponentName mComponetName;


    public synchronized static DeviceManagerSdk getInstance() {

        if (instance == null) {
            instance = new DeviceManagerSdk();
        }
        return instance;
    }


    public void init(@NonNull  Context context) {
        //初始化所有Manager管理器
        //put进去的内容，是注解的名称@Service("");
        maps.put("allowWipeData", new RealObject(ISecurityManager_AllowWipeData.class));
        maps.put("allowUninstall", new RealObject(ISecurityManager_AllowUnInstall.class));

        mDevicePolicyManager= (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);

        mComponetName=new ComponentName(context,AdminReciver.class);
    }


    /**
     * 从对象池中获取管理器
     *
     * @param manager
     * @return
     */
    public IDeviceManager getManager(String manager) {
        IDeviceManager dManager=null;
        RealObject<IDeviceManager> realObject = maps.get(manager);
        //TODO ZYB 通过注解标识找到对应的manager类，以保证多个provider产品下的api对应的实现类被调起
        Class<IDeviceManager> instance = realObject.getDeviceManager();
        //TODO 该处要通过注解返回具体对象实例
        ServiceLoader serviceLoader = ServiceLoader.load(instance);
        Iterator<IDeviceManager> iterator = serviceLoader.iterator();
        if(iterator.hasNext()){
            dManager=iterator.next();
        }
        return dManager;
    }

    /**
     * 销毁管理器实例
     *
     * @param
     */
    public void destoryManager(Context context, RealObject<IDeviceManager> realObject, IDeviceManager manager) {
        if (manager != null) {
            manager.release(context);
        }
        maps.remove(realObject);
    }


    /**
     * 内部类创建一个引用对象
     */
    class RealObject<T> {

        private Class dManager;

        public RealObject(Class<IDeviceManager> manager) {
            this.dManager = manager;
        }

        public Class<IDeviceManager> getDeviceManager() {
            return dManager;
        }

    }


}
