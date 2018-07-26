package com.nd.adhoc.dmsdk.demo.presenter.strategy;

import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.demo.model.impl.DeviceControlModel;
import com.nd.adhoc.dmsdk.demo.view.DeviceControlView;

public interface DeviceStrategy {
    /**
     * 执行策略
     * @param position 数组下标索引
     * @param view
     * @param model
     */
    void execute(int position, @NonNull DeviceControlView view, @NonNull DeviceControlModel model);
}
