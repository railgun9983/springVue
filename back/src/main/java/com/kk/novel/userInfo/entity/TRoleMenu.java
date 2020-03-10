package com.kk.novel.userInfo.entity;

import java.io.Serializable;

/**
 * (TRoleMenu)实体类
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
public class TRoleMenu implements Serializable {
    private static final long serialVersionUID = -28032310469825922L;
    
    private Long roleId;
    
    private Long menuId;


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

}