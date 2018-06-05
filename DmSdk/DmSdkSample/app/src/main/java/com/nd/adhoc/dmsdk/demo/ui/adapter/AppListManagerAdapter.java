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

import com.adhoc.dmsdk.sdk.DeviceManagerSdk;
import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.manager.app.IApplicationManager_IsRun;
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
        return new FileInfoHolder(LayoutInflater.from(this.mContext).inflate(R.layout.list_item_app,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AppListManagerAdapter.FileInfoHolder holder, final int position) {
        Log.i(this.getClass().getName(),String.format("Get list=%d",mList.size()));
        if(mList.size()>0 && mList.get(position) != null) {
            holder.tvAppInfoName.setText(mList.get(position).getName());
            holder.tvPackageName.setText(mList.get(position).getPackageName());
            holder.itemView.setTag(position);
            holder.itemView.setOnClickListener(itemClickListener);
//            if(mList.get(position).isRunning()){
//                holder.tvRunning.setText(mContext.getResources().getString(R.string.runing));
//            }else{
//                holder.tvRunning.setText(mContext.getResources().getString(R.string.stop));
//            }

            if(isRunning(mContext,mList.get(position).getPackageName())){
                holder.tvRunning.setText(mContext.getResources().getString(R.string.runing));
            }else{
                holder.tvRunning.setText(mContext.getResources().getString(R.string.stop));
            }

            holder.tvUsage.setText(String.format(mContext.getResources().getString(R.string.app_usage),
                    formatSize(mList.get(position).getCpuUsage()),
                    formatSize(mList.get(position).getRamUsage()),
                    formatSize(mList.get(position).getApplicationDataSizeUsage()),
                    formatSize(mList.get(position).getApplicationCacheSizeUsage())));

        }
    }
    /**
     * 是否在后台启动
     * @param context
     * @param packageName
     * @return
     */
    private  boolean isRunning(Context context,String packageName){
        IApplicationManager_IsRun applicationManagerIsRun= (IApplicationManager_IsRun) DeviceManagerSdk.getInstance().getManager(DeviceManagerContainer.MANAGER_APPLICATION_ISRUNNING);
        if(applicationManagerIsRun != null){
            try{
                return applicationManagerIsRun.isRunning(context,packageName);
            }catch (DeviceManagerSecurityException e){
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }


    class FileInfoHolder extends RecyclerView.ViewHolder{

        private TextView tvAppInfoName;
        private TextView tvPackageName;
        private TextView tvRunning;
        private TextView tvUsage;

        public FileInfoHolder(View itemView) {
            super(itemView);
            tvAppInfoName=(TextView) itemView.findViewById(R.id.tv_appname_app);
            tvPackageName=(TextView)itemView.findViewById(R.id.tv_package_app);
            tvRunning=itemView.findViewById(R.id.tv_running_app);
            tvUsage=itemView.findViewById(R.id.tv_usage_app);
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
