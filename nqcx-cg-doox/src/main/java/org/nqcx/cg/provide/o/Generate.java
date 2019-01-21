/*
 * Copyright 2018 nqcx.org All right reserved. This software is the confidential and proprietary information
 * of nqcx.org ("Confidential Information"). You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you entered into with nqcx.org.
 */

package org.nqcx.cg.provide.o;

import org.nqcx.cg.provide.enums.PType;
import org.nqcx.cg.provide.o.table.Table;
import org.nqcx.commons3.lang.enums.BoolEO;

import java.io.File;
import java.io.Serializable;

/**
 * @author naqichuan 2018/11/27 18:11
 */
public class Generate implements Serializable {

    // project field
    private String wsPath;
    private String author;
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
    private BoolEO provideO_ = BoolEO.FALSE;
    private String provideOPackage;
    private String provideO;
    private BoolEO provideProvide_ = BoolEO.FALSE;
    private String provideProvidePackage;
    private String provideProvide;
    // end of provide field

    // dao field
    private BoolEO dao_ = BoolEO.FALSE;
    private BoolEO daoPO_ = BoolEO.FALSE;
    private String daoPOPackage;
    private String daoPO;
    private BoolEO daoMapper_ = BoolEO.FALSE;
    private String daoMapperPackage;
    private String daoMapper;
    private BoolEO daoJpa_ = BoolEO.FALSE;
    private String daoJpaPackage;
    private String daoJpa;
    private BoolEO daoDAO_ = BoolEO.FALSE;
    private String daoDAOPackage;
    private String daoDAO;
    private BoolEO daoDAOImpl_ = BoolEO.FALSE;
    private String daoDAOImplPackage;
    private String daoDAOImpl;
    private BoolEO daoDAOTest_ = BoolEO.FALSE;
    private String daoDAOTestPackage;
    private String daoDAOTest;
    // end of dao field

    // service field
    private BoolEO service_ = BoolEO.FALSE;
    private BoolEO serviceDO_ = BoolEO.FALSE;
    private String serviceDOPackage;
    private String serviceDO;
    private BoolEO serviceService_ = BoolEO.FALSE;
    private String serviceServicePackage;
    private String serviceService;
    private BoolEO serviceServiceImpl_ = BoolEO.FALSE;
    private String serviceServiceImplPackage;
    private String serviceServiceImpl;
    private BoolEO serviceServiceTest_ = BoolEO.FALSE;
    private String serviceServiceTestPackage;
    private String serviceServiceTest;
    // end of field

    // web field
    private BoolEO web_ = BoolEO.FALSE;
    private BoolEO webVO_ = BoolEO.FALSE;
    private String webVOPackage;
    private String webVO;
    private BoolEO webController_ = BoolEO.FALSE;
    private String webControllerPackage;
    private String webController;
    // end of web field

    // generate resource ======================================================
    private File logFile;
    private Table table;
    private File provideModuleFile;
    private File daoModuleFile;
    private File serviceModuleFile;
    private File webModuleFile;

    private String idType;

    private String provideOReference;
    private String provideOVeriable;
    private String provideProvideReference;
    private String provideProvideVeriable;

    private String daoPOReference;
    private String daoPOVeriable;
    private String daoMapperReference;
    private String daoMapperVeriable;
    private String daoJpaReference;
    private String daoJpaVeriable;
    private String daoDAOReference;
    private String daoDAOVeriable;
    private String daoDAOImplReference;

    private String daoBaseTestPackage;
    private String daoBaseTest;
    private String daoBaseTestReference;

    private String serviceDOReference;
    private String serviceDOVeriable;
    private String serviceServiceReference;
    private String serviceServiceVeriable;
    private String serviceServiceImplReference;

    private String serviceBaseTestPackage;
    private String serviceBaseTest;
    private String serviceBaseTestReference;

    private String webVOReference;
    private String webVOVeriable;
    private String webControllerReference;
    private String webControllerVeriable;
    private String webAbstractControllerPackage;
    private String webAbstractController;
    private String webAbstractControllerReference;

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

    public String getWsPath() {
        return wsPath;
    }

