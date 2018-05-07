package com.nd.adhoc.dmsdk.demo.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.nd.adhoc.dmsdk.demo.R;
import com.nd.adhoc.dmsdk.demo.ui.adapter.HardwareAdapter;
import com.nd.adhoc.dmsdk.demo.base.ITabFragment;
import com.nd.adhoc.dmsdk.demo.bean.HardWareSwitchBean;
import com.nd.adhoc.dmsdk.demo.model.impl.DeviceControlModel;
import com.nd.adhoc.dmsdk.demo.presenter.impl.DeviceControlPresenter;
import com.nd.adhoc.dmsdk.demo.view.DeviceControlView;

import java.util.List;

/**
 * 设备控制 管理
 */
public class DeviceControlManagerFragment extends Fragment implements ITabFragment,DeviceControlView,HardwareAdapter.OnItemClickListener{

    private DeviceControlPresenter presenter;

    private DeviceControlModel model;

    private RecyclerView recycleviewControl;

    private HardwareAdapter hardwareAdapter;

    private StaggeredGridLayoutManager mManager;

    public static DeviceControlManagerFragment newInstance() {

        DeviceControlManagerFragment fragment = new DeviceControlManagerFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.control_manager_fragment,null);
        Log.i(this.getClass().getName(),"onCreateView");
        this.presenter=new DeviceControlPresenter(getActivity(),this);
        this.model=new DeviceControlModel(getActivity());
        this.presenter.setModle(this.model);
        recycleviewControl=view.findViewById(R.id.recycleview_control);
        mManager=new StaggeredGridLayoutManager(2,  StaggeredGridLayoutManager.VERTICAL);
        hardwareAdapter=new HardwareAdapter(getActivity());
        recycleviewControl.setAdapter(hardwareAdapter);
        recycleviewControl.setLayoutManager(mManager);
        hardwareAdapter.setOnItemClickListener(this);
        initData();
        return view;
    }

    private void initData(){
        this.presenter.getDeviceControlList();
    }


    @Override
    public int getTabName() {
        return R.string.device_control_manager;
    }

    @Override
    public int getDrawableId() {
        return R.drawable.ic_launcher_big;
    }

    @Override
    public Fragment getTabFragment() {
        return this;
    }

    @Override
    public void showList(List<HardWareSwitchBean> list) {

        if(hardwareAdapter != null){
            hardwareAdapter.setData(list);
            hardwareAdapter.notifyDataSetChanged();
            Log.i(this.getClass().getName(),String.format("showList:%d",hardwareAdapter.getItemCount()));
        }
    }

    @Override
    public void updateView(int position) {
        if(hardwareAdapter != null){
            hardwareAdapter.notifyItemChanged(position);
            Log.i(this.getClass().getName(),String.format("showList:%d",hardwareAdapter.getItemCount()));
        }
    }

    @Override
    public void jumpHome() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        getActivity().startActivity(intent);
    }

    @Override
    public void onItemClick(View view, int position) {
        presenter.onClick(position);
    }

}
