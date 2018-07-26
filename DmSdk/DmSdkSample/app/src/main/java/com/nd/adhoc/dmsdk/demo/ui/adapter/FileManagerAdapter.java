package com.nd.adhoc.dmsdk.demo.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.Formatter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nd.adhoc.dmsdk.demo.R;
import com.nd.adhoc.dmsdk.demo.bean.FileInfoBean;
import com.nd.adhoc.dmsdk.demo.ui.viewholder.AppItemView;
import com.nd.adhoc.dmsdk.demo.ui.viewholder.FileItemView;

import java.util.ArrayList;
import java.util.List;

public class FileManagerAdapter extends RecyclerView.Adapter<FileManagerAdapter.FileInfoHolder> {

    private Context mContext;

    private List<FileInfoBean> mList;

    private OnItemClickListener onItemClickListener;

    public FileManagerAdapter(Context context) {
        this.mContext = context;
        mList = new ArrayList<FileInfoBean>();
    }

    @NonNull
    @Override
    public FileManagerAdapter.FileInfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FileInfoHolder(LayoutInflater.from(this.mContext).inflate(R.layout.list_item_file, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FileManagerAdapter.FileInfoHolder holder, final int position) {
        Log.i(this.getClass().getName(), String.format("Get list=%d", mList.size()));
        holder.getFileItemView().setView(mContext,mList,position);
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(itemClickListener);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    static class FileInfoHolder extends RecyclerView.ViewHolder {

        private FileItemView mFileItemView;

        public FileInfoHolder(View itemView) {
            super(itemView);
            this.mFileItemView=new FileItemView(itemView);
        }

        public FileItemView getFileItemView(){

            return mFileItemView;
        }
    }

    public void setData(List list) {

        if (mList != null && mList.size() > 0) {
            mList.clear();
        }
        if (list != null) {
            mList.addAll(list);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private View.OnClickListener itemClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    };

    //自动z转换数据为kb/ g
    private String formatSize(long targetSize) {
        return Formatter.formatFileSize(mContext, targetSize);
    }

    public List<FileInfoBean> getList() {
        return mList;
    }

}
