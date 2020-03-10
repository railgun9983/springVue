package com.kk.novel.userInfo.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (TLog)实体类
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
public class TLog implements Serializable {
    private static final long serialVersionUID = -21334801287118748L;
    /**
    * 日志ID
    */
    private Long id;
    /**
    * 操作用户
    */
    private String username;
    /**
    * 操作内容
    */
    private String operation;
    /**
    * 耗时
    */
    private Long time;
    /**
    * 操作方法
    */
    private String method;
    /**
    * 方法参数
    */
    private String params;
    /**
    * 操作者IP
    */
    private String ip;
    /**
    * 创建时间
    */
    private String  createTime;
    /**
    * 操作地点
    */
    private String location;

    private transient  String endTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}