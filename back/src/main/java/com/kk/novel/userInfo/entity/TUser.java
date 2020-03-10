package com.kk.novel.userInfo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.io.Serializable;

/**
 * (TUser)实体类
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
public class TUser implements Serializable {
    private static final long serialVersionUID = 745035678328960282L;
    /**
    * 用户ID
    */
    private Long userId;
    /**
    * 用户名
    */
    private String username;
    /**
    * 密码
    */
    @JsonIgnore
    private String password;
    /**
    * 部门ID
    */
    private Long deptId;

    /**
    * 邮箱
    */
    private String email;
    /**
    * 联系电话
    */
    private String mobile;
    /**
    * 状态 0锁定 1有效
    */
    private String status;
    /**
    * 创建时间
    */
    private String createTime;
    /**
    * 修改时间
    */
    private String modifyTime;
    /**
    * 最近访问时间
    */
    private String lastLoginTime;
    /**
    * 性别 0男 1女 2保密
    */
    private String ssex;
    /**
    * 描述
    */
    private String description;
    /**
    * 用户头像
    */
    private String avatar;

    /**
     * 角色id
     */
    private transient String roleId;

    private transient String deptName;

    private transient String roleName;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}