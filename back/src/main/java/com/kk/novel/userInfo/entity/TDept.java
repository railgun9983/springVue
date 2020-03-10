package com.kk.novel.userInfo.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (TDept)实体类
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
public class TDept implements Serializable {
    private static final long serialVersionUID = -83427509238916405L;
    /**
    * 部门ID
    */
    private Long deptId;
    /**
    * 上级部门ID
    */
    private Long parentId;
    /**
    * 部门名称
    */
    private String deptName;
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


    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
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