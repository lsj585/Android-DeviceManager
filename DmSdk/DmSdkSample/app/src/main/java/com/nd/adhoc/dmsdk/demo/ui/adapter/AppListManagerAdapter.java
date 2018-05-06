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
import com.nd.adhoc.dmsdk.demo.bean.ApplicationInfoBean;
import com.nd.adhoc.dmsdk.demo.bean.FileInfoBean;

import java.util.ArrayList;
import java.util.List;

public class AppListManagerAdapter extends RecyclerView.Adapter<AppListManagerAdapter.FileInfoHolder> {

    private Context mContext;

    private List<ApplicationInfoBean> mList;

    private OnItemClickListener onItemClickListener;


    public AppListManagerAdapter(Context context){
        this.mContext= context;
        mList=new ArrayList<ApplicationInfoBean>();
    }
    @NonNull
    @Override
    public AppListManagerAdapter.FileInfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FileInfoHolder(LayoutInflater.from(this.mContext).inflate(R.layout.list_item_file,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AppListManagerAdapter.FileInfoHolder holder, final int position) {
        Log.i(this.getClass().getName(),String.format("Get list=%d",mList.size()));
        if(mList.size()>0 && mList.get(position) != null) {
            holder.tvAppInfoName.setText(mList.get(position).getName());
            holder.tvAppInfoSize.setText(mList.get(position).getPackageName());
            holder.itemView.setTag(position);
            holder.itemView.setOnClickListener(itemClickListener);
            if(mList.get(position).isRunning()){
                holder.tvAppStatus.setText(mContext.getResources().getString(R.string.runing));
            }else{
                holder.tvAppStatus.setText(mContext.getResources().getString(R.string.stop));
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class FileInfoHolder extends RecyclerView.ViewHolder{

        private TextView tvAppInfoName;
        private TextView tvAppInfoSize;
        private TextView tvAppStatus;

        public FileInfoHolder(View itemView) {
            super(itemView);
            tvAppInfoName=(TextView) itemView.findViewById(R.id.tv_appname_file);
            tvAppInfoSize=(TextView)itemView.findViewById(R.id.tv_appsize_file);
            tvAppStatus=itemView.findViewById(R.id.tv_appstatus_file);
        }
    }

    public void setData(List list){

        if(mList != null && mList.size()>0){
            mList.clear();
        }
        if(list != null) {
            mList.addAll(list);
        }
    }

    public  interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnMonitorDataListener{

        /**
         * 显示cpu
         * @param viewPosition
         */
        void cpu(int viewPosition);
        /**
         * 显示内存
         * @param viewPosition
         */
        void ram(int viewPosition);

        /**
         * 显示内存
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

    private  View.OnClickListener itemClickListener=new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    };

    //自动z转换数据为kb/ g
    private String formatSize(long targetSize) {
        return Formatter.formatFileSize(mContext, targetSize);
    }

    public List<ApplicationInfoBean> getList(){
        return mList;
    }


}
