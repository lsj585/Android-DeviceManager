package com.nd.adhoc.dmsdk.demo.ui.fragment.app;

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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.nd.adhoc.dmsdk.demo.R;
import com.nd.adhoc.dmsdk.demo.base.ITabFragment;
import com.nd.adhoc.dmsdk.demo.bean.FileInfoBean;
import com.nd.adhoc.dmsdk.demo.model.impl.FileManagerModel;
import com.nd.adhoc.dmsdk.demo.presenter.impl.FileManagerPresenter;
import com.nd.adhoc.dmsdk.demo.ui.adapter.FileManagerAdapter;
import com.nd.adhoc.dmsdk.demo.ui.fragment.AppManagerFragment;
import com.nd.adhoc.dmsdk.demo.view.FileManagerView;

import java.util.List;

/**
 * 从sd卡上获取未安装的apk文件
 */
public class FileManagerFragment extends Fragment implements ITabFragment,FileManagerView, FileManagerAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private FileManagerAdapter managerAdapter;
    private FileManagerPresenter presenter;
    private FileManagerModel model;
    private LinearLayoutManager mManager;
    private MaterialDialog dialog;

    public static FileManagerFragment newInstance() {
        FileManagerFragment fragment = new FileManagerFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.file_manager_fragment,null);
        recyclerView=view.findViewById(R.id.recycleview_file);
        mManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        managerAdapter=new FileManagerAdapter(getActivity());
        managerAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(managerAdapter);
        recyclerView.setLayoutManager(mManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        presenter=new FileManagerPresenter(getContext(),this);
        model=new FileManagerModel(getActivity());
        presenter.setModle(model);
        initData();
        return view;
    }


    private void initData(){
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);
        }else {
            presenter.getFileAppList();
        }
    }

    @Override
    public int getTabName() {
        return R.string.file_manager;
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
    public void showList(List<FileInfoBean> list) {
        if(getUserVisibleHint()) {
            if (managerAdapter != null) {
                managerAdapter.setData(list);
                managerAdapter.notifyDataSetChanged();
                Log.i(this.getClass().getName(), String.format("showList:%d", managerAdapter.getItemCount()));
            }
        }
    }

    @Override
    public void refresh() {
        if(getUserVisibleHint()) {

        }
    }

    @Override
    public void updateView(int viewPosition) {
        if(getUserVisibleHint()) {
            managerAdapter.notifyItemChanged(viewPosition);
            managerAdapter.getList().remove(viewPosition);
            managerAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                presenter.getFileAppList();
            } else {
                // Permission Denied
                Toast.makeText(getActivity(), "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onItemClick(View view, final int position) {
        if(dialog==null){
            dialog= new MaterialDialog.Builder(getActivity())
                    .title(R.string.title)
                    .items(R.array.file_device_operation)
                    .itemsCallback(new MaterialDialog.ListCallback() {

                        @Override
                        public void onSelection(MaterialDialog dialog, View itemView, int pos, CharSequence text) {
                            presenter.onClick(pos,position);
                        }
                    })
                    .positiveText(R.string.choose).build();
        }
        if(dialog.isShowing()){
            dialog.dismiss();
        }else{
            dialog.show();
        }
    }
}
