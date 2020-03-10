package com.kk.novel.userInfo.entity;

import java.io.Serializable;

/**
 * (TUserRole)实体类
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
public class TUserRole implements Serializable {
    private static final long serialVersionUID = -38035246592901445L;
    /**
    * 用户ID
    */
    private Long userId;
    /**
    * 角色ID
    */
    private Long roleId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}