package com.kk.novel.userInfo.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (TJob)实体类
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
public class TJob implements Serializable {
    private static final long serialVersionUID = 741685197016060022L;
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
    * cron表达式
    */
    private String cronExpression;
    /**
    * 任务状态  0：正常  1：暂停
    */
    private String status;
    /**
    * 备注
    */
    private String remark;
    /**
    * 创建时间
    */
    private Date createTime;


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

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}