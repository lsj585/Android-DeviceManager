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
    /**
     * 是否开启安全阻止
     */
    private boolean isDesiplaySave;

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

    public boolean isDesiplaySave() {
        return isDesiplaySave;
    }

    public void setDesiplaySave(boolean desiplaySave) {
        isDesiplaySave = desiplaySave;
    }
}
