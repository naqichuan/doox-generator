/*
 * Copyright 2018 nqcx.org All right reserved. This software is the
 * confidential and proprietary information of nqcx.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.cg.service.generate.impl;

import org.apache.commons.lang3.StringUtils;
import org.nqcx.cg.provide.o.CgField;
import org.nqcx.cg.provide.o.Generate;
import org.nqcx.cg.provide.o.table.Column;
import org.nqcx.cg.provide.o.table.Table;
import org.nqcx.cg.service.generate.GenerateService;
import org.nqcx.cg.service.table.TableService;
import org.nqcx.commons3.lang.o.DTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author naqichuan Feb 9, 2014 2:18:27 AM
 */
@Service
public class GenerateServiceImpl implements GenerateService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // database type to type and length
    private final static Pattern TYPE_LENGTH_PATTERN = Pattern.compile("(.+?)(\\(((\\d+?))\\))*?");
    // Class name mapping to class reference
    private final static Map<String, String> CLASS_MAPPING = new HashMap<>();

    private final static String JAVA_PATH = "src/main/java/";
    private final static String TEST_PATH = "src/test/java/";
    private final static String JAVA_EXT_NAME = ".java";
    private final static String XML_EXT_NAME = ".xml";

    private final static String BO_TXT_TEMPLATE_NAME = "bo.txt";
    private final static String O_TXT_TEMPLATE_NAME = "o.txt";
    private final static String PROVIDE_TXT_TEMPLATE_NAME = "provide.txt";
    private final static String PO_TXT_TEMPLATE_NAME = "po.txt";
    private final static String MAPPER_TXT_TEMPLATE_NAME = "mapper.txt";
    private final static String MAPPERXML_TXT_TEMPLATE_NAME = "mapperxml.txt";
    private final static String JPA_TXT_TEMPLATE_NAME = "jpa.txt";
    private final static String DAO_TXT_TEMPLATE_NAME = "dao.txt";
    private final static String DAO_TEST_TXT_TEMPLATE_NAME = "daotest.txt";
    private final static String DAOIMPL_TXT_TEMPLATE_NAME = "daoimpl.txt";
    private final static String DO_TXT_TEMPLATE_NAME = "do.txt";
    private final static String SERVICE_TXT_TEMPLATE_NAME = "service.txt";
    private final static String SERVICEIMPL_TXT_TEMPLATE_NAME = "serviceimpl.txt";
    private final static String SERVICETEST_TXT_TEMPLATE_NAME = "servicetest.txt";
    private final static String VO_TXT_TEMPLATE_NAME = "vo.txt";
    private final static String CONTROLLER_TXT_TEMPLATE_NAME = "controller.txt";

    private final static String P_PROVIDE_PATH_KEY = "provideModule";
    private final static String P_DAO_PATH_KEY = "daoModule";
    private final static String P_SERVICE_PATH_KEY = "serviceModule";
    private final static String P_WEB_PATH_KEY = "webModule";

    @Autowired
    @Qualifier("workspacePath")
    private String workspacePath;
    @Autowired
    @Qualifier("workspaceAuthor")
    private String workspaceAuthor;
    @Autowired
    @Qualifier("overwrite")
    private Boolean overwrite; // 生成文件是否覆盖原来的文件
    @Autowired
    private TableService tableService;

    static {
        CLASS_MAPPING.put("Serializable", "java.io.Serializable");

        CLASS_MAPPING.put("Integer", "java.lang.Integer");
        CLASS_MAPPING.put("Long", "java.lang.Long");

        CLASS_MAPPING.put("Date", "java.util.Date");
        CLASS_MAPPING.put("Arrays", "java.util.Arrays");
        CLASS_MAPPING.put("ArrayList", "java.util.ArrayList");
        CLASS_MAPPING.put("List", "java.util.List");
        CLASS_MAPPING.put("Optional", "java.util.Optional");

        CLASS_MAPPING.put("Entity", "javax.persistence.Entity");
        CLASS_MAPPING.put("Table", "javax.persistence.Table");
        CLASS_MAPPING.put("ID", "javax.persistence.Id");
        CLASS_MAPPING.put("GenerationType", "javax.persistence.GenerationType");
        CLASS_MAPPING.put("Column", "javax.persistence.Column");
        CLASS_MAPPING.put("GeneratedValue", "javax.persistence.GeneratedValue");
        CLASS_MAPPING.put("Temporal", "javax.persistence.Temporal");

        CLASS_MAPPING.put("DTO", "org.nqcx.commons3.lang.o.DTO");
        CLASS_MAPPING.put("NPage", "org.nqcx.commons3.lang.o.NPage");
        CLASS_MAPPING.put("NSort", "org.nqcx.commons3.lang.o.NSort");

        CLASS_MAPPING.put("IMapper", "org.nqcx.commons3.data.mapper.IMapper");
        CLASS_MAPPING.put("MapperSupport", "org.nqcx.commons3.data.mapper.MapperSupport");
        CLASS_MAPPING.put("IJpa", "org.nqcx.commons3.data.jpa.IJpa");
        CLASS_MAPPING.put("JpaSupport", "org.nqcx.commons3.data.jpa.JpaSupport");
        CLASS_MAPPING.put("IDao", "org.nqcx.commons3.dao.IDao");
        CLASS_MAPPING.put("DaoSupport", "org.nqcx.commons3.dao.DaoSupport");

        CLASS_MAPPING.put("IService", "org.nqcx.commons3.service.IService");
        CLASS_MAPPING.put("ServiceSupport", "org.nqcx.commons3.service.ServiceSupport");

        CLASS_MAPPING.put("stereotype.Service", "org.springframework.stereotype.Service");
        CLASS_MAPPING.put("Autowired", "org.springframework.beans.factory.annotation.Autowired");

        CLASS_MAPPING.put("test.Test", "org.junit.Test");
        CLASS_MAPPING.put("test.RunWith", "org.junit.runner.RunWith");
        CLASS_MAPPING.put("test.TestCase", "junit.framework.TestCase");
        CLASS_MAPPING.put("test.ContextConfiguration", "org.springframework.test.context.ContextConfiguration");
        CLASS_MAPPING.put("test.SpringJUnit4ClassRunner", "org.springframework.test.context.junit4.SpringJUnit4ClassRunner");
        CLASS_MAPPING.put("test.DependencyInjectionTestExecutionListener", "org.springframework.test.context.support.DependencyInjectionTestExecutionListener");
        CLASS_MAPPING.put("test.TransactionalTestExecutionListener", "org.springframework.test.context.transaction.TransactionalTestExecutionListener");
        CLASS_MAPPING.put("test.TestExecutionListeners", "org.springframework.test.context.TestExecutionListeners");
    }

    /**
     * generate code main method
     *
     * @param g generate o
     * @return DTO
     */
    @Override
    public DTO generate(Generate g) {
        if (g == null)
            return new DTO(false).putResult("100", "生成代码失败！");

        if (!pPathExist(workspacePath, g.getpPath()))
            return new DTO(false).putResult("101", "工程路径不存在");

        // 写入空行到日志
        File cgLogFile = new File(workspacePath + g.getpPath() + "/cglog.log");
        if (cgLogFile.exists())
            this.writeLog(cgLogFile, "");

        // 取表
        DTO trd = tableService.getTable(g.getTableName());
        Table table;
        if (trd == null || !trd.isSuccess() || (table = trd.getObject()) == null)
            return new DTO(false).putResult("102", "表不存在！");
        fillPojo(table, g.getPojoColumn(), g.getPojoField(), g.getPojoType());

        // 生成 module path
        Map<String, String> pathMap = this.getPathString(workspacePath, g.getpPath(), g.getProvideModule(),
                g.getDaoModule(), g.getServiceModule(), g.getWebModule());

        // provide
        CLASS_MAPPING.put(g.getProvideBO(), g.getProvideBOPackage() + "." + g.getProvideBO());
        CLASS_MAPPING.put(g.getProvideO(), g.getProvideOPackage() + "." + g.getProvideO());
        CLASS_MAPPING.put(g.getProvideProvide(), g.getProvideProvidePackage() + "." + g.getProvideProvide());

        String providePath = pathMap.get(P_PROVIDE_PATH_KEY);
        if (g.getProvide_().isTrue()) {
            this.generateProvide(cgLogFile, table, providePath, g.getProvideBOPackage(), g.getProvideBO(),
                    g.getProvideOPackage(), g.getProvideO(),
                    g.getProvideProvidePackage(), g.getProvideProvide());
        }

        // dao
        CLASS_MAPPING.put(g.getDaoPO(), g.getDaoPOPackage() + "." + g.getDaoPO());
        CLASS_MAPPING.put(g.getDaoMapper(), g.getDaoMapperPackage() + "." + g.getDaoMapper());
        CLASS_MAPPING.put(g.getDaoJpa(), g.getDaoJpaPackage() + "." + g.getDaoJpa());
        CLASS_MAPPING.put(g.getDaoDAO(), g.getDaoDAOPackage() + "." + g.getDaoDAO());
        CLASS_MAPPING.put(g.getDaoDAOImpl(), g.getDaoDAOPackage() + ".impl." + g.getDaoDAOImpl());

        String daoPath = pathMap.get(P_DAO_PATH_KEY);
        if (g.getDao_().isTrue()) {
            this.generateDao(cgLogFile, table, daoPath, g.getDaoPOPackage(), g.getDaoPO(),
                    g.getDaoMapperPackage(), g.getDaoMapper(),
                    g.getDaoJpaPackage(), g.getDaoJpa(),
                    g.getDaoDAOPackage(), g.getDaoDAO(), g.getDaoDAOPackage() + ".impl", g.getDaoDAOImpl(),
                    g.getProvideBO());
        }

        // service
        CLASS_MAPPING.put(g.getServiceDO(), g.getServiceDOPackage() + "." + g.getServiceDO());
        CLASS_MAPPING.put(g.getServiceService(), g.getServiceServicePackage() + "." + g.getServiceService());
        CLASS_MAPPING.put(g.getServceServiceImpl(), g.getServiceServicePackage() + ".impl." + g.getServceServiceImpl());

        String servicePath = pathMap.get(P_SERVICE_PATH_KEY);
        if (g.getService_().isTrue()) {
            this.generateService(cgLogFile, table, servicePath, g.getServiceDOPackage(), g.getServiceDO(),
                    g.getServiceServicePackage(), g.getServiceService(), g.getServiceServicePackage() + ".impl", g.getServceServiceImpl(),
                    g.getProvideProvide(), g.getDaoDAO(), g.getDaoPO());
        }

        // web
        CLASS_MAPPING.put(g.getWebVO(), g.getWebVOPackage() + "." + g.getWebVO());
        CLASS_MAPPING.put(g.getWebController(), g.getWebControllerPackage() + "." + g.getWebController());

        String webPath = pathMap.get(P_WEB_PATH_KEY);
        if (g.getWeb_().isTrue()) {
            this.generateWeb(cgLogFile, table, webPath, g.getWebVOPackage(), g.getWebVO(),
                    g.getWebControllerPackage(), g.getWebController(),
                    g.getProvideO(), g.getServiceDO());
        }

        return new DTO(true);
    }

    /**
     * @param table
     * @param columns
     * @param fields
     * @param types
     */
    private void fillPojo(Table table, String[] columns, String[] fields, String[] types) {
        if (table == null || table.getColumns() == null
                || columns == null || table.getColumns().size() != columns.length
                || fields == null || table.getColumns().size() != fields.length
                || types == null || table.getColumns().size() != types.length)
            return;

        Column c;
        for (int i = 0; i < table.getColumns().size(); i++) {
            if ((c = table.getColumns().get(i)) == null
                    || !c.getField().equalsIgnoreCase(columns[i]))
                continue;

            // column type and length
            Matcher matcher = TYPE_LENGTH_PATTERN.matcher(c.getType());
            if (matcher.matches() && matcher.groupCount() == 4) {
                if (matcher.group(1) != null)
                    c.setColumnType(matcher.group(1));
                if (matcher.group(3) != null)
                    c.setColumnLength(matcher.group(3));
            }

            c.setField_(fields[i]);
            c.setType_(types[i]);
            c.setNull_(true);

            if ("NO".equals(c.getIsNull())) {
                c.setNull_(false);
            }

            if ("PRI".equalsIgnoreCase(c.getKey()) && StringUtils.containsIgnoreCase(c.getField(), "ID")) {
                c.setId_(true);
                c.setIdType_("INT".equalsIgnoreCase(types[i]) ? "Integer" : "Long");

                c.setMybatisValue("NULL");
            }

            if ("DATETIME".equalsIgnoreCase(c.getType())
                    && StringUtils.containsIgnoreCase(c.getField(), "_create")) {
                c.setCm_(true);

                c.setMybatisValue("NOW()");
            }
            if ("TIMESTAMP".equalsIgnoreCase(c.getType())
                    && StringUtils.containsIgnoreCase(c.getField(), "_modify")) {
                c.setCm_(true);

                c.setMybatisValue("NULL");
            }
        }
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

        String serviceModulePath = wsPath + pPath + serviceModuleName;
        if ((new File(serviceModulePath)).exists())
            map.put(P_SERVICE_PATH_KEY, serviceModulePath);

        String webModulePath = wsPath + pPath + webModuleName;
        if ((new File(webModulePath)).exists())
            map.put(P_WEB_PATH_KEY, webModulePath);

        return map;
    }

    /**
     * @param imports
     * @param className
     */
    private void mappingImport(Set<String> imports, String className) {
        if (imports == null || className == null)
            return;

        String name = CLASS_MAPPING.get(className);
        if (name != null && name.length() > 0)
            imports.add(name);
    }

    /**
     * @param logFile
     * @param table
     * @param providePath
     * @param boPackage
     * @param boName
     * @param oPackage
     * @param oName
     * @param providePackage
     * @param provideName
     */
    private void generateProvide(File logFile, Table table, String providePath, String boPackage, String boName,
                                 String oPackage, String oName,
                                 String providePackage, String provideName) {

        Context cxt = new Context();
        Set<String> imports = new LinkedHashSet<>();

        // bo
        mappingImport(imports, "Serializable");

        cxt.setVariable("author", workspaceAuthor);
        cxt.setVariable("date", new Date());
        cxt.setVariable("package", boPackage);
        cxt.setVariable("imports", imports);
        cxt.setVariable("name", boName);

        // bo field
        List<String> boFields = new ArrayList<>();
        // bo field comment
        List<String> boFieldComments = new ArrayList<>();
        // getter & setter
        List<CgField> boGetterAndSetters = new ArrayList<>();

        table.getColumns().forEach(c -> {
            if (c.isCm_())
                return;

            mappingImport(imports, c.getType_());

            if(c.getComment() != null && c.getComment().length() > 0)
                boFieldComments.add("// " + c.getComment());
            else
                boFieldComments.add("");
            boFields.add(String.format("private %s %s;", c.getType_(), c.getField_()));

            CgField field = new CgField();
            field.setType(c.getType_());
            field.setField(c.getField_());
            field.setName(StringUtils.capitalize(c.getField_()));

            boGetterAndSetters.add(field);
        });

        cxt.setVariable("boFields", boFields);
        cxt.setVariable("boFieldComments", boFieldComments);
        cxt.setVariable("boGetterAndSetter", boGetterAndSetters);

        this.writeFile(logFile, providePath + "/" + JAVA_PATH + boPackage.replace('.', '/'), boName, JAVA_EXT_NAME,
                process(BO_TXT_TEMPLATE_NAME, cxt));

        // o
        cxt.clearVariables();
        imports.clear();
        mappingImport(imports, boName);

        cxt.setVariable("author", workspaceAuthor);
        cxt.setVariable("date", new Date());
        cxt.setVariable("package", oPackage);
        cxt.setVariable("imports", imports);
        cxt.setVariable("name", oName);

        cxt.setVariable("boName", boName);

        this.writeFile(logFile, providePath + "/" + JAVA_PATH + oPackage.replace('.', '/'), oName, JAVA_EXT_NAME,
                process(O_TXT_TEMPLATE_NAME, cxt));

        // provide
        cxt.clearVariables();
        imports.clear();

        cxt.setVariable("author", workspaceAuthor);
        cxt.setVariable("date", new Date());
        cxt.setVariable("package", providePackage);
        cxt.setVariable("imports", imports);
        cxt.setVariable("name", provideName);

        this.writeFile(logFile, providePath + "/" + JAVA_PATH + providePackage.replace('.', '/'), provideName, JAVA_EXT_NAME,
                process(PROVIDE_TXT_TEMPLATE_NAME, cxt));
    }

    /**
     * @param logFile
     * @param table
     * @param daoPath
     * @param poPackage
     * @param po
     * @param mapperPackage
     * @param mapper
     * @param jpaPackage
     * @param jpa
     * @param daoPackage
     * @param dao
     * @param daoImplPackage
     * @param daoImpl
     * @param boName
     */
    private void generateDao(File logFile, Table table, String daoPath, String poPackage, String po,
                             String mapperPackage, String mapper,
                             String jpaPackage, String jpa,
                             String daoPackage, String dao, String daoImplPackage, String daoImpl,
                             String boName) {

        Context cxt = new Context();
        Set<String> imports = new LinkedHashSet<>();

        // ID type
        String idType = "";
        if (table.getIdColumn() != null)
            idType = table.getIdColumn().getIdType_();

        // po
        mappingImport(imports, boName);
        mappingImport(imports, "Entity");
        mappingImport(imports, "Table");
        mappingImport(imports, "Column");

        cxt.setVariable("author", workspaceAuthor);
        cxt.setVariable("date", new Date());
        cxt.setVariable("package", poPackage);
        cxt.setVariable("imports", imports);
        cxt.setVariable("name", po);

        cxt.setVariable("boName", boName);
        cxt.setVariable("tableName", table.getName());

        // bo field
        List<String> poFields = new ArrayList<>();
        // po getter
        List<CgField> poGetter = new ArrayList<>();
        // getter & setter
        List<CgField> poGetterAndSetters = new ArrayList<>();
        table.getColumns().forEach(c -> {
            mappingImport(imports, c.getType_());

            CgField field = new CgField();
            field.setType(c.getType_());
            field.setField(c.getField_());
            field.setName(StringUtils.capitalize(c.getField_()));
            field.setAnnotations(new LinkedHashSet<>());

            // annotations
            if (c.isId_()) {
                mappingImport(imports, "ID");
                mappingImport(imports, "GeneratedValue");
                mappingImport(imports, "GenerationType");

                field.getAnnotations().add("@Override");
                field.getAnnotations().add("@Id");
                field.getAnnotations().add("@GeneratedValue(strategy = GenerationType.IDENTITY)");
            }

            String colAnno = "name = \"" + c.getField() + "\"";
            if (c.isNull_())
                colAnno += ", nullable = true";
            else
                colAnno += ", nullable = false";

            if (c.getColumnLength() != null)
                colAnno += ", length = " + c.getColumnLength();

            if (c.isCm_()) {
                colAnno += ", insertable = false, updatable = false";
                String columnDefinition = c.getType_().toUpperCase();
                if (c.getDefaultValue() != null && c.getDefaultValue().length() > 0) {
                    if (columnDefinition.length() != 0)
                        columnDefinition += " ";
                    columnDefinition += "DEFAULT " + c.getDefaultValue().toUpperCase();
                }

                if (c.getExtra() != null && c.getExtra().length() > 0) {
                    if (columnDefinition.length() != 0)
                        columnDefinition += " ";

                    columnDefinition += c.getExtra().toUpperCase();
                }
                colAnno += ", columnDefinition = \"" + columnDefinition + "\"";

                poFields.add(String.format("private %s %s;", c.getType_(), c.getField_()));
                cxt.setVariable("poFields", poFields);

                cxt.setVariable("poGetterAndSetter", poGetterAndSetters);

                poGetterAndSetters.add(field);
            } else {
                cxt.setVariable("poGetter", poGetter);

                field.getAnnotations().add("@Override");

                poGetter.add(field);
            }


            field.getAnnotations().add("@Column(" + colAnno + ")");
        });


        this.writeFile(logFile, daoPath + "/" + JAVA_PATH + poPackage.replace('.', '/'),
                po, JAVA_EXT_NAME,
                process(PO_TXT_TEMPLATE_NAME, cxt));

        // mapper
        cxt.clearVariables();
        imports.clear();

        mappingImport(imports, "IMapper");
        mappingImport(imports, po);

        cxt.setVariable("author", workspaceAuthor);
        cxt.setVariable("date", new Date());
        cxt.setVariable("package", mapperPackage);
        cxt.setVariable("imports", imports);
        cxt.setVariable("name", mapper);

        cxt.setVariable("poName", po);
        cxt.setVariable("idType", idType);

        this.writeFile(logFile, daoPath + "/" + JAVA_PATH + mapperPackage.replace('.', '/'),
                mapper, JAVA_EXT_NAME,
                process(MAPPER_TXT_TEMPLATE_NAME, cxt));
        // end of mapper

        // mapper xml
        cxt.clearVariables();
        imports.clear();

        cxt.setVariable("namespace", mapperPackage + "." + mapper);
        cxt.setVariable("tableName", table.getName());
        cxt.setVariable("po", po);

        cxt.setVariable("idType", idType);

        List<String> resultMap = new ArrayList<>();
        List<String> poInsertColumns = new ArrayList<>();
        List<String> poInsertFields = new ArrayList<>();

        List<String> poUpdateColumns = new ArrayList<>();
        List<String> poUpdateFields = new ArrayList<>();

        final String[] tableColumnsStr = {""};
        final String[] idColumn = {table.getIdColumn() != null ? table.getIdColumn().getField() : null};
        final String[] idFieldName = {table.getIdColumn() != null ? table.getIdColumn().getField_() : null};
        final String[] idField = {table.getIdColumn() != null ? "#{" + table.getIdColumn().getField_() + "}" : null};

        table.getColumns().forEach(c -> {
            if (tableColumnsStr[0].length() > 0)
                tableColumnsStr[0] += ", ";
            tableColumnsStr[0] += c.getField();

            poInsertColumns.add(c.getField());

            if (c.isId_()) {
                resultMap.add("<id property=\"" + c.getField_() + "\" column=\"" + c.getField() + "\" />");

                poInsertFields.add(c.getMybatisValue());
            } else {
                resultMap.add("<result property=\"" + c.getField_() + "\" column=\"" + c.getField() + "\" />");

                if (c.isCm_()) {
                    poInsertFields.add(c.getMybatisValue());
                } else {
                    poInsertFields.add("#{" + c.getField_() + "}");

                    poUpdateColumns.add(c.getField());
                    poUpdateFields.add("#{" + c.getField_() + "}");
                }
            }
        });

        cxt.setVariable("idColumn", idColumn[0]);
        cxt.setVariable("idFieldName", idFieldName[0]);
        cxt.setVariable("idField", idField[0]);
        cxt.setVariable("resultMap", resultMap);
        cxt.setVariable("tableColumnsStr", tableColumnsStr[0]);
        cxt.setVariable("poInsertColumns", poInsertColumns);
        cxt.setVariable("poInsertFields", poInsertFields);
        cxt.setVariable("poUpdateColumns", poUpdateColumns);
        cxt.setVariable("poUpdateFields", poUpdateFields);

        this.writeFile(logFile, daoPath + "/" + JAVA_PATH + mapperPackage.replace('.', '/'),
                mapper, XML_EXT_NAME,
                process(MAPPERXML_TXT_TEMPLATE_NAME, cxt));
        // end of mapper xml

        // jpa
        cxt.clearVariables();
        imports.clear();

        mappingImport(imports, "IJpa");
        mappingImport(imports, po);

        cxt.setVariable("author", workspaceAuthor);
        cxt.setVariable("date", new Date());
        cxt.setVariable("package", jpaPackage);
        cxt.setVariable("imports", imports);
        cxt.setVariable("name", jpa);

        cxt.setVariable("poName", po);
        cxt.setVariable("idType", idType);

        this.writeFile(logFile, daoPath + "/" + JAVA_PATH + jpaPackage.replace('.', '/'),
                jpa, JAVA_EXT_NAME,
                process(JPA_TXT_TEMPLATE_NAME, cxt));

        // dao
        cxt.clearVariables();
        imports.clear();

        mappingImport(imports, "IDao");

        cxt.setVariable("author", workspaceAuthor);
        cxt.setVariable("date", new Date());
        cxt.setVariable("package", daoPackage);
        cxt.setVariable("imports", imports);
        cxt.setVariable("name", dao);

        this.writeFile(logFile, daoPath + "/" + JAVA_PATH + daoPackage.replace('.', '/'),
                dao, JAVA_EXT_NAME,
                process(DAO_TXT_TEMPLATE_NAME, cxt));

        // dao impl
        cxt.clearVariables();
        imports.clear();

        mappingImport(imports, "DaoSupport");
        mappingImport(imports, po);
        mappingImport(imports, mapper);
        mappingImport(imports, jpa);
        mappingImport(imports, "IMapper");
        mappingImport(imports, "IJpa");
        mappingImport(imports, "stereotype.Service");

        cxt.setVariable("author", workspaceAuthor);
        cxt.setVariable("date", new Date());
        cxt.setVariable("package", daoImplPackage);
        cxt.setVariable("imports", imports);
        cxt.setVariable("name", daoImpl);

        cxt.setVariable("poName", po);
        cxt.setVariable("idType", idType);
        cxt.setVariable("daoName", dao);

        cxt.setVariable("mapperName", mapper);
        String mapperVeriable = mapper;
        if (mapperVeriable.startsWith("I"))
            mapperVeriable = StringUtils.substring(mapperVeriable, 1);
        cxt.setVariable("mapperVeriable", StringUtils.uncapitalize(mapperVeriable));
        cxt.setVariable("jpaName", jpa);
        String jpaVeriable = jpa;
        if (jpaVeriable.startsWith("I"))
            jpaVeriable = StringUtils.substring(jpaVeriable, 1);
        cxt.setVariable("jpaVeriable", StringUtils.uncapitalize(jpaVeriable));

        mappingImport(imports, dao);

        this.writeFile(logFile, daoPath + "/" + JAVA_PATH + daoImplPackage.replace('.', '/'),
                daoImpl, JAVA_EXT_NAME,
                process(DAOIMPL_TXT_TEMPLATE_NAME, cxt));

        // dao test
        cxt.clearVariables();
        imports.clear();

        mappingImport(imports, "test.TestCase");
        mappingImport(imports, "test.Test");
        mappingImport(imports, "test.RunWith");
        mappingImport(imports, "DTO");
        mappingImport(imports, "NPage");
        mappingImport(imports, "NSort");
        mappingImport(imports, "Autowired");
        mappingImport(imports, "test.ContextConfiguration");
        mappingImport(imports, "test.TestExecutionListeners");
        mappingImport(imports, "test.SpringJUnit4ClassRunner");
        mappingImport(imports, "test.DependencyInjectionTestExecutionListener");
        mappingImport(imports, "test.TransactionalTestExecutionListener");

        mappingImport(imports, "ArrayList");
        mappingImport(imports, "Arrays");
        mappingImport(imports, "List");

//        mappingImport(imports, dao);
        mappingImport(imports, po);

        cxt.setVariable("author", workspaceAuthor);
        cxt.setVariable("date", new Date());
        cxt.setVariable("package", daoPackage);
        cxt.setVariable("imports", imports);
        cxt.setVariable("name", daoImpl + "Test");

        cxt.setVariable("poName", po);
        cxt.setVariable("idType", idType);
        cxt.setVariable("daoName", dao);
        cxt.setVariable("daoVeriable", StringUtils.uncapitalize(daoImpl));

        mappingImport(imports, "Arrays");

        List<String> poSetters = new ArrayList<>();
        table.getColumns().forEach(c -> {
            if (c.isCm_())
                return;

            poSetters.add("// po.set" + StringUtils.capitalize(c.getField_()) + "(\"" + c.getField_() + "\");");
        });
        cxt.setVariable("poSetters", poSetters);

        this.writeFile(logFile, daoPath + "/" + TEST_PATH + daoPackage.replace('.', '/'),
                daoImpl + "Test", JAVA_EXT_NAME,
                process(DAO_TEST_TXT_TEMPLATE_NAME, cxt));
    }

    /**
     * @param logFile
     * @param table
     * @param servicePath
     * @param dtoPackage
     * @param do_
     * @param servicePackage
     * @param service
     * @param serviceImplPackage
     * @param serviceImpl
     * @param provideName
     * @param daoName
     * @param poName
     */
    private void generateService(File logFile, Table table, String servicePath, String dtoPackage, String do_,
                                 String servicePackage, String service, String serviceImplPackage, String serviceImpl,
                                 String provideName, String daoName, String poName) {

        Context cxt = new Context();
        Set<String> imports = new LinkedHashSet<>();

        // ID type
        String idType = "";
        if (table.getIdColumn() != null)
            idType = table.getIdColumn().getIdType_();

        // DO
        mappingImport(imports, poName);

        cxt.setVariable("author", workspaceAuthor);
        cxt.setVariable("date", new Date());
        cxt.setVariable("package", dtoPackage);
        cxt.setVariable("imports", imports);
        cxt.setVariable("name", do_);

        cxt.setVariable("poName", poName);

        this.writeFile(logFile, servicePath + "/" + JAVA_PATH + dtoPackage.replace('.', '/'),
                do_, JAVA_EXT_NAME,
                process(DO_TXT_TEMPLATE_NAME, cxt));
        // end of DO

        // service
        cxt.clearVariables();
        imports.clear();

        mappingImport(imports, "provideName");
        mappingImport(imports, "IService");

        cxt.setVariable("author", workspaceAuthor);
        cxt.setVariable("date", new Date());
        cxt.setVariable("package", servicePackage);
        cxt.setVariable("imports", imports);
        cxt.setVariable("name", service);

        cxt.setVariable("provideName", provideName);

        this.writeFile(logFile, servicePath + "/" + JAVA_PATH + servicePackage.replace('.', '/'),
                service, JAVA_EXT_NAME,
                process(SERVICE_TXT_TEMPLATE_NAME, cxt));
        // end of service

        // service impl
        cxt.clearVariables();
        imports.clear();

        mappingImport(imports, "stereotype.Service");
        mappingImport(imports, "IDao");
        mappingImport(imports, daoName);
        mappingImport(imports, service);
        mappingImport(imports, "ServiceSupport");
        mappingImport(imports, poName);
        mappingImport(imports, do_);

        cxt.setVariable("author", workspaceAuthor);
        cxt.setVariable("date", new Date());
        cxt.setVariable("package", serviceImplPackage);
        cxt.setVariable("imports", imports);
        cxt.setVariable("name", serviceImpl);

        cxt.setVariable("idType", idType);
        cxt.setVariable("doName", do_);
        cxt.setVariable("poName", poName);
        cxt.setVariable("serviceName", service);
        cxt.setVariable("daoName", daoName);
        String daoVeriable = daoName;
        if (daoVeriable.startsWith("I"))
            daoVeriable = StringUtils.substring(daoVeriable, 1);
        cxt.setVariable("daoVeriable", StringUtils.uncapitalize(daoVeriable));

        this.writeFile(logFile, servicePath + "/" + JAVA_PATH + serviceImplPackage.replace('.', '/'),
                serviceImpl, JAVA_EXT_NAME,
                process(SERVICEIMPL_TXT_TEMPLATE_NAME, cxt));
        // end of service impl

        // service test
        cxt.clearVariables();
        imports.clear();

        mappingImport(imports, "test.TestCase");
        mappingImport(imports, "test.Test");
        mappingImport(imports, "test.RunWith");
        mappingImport(imports, "DTO");
        mappingImport(imports, "NPage");
        mappingImport(imports, "NSort");
        mappingImport(imports, "Autowired");
        mappingImport(imports, "test.ContextConfiguration");
        mappingImport(imports, "test.TestExecutionListeners");
        mappingImport(imports, "test.SpringJUnit4ClassRunner");
        mappingImport(imports, "test.DependencyInjectionTestExecutionListener");
        mappingImport(imports, "test.TransactionalTestExecutionListener");

        mappingImport(imports, "ArrayList");
        mappingImport(imports, "Arrays");
        mappingImport(imports, "List");
        mappingImport(imports, "Optional");

//        mappingImport(imports, service);
        mappingImport(imports, do_);

        cxt.setVariable("author", workspaceAuthor);
        cxt.setVariable("date", new Date());
        cxt.setVariable("package", servicePackage);
        cxt.setVariable("imports", imports);
        cxt.setVariable("name", serviceImpl + "Test");

        cxt.setVariable("idType", idType);
        cxt.setVariable("doName", do_);
        cxt.setVariable("serviceName", service);
        cxt.setVariable("serviceVeriable", StringUtils.uncapitalize(serviceImpl));

        List<String> doSetters = new ArrayList<>();
        table.getColumns().forEach(c -> {
            if (c.isCm_())
                return;

            doSetters.add("// do_.set" + StringUtils.capitalize(c.getField_()) + "(\"" + c.getField_() + "\");");
        });
        cxt.setVariable("doSetters", doSetters);

        this.writeFile(logFile, servicePath + "/" + TEST_PATH + servicePackage.replace('.', '/'),
                serviceImpl + "Test", JAVA_EXT_NAME,
                process(SERVICETEST_TXT_TEMPLATE_NAME, cxt));
        // end of service test
    }

    /**
     * @param logFile
     * @param webPath
     * @param voPackage
     * @param vo
     * @param controllerPackage
     * @param controller
     * @param oName
     * @param doName
     */
    private void generateWeb(File logFile, Table table, String webPath, String voPackage, String vo,
                             String controllerPackage, String controller,
                             String oName, String doName) {

        Context cxt = new Context();
        Set<String> imports = new LinkedHashSet<>();

        // vo
        mappingImport(imports, doName);

        cxt.setVariable("author", workspaceAuthor);
        cxt.setVariable("date", new Date());
        cxt.setVariable("package", voPackage);
        cxt.setVariable("imports", imports);
        cxt.setVariable("name", vo);

        cxt.setVariable("doName", doName);

        this.writeFile(logFile, webPath + "/" + JAVA_PATH + voPackage.replace('.', '/'),
                vo, JAVA_EXT_NAME,
                process(VO_TXT_TEMPLATE_NAME, cxt));
        // end of vo

        // controller
        cxt.clearVariables();
        imports.clear();

        cxt.setVariable("author", workspaceAuthor);
        cxt.setVariable("date", new Date());
        cxt.setVariable("package", controllerPackage);
        cxt.setVariable("imports", imports);
        cxt.setVariable("name", controller);

        this.writeFile(logFile, webPath + "/" + JAVA_PATH + controllerPackage.replace('.', '/'),
                controller, JAVA_EXT_NAME,
                process(CONTROLLER_TXT_TEMPLATE_NAME, cxt));
        // end of controller

    }


