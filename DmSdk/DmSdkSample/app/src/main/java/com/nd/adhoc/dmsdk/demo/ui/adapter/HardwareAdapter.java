package com.nd.adhoc.dmsdk.demo.ui.adapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nd.adhoc.dmsdk.demo.R;
import com.nd.adhoc.dmsdk.demo.bean.HardWareSwitchBean;
import com.nd.adhoc.dmsdk.demo.ui.weget.GridDivider;

import java.util.ArrayList;
import java.util.List;

public class HardwareAdapter extends RecyclerView.Adapter<HardwareAdapter.HardWareSwitchHolder> {

    private Context mContext;

    private List<HardWareSwitchBean> mList;

    private OnItemClickListener onItemClickListener;

    public HardwareAdapter(Context context){
        this.mContext= context;
        mList=new ArrayList<HardWareSwitchBean>();
    }
    @NonNull
    @Override
    public HardwareAdapter.HardWareSwitchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HardWareSwitchHolder(LayoutInflater.from(this.mContext).inflate(R.layout.list_item_control,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HardwareAdapter.HardWareSwitchHolder holder, final int position) {
        Log.i(this.getClass().getName(),String.format("Get list=%d",mList.size()));
        if(mList.size()>0 && mList.get(position) != null) {
            holder.tvHardwareName.setText(mList.get(position).getName());
            holder.tvHardWareStatus.setText(mList.get(position).getStatus()==0?"关":"开");
            holder.itemView.setTag(position);
            holder.itemView.setOnClickListener(itemClickListener);
            if(mList.get(position).getStatus()==0){
                holder.ivHardWareStatus.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_signal_gray));
                holder.tvDevicePolicy.setVisibility(View.VISIBLE);
            }else{
                holder.ivHardWareStatus.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_signal_green));
                holder.tvDevicePolicy.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class HardWareSwitchHolder extends RecyclerView.ViewHolder{

        private TextView tvHardwareName;
        private ImageView ivHardWare;
        private TextView tvHardWareStatus;
        private ImageView ivHardWareStatus;
        private TextView tvDevicePolicy;

        public HardWareSwitchHolder(View itemView) {
            super(itemView);
            tvHardwareName=(TextView) itemView.findViewById(R.id.tv_devicename_control);
            ivHardWare=itemView.findViewById(R.id.iv_devicename_control);
            tvHardWareStatus=itemView.findViewById(R.id.tv_devicestatus_control);
            ivHardWareStatus=itemView.findViewById(R.id.iv_devicestatus_control);
            tvDevicePolicy=itemView.findViewById(R.id.tv_devicepolicy_control);
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
        void onItemClick(View view , int position);
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
}
