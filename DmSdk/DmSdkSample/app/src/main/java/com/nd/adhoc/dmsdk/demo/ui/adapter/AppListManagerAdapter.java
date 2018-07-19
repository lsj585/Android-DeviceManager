package com.nd.adhoc.dmsdk.demo.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nd.adhoc.dmsdk.demo.R;
import com.nd.adhoc.dmsdk.demo.bean.ApplicationInfoBean;
import com.nd.adhoc.dmsdk.demo.ui.viewholder.AppItemView;

import java.util.ArrayList;
import java.util.List;

public class AppListManagerAdapter extends RecyclerView.Adapter<AppListManagerAdapter.FileInfoHolder> {

    private Context mContext;

    private List<ApplicationInfoBean> mList;

    private OnItemClickListener onItemClickListener;


    public AppListManagerAdapter(Context context) {
        this.mContext = context;
        mList = new ArrayList<ApplicationInfoBean>();
    }

    @NonNull
    @Override
    public AppListManagerAdapter.FileInfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FileInfoHolder(LayoutInflater.from(this.mContext).inflate(R.layout.list_item_app, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AppListManagerAdapter.FileInfoHolder holder, final int position) {
        holder.getAppItemView().setView(mContext,mList,position);
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(itemClickListener);
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }


    static class FileInfoHolder extends RecyclerView.ViewHolder {

        private AppItemView mAppItemView;

        public FileInfoHolder(View itemView) {
            super(itemView);
            this.mAppItemView=new AppItemView(itemView);
        }

        public AppItemView getAppItemView(){

            return mAppItemView;
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

    public interface OnMonitorDataListener {

        /**
         * 显示cpu
         *
         * @param viewPosition
         */
        void cpu(int viewPosition);

        /**
         * 显示内存
         *
         * @param viewPosition
         */
        void ram(int viewPosition);

        /**
         * 显示内存
         *
         * @param viewPosition
         */
        void applicationDataSize(int viewPosition);


        void applicationCacheSize(int viewPosition);
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

    public List<ApplicationInfoBean> getList() {
        return mList;
    }


}
