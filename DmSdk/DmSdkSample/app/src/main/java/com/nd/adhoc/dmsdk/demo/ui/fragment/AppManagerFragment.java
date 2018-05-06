package com.nd.adhoc.dmsdk.demo.ui.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.nd.adhoc.dmsdk.demo.R;
import com.nd.adhoc.dmsdk.demo.base.ITabFragment;
import com.nd.adhoc.dmsdk.demo.bean.ApplicationInfoBean;
import com.nd.adhoc.dmsdk.demo.bean.FileInfoBean;
import com.nd.adhoc.dmsdk.demo.model.impl.AppListManagerModel;
import com.nd.adhoc.dmsdk.demo.model.impl.FileManagerModel;
import com.nd.adhoc.dmsdk.demo.presenter.impl.AppListManagerPresenter;
import com.nd.adhoc.dmsdk.demo.presenter.impl.FileManagerPresenter;
import com.nd.adhoc.dmsdk.demo.ui.adapter.AppListManagerAdapter;
import com.nd.adhoc.dmsdk.demo.ui.adapter.FileManagerAdapter;
import com.nd.adhoc.dmsdk.demo.view.AppManagerView;
import com.nd.adhoc.dmsdk.demo.view.FileManagerView;

import java.util.List;

/**
 * app 管理界面
 */
public class AppManagerFragment extends Fragment implements ITabFragment, AppManagerView, AppListManagerAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private AppListManagerAdapter managerAdapter;
    private AppListManagerPresenter presenter;
    private AppListManagerModel model;
    private LinearLayoutManager mManager;
    private MaterialDialog dialog;

    public static AppManagerFragment newInstance() {
        AppManagerFragment fragment = new AppManagerFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.app_manager_fragment, null);
        recyclerView = view.findViewById(R.id.recycleview_app);
        mManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        managerAdapter = new AppListManagerAdapter(getActivity());
        managerAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(managerAdapter);
        recyclerView.setLayoutManager(mManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        presenter = new AppListManagerPresenter(getContext(), this);
        model = new AppListManagerModel(getActivity());
        presenter.setModle(model);
        initData();
        return view;
    }


    private void initData() {
        presenter.getApplist();
    }

    @Override
    public int getTabName() {
        return R.string.app_manager;
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
    public void showList(List<ApplicationInfoBean> list) {
        if (managerAdapter != null) {
            managerAdapter.setData(list);
            managerAdapter.notifyDataSetChanged();
            Log.i(this.getClass().getName(), String.format("showList:%d", managerAdapter.getItemCount()));
        }
    }

    @Override
    public void updateView(int viewPosition) {
        managerAdapter.notifyItemChanged(viewPosition);
    }

    @Override
    public void removeUpdate(int viewPosition) {
        managerAdapter.getList().remove(viewPosition);
        managerAdapter.notifyDataSetChanged();
    }


    @Override
    public void onItemClick(View view, final int position) {
        if (dialog == null) {
            dialog = new MaterialDialog.Builder(getActivity())
                    .title(R.string.title)
                    .items(R.array.app_device_operation)
                    .itemsCallback(new MaterialDialog.ListCallback() {

                        @Override
                        public void onSelection(MaterialDialog dialog, View itemView, int pos, CharSequence text) {
                            presenter.onClick(pos, position);
                        }
                    })
                    .positiveText(R.string.choose).build();
        }
        if (dialog.isShowing()) {
            dialog.dismiss();
        } else {
            dialog.show();
        }
    }
}
