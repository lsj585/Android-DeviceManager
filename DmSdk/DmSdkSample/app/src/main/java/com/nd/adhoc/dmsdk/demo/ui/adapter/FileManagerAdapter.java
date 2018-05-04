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

import java.util.ArrayList;
import java.util.List;

public class FileManagerAdapter extends RecyclerView.Adapter<FileManagerAdapter.FileInfoHolder> {

    private Context mContext;

    private List<FileInfoBean> mList;

    private OnItemClickListener onItemClickListener;

    public FileManagerAdapter(Context context){
        this.mContext= context;
        mList=new ArrayList<FileInfoBean>();
    }
    @NonNull
    @Override
    public FileManagerAdapter.FileInfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FileInfoHolder(LayoutInflater.from(this.mContext).inflate(R.layout.list_item_file,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FileManagerAdapter.FileInfoHolder holder, final int position) {
        Log.i(this.getClass().getName(),String.format("Get list=%d",mList.size()));
        if(mList.size()>0 && mList.get(position) != null) {
            holder.tvAppInfoName.setText(mList.get(position).getName());
            holder.tvAppInfoSize.setText(formatSize(mList.get(position).getSize()));
            holder.itemView.setTag(position);
            holder.itemView.setOnClickListener(itemClickListener);
            if(mList.get(position).getStatus()==0){
                holder.tvAppStatus.setText(mContext.getResources().getString(R.string.no_install));
            }else{
                holder.tvAppStatus.setText(mContext.getResources().getString(R.string.installing));
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

    public List<FileInfoBean> getList(){
        return mList;
    }

}
