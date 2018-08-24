package com.nd.adhoc.dmsdk.demo.presenter.factory;
import com.nd.adhoc.dmsdk.demo.presenter.annotation.FileControlStategyKey;
import com.nd.adhoc.dmsdk.demo.presenter.strategy.FileControlStrategy;
import com.nd.adhoc.dmsdk.demo.presenter.strategy.file.FileStrategy_GetFileList;
import com.nd.adhoc.dmsdk.demo.presenter.strategy.file.FileStrategy_InstallApk;
import com.nd.sdp.android.serviceloader.AnnotationServiceLoader;
import com.nd.sdp.android.serviceloader.ServiceLoader;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 应用管理类
 */
public class ApkFireFactory {

    /**
     * 应用卸载
     */
    public static final int STRATEGY_FILE_GETLIST = 0;
    public static final int STRATEGY_FILE_INSTALLAPK = 1;


    private Map<Integer,FileControlStrategy> mStrategys;


    public ApkFireFactory(){
        mStrategys=new ConcurrentHashMap<>();
//
//        ServiceLoader serviceLoader = AnnotationServiceLoader.load(FileControlStrategy.class);
//        Iterator<FileControlStrategy> iterator = serviceLoader.iterator();
//        while(iterator.hasNext()){
//            FileControlStrategy deviceManager=iterator.next();
//            FileControlStategyKey apiImpl=deviceManager.getClass().getAnnotation(FileControlStategyKey.class);
//            if(apiImpl!=null){
//                mStrategys.put(apiImpl.value(),deviceManager);
//            }
//        }

        mStrategys.put(STRATEGY_FILE_GETLIST,new FileStrategy_GetFileList());
        mStrategys.put(STRATEGY_FILE_INSTALLAPK,new FileStrategy_InstallApk());

    }

    /**
     * 产品目标对象
     *
     * @param position
     * @return
     */
    public FileControlStrategy getStrategy(int position) {
        return mStrategys.get(position);
    }

    public void destory(){
        mStrategys.clear();
        mStrategys=null;
    }
}
