package com.kk.novel.userInfo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * (TLoginLog)实体类
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
public class TLoginLog implements Serializable {
    private static final long serialVersionUID = 804338737265057904L;
    
    private Integer id;
    /**
    * 用户名
    */
    private String username;
    /**
    * 登录时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date loginTime;
    /**
    * 登录地点
    */
    private String location;
    /**
    * IP地址
    */
    private String ip;

    private transient Integer  num;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}