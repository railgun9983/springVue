package com.kk.novel.userInfo.entity;

import java.io.Serializable;

/**
 * (KLocks)实体类
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
public class KLocks implements Serializable {
    private static final long serialVersionUID = 422072045179922683L;
    
    private String schedName;
    
    private String lockName;


    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }

    public String getLockName() {
        return lockName;
    }

    public void setLockName(String lockName) {
        this.lockName = lockName;
    }

}