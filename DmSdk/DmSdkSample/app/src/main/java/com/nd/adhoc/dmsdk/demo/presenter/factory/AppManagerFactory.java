package com.nd.adhoc.dmsdk.demo.presenter.factory;
import com.nd.adhoc.dmsdk.demo.presenter.strategy.AppStrategy;
import com.nd.adhoc.dmsdk.demo.presenter.annotation.AppStategyKey;
import com.nd.sdp.android.serviceloader.AnnotationServiceLoader;
import com.nd.sdp.android.serviceloader.ServiceLoader;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 应用管理类
 */
public class AppManagerFactory {

    /**
     * 应用卸载
     */
    public static final int STRATEGY_UNINSTALL = 0;
    public static final int STRATEGY_FORBIDUNINSTALL = 1;
    public static final int STRATEGY_WIPEDATA = 2;
    public static final int STRATEGY_FORBRID_STARTAPP = 3;
    public static final int STRATEGY_ALLOW_STOPAPP = 4;
    public static final int STRATEGY_START = 5;
    public static final int STRATEGY_STOP = 6;
    public static final int STRATEGY_FORBRID_STOPAPP = 7;
    public static final int STRATEGY_ALLOW_START = 8;
    public static final int STRATEGY_ALLOW_WIPEDATA = 9;
    public static final int STRATEGY_ALLOW_UNINSTALL = 10;
    public static final int STRATEGY_GET_APPLIST = 11;

    private Map<Integer,AppStrategy>  mStrategys;


    public AppManagerFactory() {

        mStrategys=new ConcurrentHashMap<>();

        ServiceLoader serviceLoader = AnnotationServiceLoader.load(AppStrategy.class);
        Iterator<AppStrategy> iterator = serviceLoader.iterator();
        while(iterator.hasNext()){
            AppStrategy deviceManager=iterator.next();
            AppStategyKey apiImpl=deviceManager.getClass().getAnnotation(AppStategyKey.class);
            if(apiImpl!=null){
                mStrategys.put(apiImpl.value(),deviceManager);
            }
        }
    }


    /**
     * 产品目标对象
     *
     * @param position
     * @return
     */
    public AppStrategy getStrategy(int position) {

        return mStrategys.get(position);
    }

    public void destory(){
        mStrategys.clear();
        mStrategys=null;
    }

}
