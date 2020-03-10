package com.kk.novel.userInfo.entity;

import java.io.Serializable;

/**
 * (KPausedTriggerGrps)实体类
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
public class KPausedTriggerGrps implements Serializable {
    private static final long serialVersionUID = -53815599956619734L;
    
    private String schedName;
    
    private String triggerGroup;


    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }

    public String getTriggerGroup() {
        return triggerGroup;
    }

    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }

}