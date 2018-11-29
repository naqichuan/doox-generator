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

    // table
    private String tableName;
    private String tablePack;
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

    // provide field
    private BoolEO provide_ = BoolEO.FALSE;
    private String provideBOPackage;
    private String provideOPackage;
    private String provideProvidePackage;
    private String privideBO;
    private String privideO;
    private String privideProvide;
    // end of provide field

    // pojo
    String[] tableColumns;
    String[] provideFields;
    String[] provideTypes;
    // end of pojo

    // dao field
    private BoolEO dao_ = BoolEO.FALSE;
    private String daoPOPackage;
    private String daoMapperPackage;
    private String daoJpaPackage;
    private String daoDaoPackage;
    private String daoPO;
    private String daoMapper;
    private String daoJpa;
    private String daoIDAO;
    private String daoDAO;
    private String daoDAOImpl;


    // end of dao field


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTablePack() {
        return tablePack;
    }

    public void setTablePack(String tablePack) {
        this.tablePack = tablePack;
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

    public BoolEO getProvide_() {
        return provide_;
    }

    public void setProvide_(BoolEO provide_) {
        this.provide_ = provide_;
    }

    public String getProvideBOPackage() {
        return provideBOPackage;
    }

    public void setProvideBOPackage(String provideBOPackage) {
        this.provideBOPackage = provideBOPackage;
    }

    public String getProvideOPackage() {
        return provideOPackage;
    }

    public void setProvideOPackage(String provideOPackage) {
        this.provideOPackage = provideOPackage;
    }

    public String getProvideProvidePackage() {
        return provideProvidePackage;
    }

    public void setProvideProvidePackage(String provideProvidePackage) {
        this.provideProvidePackage = provideProvidePackage;
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

    public String[] getTableColumns() {
        return tableColumns;
    }

    public void setTableColumns(String[] tableColumns) {
        this.tableColumns = tableColumns;
    }

    public String[] getProvideFields() {
        return provideFields;
    }

    public void setProvideFields(String[] provideFields) {
        this.provideFields = provideFields;
    }

    public String[] getProvideTypes() {
        return provideTypes;
    }

    public void setProvideTypes(String[] provideTypes) {
        this.provideTypes = provideTypes;
    }

    public BoolEO getDao_() {
        return dao_;
    }

    public void setDao_(BoolEO dao_) {
        this.dao_ = dao_;
    }

    public String getDaoPOPackage() {
        return daoPOPackage;
    }

    public void setDaoPOPackage(String daoPOPackage) {
        this.daoPOPackage = daoPOPackage;
    }

    public String getDaoMapperPackage() {
        return daoMapperPackage;
    }

    public void setDaoMapperPackage(String daoMapperPackage) {
        this.daoMapperPackage = daoMapperPackage;
    }

    public String getDaoJpaPackage() {
        return daoJpaPackage;
    }

    public void setDaoJpaPackage(String daoJpaPackage) {
        this.daoJpaPackage = daoJpaPackage;
    }

    public String getDaoDaoPackage() {
        return daoDaoPackage;
    }

    public void setDaoDaoPackage(String daoDaoPackage) {
        this.daoDaoPackage = daoDaoPackage;
    }

    public String getDaoPO() {
        return daoPO;
    }

    public void setDaoPO(String daoPO) {
        this.daoPO = daoPO;
    }

    public String getDaoMapper() {
        return daoMapper;
    }

    public void setDaoMapper(String daoMapper) {
        this.daoMapper = daoMapper;
    }

    public String getDaoJpa() {
        return daoJpa;
    }

    public void setDaoJpa(String daoJpa) {
        this.daoJpa = daoJpa;
    }

    public String getDaoIDAO() {
        return daoIDAO;
    }

    public void setDaoIDAO(String daoIDAO) {
        this.daoIDAO = daoIDAO;
    }

    public String getDaoDAO() {
        return daoDAO;
    }

    public void setDaoDAO(String daoDAO) {
        this.daoDAO = daoDAO;
    }

    public String getDaoDAOImpl() {
        return daoDAOImpl;
    }

    public void setDaoDAOImpl(String daoDAOImpl) {
        this.daoDAOImpl = daoDAOImpl;
    }
}
