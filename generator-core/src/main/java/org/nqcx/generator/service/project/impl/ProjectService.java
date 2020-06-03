/*
 * Copyright 2018 nqcx.org All right reserved. This software is the
 * confidential and proprietary information of nqcx.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.generator.service.project.impl;

import com.sun.org.apache.xerces.internal.dom.DeferredElementImpl;
import org.nqcx.doox.commons.lang.o.DTO;
import org.nqcx.doox.commons.util.StringUtils;
import org.nqcx.generator.common.util.CgFileUtils;
import org.nqcx.generator.provide.enums.PType;
import org.nqcx.generator.provide.o.CgFile;
import org.nqcx.generator.provide.o.project.Project;
import org.nqcx.generator.service.project.IProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author naqichuan Feb 9, 2014 10:15:26 PM
 */
@Service
public class ProjectService implements IProjectService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProjectService.class);

    @Value("${project.author}")
    private String projectAuthor;
    @Value("${project.basedir}")
    private String projectBasedir;

    @Override
    public DTO info(String basedir, String author) {
        if (basedir == null || basedir.length() == 0) {
            basedir = projectBasedir;
        }

        if (StringUtils.isBlank(author))
            author = this.projectAuthor;

        Project p = new Project();

        p.setAuthor(author);
        p.setProjectPath(basedir);
        p.setProjectType(PType.SINGLE);

        CgFile pom = new CgFile(basedir, "pom.xml", true);
        File pomFile = new File(pom.getFullPath());
        if (pomFile.exists() && pomFile.isFile()) {
            p.setGroupId(this.groupId(pom.getFullPath()));
            p.setArtifactId(this.artifactId(pom.getFullPath()));
            p.setVersion(this.version(pom.getFullPath()));

            p.setModules(this.modules(pom.getFullPath()));

            if (p.getModules() != null && p.getModules().size() > 0)
                p.setProjectType(PType.MULTIPLE);
        }

        return new DTO(true).setObject(p);
    }

    private String groupId(String pom) {
        File pomFile = new File(pom);
        if (pomFile.exists() && pomFile.isFile()) {
            try {
                DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document document = db.parse(pomFile);

                XPathFactory factory = XPathFactory.newInstance();
                XPath xpath = factory.newXPath();
                XPathExpression expr = xpath.compile("/project/groupId");
                NodeList nodeList = (NodeList) expr.evaluate(document, XPathConstants.NODE);
                if (nodeList == null) {
                    expr = xpath.compile("/project/parent/groupId");
                    nodeList = (NodeList) expr.evaluate(document, XPathConstants.NODE);
                }

                if (nodeList instanceof DeferredElementImpl)
                    return ((DeferredElementImpl) nodeList).getTextContent();
            } catch (Exception e) {
                LOGGER.error("", e);
            }
        }

        return null;
    }

    private String artifactId(String pom) {
        File pomFile = new File(pom);
        if (pomFile.exists() && pomFile.isFile()) {
            try {
                DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document document = db.parse(pomFile);

                XPathFactory factory = XPathFactory.newInstance();
                XPath xpath = factory.newXPath();
                XPathExpression expr = xpath.compile("/project/artifactId");
                NodeList nodeList = (NodeList) expr.evaluate(document, XPathConstants.NODE);
                if (nodeList == null) {
                    expr = xpath.compile("/project/parent/artifactId");
                    nodeList = (NodeList) expr.evaluate(document, XPathConstants.NODE);
                }

                if (nodeList instanceof DeferredElementImpl)
                    return ((DeferredElementImpl) nodeList).getTextContent();
            } catch (Exception e) {
                LOGGER.error("", e);
            }
        }

        return null;
    }

    private String version(String pom) {
        File pomFile = new File(pom);
        if (pomFile.exists() && pomFile.isFile()) {
            try {
                DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document document = db.parse(pomFile);

                XPathFactory factory = XPathFactory.newInstance();
                XPath xpath = factory.newXPath();
                XPathExpression expr = xpath.compile("/project/version");
                NodeList nodeList = (NodeList) expr.evaluate(document, XPathConstants.NODE);
                if (nodeList == null) {
                    expr = xpath.compile("/project/parent/version");
                    nodeList = (NodeList) expr.evaluate(document, XPathConstants.NODE);
                }

                if (nodeList instanceof DeferredElementImpl)
                    return ((DeferredElementImpl) nodeList).getTextContent();
            } catch (Exception e) {
                LOGGER.error("", e);
            }
        }

        return null;
    }

    private List<String> modules(String pom) {
        File pomFile = new File(pom);
        if (pomFile.exists() && pomFile.isFile()) {
            try {
                DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document document = db.parse(pomFile);

                XPathFactory factory = XPathFactory.newInstance();
                XPath xpath = factory.newXPath();
                XPathExpression expr = xpath.compile("/project/modules/module");
                NodeList nodeList = (NodeList) expr.evaluate(document, XPathConstants.NODESET);

                List<String> modules = new ArrayList<>();

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element show = (Element) nodeList.item(i);
                    modules.add(show.getTextContent());
                }

                return modules;
            } catch (Exception e) {
                LOGGER.error("", e);
            }
        }

        return null;
    }

    @Override
    public DTO openFile(String wsPath, String projectPath, String path, String name) {
        DTO dto = new DTO();

        dto.setObject(CgFileUtils.getCgFile(wsPath + projectPath,
                path, name, false));

        return dto.setSuccess(true);
    }

    @Override
    public DTO groupId(String wsPath, String projectPath) {
        CgFile cgPom = new CgFile(wsPath + projectPath, "pom.xml", true);

        File pomFile = new File(cgPom.getFullPath());
        if (pomFile.exists() && pomFile.isFile()) {
            try {
                DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document document = db.parse(pomFile);

                XPathFactory factory = XPathFactory.newInstance();
                XPath xpath = factory.newXPath();
                XPathExpression expr = xpath.compile("/project/groupId");
                NodeList nodeList = (NodeList) expr.evaluate(document, XPathConstants.NODE);
                if (nodeList == null) {
                    expr = xpath.compile("/project/parent/groupId");
                    nodeList = (NodeList) expr.evaluate(document, XPathConstants.NODE);
                }

                if (nodeList instanceof DeferredElementImpl)
                    return new DTO(true).setObject(((DeferredElementImpl) nodeList).getTextContent());
            } catch (Exception e) {
                LOGGER.error("", e);
            }
        }

        return new DTO(true);
    }
}
