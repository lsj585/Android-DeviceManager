package com.nd.adhoc.dmsdk.demo.presenter.strategy;

import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.demo.model.impl.AppListManagerModel;
import com.nd.adhoc.dmsdk.demo.view.AppManagerView;

public interface AppStrategy {

    /**
     * 执行策略
     * @param position 数组下标索引
     * @param view
     * @param model
     */
    void execute(int position, @NonNull  AppManagerView view, @NonNull AppListManagerModel model);
}
