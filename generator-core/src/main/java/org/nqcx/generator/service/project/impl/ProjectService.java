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
import org.nqcx.generator.common.util.CgFileUtils;
import org.nqcx.generator.provide.o.CgFile;
import org.nqcx.generator.provide.o.project.Project;
import org.nqcx.generator.service.project.IProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;

/**
 * @author naqichuan Feb 9, 2014 10:15:26 PM
 */
@Service
public class ProjectService implements IProjectService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProjectService.class);

    @Override
    public DTO info(String basedir) {
        DTO dto = new DTO();

        Project p = new Project();

        CgFile cgPom = new CgFile(basedir, "pom.xml", true);

        return dto.setSuccess(true);
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
