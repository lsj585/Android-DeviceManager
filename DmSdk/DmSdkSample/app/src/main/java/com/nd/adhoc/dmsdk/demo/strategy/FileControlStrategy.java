package com.nd.adhoc.dmsdk.demo.strategy;

import android.support.annotation.NonNull;
import com.nd.adhoc.dmsdk.demo.model.impl.FileManagerModel;
import com.nd.adhoc.dmsdk.demo.view.FileManagerView;

public interface FileControlStrategy {
    /**
     * 执行策略
     * @param position 数组下标索引
     * @param view
     * @param model
     */
    void execute(int position, @NonNull FileManagerView view, @NonNull FileManagerModel model);
}
