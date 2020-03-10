package com.kk.novel.userInfo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;
import java.io.Serializable;

/**
 * (TMenu)实体类
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
@TableName("t_menu")
public class TMenu implements Serializable {
    private static final long serialVersionUID = -44067216603504336L;
    /**
    * 菜单/按钮ID
    */
    @TableId(value = "MENU_ID",type = IdType.AUTO)
    private Long menuId;
    /**
    * 上级菜单ID
    */
    private Long parentId;
    /**
    * 菜单/按钮名称
    */
    private String menuName;
    /**
    * 对应路由path
    */
    private String path;
    /**
    * 对应路由组件component
    */
    private String component;
    /**
    * 权限标识
    */
    private String perms;
    /**
    * 图标
    */
    private String icon;
    /**
    * 类型 0菜单 1按钮
    */
    private String type;
    /**
    * 排序
    */
    private Integer orderNum;
    /**
    * 创建时间
    */
    private String createTime;
    /**
    * 修改时间
    */
    private String modifyTime;
    /**
    * 是否展示
    */
    private String isShow;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
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