package com.kk.novel.userInfo.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (TJobLog)实体类
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
public class TJobLog implements Serializable {
    private static final long serialVersionUID = 736422771764779217L;
    /**
    * 任务日志id
    */
    private Long logId;
    /**
    * 任务id
    */
    private Long jobId;
    /**
    * spring bean名称
    */
    private String beanName;
    /**
    * 方法名
    */
    private String methodName;
    /**
    * 参数
    */
    private String params;
    /**
    * 任务状态    0：成功    1：失败
    */
    private String status;
    /**
    * 失败信息
    */
    private String error;
    /**
    * 耗时(单位：毫秒)
    */
    private Double times;
    /**
    * 创建时间
    */
    private Date createTime;


    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Double getTimes() {
        return times;
    }

    public void setTimes(Double times) {
        this.times = times;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}