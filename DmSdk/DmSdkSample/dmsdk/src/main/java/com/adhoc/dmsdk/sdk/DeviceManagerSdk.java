package com.adhoc.dmsdk.sdk;

import android.content.Context;

import com.nd.adhoc.dmsdk.api.IDeviceManager;
import com.nd.adhoc.dmsdk.api.manager.security.ISecurityManager_AllowUnInstall;
import com.nd.adhoc.dmsdk.api.manager.security.ISecurityManager_AllowWipeData;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SDK入口类 管理对象池
 */
public class DeviceManagerSdk {


    private static DeviceManagerSdk instance;

    private Map<String,RealObject> maps=new ConcurrentHashMap<String,RealObject>();


    public synchronized  static DeviceManagerSdk getInstance(){

        if(instance==null){
            instance=new DeviceManagerSdk();
        }
        return instance;
    }


    public void init(Context context){
        //初始化所有Manager管理器
        //put进去的内容，是注解的名称@Service("");
        maps.put("allowWipeData",new RealObject(ISecurityManager_AllowWipeData.class));
        maps.put("allowUninstall", new RealObject(ISecurityManager_AllowUnInstall.class));
    }


    /**
     * 从对象池中获取管理器
     * @param manager
     * @return
     */
    public IDeviceManager getManager(String manager){

       RealObject<IDeviceManager> interf= maps.get(manager);
       //TODO ZYB 通过注解标识找到对应的manager类，以保证多个provider产品下的api对应的实现类被调起
       Class<IDeviceManager> instance=interf.getDeviceManager();
       if(instance== null){

           //实例化对象

       }
       //TODO 该处要通过注解返回具体对象实例
       return null;
    }

    /**
     * 销毁管理器实例
     * @param manager
     */
    public void destoryManager(RealObject<IDeviceManager> manager){
        if(manager.getDeviceManager() != null){
            Class<IDeviceManager> iDeviceManager=manager.getDeviceManager();
            //使用反射方法，执行IDeviceManager中的release方法回收对象

        }
        maps.remove(manager);
    }


    /**
     * 内部类创建一个引用对象
     */
    class RealObject<T>{

        private Class dManager;

        public RealObject(Class<IDeviceManager> manager){
            this.dManager=manager;
        }

        public Class<IDeviceManager> getDeviceManager(){
            return dManager;
        }

    }


}
