package com.nd.adhoc.dmsdk.demo.ui.viewholder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nd.adhoc.dmsdk.demo.R;
import com.nd.adhoc.dmsdk.demo.bean.FileInfoBean;
import com.nd.adhoc.dmsdk.demo.bean.HardWareSwitchBean;

import java.util.List;

/**
 * @author richsjeson
 *         硬件开关的View
 */

public class FileItemView {

    private TextView tvAppInfoName;
    private TextView tvAppInfoSize;
    private TextView tvAppStatus;

    public FileItemView(View itemView) {
        tvAppInfoName = (TextView) itemView.findViewById(R.id.tv_appname_file);
        tvAppInfoSize = (TextView) itemView.findViewById(R.id.tv_appsize_file);
        tvAppStatus = itemView.findViewById(R.id.tv_appstatus_file);

    }

    public void setView(@NonNull Context context, @NonNull List<FileInfoBean> list, int position) {

        if (list.size() == 0) {
            return;
        }

        if (list.get(position) == null) {
            return;
        }
        tvAppInfoName.setText(list.get(position).getName());
        tvAppInfoSize.setText(formatSize(context,list.get(position).getSize()));
        if (list.get(position).getStatus() == 0) {
            tvAppStatus.setText(context.getResources().getString(R.string.no_install));
        } else {
            tvAppStatus.setText(context.getResources().getString(R.string.installing));
        }
    }

    //自动z转换数据为kb/ g
    private String formatSize(Context context,long targetSize) {
        return Formatter.formatFileSize(context, targetSize);
    }
}