    public void setWsPath(String wsPath) {
        this.wsPath = wsPath;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public BoolEO getProvideO_() {
        return provideO_;
    }

    public void setProvideO_(BoolEO provideO_) {
        this.provideO_ = provideO_;
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

    public BoolEO getProvideProvide_() {
        return provideProvide_;
    }

    public void setProvideProvide_(BoolEO provideProvide_) {
        this.provideProvide_ = provideProvide_;
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

    public BoolEO getDaoPO_() {
        return daoPO_;
    }

    public void setDaoPO_(BoolEO daoPO_) {
        this.daoPO_ = daoPO_;
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

    public BoolEO getDaoMapper_() {
        return daoMapper_;
    }

    public void setDaoMapper_(BoolEO daoMapper_) {
        this.daoMapper_ = daoMapper_;
    }

    public String getDaoMapper() {
        return daoMapper;
    }

    public void setDaoMapper(String daoMapper) {
        this.daoMapper = daoMapper;
    }

    public BoolEO getDaoJpa_() {
        return daoJpa_;
    }

    public void setDaoJpa_(BoolEO daoJpa_) {
        this.daoJpa_ = daoJpa_;
    }

    public String getDaoJpa() {
        return daoJpa;
    }

    public void setDaoJpa(String daoJpa) {
        this.daoJpa = daoJpa;
    }

    public BoolEO getDaoDAO_() {
        return daoDAO_;
    }

    public void setDaoDAO_(BoolEO daoDAO_) {
        this.daoDAO_ = daoDAO_;
    }

    public String getDaoDAO() {
        return daoDAO;
    }

    public void setDaoDAO(String daoDAO) {
        this.daoDAO = daoDAO;
    }


    public BoolEO getDaoDAOImpl_() {
        return daoDAOImpl_;
    }

    public void setDaoDAOImpl_(BoolEO daoDAOImpl_) {
        this.daoDAOImpl_ = daoDAOImpl_;
    }

    public String getDaoDAOImplPackage() {
        return daoDAOImplPackage;
    }

    public void setDaoDAOImplPackage(String daoDAOImplPackage) {
        this.daoDAOImplPackage = daoDAOImplPackage;
    }

    public String getDaoDAOImpl() {
        return daoDAOImpl;
    }

    public void setDaoDAOImpl(String daoDAOImpl) {
        this.daoDAOImpl = daoDAOImpl;
    }

    public BoolEO getDaoDAOTest_() {
        return daoDAOTest_;
    }

    public void setDaoDAOTest_(BoolEO daoDAOTest_) {
        this.daoDAOTest_ = daoDAOTest_;
    }

    public String getDaoDAOTestPackage() {
        return daoDAOTestPackage;
    }

    public void setDaoDAOTestPackage(String daoDAOTestPackage) {
        this.daoDAOTestPackage = daoDAOTestPackage;
    }

    public String getDaoDAOTest() {
        return daoDAOTest;
    }

    public void setDaoDAOTest(String daoDAOTest) {
        this.daoDAOTest = daoDAOTest;
    }

    public BoolEO getService_() {
        return service_;
    }

    public void setService_(BoolEO service_) {
        this.service_ = service_;
    }

    public BoolEO getServiceDO_() {
        return serviceDO_;
    }

    public void setServiceDO_(BoolEO serviceDO_) {
        this.serviceDO_ = serviceDO_;
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

    public BoolEO getServiceService_() {
        return serviceService_;
    }

    public void setServiceService_(BoolEO serviceService_) {
        this.serviceService_ = serviceService_;
    }

    public String getServiceService() {
        return serviceService;
    }

    public void setServiceService(String serviceService) {
        this.serviceService = serviceService;
    }

    public BoolEO getServiceServiceImpl_() {
        return serviceServiceImpl_;
    }

    public void setServiceServiceImpl_(BoolEO serviceServiceImpl_) {
        this.serviceServiceImpl_ = serviceServiceImpl_;
    }

    public String getServiceServiceImplPackage() {
        return serviceServiceImplPackage;
    }

    public void setServiceServiceImplPackage(String serviceServiceImplPackage) {
        this.serviceServiceImplPackage = serviceServiceImplPackage;
    }

    public String getServiceServiceImpl() {
        return serviceServiceImpl;
    }

    public void setServiceServiceImpl(String serviceServiceImpl) {
        this.serviceServiceImpl = serviceServiceImpl;
    }

    public BoolEO getServiceServiceTest_() {
        return serviceServiceTest_;
    }

    public void setServiceServiceTest_(BoolEO serviceServiceTest_) {
        this.serviceServiceTest_ = serviceServiceTest_;
    }

    public String getServiceServiceTestPackage() {
        return serviceServiceTestPackage;
    }

    public void setServiceServiceTestPackage(String serviceServiceTestPackage) {
        this.serviceServiceTestPackage = serviceServiceTestPackage;
    }

    public String getServiceServiceTest() {
        return serviceServiceTest;
    }

    public void setServiceServiceTest(String serviceServiceTest) {
        this.serviceServiceTest = serviceServiceTest;
    }

    public BoolEO getWeb_() {
        return web_;
    }

    public void setWeb_(BoolEO web_) {
        this.web_ = web_;
    }

    public BoolEO getWebVO_() {
        return webVO_;
    }

    public void setWebVO_(BoolEO webVO_) {
        this.webVO_ = webVO_;
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

    public BoolEO getWebController_() {
        return webController_;
    }

    public void setWebController_(BoolEO webController_) {
        this.webController_ = webController_;
    }

    public String getWebController() {
        return webController;
    }

    public void setWebController(String webController) {
        this.webController = webController;
    }

    // generate resource ======================================================

    public File getLogFile() {
        return logFile;
    }

    public void setLogFile(File logFile) {
        this.logFile = logFile;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }


    public File getProvideModuleFile() {
        return provideModuleFile;
    }

    public void setProvideModuleFile(File provideModuleFile) {
        this.provideModuleFile = provideModuleFile;
    }

    public File getDaoModuleFile() {
        return daoModuleFile;
    }

    public void setDaoModuleFile(File daoModuleFile) {
        this.daoModuleFile = daoModuleFile;
    }

    public File getServiceModuleFile() {
        return serviceModuleFile;
    }

    public void setServiceModuleFile(File serviceModuleFile) {
        this.serviceModuleFile = serviceModuleFile;
    }

    public File getWebModuleFile() {
        return webModuleFile;
    }

    public void setWebModuleFile(File webModuleFile) {
        this.webModuleFile = webModuleFile;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getProvideOReference() {
        return provideOReference;
    }

    public void setProvideOReference(String provideOReference) {
        this.provideOReference = provideOReference;
    }

    public String getProvideOVeriable() {
        return provideOVeriable;
    }

    public void setProvideOVeriable(String provideOVeriable) {
        this.provideOVeriable = provideOVeriable;
    }

    public String getProvideProvideReference() {
        return provideProvideReference;
    }

    public void setProvideProvideReference(String provideProvideReference) {
        this.provideProvideReference = provideProvideReference;
    }

    public String getProvideProvideVeriable() {
        return provideProvideVeriable;
    }

    public void setProvideProvideVeriable(String provideProvideVeriable) {
        this.provideProvideVeriable = provideProvideVeriable;
    }

    public String getDaoPOReference() {
        return daoPOReference;
    }

    public void setDaoPOReference(String daoPOReference) {
        this.daoPOReference = daoPOReference;
    }

    public String getDaoPOVeriable() {
        return daoPOVeriable;
    }

    public void setDaoPOVeriable(String daoPOVeriable) {
        this.daoPOVeriable = daoPOVeriable;
    }

    public String getDaoMapperReference() {
        return daoMapperReference;
    }

    public void setDaoMapperReference(String daoMapperReference) {
        this.daoMapperReference = daoMapperReference;
    }

    public String getDaoMapperVeriable() {
        return daoMapperVeriable;
    }

    public void setDaoMapperVeriable(String daoMapperVeriable) {
        this.daoMapperVeriable = daoMapperVeriable;
    }

    public String getDaoJpaReference() {
        return daoJpaReference;
    }

    public void setDaoJpaReference(String daoJpaReference) {
        this.daoJpaReference = daoJpaReference;
    }

    public String getDaoJpaVeriable() {
        return daoJpaVeriable;
    }

    public void setDaoJpaVeriable(String daoJpaVeriable) {
        this.daoJpaVeriable = daoJpaVeriable;
    }

    public String getDaoDAOReference() {
        return daoDAOReference;
    }

    public void setDaoDAOReference(String daoDAOReference) {
        this.daoDAOReference = daoDAOReference;
    }

    public String getDaoDAOVeriable() {
        return daoDAOVeriable;
    }

    public void setDaoDAOVeriable(String daoDAOVeriable) {
        this.daoDAOVeriable = daoDAOVeriable;
    }

    public String getDaoDAOImplReference() {
        return daoDAOImplReference;
    }

    public void setDaoDAOImplReference(String daoDAOImplReference) {
        this.daoDAOImplReference = daoDAOImplReference;
    }

    public String getDaoBaseTestPackage() {
        return daoBaseTestPackage;
    }

    public void setDaoBaseTestPackage(String daoBaseTestPackage) {
        this.daoBaseTestPackage = daoBaseTestPackage;
    }

    public String getDaoBaseTest() {
        return daoBaseTest;
    }

    public void setDaoBaseTest(String daoBaseTest) {
        this.daoBaseTest = daoBaseTest;
    }

    public String getDaoBaseTestReference() {
        return daoBaseTestReference;
    }

    public void setDaoBaseTestReference(String daoBaseTestReference) {
        this.daoBaseTestReference = daoBaseTestReference;
    }

    public String getServiceDOReference() {
        return serviceDOReference;
    }

    public void setServiceDOReference(String serviceDOReference) {
        this.serviceDOReference = serviceDOReference;
    }

    public String getServiceDOVeriable() {
        return serviceDOVeriable;
    }

    public void setServiceDOVeriable(String serviceDOVeriable) {
        this.serviceDOVeriable = serviceDOVeriable;
    }

    public String getServiceServiceReference() {
        return serviceServiceReference;
    }

    public void setServiceServiceReference(String serviceServiceReference) {
        this.serviceServiceReference = serviceServiceReference;
    }

    public String getServiceServiceVeriable() {
        return serviceServiceVeriable;
    }

    public void setServiceServiceVeriable(String serviceServiceVeriable) {
        this.serviceServiceVeriable = serviceServiceVeriable;
    }

    public String getServiceServiceImplReference() {
        return serviceServiceImplReference;
    }

    public void setServiceServiceImplReference(String serviceServiceImplReference) {
        this.serviceServiceImplReference = serviceServiceImplReference;
    }

    public String getServiceBaseTestPackage() {
        return serviceBaseTestPackage;
    }

    public void setServiceBaseTestPackage(String serviceBaseTestPackage) {
        this.serviceBaseTestPackage = serviceBaseTestPackage;
    }

    public String getServiceBaseTest() {
        return serviceBaseTest;
    }

    public void setServiceBaseTest(String serviceBaseTest) {
        this.serviceBaseTest = serviceBaseTest;
    }

    public String getServiceBaseTestReference() {
        return serviceBaseTestReference;
    }

    public void setServiceBaseTestReference(String serviceBaseTestReference) {
        this.serviceBaseTestReference = serviceBaseTestReference;
    }

    public String getWebVOReference() {
        return webVOReference;
    }

    public void setWebVOReference(String webVOReference) {
        this.webVOReference = webVOReference;
    }

    public String getWebVOVeriable() {
        return webVOVeriable;
    }

    public void setWebVOVeriable(String webVOVeriable) {
        this.webVOVeriable = webVOVeriable;
    }

    public String getWebControllerReference() {
        return webControllerReference;
    }

    public void setWebControllerReference(String webControllerReference) {
        this.webControllerReference = webControllerReference;
    }

    public String getWebControllerVeriable() {
        return webControllerVeriable;
    }

    public void setWebControllerVeriable(String webControllerVeriable) {
        this.webControllerVeriable = webControllerVeriable;
    }

    public String getWebAbstractControllerPackage() {
        return webAbstractControllerPackage;
    }

    public void setWebAbstractControllerPackage(String webAbstractControllerPackage) {
        this.webAbstractControllerPackage = webAbstractControllerPackage;
    }

    public String getWebAbstractController() {
        return webAbstractController;
    }

    public void setWebAbstractController(String webAbstractController) {
        this.webAbstractController = webAbstractController;
    }

    public String getWebAbstractControllerReference() {
        return webAbstractControllerReference;
    }

    public void setWebAbstractControllerReference(String webAbstractControllerReference) {
        this.webAbstractControllerReference = webAbstractControllerReference;
    }
}
