package com.nd.adhoc.dmsdk.demo.bean;

/**
 * 获取文件属性
 */
public class FileInfoBean extends BaseBean {
    /**
     * 文件名
     */
    private String name;
    /**
     * 文件大小
     */
    private long size;
    /**
     * 文件最后一次更改时间
     */
    private String lastTime;
    /**
     * 文件路径
     */
    private String path;
    /**
     * 0 未安装 1 安装中
     */
    private int status;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
