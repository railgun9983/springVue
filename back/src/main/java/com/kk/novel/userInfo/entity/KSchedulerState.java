package com.kk.novel.userInfo.entity;

import java.io.Serializable;

/**
 * (KSchedulerState)实体类
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
public class KSchedulerState implements Serializable {
    private static final long serialVersionUID = -83480241471221304L;
    
    private String schedName;
    
    private String instanceName;
    
    private Long lastCheckinTime;
    
    private Long checkinInterval;


    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public Long getLastCheckinTime() {
        return lastCheckinTime;
    }

    public void setLastCheckinTime(Long lastCheckinTime) {
        this.lastCheckinTime = lastCheckinTime;
    }

    public Long getCheckinInterval() {
        return checkinInterval;
    }

    public void setCheckinInterval(Long checkinInterval) {
        this.checkinInterval = checkinInterval;
    }

}