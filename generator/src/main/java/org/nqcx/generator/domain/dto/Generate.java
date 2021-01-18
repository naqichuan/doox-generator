/*
 * Copyright 2018 nqcx.org All right reserved. This software is the confidential and proprietary information
 * of nqcx.org ("Confidential Information"). You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you entered into with nqcx.org.
 */

package org.nqcx.generator.domain.dto;

import org.nqcx.doox.commons.lang.enums.BoolEO;
import org.nqcx.generator.domain.dto.table.Table;
import org.nqcx.generator.domain.enums.PType;

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
    private String apiModule;
    private String daoModule;
    private String serviceModule;
    private String webModule;
    private String uiModule;
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

    // api field
    private BoolEO api_ = BoolEO.FALSE;
    private BoolEO apiDTO_ = BoolEO.FALSE;
    private BoolEO apiDTOOverwrite_ = BoolEO.FALSE;
    private String apiDTOPackage;
    private String apiDTO;
    private BoolEO apiApi_ = BoolEO.FALSE;
    private BoolEO apiApiOverwrite_ = BoolEO.FALSE;
    private String apiApiPackage;
    private String apiApi;
    // end of api field

    // dao field
    private BoolEO dao_ = BoolEO.FALSE;
    private BoolEO daoPO_ = BoolEO.FALSE;
    private BoolEO daoPOOverwrite_ = BoolEO.FALSE;
    private String daoPOPackage;
    private String daoPO;
    private BoolEO daoMapper_ = BoolEO.FALSE;
    private BoolEO daoMapperOverwrite_ = BoolEO.FALSE;
    private String daoMapperPackage;
    private String daoMapper;
    private BoolEO daoMapperXml_ = BoolEO.FALSE;
    private BoolEO daoMapperXmlOverwrite_ = BoolEO.FALSE;
    private String daoMapperXmlPackage;
    private String daoMapperXml;
    private BoolEO daoJpa_ = BoolEO.FALSE;
    private BoolEO daoJpaOverwrite_ = BoolEO.FALSE;
    private String daoJpaPackage;
    private String daoJpa;
    private BoolEO daoCacheSupport_ = BoolEO.FALSE;
    private BoolEO daoCacheSupportOverwrite_ = BoolEO.FALSE;
    private String daoCacheSupportPackage;
    private String daoCacheSupport;
    private BoolEO daoDAO_ = BoolEO.FALSE;
    private BoolEO daoDAOOverwrite_ = BoolEO.FALSE;
    private String daoDAOPackage;
    private String daoDAO;
    private BoolEO daoDAOImpl_ = BoolEO.FALSE;
    private BoolEO daoDAOImplOverwrite_ = BoolEO.FALSE;
    private String daoDAOImplPackage;
    private String daoDAOImpl;
    private BoolEO daoDAOTest_ = BoolEO.FALSE;
    private BoolEO daoDAOTestOverwrite_ = BoolEO.FALSE;
    private String daoDAOTestPackage;
    private String daoDAOTest;
    // end of dao field

    // service field
    private BoolEO service_ = BoolEO.FALSE;
    private BoolEO serviceVO_ = BoolEO.FALSE;
    private BoolEO serviceVOOverwrite_ = BoolEO.FALSE;
    private String serviceVOPackage;
    private String serviceVO;
    private BoolEO serviceService_ = BoolEO.FALSE;
    private BoolEO serviceServiceOverwrite_ = BoolEO.FALSE;
    private String serviceServicePackage;
    private String serviceService;
    private BoolEO serviceServiceImpl_ = BoolEO.FALSE;
    private BoolEO serviceServiceImplOverwrite_ = BoolEO.FALSE;
    private String serviceServiceImplPackage;
    private String serviceServiceImpl;
    private BoolEO serviceServiceTest_ = BoolEO.FALSE;
    private BoolEO serviceServiceTestOverwrite_ = BoolEO.FALSE;
    private String serviceServiceTestPackage;
    private String serviceServiceTest;
    // end of field

    // web field
    private BoolEO web_ = BoolEO.FALSE;
    private BoolEO webController_ = BoolEO.FALSE;
    private BoolEO webControllerOverwrite_ = BoolEO.FALSE;
    private String webControllerPackage;
    private String webController;
    private BoolEO webRestController_ = BoolEO.FALSE;
    private BoolEO webRestControllerOverwrite_ = BoolEO.FALSE;
    private String webRestControllerPackage;
    private String webRestController;
    // end of web field

    // ui field
    private BoolEO ui_ = BoolEO.FALSE;
    private BoolEO uiApi_ = BoolEO.FALSE;
    private BoolEO uiApiOverwrite_ = BoolEO.FALSE;
    private String uiApiPackage;
    private String uiApi;
    private BoolEO uiView_ = BoolEO.FALSE;
    private BoolEO uiViewOverwrite_ = BoolEO.FALSE;
    private String uiViewPackage;
    private String uiView;
    // end of ui field

    // generate resource ======================================================
    private File logFile;
    private Table table;
    private File apiModuleFile;
    private File daoModuleFile;
    private File serviceModuleFile;
    private File webModuleFile;
    private File uiModuleFile;

    private String idType;
    private String idName;

    private String apiDTOReference;
    private String apiDTOVeriable;
    private String apiApiReference;
    private String apiApiVeriable;

    private String daoPOReference;
    private String daoPOVeriable;
    private String daoMapperReference;
    private String daoMapperVeriable;
    private String daoJpaReference;
    private String daoJpaVeriable;
    private String daoCacheSupportReference;
    private String daoDAOReference;
    private String daoDAOVeriable;
    private String daoDAOImplReference;

    private String daoBaseTestPackage;
    private String daoBaseTest;
    private String daoBaseTestReference;

    private String serviceServiceReference;
    private String serviceServiceVeriable;
    private String serviceServiceImplReference;

    private String serviceBaseTestPackage;
    private String serviceBaseTest;
    private String serviceBaseTestReference;

    private String serviceVOReference;
    private String serviceVOVeriable;
    private String webControllerReference;
    private String webControllerVeriable;
    private String webRestControllerReference;
    private String webRestControllerVeriable;
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

    public String getBasedir() {
        return wsPath;
    }

    public void setBasedir(String basedir) {
        this.wsPath = basedir;
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

    public String getApiModule() {
        return apiModule;
    }

    public void setApiModule(String apiModule) {
        this.apiModule = apiModule;
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

    public String getUiModule() {
        return uiModule;
    }

    public void setUiModule(String uiModule) {
        this.uiModule = uiModule;
    }

    public BoolEO getApi_() {
        return api_;
    }

    public void setApi_(BoolEO api_) {
        this.api_ = api_;
    }

    public BoolEO getApiDTO_() {
        return apiDTO_;
    }

    public void setApiDTO_(BoolEO apiDTO_) {
        this.apiDTO_ = apiDTO_;
    }

    public BoolEO getApiDTOOverwrite_() {
        return apiDTOOverwrite_;
    }

    public void setApiDTOOverwrite_(BoolEO apiDTOOverwrite_) {
        this.apiDTOOverwrite_ = apiDTOOverwrite_;
    }

    public String getApiDTOPackage() {
        return apiDTOPackage;
    }

    public void setApiDTOPackage(String apiDTOPackage) {
        this.apiDTOPackage = apiDTOPackage;
    }

    public String getApiApiPackage() {
        return apiApiPackage;
    }

    public void setApiApiPackage(String apiApiPackage) {
        this.apiApiPackage = apiApiPackage;
    }

    public BoolEO getApiApi_() {
        return apiApi_;
    }

    public void setApiApi_(BoolEO apiApi_) {
        this.apiApi_ = apiApi_;
    }

    public BoolEO getApiApiOverwrite_() {
        return apiApiOverwrite_;
    }

    public void setApiApiOverwrite_(BoolEO apiApiOverwrite_) {
        this.apiApiOverwrite_ = apiApiOverwrite_;
    }

    public String getApiDTO() {
        return apiDTO;
    }

    public void setApiDTO(String apiDTO) {
        this.apiDTO = apiDTO;
    }

    public String getApiApi() {
        return apiApi;
    }

    public void setApiApi(String apiApi) {
        this.apiApi = apiApi;
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

    public BoolEO getDaoPOOverwrite_() {
        return daoPOOverwrite_;
    }

    public void setDaoPOOverwrite_(BoolEO daoPOOverwrite_) {
        this.daoPOOverwrite_ = daoPOOverwrite_;
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

    public BoolEO getDaoMapperOverwrite_() {
        return daoMapperOverwrite_;
    }

    public void setDaoMapperOverwrite_(BoolEO daoMapperOverwrite_) {
        this.daoMapperOverwrite_ = daoMapperOverwrite_;
    }

    public String getDaoMapper() {
        return daoMapper;
    }

    public void setDaoMapper(String daoMapper) {
        this.daoMapper = daoMapper;
    }

    public BoolEO getDaoMapperXml_() {
        return daoMapperXml_;
    }

    public void setDaoMapperXml_(BoolEO daoMapperXml_) {
        this.daoMapperXml_ = daoMapperXml_;
    }

    public BoolEO getDaoMapperXmlOverwrite_() {
        return daoMapperXmlOverwrite_;
    }

    public void setDaoMapperXmlOverwrite_(BoolEO daoMapperXmlOverwrite_) {
        this.daoMapperXmlOverwrite_ = daoMapperXmlOverwrite_;
    }

    public String getDaoMapperXmlPackage() {
        return daoMapperXmlPackage;
    }

    public void setDaoMapperXmlPackage(String daoMapperXmlPackage) {
        this.daoMapperXmlPackage = daoMapperXmlPackage;
    }

    public String getDaoMapperXml() {
        return daoMapperXml;
    }

    public void setDaoMapperXml(String daoMapperXml) {
        this.daoMapperXml = daoMapperXml;
    }

    public BoolEO getDaoJpa_() {
        return daoJpa_;
    }

    public void setDaoJpa_(BoolEO daoJpa_) {
        this.daoJpa_ = daoJpa_;
    }

    public BoolEO getDaoJpaOverwrite_() {
        return daoJpaOverwrite_;
    }

    public void setDaoJpaOverwrite_(BoolEO daoJpaOverwrite_) {
        this.daoJpaOverwrite_ = daoJpaOverwrite_;
    }

    public String getDaoJpa() {
        return daoJpa;
    }

    public void setDaoJpa(String daoJpa) {
        this.daoJpa = daoJpa;
    }


    public BoolEO getDaoCacheSupport_() {
        return daoCacheSupport_;
    }

    public void setDaoCacheSupport_(BoolEO daoCacheSupport_) {
        this.daoCacheSupport_ = daoCacheSupport_;
    }

    public BoolEO getDaoCacheSupportOverwrite_() {
        return daoCacheSupportOverwrite_;
    }

    public void setDaoCacheSupportOverwrite_(BoolEO daoCacheSupportOverwrite_) {
        this.daoCacheSupportOverwrite_ = daoCacheSupportOverwrite_;
    }

    public String getDaoCacheSupportPackage() {
        return daoCacheSupportPackage;
    }

    public void setDaoCacheSupportPackage(String daoCacheSupportPackage) {
        this.daoCacheSupportPackage = daoCacheSupportPackage;
    }

    public String getDaoCacheSupport() {
        return daoCacheSupport;
    }

    public void setDaoCacheSupport(String daoCacheSupport) {
        this.daoCacheSupport = daoCacheSupport;
    }

    public BoolEO getDaoDAO_() {
        return daoDAO_;
    }

    public void setDaoDAO_(BoolEO daoDAO_) {
        this.daoDAO_ = daoDAO_;
    }

    public BoolEO getDaoDAOOverwrite_() {
        return daoDAOOverwrite_;
    }

    public void setDaoDAOOverwrite_(BoolEO daoDAOOverwrite_) {
        this.daoDAOOverwrite_ = daoDAOOverwrite_;
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

    public BoolEO getDaoDAOImplOverwrite_() {
        return daoDAOImplOverwrite_;
    }

    public void setDaoDAOImplOverwrite_(BoolEO daoDAOImplOverwrite_) {
        this.daoDAOImplOverwrite_ = daoDAOImplOverwrite_;
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

    public BoolEO getDaoDAOTestOverwrite_() {
        return daoDAOTestOverwrite_;
    }

    public void setDaoDAOTestOverwrite_(BoolEO daoDAOTestOverwrite_) {
        this.daoDAOTestOverwrite_ = daoDAOTestOverwrite_;
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

    public String getServiceServicePackage() {
        return serviceServicePackage;
    }

    public void setServiceServicePackage(String serviceServicePackage) {
        this.serviceServicePackage = serviceServicePackage;
    }

    public BoolEO getServiceService_() {
        return serviceService_;
    }

    public void setServiceService_(BoolEO serviceService_) {
        this.serviceService_ = serviceService_;
    }

    public BoolEO getServiceServiceOverwrite_() {
        return serviceServiceOverwrite_;
    }

    public void setServiceServiceOverwrite_(BoolEO serviceServiceOverwrite_) {
        this.serviceServiceOverwrite_ = serviceServiceOverwrite_;
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

    public BoolEO getServiceServiceImplOverwrite_() {
        return serviceServiceImplOverwrite_;
    }

    public void setServiceServiceImplOverwrite_(BoolEO serviceServiceImplOverwrite_) {
        this.serviceServiceImplOverwrite_ = serviceServiceImplOverwrite_;
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

    public BoolEO getServiceServiceTestOverwrite_() {
        return serviceServiceTestOverwrite_;
    }

    public void setServiceServiceTestOverwrite_(BoolEO serviceServiceTestOverwrite_) {
        this.serviceServiceTestOverwrite_ = serviceServiceTestOverwrite_;
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

    public BoolEO getServiceVO_() {
        return serviceVO_;
    }

    public void setServiceVO_(BoolEO serviceVO_) {
        this.serviceVO_ = serviceVO_;
    }

    public BoolEO getServiceVOOverwrite_() {
        return serviceVOOverwrite_;
    }

    public void setServiceVOOverwrite_(BoolEO serviceVOOverwrite_) {
        this.serviceVOOverwrite_ = serviceVOOverwrite_;
    }

    public String getServiceVOPackage() {
        return serviceVOPackage;
    }

    public String getServiceVO() {
        return serviceVO;
    }

    public void setServiceVO(String serviceVO) {
        this.serviceVO = serviceVO;
    }

    public void setServiceVOPackage(String serviceVOPackage) {
        this.serviceVOPackage = serviceVOPackage;
    }

    public BoolEO getWeb_() {
        return web_;
    }

    public void setWeb_(BoolEO web_) {
        this.web_ = web_;
    }

    public String getWebControllerPackage() {
        return webControllerPackage;
    }

    public void setWebControllerPackage(String webControllerPackage) {
        this.webControllerPackage = webControllerPackage;
    }

    public BoolEO getWebController_() {
        return webController_;
    }

    public void setWebController_(BoolEO webController_) {
        this.webController_ = webController_;
    }

    public BoolEO getWebControllerOverwrite_() {
        return webControllerOverwrite_;
    }

    public void setWebControllerOverwrite_(BoolEO webControllerOverwrite_) {
        this.webControllerOverwrite_ = webControllerOverwrite_;
    }

    public String getWebController() {
        return webController;
    }

    public void setWebController(String webController) {
        this.webController = webController;
    }

    public BoolEO getWebRestController_() {
        return webRestController_;
    }

    public void setWebRestController_(BoolEO webRestController_) {
        this.webRestController_ = webRestController_;
    }

    public BoolEO getWebRestControllerOverwrite_() {
        return webRestControllerOverwrite_;
    }

    public void setWebRestControllerOverwrite_(BoolEO webRestControllerOverwrite_) {
        this.webRestControllerOverwrite_ = webRestControllerOverwrite_;
    }

    public String getWebRestControllerPackage() {
        return webRestControllerPackage;
    }

    public void setWebRestControllerPackage(String webRestControllerPackage) {
        this.webRestControllerPackage = webRestControllerPackage;
    }

    public String getWebRestController() {
        return webRestController;
    }

    public void setWebRestController(String webRestController) {
        this.webRestController = webRestController;
    }

    public BoolEO getUi_() {
        return ui_;
    }

    public void setUi_(BoolEO ui_) {
        this.ui_ = ui_;
    }

    public BoolEO getUiApi_() {
        return uiApi_;
    }

    public void setUiApi_(BoolEO uiApi_) {
        this.uiApi_ = uiApi_;
    }

    public BoolEO getUiApiOverwrite_() {
        return uiApiOverwrite_;
    }

    public void setUiApiOverwrite_(BoolEO uiApiOverwrite_) {
        this.uiApiOverwrite_ = uiApiOverwrite_;
    }

    public String getUiApiPackage() {
        return uiApiPackage;
    }

    public void setUiApiPackage(String uiApiPackage) {
        this.uiApiPackage = uiApiPackage;
    }

    public String getUiApi() {
        return uiApi;
    }

    public void setUiApi(String uiApi) {
        this.uiApi = uiApi;
    }

    public BoolEO getUiView_() {
        return uiView_;
    }

    public void setUiView_(BoolEO uiView_) {
        this.uiView_ = uiView_;
    }

    public BoolEO getUiViewOverwrite_() {
        return uiViewOverwrite_;
    }

    public void setUiViewOverwrite_(BoolEO uiViewOverwrite_) {
        this.uiViewOverwrite_ = uiViewOverwrite_;
    }

    public String getUiViewPackage() {
        return uiViewPackage;
    }

    public void setUiViewPackage(String uiViewPackage) {
        this.uiViewPackage = uiViewPackage;
    }

    public String getUiView() {
        return uiView;
    }

    public void setUiView(String uiView) {
        this.uiView = uiView;
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


    public File getApiModuleFile() {
        return apiModuleFile;
    }

    public void setApiModuleFile(File apiModuleFile) {
        this.apiModuleFile = apiModuleFile;
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

    public File getUiModuleFile() {
        return uiModuleFile;
    }

    public void setUiModuleFile(File uiModuleFile) {
        this.uiModuleFile = uiModuleFile;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public String getApiDTOReference() {
        return apiDTOReference;
    }

    public void setApiDTOReference(String apiDTOReference) {
        this.apiDTOReference = apiDTOReference;
    }

    public String getApiDTOVeriable() {
        return apiDTOVeriable;
    }

    public void setApiDTOVeriable(String apiDTOVeriable) {
        this.apiDTOVeriable = apiDTOVeriable;
    }

    public String getApiApiReference() {
        return apiApiReference;
    }

    public void setApiApiReference(String apiApiReference) {
        this.apiApiReference = apiApiReference;
    }

    public String getApiApiVeriable() {
        return apiApiVeriable;
    }

    public void setApiApiVeriable(String apiApiVeriable) {
        this.apiApiVeriable = apiApiVeriable;
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

    public String getDaoCacheSupportReference() {
        return daoCacheSupportReference;
    }

    public void setDaoCacheSupportReference(String daoCacheSupportReference) {
        this.daoCacheSupportReference = daoCacheSupportReference;
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

    public String getServiceVOReference() {
        return serviceVOReference;
    }

    public void setServiceVOReference(String serviceVOReference) {
        this.serviceVOReference = serviceVOReference;
    }

    public String getServiceVOVeriable() {
        return serviceVOVeriable;
    }

    public void setServiceVOVeriable(String serviceVOVeriable) {
        this.serviceVOVeriable = serviceVOVeriable;
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

    public String getWebRestControllerReference() {
        return webRestControllerReference;
    }

    public void setWebRestControllerReference(String webRestControllerReference) {
        this.webRestControllerReference = webRestControllerReference;
    }

    public String getWebRestControllerVeriable() {
        return webRestControllerVeriable;
    }

    public void setWebRestControllerVeriable(String webRestControllerVeriable) {
        this.webRestControllerVeriable = webRestControllerVeriable;
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
