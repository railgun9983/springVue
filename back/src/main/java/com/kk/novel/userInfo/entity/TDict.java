package com.kk.novel.userInfo.entity;

import java.io.Serializable;

/**
 * (TDict)实体类
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
public class TDict implements Serializable {
    private static final long serialVersionUID = 132407007322321045L;
    /**
    * 字典ID
    */
    private Long dictId;
    /**
    * 键
    */
    private Long keyy;
    /**
    * 值
    */
    private String valuee;
    /**
    * 字段名称
    */
    private String fieldName;
    /**
    * 表名
    */
    private String tableName;


    public Long getDictId() {
        return dictId;
    }

    public void setDictId(Long dictId) {
        this.dictId = dictId;
    }

    public Long getKeyy() {
        return keyy;
    }

    public void setKeyy(Long keyy) {
        this.keyy = keyy;
    }

    public String getValuee() {
        return valuee;
    }

    public void setValuee(String valuee) {
        this.valuee = valuee;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

}