/*
 * Copyright 2020 nqcx.org All right reserved. This software is the confidential and proprietary information
 * of nqcx.org ("Confidential Information"). You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you entered into with nqcx.org.
 */

package org.nqcx.generator.service.project;

import org.junit.jupiter.api.Test;
import org.nqcx.generator.provide.o.project.Project;
import org.nqcx.generator.service.BaseServiceTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author naqichuan 2020-06-02 14:02
 */
public class ProjectServiceTest extends BaseServiceTest {

    @Autowired
    private IProjectService projectService;

    @Test
    public void info() {

//        projectService.info("/Users/nqcx/Works/Sources/OpenSource/doox-generator/generator-web");
        Project p = projectService.info(null).getObject();

        System.out.println(p);
    }
}
