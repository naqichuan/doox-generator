/*
 * Copyright 2018 nqcx.org All right reserved. This software is the
 * confidential and proprietary information of nqcx.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.generator.web.controller.project;

import org.nqcx.generator.service.ws.ProjectService;
import org.nqcx.generator.web.controller.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author naqichuan Feb 24, 2014 10:11:06 PM
 */
@Controller
@RequestMapping("/project")
public class ProjectController extends AbstractController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/openFile", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json")
    @ResponseBody
    public Map<?, ?> openFile(
            @RequestParam(value = "wsPath") String wsPath,
            @RequestParam(value = "pPath", required = false, defaultValue = "") String pPath,
            @RequestParam(value = "path", required = false, defaultValue = "") String path,
            @RequestParam(value = "name", required = false, defaultValue = "") String name) {
        return buildResult(projectService.openFile(wsPath, pPath, path, name));
    }
}
