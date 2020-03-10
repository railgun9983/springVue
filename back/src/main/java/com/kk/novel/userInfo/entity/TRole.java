package com.kk.novel.userInfo.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (TRole)实体类
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
public class TRole implements Serializable {
    private static final long serialVersionUID = -63389047490106184L;
    /**
    * 角色ID
    */
    private Long roleId;
    /**
    * 角色名称
    */
    private String roleName;
    /**
    * 角色描述
    */
    private String remark;
    /**
    * 创建时间
    */
    private String createTime;
    /**
    * 修改时间
    */
    private String modifyTime;


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}