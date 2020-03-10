package com.kk.novel.userInfo.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (TTest)实体类
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
public class TTest implements Serializable {
    private static final long serialVersionUID = -74254347277291187L;
    
    private Long id;
    
    private String field1;
    
    private Integer field2;
    
    private String field3;
    
    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public Integer getField2() {
        return field2;
    }

    public void setField2(Integer field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}