/*
 * Copyright 2018 nqcx.org All right reserved. This software is the confidential and proprietary information
 * of nqcx.org ("Confidential Information"). You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you entered into with nqcx.org.
 */

package org.nqcx.cg.provide.o;

import org.nqcx.commons3.lang.enums.BoolEO;

import java.io.Serializable;

/**
 * @author naqichuan 2018/11/27 18:11
 */
public class Generate implements Serializable {

//    @RequestParam(value = "tableName", required = true) String tableName,
//    @RequestParam(value = "pName", required = true) String pName,
//    @RequestParam(value = "pType", required = true) String pType,
//    @RequestParam(value = "pPath", required = true) String pPath,
//    @RequestParam(value = "pPackage", required = true) String pPackage,
//    @RequestParam(value = "entityName", required = false) String entityName,
//    @RequestParam(value = "entityProjectName", required = false, defaultValue = "") String entityProjectName,
//    @RequestParam(value = "entityProjectPackage", required = false, defaultValue = "") String entityProjectPackage,
//    @RequestParam(value = "mapperProjectName", required = false, defaultValue = "") String mapperProjectName,
//    @RequestParam(value = "mapperProjectPackage", required = false, defaultValue = "") String mapperProjectPackage,
//    @RequestParam(value = "serviceProjectName", required = false, defaultValue = "") String serviceProjectName,
//    @RequestParam(value = "serviceProjectPackage", required = false, defaultValue = "") String serviceProjectPackage,
//    String[] tableColumns, String[] entityField, String[] entityType,
//    @RequestParam(value = "mapperInterface", required = false, defaultValue = "") String mapperInterface,
//    @RequestParam(value = "serviceInterface", required = false, defaultValue = "") String serviceInterface,
//    @RequestParam(value = "serviceImplement", required = false, defaultValue = "") String serviceImplement

    // table
    private String tableName;
    // end of table

    // project field
    private String pName;
    private String pPath;
    private String pPackage;
    private String pType;
    private String provideModule;
    private String daoModule;
    private String serviceModule;
    private String webModule;
    // end of project filed

    // provide
    private BoolEO provide = BoolEO.FALSE;
    private String provideProvidePackage;
    private String provideServicePackage;
    private String privideBO;
    private String privideO;
    private String privideProvide;
    private String privideService;
    // end of provide


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpPath() {
        return pPath;
    }

    public void setpPath(String pPath) {
        this.pPath = pPath;
    }

    public String getpPackage() {
        return pPackage;
    }

    public void setpPackage(String pPackage) {
        this.pPackage = pPackage;
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    public String getProvideModule() {
        return provideModule;
    }

    public void setProvideModule(String provideModule) {
        this.provideModule = provideModule;
    }

    public String getDaoModule() {
        return daoModule;
    }

    public void setDaoModule(String daoModule) {
        this.daoModule = daoModule;
    }

    public String getServiceModule() {
        return serviceModule;
    }

    public void setServiceModule(String serviceModule) {
        this.serviceModule = serviceModule;
    }

    public String getWebModule() {
        return webModule;
    }

    public void setWebModule(String webModule) {
        this.webModule = webModule;
    }

    public BoolEO getProvide() {
        return provide;
    }

    public void setProvide(BoolEO provide) {
        this.provide = provide;
    }

    public String getProvideProvidePackage() {
        return provideProvidePackage;
    }

    public void setProvideProvidePackage(String provideProvidePackage) {
        this.provideProvidePackage = provideProvidePackage;
    }

    public String getProvideServicePackage() {
        return provideServicePackage;
    }

    public void setProvideServicePackage(String provideServicePackage) {
        this.provideServicePackage = provideServicePackage;
    }

    public String getPrivideBO() {
        return privideBO;
    }

    public void setPrivideBO(String privideBO) {
        this.privideBO = privideBO;
    }

    public String getPrivideO() {
        return privideO;
    }

    public void setPrivideO(String privideO) {
        this.privideO = privideO;
    }

    public String getPrivideProvide() {
        return privideProvide;
    }

    public void setPrivideProvide(String privideProvide) {
        this.privideProvide = privideProvide;
    }

    public String getPrivideService() {
        return privideService;
    }

    public void setPrivideService(String privideService) {
        this.privideService = privideService;
    }
}
