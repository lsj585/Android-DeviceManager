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
import com.nd.adhoc.dmsdk.demo.ui.viewholder.AppItemView;
import com.nd.adhoc.dmsdk.demo.ui.viewholder.HardwareView;
import com.nd.adhoc.dmsdk.demo.ui.weget.GridDivider;

import java.util.ArrayList;
import java.util.List;

public class HardwareAdapter extends RecyclerView.Adapter<HardwareAdapter.HardWareSwitchHolder> {

    private Context mContext;

    private List<HardWareSwitchBean> mList;

    private OnItemClickListener onItemClickListener;

    public HardwareAdapter(Context context){
        this.mContext= context;
        mList=new ArrayList<>();
    }
    @NonNull
    @Override
    public HardwareAdapter.HardWareSwitchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HardWareSwitchHolder(LayoutInflater.from(this.mContext).inflate(R.layout.list_item_control,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HardwareAdapter.HardWareSwitchHolder holder, final int position) {
        Log.i(this.getClass().getName(),String.format("Get list=%d",mList.size()));
        if(mList.size()==0){
            return ;
        }

        if(mList.get(position)==null){
            return;
        }

        holder.getHardView().setView(mContext,mList,position);
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(itemClickListener);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }




    static class HardWareSwitchHolder extends RecyclerView.ViewHolder {

        private HardwareView mHardView;

        public HardWareSwitchHolder(View itemView) {
            super(itemView);
            this.mHardView=new HardwareView(itemView);
        }

        public HardwareView getHardView(){

            return mHardView;
        }

    }



    public void setData(List list){

        if(mList==null){
            return;
        }
        if(mList.size()>0){
            mList.clear();
        }
        mList.addAll(list);
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
