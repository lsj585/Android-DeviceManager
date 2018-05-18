package com.nd.adhoc.dmsdk.demo.model.impl;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.nd.adhoc.dmsdk.api.knox.manager.DeviceSdkApiManager;
import com.nd.adhoc.dmsdk.demo.bean.FileInfoBean;
import com.nd.adhoc.dmsdk.demo.model.BaseModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取文件列表信息
 */
public class FileManagerModel extends BaseModel<FileInfoBean> {

    private List<FileInfoBean> fileinfoList;

    private DeviceSdkApiManager manager;


    public FileManagerModel(Context context) {
        super(context);
        manager=new DeviceSdkApiManager(context);
    }

    @Override
    public List<FileInfoBean> getList() {

        if(fileinfoList == null){

            fileinfoList=new ArrayList<FileInfoBean>();
        }

        fileinfoList.clear();
        return createList();
    }

    @Override
    public void update(FileInfoBean fileInfoBean, int position) {

    }

    @Override
    public void delete(FileInfoBean fileInfoBean) {

    }

    @Override
    public void delete(int position) {

    }

    @Override
    public FileInfoBean findById(long id) {
        return null;
    }

    @Override
    public void update(int position) {
    }

    @Override
    public void release() {

        fileinfoList=null;
        manager.release();
    }

    private Cursor getFileCursor(Context context) {
        StringBuilder selection = new StringBuilder();
        String[] columns = new String[]{
                MediaStore.Files.FileColumns._ID, MediaStore.Files.FileColumns.DATA, MediaStore.Files.FileColumns.SIZE, MediaStore.Files.FileColumns.DATE_MODIFIED,
                MediaStore.Files.FileColumns.DISPLAY_NAME, MediaStore.Files.FileColumns.MIME_TYPE, MediaStore.Files.FileColumns.TITLE
        };
        String volumeName = "external";

        Uri uri = MediaStore.Files.getContentUri(volumeName);
        //扩展名
        selection.append("( " + MediaStore.Files.FileColumns.DATA + " like '%" + ".apk" + "%')");
        String select = selection.toString();

        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(uri, columns, select, null, null);
            if (cursor != null) {
                System.out.println("cursor size =" + cursor.getCount());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return cursor;
    }

    /**
     * 转换文件属性
     * @param filePath
     * @return
     */
    private FileInfoBean getFileInfo(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        FileInfoBean fileInfo = new FileInfoBean();
        fileInfo.setPath(filePath);
        fileInfo.setSize(file.length());
        fileInfo.setStatus(0);
        fileInfo.setLastTime(String.format("%d",file.lastModified()));
        return fileInfo;
    }

    private static String getFileName(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return filePath;
        }

        int filePosi = filePath.lastIndexOf(File.separator);
        return (filePosi == -1) ? filePath : filePath.substring(filePosi + 1);
    }

    /**
     * 获取设备上所存在的apk信息
     * @return
     */
    private List<FileInfoBean> createList(){
        Cursor cursor = getFileCursor(context);
        if(cursor != null){
            if (cursor.moveToFirst()) {
                do {
                    String filePath = cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA));
                    FileInfoBean fileInfo=getFileInfo(filePath);
                    if (fileInfo != null) {
                        String name = getFileName(fileInfo.getPath()).toLowerCase();
                        fileInfo.setLastTime(cursor.getLong(cursor.getColumnIndex(MediaStore.Files.FileColumns.DATE_MODIFIED)) * 1000 + "");
                        fileInfo.setName(name);
                        int lastIndex = name.lastIndexOf(".");
                        if (lastIndex != -1) {
                            String temp = name.substring(lastIndex);
                            if (".apk".equals(temp)) {
                                //判断文件是否存在
                                File f = new File(fileInfo.getPath());
                                if (f.exists())
                                    fileinfoList.add(fileInfo);
                            }
                        }
                    }
                } while (cursor.moveToNext());
            }
        }
        return fileinfoList;
    }

    public boolean install(int position){
        FileInfoBean bean =fileinfoList.get(position);
        bean.setStatus(1);
        if(bean != null){
            return manager.installApp(bean.getPath());
        }else{
            return false;
        }
    }

    public boolean forbidInstall(int position){
        FileInfoBean bean =fileinfoList.get(position);
        bean.setStatus(0);
        if(bean != null){
            return manager.installApp(bean.getPath());
        }else{
            return false;
        }
    }
}
