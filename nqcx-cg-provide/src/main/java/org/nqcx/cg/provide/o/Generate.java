/*
 * Copyright 2018 nqcx.org All right reserved. This software is the confidential and proprietary information
 * of nqcx.org ("Confidential Information"). You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you entered into with nqcx.org.
 */

package org.nqcx.cg.provide.o;

import org.nqcx.cg.provide.enums.PType;
import org.nqcx.commons3.lang.enums.BoolEO;

import java.io.Serializable;

/**
 * @author naqichuan 2018/11/27 18:11
 */
public class Generate implements Serializable {

    // project field
    private String pName;
    private String pPath;
    private String pPackage;
    private PType pType;
    private String provideModule;
    private String daoModule;
    private String serviceModule;
    private String webModule;
    // end of project filed

    // table
    private String tableName;
    private String tablePack; // table package
    // end of table

    // pojo
    private String[] pojoColumn;
    private String[] pojoField;
    private String[] pojoType;
    // end of pojo

    // provide field
    private BoolEO provide_ = BoolEO.FALSE;
    private String provideBOPackage;
    private String provideOPackage;
    private String provideProvidePackage;
    private String provideBO;
    private String provideO;
    private String provideProvide;
    // end of provide field

    // dao field
    private BoolEO dao_ = BoolEO.FALSE;
    private String daoPOPackage;
    private String daoMapperPackage;
    private String daoJpaPackage;
    private String daoDAOPackage;
    private String daoPO;
    private String daoMapper;
    private String daoJpa;
    private String daoDAO;
    private String daoDAOImpl;
    // end of dao field

    // service field
    private BoolEO service_ = BoolEO.FALSE;
    private String serviceDOPackage;
    private String serviceServicePackage;
    private String serviceDO;
    private String serviceService;
    private String servceServiceImpl;
    // end of field

    // web field
    private BoolEO web_ = BoolEO.FALSE;
    private String webVOPackage;
    private String webControllerPackage;
    private String webVO;
    private String webController;
    // end of web field

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

    public PType getpType() {
        return pType;
    }

    public void setpType(PType pType) {
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

    public String getProvideBO() {
        return provideBO;
    }

    public void setProvideBO(String provideBO) {
        this.provideBO = provideBO;
    }

    public String getProvideO() {
        return provideO;
    }

    public void setProvideO(String provideO) {
        this.provideO = provideO;
    }

    public String getProvideProvide() {
        return provideProvide;
    }

    public void setProvideProvide(String provideProvide) {
        this.provideProvide = provideProvide;
    }

    public String[] getPojoColumn() {
        return pojoColumn;
    }

    public void setPojoColumn(String[] pojoColumn) {
        this.pojoColumn = pojoColumn;
    }

    public String[] getPojoField() {
        return pojoField;
    }

    public void setPojoField(String[] pojoField) {
        this.pojoField = pojoField;
    }

    public String[] getPojoType() {
        return pojoType;
    }

    public void setPojoType(String[] pojoType) {
        this.pojoType = pojoType;
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

    public String getDaoDAOPackage() {
        return daoDAOPackage;
    }

    public void setDaoDAOPackage(String daoDAOPackage) {
        this.daoDAOPackage = daoDAOPackage;
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

    public BoolEO getService_() {
        return service_;
    }

    public void setService_(BoolEO service_) {
        this.service_ = service_;
    }

    public String getServiceDOPackage() {
        return serviceDOPackage;
    }

    public void setServiceDOPackage(String serviceDOPackage) {
        this.serviceDOPackage = serviceDOPackage;
    }

    public String getServiceServicePackage() {
        return serviceServicePackage;
    }

    public void setServiceServicePackage(String serviceServicePackage) {
        this.serviceServicePackage = serviceServicePackage;
    }

    public String getServiceDO() {
        return serviceDO;
    }

    public void setServiceDO(String serviceDO) {
        this.serviceDO = serviceDO;
    }

    public String getServiceService() {
        return serviceService;
    }

    public void setServiceService(String serviceService) {
        this.serviceService = serviceService;
    }

    public String getServceServiceImpl() {
        return servceServiceImpl;
    }

    public void setServceServiceImpl(String servceServiceImpl) {
        this.servceServiceImpl = servceServiceImpl;
    }

    public BoolEO getWeb_() {
        return web_;
    }

    public void setWeb_(BoolEO web_) {
        this.web_ = web_;
    }

    public String getWebVOPackage() {
        return webVOPackage;
    }

    public void setWebVOPackage(String webVOPackage) {
        this.webVOPackage = webVOPackage;
    }

    public String getWebControllerPackage() {
        return webControllerPackage;
    }

    public void setWebControllerPackage(String webControllerPackage) {
        this.webControllerPackage = webControllerPackage;
    }

    public String getWebVO() {
        return webVO;
    }

    public void setWebVO(String webVO) {
        this.webVO = webVO;
    }

    public String getWebController() {
        return webController;
    }

    public void setWebController(String webController) {
        this.webController = webController;
    }
}
