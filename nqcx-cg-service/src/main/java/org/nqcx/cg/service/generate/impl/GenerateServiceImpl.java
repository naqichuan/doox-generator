/*
 * Copyright 2014 nqcx.org All right reserved. This software is the
 * confidential and proprietary information of nqcx.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.cg.service.generate.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.nqcx.cg.common.util.CgFileUtils;
import org.nqcx.cg.entity.ws.enums.PType;
import org.nqcx.cg.provide.o.Generate;
import org.nqcx.cg.service.generate.GenerateService;
import org.nqcx.commons3.lang.o.DTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.EngineContext;
import org.thymeleaf.context.IContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

/**
 * @author naqichuan Feb 9, 2014 2:18:27 AM
 */
@Service
public class GenerateServiceImpl implements GenerateService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static String JAVA_PATH = "src/main/java/";
    private static String JAVA_EXT_NAME = ".java";
    private static String XML_EXT_NAME = ".xml";

    private static String ENTITY_TEMPLATE_NAME = "entity.vm";
    private static String MAPPER_JAVA_TEMPLATE_NAME = "mapper_java.vm";
    private static String MAPPER_XML_TEMPLATE_NAME = "mapper_xml.vm";
    private static String SERVICE_INTERFACE_TEMPLATE_NAME = "service_interface.vm";
    private static String SERVICE_IMPLEMENTS_TEMPLATE_NAME = "service_implements.vm";

    private static String P_PROVIDE_PATH_KEY = "provideModule";
    private static String P_DAO_PATH_KEY = "daoModule";
    private static String P_SERVICE_PATH_KEY = "serviceModule";
    private static String P_WEB_PATH_KEY = "webModule";

    @Autowired
    private String workspacePath;

    @Override
    public DTO generate(Generate g, String tableName, String pName, PType pType, String pPath, String pPackage,
                        String entityName,
                        String entityProjectName, String entityProjectPackage,
                        String mapperProjectName,
                        String mapperProjectPackage,
                        String serviceProjectName, String serviceProjectPackage,
                        String[] tableColumns, String[] provideField, String[] provideType,
                        String mapperInterface, String serviceInterface, String serviceImplement) {

        if (g == null)
            return new DTO(false).putResult("100", "生成代码失败！");

        if (!pPathExist(workspacePath, g.getpPath()))
            return new DTO(false).putResult("101", "工程路径不存在");

        Map<String, String> pathMap = this.getPathString(workspacePath, pPath, g.getProvideModule(),
                g.getDaoModule(), g.getServiceModule(), g.getWebModule());

        String providePath = pathMap.get(P_PROVIDE_PATH_KEY);
        if (g.getProvide_().isTrue()) {
            this.generateProvide(providePath, g.getPrivideO(), g.getProvideProvidePackage(), provideField, provideType);
        }

//        String mapperPath = pathMap.get(P_MAPPER_PATH_KEY);
//        if ((mapperPath != null || (PType.MULTIPLE == pType && isNotBlank(mapperProjectName)))
//                && isNotBlank(mapperProjectPackage) && isNotBlank(mapperInterface)) {
//            this.generateMapper(tableName, mapperPath, mapperInterface, mapperProjectPackage, entityName, tableColumns,
//                    entityField);
//        }
//
//        String daoInterface = mapperInterface;
//        String daoProjectPackage = mapperProjectPackage;
//
//        String servicePath = pathMap.get(P_SERVICE_PATH_KEY);
//        if ((servicePath != null || (PType.MULTIPLE == pType && isNotBlank(serviceProjectName)))
//                && isNotBlank(serviceInterface) && isNotBlank(serviceImplement) && isNotBlank(serviceProjectPackage)) {
//            this.generateService(serviceProjectName, servicePath, serviceInterface, serviceImplement,
//                    serviceProjectPackage, daoInterface, daoProjectPackage);
//        }

        return new DTO(true);
    }

    /**
     * @param wsPath
     * @param pPath
     * @return
     */
    private boolean pPathExist(String wsPath, String pPath) {
        File file = new File(wsPath + pPath);
        if (!file.exists())
            return false;
        return true;
    }

    /**
     * @param wsPath
     * @param pPath
     * @param provideModuleName
     * @param daoModuleName
     * @param serviceModuleName
     * @param webModuleName
     * @return
     */
    private Map<String, String> getPathString(String wsPath, String pPath,
                                              String provideModuleName,
                                              String daoModuleName,
                                              String serviceModuleName,
                                              String webModuleName) {
        Map<String, String> map = new HashMap<String, String>();

        String provideModulePath = wsPath + pPath + provideModuleName;
        if (new File(provideModulePath).exists())
            map.put(P_PROVIDE_PATH_KEY, provideModulePath);

        String daoModulePath = wsPath + pPath + daoModuleName;
        if (new File(daoModulePath).exists())
            map.put(P_DAO_PATH_KEY, daoModulePath);

        String serviceModulePath = wsPath + pPath + webModuleName;
        if ((new File(serviceModulePath)).exists())
            map.put(P_SERVICE_PATH_KEY, serviceModulePath);

        String webModulePath = wsPath + pPath + serviceModuleName;
        if ((new File(webModulePath)).exists())
            map.put(P_WEB_PATH_KEY, webModulePath);


        return map;
    }

    /**
     * @param entityPath
     * @param entityName
     * @param entityPackage
     * @param entityField
     * @param entityType
     */
    private void generateProvide(String entityPath, String entityName, String entityPackage, String[] entityField,
                                 String[] entityType) {

        Context cxt = new Context();
        cxt.setVariable("abcde", "aa");
//        Map<String, Object> model = new HashMap<String, Object>();
//        model.put("date", new Date());
//        model.put("entityName", entityName);
//        model.put("entityPackage", entityPackage);
//
//        List<String> entityFields = new ArrayList<String>();
//        StringBuffer entityFieldsTmp = null;
//        for (int i = 0; i < entityField.length; i++) {
//            entityFieldsTmp = new StringBuffer();
//            entityFieldsTmp.append("private ");
//            entityFieldsTmp.append(entityType[i]);
//            entityFieldsTmp.append(" ");
//            entityFieldsTmp.append(entityField[i]);
//            entityFieldsTmp.append(";");
//            entityFields.add(entityFieldsTmp.toString());
//        }
//        model.put("entityFields", entityFields);
//
//        List<String> entityMethods = new ArrayList<String>();
//        StringBuffer entityMethodsTmp = null;
//        for (int i = 0; i < entityField.length; i++) {
//            entityMethodsTmp = new StringBuffer();
//
//            entityMethodsTmp.append("\tpublic " + entityType[i] + " get" + StringUtils.capitalize(entityField[i])
//                    + "() {\r\n");
//            entityMethodsTmp.append("\t\treturn " + entityField[i] + ";\r\n");
//            entityMethodsTmp.append("\t}\r\n");
//            entityMethodsTmp.append("\r\n");
//            entityMethodsTmp.append("\tpublic void set" + StringUtils.capitalize(entityField[i]) + "(" + entityType[i]
//                    + " " + entityField[i] + ") {\r\n");
//            entityMethodsTmp.append("\t\tthis." + entityField[i] + " = " + entityField[i] + ";\r\n");
//            entityMethodsTmp.append("\t}\r\n");
//
//            entityMethods.add(entityMethodsTmp.toString());
//        }
//        model.put("entityMethods", entityMethods);
//
//        this.writeFile(entityPath + "/" + JAVA_PATH + entityPackage.replace('.', '/'), entityName + JAVA_EXT_NAME,
//                mergeTemplateIntoString(ENTITY_TEMPLATE_NAME, model));
        System.out.println(this.process("abcd", cxt));
    }

    /**
     * @param tableName
     * @param mapperPath
     * @param mapperName
     * @param mapperPackage
     */
    private void generateMapper(String tableName, String mapperPath, String mapperName, String mapperPackage,
                                String entityName, String[] tableColumns, String[] entityField) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("date", new Date());
        model.put("mapperName", mapperName);
        model.put("mapperPackage", mapperPackage);
        model.put("tableName", tableName);
        model.put("entityName", entityName);

        List<String> resultMap = new ArrayList<String>();
        List<String> entityFields = new ArrayList<String>();
        String tableColumnsStr = new String();
        String idColumn = new String();
        String idField = new String();
        String idFieldName = new String();

        if (tableColumns != null && entityField != null && tableColumns.length == entityField.length)
            for (int i = 0; i < entityField.length; i++) {
                if (i == 0) {
                    idColumn = tableColumns[i];
                    idFieldName = entityField[i];
                    idField = "#{" + entityField[i] + "}";
                }

                if ("id".equals(entityField[i])) {
                    resultMap.add("<id property=\"" + entityField[i] + "\" column=\"" + tableColumns[i] + "\" />");

                    entityFields.add("NULL");

                    idColumn = tableColumns[i];
                    idFieldName = entityField[i];
                    idField = "#{" + entityField[i] + "}";
                } else {
                    resultMap.add("<result property=\"" + entityField[i] + "\" column=\"" + tableColumns[i] + "\" />");

                    entityFields.add("#{" + entityField[i] + "}");
                }

                if (tableColumnsStr.length() == 0)
                    tableColumnsStr += tableColumns[i];
                else
                    tableColumnsStr += ", " + tableColumns[i];

            }
        model.put("idColumn", idColumn);
        model.put("idField", idField);
        model.put("idFieldName", idFieldName);
        model.put("tableColumnsStr", tableColumnsStr);
        model.put("resultMap", resultMap);
        model.put("entityFields", entityFields);
        model.put("tableColumns", tableColumns);

        this.writeFile(
                CgFileUtils.formatPath(mapperPath + "/" + JAVA_PATH + mapperPackage.replace('.', '/'), false, false),
                mapperName + JAVA_EXT_NAME, mergeTemplateIntoString(MAPPER_JAVA_TEMPLATE_NAME, model));

        this.writeFile(
                CgFileUtils.formatPath(mapperPath + "/" + JAVA_PATH + mapperPackage.replace('.', '/'), false, false),
                mapperName + XML_EXT_NAME, mergeTemplateIntoString(MAPPER_XML_TEMPLATE_NAME, model));
    }

    // /**
    // * @param managerProjectName
    // * @param managerPath
    // * @param managerInterface
    // * @param managerImplement
    // * // * @param managerProjectPackage
    // * @param mapperInterface
    // * @param mapperProjectPackage
    // */
    // private void generateManager(String managerProjectName, String managerPath, String managerInterface,
    // String managerImplement,/* String managerProjectPackage, */String mapperInterface,
    // String mapperProjectPackage) {
    //
    // Map<String, Object> modelInterface = new HashMap<String, Object>();
    // modelInterface.put("date", new Date());
    // modelInterface.put("managerInterface", managerInterface);
    // modelInterface.put("managerProjectPackage", managerProjectPackage);
    //
    // this.writeFile(CgFileUtils.formatPath(managerPath + "/" + JAVA_PATH + managerProjectPackage.replace('.', '/'),
    // false, false), managerInterface + JAVA_EXT_NAME,
    // mergeTemplateIntoString(MANAGERR_INTERFACE_TEMPLATE_NAME, modelInterface));
    //
    // Map<String, Object> modelImplement = new HashMap<String, Object>();
    // modelImplement.put("date", new Date());
    // modelImplement.put("managerInterface", managerInterface);
    // modelImplement.put("managerProjectPackage", managerProjectPackage);
    // modelImplement.put("managerImplements", managerImplement);
    // modelImplement.put("managerProjectPackage1", managerProjectPackage + ".impl");
    // modelImplement.put("mapperInterface", mapperInterface);
    // modelImplement.put("mapperInterface1", StringUtils.uncapitalize(mapperInterface));
    // modelImplement.put("mapperProjectPackage", mapperProjectPackage);
    //
    // this.writeFile(CgFileUtils.formatPath(
    // managerPath + "/" + JAVA_PATH + (managerProjectPackage + ".impl").replace('.', '/'), false, false),
    // managerImplement + JAVA_EXT_NAME,
    // mergeTemplateIntoString(MANAGERR_IMPLEMENTS_TEMPLATE_NAME, modelImplement));
    // }

    /**
     * @param serviceProjectName
     * @param servicePath
     * @param serviceInterface
     * @param serviceImplement
     * @param serviceProjectPackage
     * @param daoInterface
     * @param daoProjectPackage
     */
    private void generateService(String serviceProjectName, String servicePath, String serviceInterface,
                                 String serviceImplement, String serviceProjectPackage, String daoInterface, String daoProjectPackage) {

        Map<String, Object> modelInterface = new HashMap<String, Object>();
        modelInterface.put("date", new Date());
        modelInterface.put("serviceInterface", serviceInterface);
        modelInterface.put("serviceProjectPackage", serviceProjectPackage);

        this.writeFile(CgFileUtils.formatPath(servicePath + "/" + JAVA_PATH + serviceProjectPackage.replace('.', '/'),
                false, false), serviceInterface + JAVA_EXT_NAME,
                mergeTemplateIntoString(SERVICE_INTERFACE_TEMPLATE_NAME, modelInterface));

        Map<String, Object> modelImplement = new HashMap<String, Object>();
        modelImplement.put("date", new Date());
        modelImplement.put("serviceInterface", serviceInterface);
        modelImplement.put("serviceImplements", serviceImplement);
        modelImplement.put("serviceProjectPackage", serviceProjectPackage);
        modelImplement.put("serviceProjectPackage1", serviceProjectPackage + ".impl");
        modelImplement.put("daoInterface", daoInterface);
        modelImplement.put("daoInterface1", StringUtils.uncapitalize(daoInterface));
        modelImplement.put("daoProjectPackage", daoProjectPackage);

        this.writeFile(CgFileUtils.formatPath(
                servicePath + "/" + JAVA_PATH + (serviceProjectPackage + ".impl").replace('.', '/'), false, false),
                serviceImplement + JAVA_EXT_NAME,
                mergeTemplateIntoString(SERVICE_IMPLEMENTS_TEMPLATE_NAME, modelImplement));
    }

    /**
     * @return
     * @throws Exception
     */
    private VelocityEngine velocityEngine() {
        Properties properties = new Properties();
        properties.setProperty("input.encoding", "UTF-8");
        properties.setProperty("output.encoding", "UTF-8");

        properties.setProperty("resource.loader", "file");
        properties.setProperty("file.resource.loader.cache", "true");
        properties.setProperty("file.resource.loader.path", "classpath:/template/");

        properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        VelocityEngine velocityEngine = new VelocityEngine(properties);
        return velocityEngine;
    }

    /**
     * @param templateLocation
     * @param model
     * @return
     */
    private String mergeTemplateIntoString(String templateLocation, Map<String, Object> model) {
        VelocityEngine velocityEngine = velocityEngine();

        VelocityContext context = new VelocityContext(model);

        StringWriter stringWriter = new StringWriter();
        velocityEngine.mergeTemplate(templateLocation, "UTF-8", context, stringWriter);

        return stringWriter.toString();
    }

    private String process(String template, IContext context) {
        return templateEngine().process(template, context);
    }


    public TemplateEngine templateEngine() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

        templateResolver.setTemplateMode(TemplateMode.TEXT);
        templateResolver.setPrefix("/template/");
        templateResolver.setSuffix(".txt");
        templateResolver.setCacheTTLMs(Long.valueOf(3600000L));
        templateResolver.setCacheable(true);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        return templateEngine;
    }

    /**
     * @param path
     * @param content
     */
    private void writeFile(String path, String fileName, String content) {
        if (isBlank(path))
            return;
        if (content == null)
            content = "";

        File pathFile = new File(path);
        if (!pathFile.exists())
            pathFile.mkdirs();

        FileWriter fw = null;
        try {
            fw = new FileWriter(path + "/" + fileName);
            fw.write(content, 0, content.length());
            fw.flush();
        } catch (IOException e) {
            logger.error("", e);
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    logger.error("", e);
                }
            }
        }
    }

    /**
     * @param str
     * @return
     */
    private boolean isNotBlank(String str) {
        return StringUtils.isNotBlank(str);
    }

    /**
     * @param str
     * @return
     */
    private boolean isBlank(String str) {
        return StringUtils.isBlank(str);
    }
}
