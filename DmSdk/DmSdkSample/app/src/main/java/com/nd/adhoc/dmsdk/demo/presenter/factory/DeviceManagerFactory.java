package com.nd.adhoc.dmsdk.demo.presenter.factory;

import com.nd.adhoc.dmsdk.demo.presenter.strategy.DeviceStrategy;
import com.nd.adhoc.dmsdk.demo.presenter.annotation.DeviceStategyKey;
import com.nd.sdp.android.serviceloader.AnnotationServiceLoader;
import com.nd.sdp.android.serviceloader.ServiceLoader;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 应用管理类
 */
public class DeviceManagerFactory {

    /**
     * 应用卸载
     */
    public static final int STRATEGY_DEVICE_GETLIST=0;
    public static final int STRATEGY_DEVICE_OPERATION=1;

    private Map<Integer,DeviceStrategy> mStrategys;

    public DeviceManagerFactory(){

        mStrategys=new ConcurrentHashMap<>();

        ServiceLoader serviceLoader = AnnotationServiceLoader.load(DeviceStrategy.class);
        Iterator<DeviceStrategy> iterator = serviceLoader.iterator();
        while(iterator.hasNext()){
            DeviceStrategy deviceStrategy=iterator.next();
            DeviceStategyKey apiImpl=deviceStrategy.getClass().getAnnotation(DeviceStategyKey.class);
            if(apiImpl!=null){
                mStrategys.put(apiImpl.value(),deviceStrategy);
            }
        }
    }

    /**
     * 产品目标对象
     *
     * @param position
     * @return
     */
    public DeviceStrategy getStrategy(int position) {
        return mStrategys.get(position);
    }

    public void destroy(){
        mStrategys.clear();
        mStrategys=null;
    }
}
