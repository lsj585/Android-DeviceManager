package com.nd.adhoc.dmsdk.demo.view;

import com.nd.adhoc.dmsdk.demo.bean.FileInfoBean;
import com.nd.adhoc.dmsdk.demo.bean.HardWareSwitchBean;

import java.util.List;

public interface FileManagerView extends BaseView {


    void showList(List<FileInfoBean> list);

    void refresh();

    void updateView(int viewPosition);
}
