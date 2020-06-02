/*
 * Copyright 2020 nqcx.org All right reserved. This software is the confidential and proprietary information
 * of nqcx.org ("Confidential Information"). You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you entered into with nqcx.org.
 */

package org.nqcx.generator.provide.o.project;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.nqcx.generator.provide.enums.PType;

import java.io.Serializable;
import java.util.List;

/**
 * @author naqichuan 2020-06-02 11:58
 */
public class Project implements Serializable {

    private String author;

    private String projectPath;
    private PType projectType;

    private String groupId;
    private String artifactId;
    private String version;

    private List<String> modules;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public PType getProjectType() {
        return projectType;
    }

    public void setProjectType(PType projectType) {
        this.projectType = projectType;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<String> getModules() {
        return modules;
    }

    public void setModules(List<String> modules) {
        this.modules = modules;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
