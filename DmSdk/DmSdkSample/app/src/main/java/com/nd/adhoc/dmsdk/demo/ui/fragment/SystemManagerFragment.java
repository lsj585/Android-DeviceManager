package com.nd.adhoc.dmsdk.demo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nd.adhoc.dmsdk.demo.R;
import com.nd.adhoc.dmsdk.demo.base.ITabFragment;

public class SystemManagerFragment extends Fragment implements ITabFragment {

    public static SystemManagerFragment newInstance() {
        SystemManagerFragment fragment = new SystemManagerFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.systeml_manager_fragment,null);
        return view;
    }


    @Override
    public int getTabName() {
        return R.string.system_manager;
    }

    @Override
    public int getDrawableId() {
        return R.drawable.ic_launcher_big;
    }

    @Override
    public Fragment getTabFragment() {
        return this;
    }
}