//    /**
//     * @return
//     * @throws Exception
//     */
//    private VelocityEngine velocityEngine() {
//        Properties properties = new Properties();
//        properties.setProperty("input.encoding", "UTF-8");
//        properties.setProperty("output.encoding", "UTF-8");
//
//        properties.setProperty("resource.loader", "file");
//        properties.setProperty("file.resource.loader.cache", "true");
//        properties.setProperty("file.resource.loader.path", "classpath:/template/");
//
//        properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
//        VelocityEngine velocityEngine = new VelocityEngine(properties);
//        return velocityEngine;
//    }
//
//    /**
//     * @param templateLocation
//     * @param model
//     * @return
//     */
//    private String mergeTemplateIntoString(String templateLocation, Map<String, Object> model) {
//        VelocityEngine velocityEngine = velocityEngine();
//
//        VelocityContext context = new VelocityContext(model);
//
//        StringWriter stringWriter = new StringWriter();
//        velocityEngine.mergeTemplate(templateLocation, "UTF-8", context, stringWriter);
//
//        return stringWriter.toString();
//    }

    private String process(String template, IContext context) {
        return templateEngine().process(template, context);
    }


    public TemplateEngine templateEngine() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

        templateResolver.setTemplateMode(TemplateMode.TEXT);
        templateResolver.setPrefix("/template/");
        templateResolver.setSuffix(".txt");
        templateResolver.setCacheTTLMs(3600000L);
        templateResolver.setCacheable(false);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        return templateEngine;
    }

    /**
     * @param pPath
     * @param content
     */
    private void writeFile(File logFile, String pPath, String name, String ext, String content) {
        if (isBlank(pPath))
            return;
        if (content == null)
            content = "";

        File pathFile = new File(pPath);
        if (!pathFile.exists())
            pathFile.mkdirs();

        String fileName = pPath + "/" + name + ext;
        File file = new File(fileName);
        if (file.exists() && !overwrite)
            // 文件存在且不覆盖文件，在文件名前加"_"
            fileName = pPath + "/" + "_" + name + ext;

        // write log
        writeLog(logFile, fileName);

        FileWriter fw = null;
        try {
            fw = new FileWriter(fileName);
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
     * @param logFile
     * @param line
     */
    private void writeLog(File logFile, String line) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(logFile, true);
            fw.write(line + "\n");
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
    private boolean isBlank(String str) {
        return StringUtils.isBlank(str);
    }
}
