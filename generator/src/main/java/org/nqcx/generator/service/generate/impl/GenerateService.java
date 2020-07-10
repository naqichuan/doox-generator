/*
 * Copyright 2018 nqcx.org All right reserved. This software is the
 * confidential and proprietary information of nqcx.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.generator.service.generate.impl;

import org.apache.commons.lang3.StringUtils;
import org.nqcx.doox.commons.lang.enums.BoolEO;
import org.nqcx.doox.commons.lang.o.DTO;
import org.nqcx.doox.commons.util.date.DateUtils;
import org.nqcx.generator.domain.dto.CgField;
import org.nqcx.generator.domain.dto.Generate;
import org.nqcx.generator.domain.dto.table.Column;
import org.nqcx.generator.domain.dto.table.Table;
import org.nqcx.generator.service.generate.IGenerateService;
import org.nqcx.generator.service.table.ITableService;
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

import static org.nqcx.doox.commons.util.date.DateFormatUtils.TIME;

/**
 * @author naqichuan Feb 9, 2014 2:18:27 AM
 */
@Service
public class GenerateService implements IGenerateService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // database type to type and length
    private final static Pattern TYPE_LENGTH_PATTERN = Pattern.compile("(.+?)(\\((\\d+?)\\))*?");
    // Class name mapping to class reference
    private final static Map<String, String> CLASS_MAPPING = new HashMap<>();

    private final static String JAVA_PATH = "src/main/java/";
    private final static String TEST_PATH = "src/test/java/";
    private final static String JAVA_EXT_NAME = ".java";
    private final static String XML_EXT_NAME = ".xml";

    private final static String DTO_TXT_TEMPLATE_NAME = "dto.txt";
    private final static String PROVIDE_TXT_TEMPLATE_NAME = "api.txt";
    private final static String PO_TXT_TEMPLATE_NAME = "po.txt";
    private final static String MAPPER_TXT_TEMPLATE_NAME = "mapper.txt";
    private final static String MAPPERXML_TXT_TEMPLATE_NAME = "mapperxml.txt";
    private final static String JPA_TXT_TEMPLATE_NAME = "jpa.txt";
    private final static String DAO_TXT_TEMPLATE_NAME = "dao.txt";
    private final static String DAO_TEST_TXT_TEMPLATE_NAME = "daotest.txt";
    private final static String DAOIMPL_TXT_TEMPLATE_NAME = "daoimpl.txt";
    private final static String SERVICE_TXT_TEMPLATE_NAME = "service.txt";
    private final static String SERVICEIMPL_TXT_TEMPLATE_NAME = "serviceimpl.txt";
    private final static String SERVICETEST_TXT_TEMPLATE_NAME = "servicetest.txt";
    private final static String VO_TXT_TEMPLATE_NAME = "vo.txt";
    private final static String CONTROLLER_TXT_TEMPLATE_NAME = "controller.txt";

    private final static String CACHESUPPORT_TXT_TEMPLATE_NAME = "cachesupport.txt";

    private final Boolean overwrite; // 生成文件是否覆盖原来的文件
    private final ITableService tableService;

    static {
        CLASS_MAPPING.put("Serializable", "java.io.Serializable");

        CLASS_MAPPING.put("Integer", "java.lang.Integer");
        CLASS_MAPPING.put("Long", "java.lang.Long");

        CLASS_MAPPING.put("Date", "java.util.Date");
        CLASS_MAPPING.put("Arrays", "java.util.Arrays");
        CLASS_MAPPING.put("ArrayList", "java.util.ArrayList");
        CLASS_MAPPING.put("List", "java.util.List");
        CLASS_MAPPING.put("Map", "java.util.Map");
        CLASS_MAPPING.put("Optional", "java.util.Optional");

        CLASS_MAPPING.put("Entity", "javax.persistence.Entity");
        CLASS_MAPPING.put("Table", "javax.persistence.Table");
        CLASS_MAPPING.put("ID", "javax.persistence.Id");
        CLASS_MAPPING.put("GenerationType", "javax.persistence.GenerationType");
        CLASS_MAPPING.put("Column", "javax.persistence.Column");
        CLASS_MAPPING.put("GeneratedValue", "javax.persistence.GeneratedValue");
        CLASS_MAPPING.put("Temporal", "javax.persistence.Temporal");

        CLASS_MAPPING.put("DTO", "org.nqcx.doox.commons.lang.o.DTO");
        CLASS_MAPPING.put("NPage", "org.nqcx.doox.commons.lang.o.NPage");
        CLASS_MAPPING.put("NSort", "org.nqcx.doox.commons.lang.o.NSort");

        CLASS_MAPPING.put("PeriodConst", "org.nqcx.doox.commons.lang.consts.PeriodConst");
        CLASS_MAPPING.put("IMapper", "org.nqcx.doox.commons.data.mapper.IMapper");
        CLASS_MAPPING.put("DAOSupport", "org.nqcx.doox.commons.dao.DAOSupport");
        CLASS_MAPPING.put("IJpa", "org.nqcx.doox.commons.data.jpa.IJpa");
        CLASS_MAPPING.put("IDAO", "org.nqcx.doox.commons.dao.IDAO");

        CLASS_MAPPING.put("IService", "org.nqcx.doox.commons.service.IService");
        CLASS_MAPPING.put("ServiceSupport", "org.nqcx.doox.commons.service.ServiceSupport");

        CLASS_MAPPING.put("stereotype.Repository", "org.springframework.stereotype.Repository");
        CLASS_MAPPING.put("stereotype.Service", "org.springframework.stereotype.Service");
        CLASS_MAPPING.put("Autowired", "org.springframework.beans.factory.annotation.Autowired");
        CLASS_MAPPING.put("Qualifier", "org.springframework.beans.factory.annotation.Qualifier");

        CLASS_MAPPING.put("MediaType", "org.springframework.http.MediaType");
        CLASS_MAPPING.put("Controller", "org.springframework.stereotype.Controller");
        CLASS_MAPPING.put("PathVariable", "org.springframework.web.bind.annotation.PathVariable");
        CLASS_MAPPING.put("RequestMapping", "org.springframework.web.bind.annotation.RequestMapping");
        CLASS_MAPPING.put("RequestMethod", "org.springframework.web.bind.annotation.RequestMethod");
        CLASS_MAPPING.put("RequestParam", "org.springframework.web.bind.annotation.RequestParam");
        CLASS_MAPPING.put("ResponseBody", "org.springframework.web.bind.annotation.ResponseBody");
        CLASS_MAPPING.put("ModelAndView", "org.springframework.web.servlet.ModelAndView");

        CLASS_MAPPING.put("test.Test", "org.junit.Test");

        CLASS_MAPPING.put("LoggerConst", "org.nqcx.doox.commons.lang.consts.LoggerConst");
        CLASS_MAPPING.put("Logger", "org.slf4j.Logger");
        CLASS_MAPPING.put("LoggerFactory", "org.slf4j.LoggerFactory");

        CLASS_MAPPING.put("RedisTemplate", "org.springframework.data.redis.core.RedisTemplate");
    }

    @Autowired
    public GenerateService(@Qualifier("overwrite") Boolean overwrite, ITableService tableService) {
        this.overwrite = overwrite;
        this.tableService = tableService;
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

        if (!pathExist(g.getpPath()))
            return new DTO(false).putResult("101", "工程路径不存在");

        // 取表
        DTO trd = tableService.getTable(g.getTableName());
        Table table;
        if (trd == null || !trd.isSuccess() || (table = trd.getObject()) == null)
            return new DTO(false).putResult("102", "表不存在！");
        g.setTable(table);

        // 初始化
        if (!this.generateInit(g))
            return new DTO(false).putResult("100", "生成代码失败！");

        // 写入空行到日志
        this.writeLog(g.getLogFile(), "");
        this.writeLog(g.getLogFile(), TIME.format(DateUtils.date()));

        // api
        if (g.getApi_().isTrue())
            this.generateApi(g);

        // dao
        if (g.getDao_().isTrue())
            this.generateDao(g);

        // service
        if (g.getService_().isTrue()) {
            this.generateService(g);
        }

        // web
        if (g.getWeb_().isTrue())
            this.generateWeb(g);

        return new DTO(true);
    }

    private boolean generateInit(Generate g) {
        if (g == null || g.getTable() == null)
            return false;

        // 日志
        g.setLogFile(new File(g.getpPath() + "/cglog.log"));

        // module path
        this.modulePath(g);

        // 表信息
        this.fillPojo(g.getTable(), g.getPojoColumn(), g.getPojoField(), g.getPojoType());

        // idType & idName
        this.idTypeAndName(g);

        // 类信息
        this.fillClass(g);


        return true;
    }

    /**
     * @param table   table
     * @param columns columns
     * @param fields  fields
     * @param types   types
     */
    private void fillPojo(Table table, String[] columns, String[] fields, String[] types) {
        if (table == null || table.getColumns() == null
                || columns == null || table.getColumns().size() != columns.length
                || fields == null || table.getColumns().size() != fields.length
                || types == null || table.getColumns().size() != types.length)
            return;

        boolean hasId = false;
        String idFieldName = "";
        Column c;
        for (int i = 0; i < table.getColumns().size(); i++) {
            if ((c = table.getColumns().get(i)) == null
                    || !c.getField().equalsIgnoreCase(columns[i]))
                continue;

            // column type and length
            Matcher matcher = TYPE_LENGTH_PATTERN.matcher(c.getType());
            if (matcher.matches() && matcher.groupCount() == 3) {
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


            if (StringUtils.equalsAnyIgnoreCase(c.getField(), "ID"))
                idFieldName = c.getField();

            if ("PRI".equalsIgnoreCase(c.getKey()) && StringUtils.containsIgnoreCase(c.getField(), "ID")) {
                hasId = true;

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

                c.setMybatisValue("CURRENT_TIMESTAMP()");
            }
        }

        // 检查是否有 id 字段，如果没有，检查是否 field 为 id 的字段，如果有设置为 id
        if (!hasId) {
            String finalIdFieldName = idFieldName;
            table.getColumns().forEach(col -> {
                if (StringUtils.equals(finalIdFieldName, col.getField())) {
                    col.setId_(true);
                    col.setIdType_("Long");
                    col.setMybatisValue("NULL");
                }
            });
        }
    }

    /**
     * @param g g
     */
    private void idTypeAndName(Generate g) {
        if (g == null)
            return;

        g.setIdType("Long");
        g.setIdName("id");

        if (g.getTable() != null && g.getTable().getIdColumn() != null) {
            g.setIdName(g.getTable().getIdColumn().getField_());
            g.setIdType(g.getTable().getIdColumn().getIdType_());
        }
    }

    /**
     * @param g g
     */
    private void fillClass(Generate g) {
        if (g == null)
            return;

        // name & reference & veriable
        this.nrv(g);

        // class mapping
        this.classMapping(g);
    }

    /**
     * name & reference & veriable
     *
     * @param g g
     */
    private void nrv(Generate g) {
        if (g == null)
            return;

        g.setApiDTOReference(g.getApiDTOPackage() + "." + g.getApiDTO());
        g.setApiDTOVeriable(StringUtils.uncapitalize(g.getApiDTO()));
        g.setApiApiReference(g.getApiApiPackage() + "." + g.getApiApi());
        g.setApiApiVeriable(StringUtils.uncapitalize(StringUtils.substring(g.getApiApi(), 1)));

        g.setDaoPOReference(g.getDaoPOPackage() + "." + g.getDaoPO());
        g.setDaoPOVeriable(StringUtils.uncapitalize(g.getDaoPO()));
        g.setDaoMapperReference(g.getDaoMapperPackage() + "." + g.getDaoMapper());
        g.setDaoMapperVeriable(StringUtils.uncapitalize(StringUtils.substring(g.getDaoMapper(), 1)));
        g.setDaoJpaReference(g.getDaoJpaPackage() + "." + g.getDaoJpa());
        g.setDaoJpaVeriable(StringUtils.uncapitalize(StringUtils.substring(g.getDaoJpa(), 1)));
        g.setDaoDAOReference(g.getDaoDAOPackage() + "." + g.getDaoDAO());
        g.setDaoCacheSupportReference(g.getDaoCacheSupportPackage() + "." + g.getDaoCacheSupport());
        g.setDaoDAOVeriable(StringUtils.uncapitalize(StringUtils.substring(g.getDaoDAO(), 1)));
        g.setDaoDAOImplReference(g.getDaoDAOImplPackage() + "." + g.getDaoDAOImpl());

        g.setDaoBaseTestPackage(g.getpPackage() + ".dao");
        g.setDaoBaseTest("BaseDAOTest");
        g.setDaoBaseTestReference(g.getDaoBaseTestPackage() + "." + g.getDaoBaseTest());
        g.setDaoDAOTestPackage(g.getDaoDAOTestPackage());
        g.setDaoDAOTest(g.getDaoDAOTest());

        g.setServiceServiceReference(g.getServiceServicePackage() + "." + g.getServiceService());
        g.setServiceServiceVeriable(StringUtils.uncapitalize(StringUtils.substring(g.getServiceService(), 1)));
        g.setServiceServiceImplReference(g.getServiceServiceImplPackage() + "." + g.getServiceServiceImpl());

        g.setServiceBaseTestPackage(g.getpPackage() + ".service");
        g.setServiceBaseTest("BaseServiceTest");
        g.setServiceBaseTestReference(g.getServiceBaseTestPackage() + "." + g.getServiceBaseTest());
        g.setServiceServiceTestPackage(g.getServiceServiceTestPackage());
        g.setServiceServiceTest(g.getServiceServiceTest());

        g.setServiceVOReference(g.getServiceVOPackage() + "." + g.getServiceVO());
        g.setServiceVOVeriable(StringUtils.uncapitalize(g.getServiceVO()));
        g.setWebControllerReference(g.getWebControllerPackage() + "." + g.getWebController());
        g.setWebControllerVeriable(StringUtils.uncapitalize(StringUtils.substring(g.getWebController(), 1)));

        g.setWebAbstractControllerPackage(g.getpPackage() + ".web.controller");
        g.setWebAbstractController("AbstractController");
        g.setWebAbstractControllerReference(g.getWebAbstractControllerPackage() + "." + g.getWebAbstractController());
    }

    /**
     * @param g g
     */
    private void classMapping(Generate g) {
        if (g == null)
            return;

        CLASS_MAPPING.put(g.getApiDTO(), g.getApiDTOReference());
        CLASS_MAPPING.put(g.getApiApi(), g.getApiApiReference());

        CLASS_MAPPING.put(g.getDaoPO(), g.getDaoPOReference());
        CLASS_MAPPING.put(g.getDaoMapper(), g.getDaoMapperReference());
        CLASS_MAPPING.put(g.getDaoJpa(), g.getDaoJpaReference());
        CLASS_MAPPING.put(g.getDaoDAO(), g.getDaoDAOReference());
        CLASS_MAPPING.put(g.getDaoCacheSupport(), g.getDaoCacheSupportReference());
        CLASS_MAPPING.put(g.getDaoDAOImpl(), g.getDaoDAOImplReference());
        CLASS_MAPPING.put(g.getDaoBaseTest(), g.getDaoBaseTestReference());

        CLASS_MAPPING.put(g.getServiceService(), g.getServiceServiceReference());
        CLASS_MAPPING.put(g.getServiceServiceImpl(), g.getServiceServiceImplReference());
        CLASS_MAPPING.put(g.getServiceBaseTest(), g.getServiceBaseTestReference());

        CLASS_MAPPING.put(g.getServiceVO(), g.getServiceVOReference());
        CLASS_MAPPING.put(g.getWebController(), g.getWebControllerReference());

        CLASS_MAPPING.put(g.getWebAbstractController(), g.getWebAbstractControllerReference());
    }

    /**
     * @return boolean
     */
    private boolean pathExist(String path) {
        return new File(path).exists();
    }

    /**
     * @param g g
     */
    private void modulePath(Generate g) {
        String path = g.getpPath();

        g.setApiModuleFile(new File(path + g.getApiModule()));
        g.setDaoModuleFile(new File(path + g.getDaoModule()));
        g.setServiceModuleFile(new File(path + g.getServiceModule()));
        g.setWebModuleFile(new File(path + g.getWebModule()));
    }

    /**
     * @param imports   set
     * @param className string
     */
    private void mappingImport(Set<String> imports, String className) {
        if (imports == null || className == null)
            return;

        String name = CLASS_MAPPING.get(className);
        if (name != null && name.length() > 0)
            imports.add(name);
    }

    /**
     * @param cxt      cxt
     * @param imports  imports
     * @param author   author
     * @param _package package
     * @param name     name
     */
    private void baseVariable(Context cxt, Set<String> imports,
                              String author, String _package, String name) {
        cxt.clearVariables();
        imports.clear();

        cxt.setVariable("imports", imports);
        cxt.setVariable("date", new Date());
        cxt.setVariable("author", author);

        cxt.setVariable("package", _package);
        cxt.setVariable("name", name);
    }

    /**
     * @param g g
     */
    private void generateApi(Generate g) {

        Context cxt = new Context();
        Set<String> imports = new LinkedHashSet<>();

        // o
        if (g.getApiDTO_().isTrue()) {
            baseVariable(cxt, imports, g.getAuthor(), g.getApiDTOPackage(), g.getApiDTO());
            mappingImport(imports, "Serializable");

            List<String> oFieldComments = new ArrayList<>();
            List<String> oFields = new ArrayList<>();
            List<CgField> oGetterAndSetters = new ArrayList<>();

            g.getTable().getColumns().forEach(c -> {
                if (c.isCm_())
                    return;

                mappingImport(imports, c.getType_());

                if (c.getComment() != null && c.getComment().length() > 0)
                    oFieldComments.add("// " + c.getComment());
                else
                    oFieldComments.add("");

                oFields.add(String.format("private %s %s;", c.getType_(), c.getField_()));

                oGetterAndSetters.add(cgField(c));
            });

            cxt.setVariable("oFieldComments", oFieldComments);
            cxt.setVariable("oFields", oFields);
            cxt.setVariable("oGetterAndSetter", oGetterAndSetters);

            this.writeFile(g.getLogFile(),
                    package2path(g.getApiModuleFile().getPath(), JAVA_PATH, g.getApiDTOPackage()),
                    g.getApiDTO(), JAVA_EXT_NAME,
                    process(DTO_TXT_TEMPLATE_NAME, cxt));
        }

        if (g.getApiApi_().isTrue()) {
            // api
            baseVariable(cxt, imports, g.getAuthor(), g.getApiApiPackage(), g.getApiApi());

            this.writeFile(g.getLogFile(),
                    package2path(g.getApiModuleFile().getPath(), JAVA_PATH, g.getApiApiPackage()),
                    g.getApiApi(), JAVA_EXT_NAME,
                    process(PROVIDE_TXT_TEMPLATE_NAME, cxt));
        }
    }

    /**
     * @param modulePath   modulePath
     * @param resoucesPath resoucesPath
     * @param _package     _package
     * @return sting
     */
    private String package2path(String modulePath, String resoucesPath, String _package) {
        return StringUtils.trimToEmpty(modulePath)
                + "/"
                + StringUtils.trimToEmpty(resoucesPath)
                + _package.replace('.', '/');
    }

    /**
     * @param c c
     */
    private CgField cgField(Column c) {
        CgField field = new CgField();

        if (c == null)
            return field;

        field.setType(c.getType_());
        field.setField(c.getField_());
        field.setName(StringUtils.capitalize(c.getField_()));

        return field;
    }


    /**
     * @param g g
     */
    private void generateDao(Generate g) {

        Context cxt = new Context();
        Set<String> imports = new LinkedHashSet<>();

        if (g.getDaoPO_().isTrue()) {
            // po
            baseVariable(cxt, imports, g.getAuthor(), g.getDaoPOPackage(), g.getDaoPO());

            mappingImport(imports, "Entity");
            mappingImport(imports, "Table");
            mappingImport(imports, "Column");

            // po field
            List<String> poFields = new ArrayList<>();
            List<String> poFieldComments = new ArrayList<>();
            List<CgField> poGetterAndSetters = new ArrayList<>();

            g.getTable().getColumns().forEach(c -> {
                mappingImport(imports, c.getType_());

                CgField field = cgField(c);
                field.setAnnotations(new LinkedHashSet<>());

                if (c.getComment() != null && c.getComment().length() > 0)
                    poFieldComments.add("// " + c.getComment());
                else
                    poFieldComments.add("");

                // annotations
                if (c.isId_()) {
                    mappingImport(imports, "ID");
                    mappingImport(imports, "GeneratedValue");
                    mappingImport(imports, "GenerationType");

                    field.getAnnotations().add("@Id");
                    field.getAnnotations().add("@GeneratedValue(strategy = GenerationType.IDENTITY)");
                }

                String colAnno = "name = \"" + c.getField() + "\"";
                if (!c.isNull_())
                    colAnno += ", nullable = false";
//                else
//                    colAnno += ", nullable = true";

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
                }
                field.getAnnotations().add("@Column(" + colAnno + ")");

                poFields.add(String.format("private %s %s;", c.getType_(), c.getField_()));

                poGetterAndSetters.add(field);
            });
            cxt.setVariable("tableName", g.getTable().getName());

            cxt.setVariable("poFields", poFields);
            cxt.setVariable("poGetterAndSetter", poGetterAndSetters);
            cxt.setVariable("poFieldComments", poFieldComments);

            this.writeFile(g.getLogFile(),
                    package2path(g.getDaoModuleFile().getPath(), JAVA_PATH, g.getDaoPOPackage()),
                    g.getDaoPO(), JAVA_EXT_NAME,
                    process(PO_TXT_TEMPLATE_NAME, cxt));
        }

        if (g.getDaoMapper_().isTrue()) {
            // mapper
            baseVariable(cxt, imports, g.getAuthor(), g.getDaoMapperPackage(), g.getDaoMapper());

            mappingImport(imports, "IMapper");
            mappingImport(imports, g.getDaoPO());

            cxt.setVariable("poName", g.getDaoPO());
            cxt.setVariable("idType", g.getIdType());

            this.writeFile(g.getLogFile(),
                    package2path(g.getDaoModuleFile().getPath(), JAVA_PATH, g.getDaoMapperPackage()),
                    g.getDaoMapper(), JAVA_EXT_NAME,
                    process(MAPPER_TXT_TEMPLATE_NAME, cxt));
            // end of mapper

            // mapper xml
            baseVariable(cxt, imports, g.getAuthor(), g.getDaoMapperPackage(), g.getDaoMapper());

            List<String> resultMap = new ArrayList<>();
            List<String> poInsertColumns = new ArrayList<>();
            List<String> poInsertFields = new ArrayList<>();

            List<String> poUpdateColumns = new ArrayList<>();
            List<String> poUpdateFields = new ArrayList<>();

            List<String> poConditionColumns = new ArrayList<>();
            List<String> poConditionFields = new ArrayList<>();

            Column idc = g.getTable().getIdColumn();
            final String[] tableColumnsStr = {""};
            final String[] idColumn = {idc != null ? idc.getField() : null};
            final String[] idFieldName = {idc != null ? idc.getField_() : null};
            final String[] idField = {idc != null ? "#{" + idc.getField_() + "}" : null};

            g.getTable().getColumns().forEach(c -> {
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

                        poConditionColumns.add(c.getField());
                        poConditionFields.add(c.getField_());
                    }
                }
            });

            cxt.setVariable("tableName", g.getTable().getName());
            cxt.setVariable("namespace", g.getDaoMapperReference());

            cxt.setVariable("poName", g.getDaoPO());
            cxt.setVariable("idType", g.getIdType());

            cxt.setVariable("idColumn", idColumn[0]);
            cxt.setVariable("idFieldName", idFieldName[0]);
            cxt.setVariable("idField", idField[0]);
            cxt.setVariable("resultMap", resultMap);
            cxt.setVariable("tableColumnsStr", tableColumnsStr[0]);
            cxt.setVariable("poInsertColumns", poInsertColumns);
            cxt.setVariable("poInsertFields", poInsertFields);
            cxt.setVariable("poUpdateColumns", poUpdateColumns);
            cxt.setVariable("poUpdateFields", poUpdateFields);
            cxt.setVariable("poConditionColumns", poConditionColumns);
            cxt.setVariable("poConditionFields", poConditionFields);

            this.writeFile(g.getLogFile(),
                    package2path(g.getDaoModuleFile().getPath(), JAVA_PATH, g.getDaoMapperPackage()),
                    g.getDaoMapper(), XML_EXT_NAME,
                    process(MAPPERXML_TXT_TEMPLATE_NAME, cxt));
            // end of mapper xml
        }

        if (g.getDaoJpa_().isTrue()) {
            // jpa
            baseVariable(cxt, imports, g.getAuthor(), g.getDaoJpaPackage(), g.getDaoJpa());

            mappingImport(imports, "IJpa");
            mappingImport(imports, g.getDaoPO());

            cxt.setVariable("poName", g.getDaoPO());
            cxt.setVariable("idType", g.getIdType());

            this.writeFile(g.getLogFile(),
                    package2path(g.getDaoModuleFile().getPath(), JAVA_PATH, g.getDaoJpaPackage()),
                    g.getDaoJpa(), JAVA_EXT_NAME,
                    process(JPA_TXT_TEMPLATE_NAME, cxt));
        }

        if (g.getDaoDAO_().isTrue()) {
            // dao
            baseVariable(cxt, imports, g.getAuthor(), g.getDaoDAOPackage(), g.getDaoDAO());

            mappingImport(imports, "IDAO");
            mappingImport(imports, g.getDaoPO());

            cxt.setVariable("poName", g.getDaoPO());
            cxt.setVariable("idType", g.getIdType());

            this.writeFile(g.getLogFile(),
                    package2path(g.getDaoModuleFile().getPath(), JAVA_PATH, g.getDaoDAOPackage()),
                    g.getDaoDAO(), JAVA_EXT_NAME,
                    process(DAO_TXT_TEMPLATE_NAME, cxt));
        }

        if (g.getDaoCacheSupport_().isTrue()) {
            // cache support
            baseVariable(cxt, imports, g.getAuthor(), g.getDaoCacheSupportPackage(), g.getDaoCacheSupport());

            mappingImport(imports, "PeriodConst");

            cxt.setVariable("nsSchema", g.getpName().toUpperCase());

            this.writeFile(g.getLogFile(),
                    package2path(g.getDaoModuleFile().getPath(), JAVA_PATH, g.getDaoCacheSupportPackage()),
                    g.getDaoCacheSupport(), JAVA_EXT_NAME,
                    process(CACHESUPPORT_TXT_TEMPLATE_NAME, cxt));
        }

        if (g.getDaoDAOImpl_().isTrue()) {
            // dao defalt & jpa impl
            baseVariable(cxt, imports, g.getAuthor(), g.getDaoDAOImplPackage(), g.getDaoDAOImpl());

            cxt.setVariable("poName", g.getDaoPO());
            cxt.setVariable("idType", g.getIdType());
            cxt.setVariable("idName", g.getIdName());
            cxt.setVariable("idNameUpperCase", g.getIdName().toUpperCase());

            cxt.setVariable("daoName", g.getDaoDAO());
            cxt.setVariable("mapperName", g.getDaoMapper());

            cxt.setVariable("mapperVeriable", g.getDaoMapperVeriable());

            imports.clear();
            mappingImport(imports, "stereotype.Repository");
            mappingImport(imports, "Logger");
            mappingImport(imports, "LoggerFactory");
            mappingImport(imports, g.getDaoMapper());
            mappingImport(imports, g.getDaoPO());
            mappingImport(imports, g.getDaoDAO());
            mappingImport(imports, "RedisTemplate");
            mappingImport(imports, g.getDaoCacheSupport());
            mappingImport(imports, "DAOSupport");

            // 如果没有 jpa 选项，不增加 jpa 引用
            if (BoolEO.TRUE.is(g.getDaoJpa_())) {
                cxt.setVariable("jpaVeriable", g.getDaoJpaVeriable());
                cxt.setVariable("jpaName", g.getDaoJpa());
                cxt.setVariable("jpaVeriable", g.getDaoJpaVeriable());

                mappingImport(imports, g.getDaoJpa());
            }

            cxt.setVariable("name", StringUtils.capitalize(g.getDaoDAOVeriable()));
            this.writeFile(g.getLogFile(),
                    package2path(g.getDaoModuleFile().getPath(), JAVA_PATH, g.getDaoDAOImplPackage()),
                    StringUtils.capitalize(g.getDaoDAOVeriable()), JAVA_EXT_NAME,
                    process(DAOIMPL_TXT_TEMPLATE_NAME, cxt));
        }

        if (g.getDaoDAOTest_().isTrue()) {
            // dao test
            baseVariable(cxt, imports, g.getAuthor(), g.getDaoDAOTestPackage(), g.getDaoDAOTest());

            baseTestImport(imports);

            mappingImport(imports, "Qualifier");
            mappingImport(imports, g.getDaoPO());
            mappingImport(imports, g.getDaoBaseTest());
            mappingImport(imports, g.getDaoDAO());

            cxt.setVariable("daoName", g.getDaoDAO());
            cxt.setVariable("daoVeriable", g.getDaoDAOVeriable());

            cxt.setVariable("poName", g.getDaoPO());
            cxt.setVariable("idType", g.getIdType());

            this.testSetter(g, cxt);

            this.writeFile(g.getLogFile(),
                    package2path(g.getDaoModuleFile().getPath(), TEST_PATH, g.getDaoDAOTestPackage()),
                    g.getDaoDAOTest(), JAVA_EXT_NAME,
                    process(DAO_TEST_TXT_TEMPLATE_NAME, cxt));
        }
    }

    /**
     * @param imports imports
     */
    private void baseTestImport(Set<String> imports) {
        mappingImport(imports, "test.Test");
        mappingImport(imports, "DTO");
        mappingImport(imports, "NPage");
        mappingImport(imports, "NSort");
        mappingImport(imports, "Autowired");

        mappingImport(imports, "ArrayList");
        mappingImport(imports, "Arrays");
        mappingImport(imports, "List");
        mappingImport(imports, "Optional");
    }

    /**
     * @param g g
     */
    private void generateService(Generate g) {

        Context cxt = new Context();
        Set<String> imports = new LinkedHashSet<>();

        if (g.getServiceService_().isTrue()) {
            // service
            baseVariable(cxt, imports, g.getAuthor(), g.getServiceServicePackage(), g.getServiceService());

            mappingImport(imports, "IService");
            mappingImport(imports, g.getDaoPO());

            cxt.setVariable("poName", g.getDaoPO());
            cxt.setVariable("idType", g.getIdType());

            // 是否在 interface 中增加 api interface
            if (BoolEO.TRUE.is(g.getApiApi_())) {
                mappingImport(imports, g.getApiApi());
                cxt.setVariable("apiName", g.getApiApi());
            }

            this.writeFile(g.getLogFile(),
                    package2path(g.getServiceModuleFile().getPath(), JAVA_PATH, g.getServiceServicePackage()),
                    g.getServiceService(), JAVA_EXT_NAME,
                    process(SERVICE_TXT_TEMPLATE_NAME, cxt));
            // end of service
        }

        if (g.getServiceVO_().isTrue()) {
            // vo
            baseVariable(cxt, imports, g.getAuthor(), g.getServiceVOPackage(), g.getServiceVO());

            mappingImport(imports, g.getDaoPO());

            cxt.setVariable("poName", g.getDaoPO());

            this.writeFile(g.getLogFile(),
                    package2path(g.getServiceModuleFile().getPath(), JAVA_PATH, g.getServiceVOPackage()),
                    g.getServiceVO(), JAVA_EXT_NAME,
                    process(VO_TXT_TEMPLATE_NAME, cxt));
            // end of vo
        }

        if (g.getServiceServiceImpl_().isTrue()) {
            // service impl
            baseVariable(cxt, imports, g.getAuthor(), g.getServiceServiceImplPackage(), g.getServiceServiceImpl());

            mappingImport(imports, g.getDaoDAO());
            mappingImport(imports, g.getDaoPO());
            mappingImport(imports, g.getServiceService());
            mappingImport(imports, "ServiceSupport");
            mappingImport(imports, "Qualifier");
            mappingImport(imports, "stereotype.Service");

            cxt.setVariable("poName", g.getDaoPO());
            cxt.setVariable("idType", g.getIdType());
            cxt.setVariable("daoName", g.getDaoDAO());
            cxt.setVariable("serviceName", g.getServiceService());

            cxt.setVariable("daoVeriable", g.getDaoDAOVeriable());

            this.writeFile(g.getLogFile(),
                    package2path(g.getServiceModuleFile().getPath(), JAVA_PATH, g.getServiceServiceImplPackage()),
                    g.getServiceServiceImpl(), JAVA_EXT_NAME,
                    process(SERVICEIMPL_TXT_TEMPLATE_NAME, cxt));
            // end of service impl
        }

        if (g.getServiceServiceTest_().isTrue()) {
            // service test
            baseVariable(cxt, imports, g.getAuthor(), g.getServiceServiceTestPackage(), g.getServiceServiceTest());

            baseTestImport(imports);

            mappingImport(imports, g.getDaoPO());
            mappingImport(imports, g.getServiceBaseTest());

            cxt.setVariable("poName", g.getDaoPO());
            cxt.setVariable("idType", g.getIdType());
            cxt.setVariable("serviceName", g.getServiceService());
            cxt.setVariable("serviceVeriable", g.getServiceServiceVeriable());

            this.testSetter(g, cxt);

            this.writeFile(g.getLogFile(),
                    package2path(g.getServiceModuleFile().getPath(), TEST_PATH, g.getServiceServiceTestPackage()),
                    g.getServiceServiceTest(), JAVA_EXT_NAME,
                    process(SERVICETEST_TXT_TEMPLATE_NAME, cxt));
            // end of service test
        }
    }

    /**
     * @param g   g
     * @param cxt cxt
     */
    private void testSetter(Generate g, Context cxt) {
        List<String> poSetters = new ArrayList<>();
        g.getTable().getColumns().forEach(c -> {
            if (c.isCm_())
                return;

            poSetters.add("// po.set" + StringUtils.capitalize(c.getField_()) + "(\"" + c.getField_() + "\");");
        });
        cxt.setVariable("poSetters", poSetters);
    }

    /**
     * @param g g
     */
    private void generateWeb(Generate g) {

        Context cxt = new Context();
        Set<String> imports = new LinkedHashSet<>();

        if (g.getWebController_().isTrue()) {
            // controller
            baseVariable(cxt, imports, g.getAuthor(), g.getWebControllerPackage(), g.getWebController());

            mappingImport(imports, "DTO");
            mappingImport(imports, "NPage");
            mappingImport(imports, "NSort");

            mappingImport(imports, "MediaType");
            mappingImport(imports, "Controller");
            mappingImport(imports, "PathVariable");
            mappingImport(imports, "RequestMapping");
            mappingImport(imports, "RequestMethod");
            mappingImport(imports, "RequestParam");
            mappingImport(imports, "ResponseBody");
            mappingImport(imports, "ModelAndView");

            mappingImport(imports, "Map");

            mappingImport(imports, g.getServiceVO());
            mappingImport(imports, g.getServiceService());
            mappingImport(imports, g.getWebAbstractController());

            cxt.setVariable("idType", g.getIdType());
            cxt.setVariable("tableName", g.getTable().getName());
            cxt.setVariable("serviceName", g.getServiceService());
            cxt.setVariable("serviceVeriable", g.getServiceServiceVeriable());
            cxt.setVariable("serviceVO", g.getServiceVO());
            cxt.setVariable("serviceVOVeriable", g.getServiceVOVeriable());

            this.writeFile(g.getLogFile(),
                    package2path(g.getWebModuleFile().getPath(), JAVA_PATH, g.getWebControllerPackage()),
                    g.getWebController(), JAVA_EXT_NAME,
                    process(CONTROLLER_TXT_TEMPLATE_NAME, cxt));
            // end of controller
        }
    }

    private String process(String template, IContext context) {
        return templateEngine().process(template, context);
    }


    private TemplateEngine templateEngine() {
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
     * @param pPath   string
     * @param content string
     */
    private void writeFile(File logFile, String pPath, String name, String ext, String content) {
        if (isBlank(pPath))
            return;
        if (content == null)
            content = "";

        File pathFile = new File(pPath);
        if (!pathFile.exists() && !pathFile.mkdirs())
            return;

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
     * @param logFile file
     * @param line    string
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
     * @param str string
     * @return boolean
     */
    private boolean isBlank(String str) {
        return StringUtils.isBlank(str);
    }
}
