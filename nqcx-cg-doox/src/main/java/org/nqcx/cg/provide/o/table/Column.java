/*
 * Copyright 2018 nqcx.org All right reserved. This software is the
 * confidential and proprietary information of nqcx.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.cg.provide.o.table;

import org.nqcx.cg.provide.bo.ColumnBO;

/**
 * @author naqichuan Feb 8, 2014 9:49:43 PM
 */
public class Column extends ColumnBO {

    // pojo field from page commit
    private String field_;
    private String type_;
    private boolean null_ = true;
    private boolean id_ = false;
    private boolean cm_ = false;

    private String idType_;
    // end of pojo

    // table extend field
    private String columnType;
    private String columnLength;
    // end table expand field

    // mybatis field value
    private String mybatisValue;
    // end of mybatis field value

    public String getField_() {
        return field_;
    }

    public void setField_(String field_) {
        this.field_ = field_;
    }

    public String getType_() {
        return type_;
    }

    public void setType_(String type_) {
        this.type_ = type_;
    }

    public boolean isNull_() {
        return null_;
    }

    public void setNull_(boolean null_) {
        this.null_ = null_;
    }

    public boolean isId_() {
        return id_;
    }

    public void setId_(boolean id_) {
        this.id_ = id_;
    }

    public boolean isCm_() {
        return cm_;
    }

    public void setCm_(boolean cm_) {
        this.cm_ = cm_;
    }

    public String getIdType_() {
        return idType_ == null ? "Long" : idType_;
    }

    public void setIdType_(String idType_) {
        this.idType_ = idType_;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnLength() {
        return columnLength;
    }

    public void setColumnLength(String columnLength) {
        this.columnLength = columnLength;
    }

    public String getMybatisValue() {
        return mybatisValue;
    }

    public void setMybatisValue(String mybatisValue) {
        this.mybatisValue = mybatisValue;
    }

    @Override
    public String toString() {
        return "Column{" +
                "field_='" + field_ + '\'' +
                ", type_='" + type_ + '\'' +
                ", null_=" + null_ +
                ", id_=" + id_ +
                ", cm_=" + cm_ +
                ", columnType='" + columnType + '\'' +
                ", columnLength='" + columnLength + '\'' +
                ", mybatisValue='" + mybatisValue + '\'' +
                "} " + super.toString();
    }
}
