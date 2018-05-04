package com.nd.adhoc.dmsdk.demo.bean;

/**
 * 硬件开关类
 */
public class HardWareSwitchBean extends BaseBean{

    /**
     * 硬件开关的名称
     */
    private String name;
    /**
     * 开关状态 1 打开 0 关闭
     */
    private int status;

    private long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
