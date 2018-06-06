package com.nd.adhoc.dmsdk.demo.strategy;
import com.nd.adhoc.dmsdk.demo.strategy.device.DeviceOperationStrategy;
import com.nd.adhoc.dmsdk.demo.strategy.device.GetDeviceOperationListStrategy;

/**
 * 应用管理类
 */
public class DeviceManagerFactory {

    /**
     * 卸载应用-策略
     */
    private DeviceStrategy mGetDeviceListStrategy ;
    /**
     * 阻止卸载应用-策略
     */
    private DeviceStrategy mOperationStrategy;

    /**
     * 应用卸载
     */
    public static final int STRATEGY_DEVICE_GETLIST=0;
    public static final int STRATEGY_DEVICE_OPERATION=1;

    /**
     * 产品目标对象
     *
     * @param position
     * @return
     */
    public DeviceStrategy getStrategy(int position) {

        switch (position) {
            case STRATEGY_DEVICE_GETLIST:
                if (mGetDeviceListStrategy == null) {
                    return mGetDeviceListStrategy = new GetDeviceOperationListStrategy();
                }
                return mGetDeviceListStrategy;
            case STRATEGY_DEVICE_OPERATION:
                if (mOperationStrategy == null) {
                    return mOperationStrategy = new DeviceOperationStrategy();
                }
                return mOperationStrategy;
            default:
                if (mGetDeviceListStrategy == null) {
                    return mGetDeviceListStrategy = new GetDeviceOperationListStrategy();
                }
                return mGetDeviceListStrategy;
        }
    }
}
