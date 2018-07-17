package com.nd.adhoc.dmsdk.demo.ui.viewholder;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.nd.adhoc.dmsdk.demo.R;
import com.nd.adhoc.dmsdk.demo.bean.HardWareSwitchBean;

import java.util.List;

/**
 * @author richsjeson
 * 硬件开关的View
 */

public class HardwareView {

    private View mItemView;
    private TextView tvHardwareName;
    private ImageView ivHardWare;
    private TextView tvHardWareStatus;
    private ImageView ivHardWareStatus;
    private TextView tvDevicePolicy;

    public HardwareView(View itemView) {
        this.mItemView=itemView;
        tvHardwareName=(TextView) itemView.findViewById(R.id.tv_devicename_control);
        ivHardWare=itemView.findViewById(R.id.iv_devicename_control);
        tvHardWareStatus=itemView.findViewById(R.id.tv_devicestatus_control);
        ivHardWareStatus=itemView.findViewById(R.id.iv_devicestatus_control);
        tvDevicePolicy=itemView.findViewById(R.id.tv_devicepolicy_control);

    }

    public void setView(@NonNull Context context, @NonNull List<HardWareSwitchBean> list, int position) {
        if (list.size() == 0) {
            return;
        }

        if (list.get(position) == null) {
            return;
        }
        tvHardwareName.setText(list.get(position).getName());
        tvHardWareStatus.setText(list.get(position).getStatus()==0?"关":"开");
        if(list.get(position).isDesiplaySave() && list.get(position).getStatus()==0){
            ivHardWareStatus.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_signal_gray));
            tvDevicePolicy.setVisibility(View.VISIBLE);
        }else{
            ivHardWareStatus.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_signal_green));
            tvDevicePolicy.setVisibility(View.GONE);
        }
    }
}
