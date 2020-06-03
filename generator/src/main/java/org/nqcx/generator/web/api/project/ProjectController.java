/*
 * Copyright 2018 nqcx.org All right reserved. This software is the
 * confidential and proprietary information of nqcx.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.generator.web.api.project;

import org.nqcx.doox.commons.web.cookie.CookieUtils;
import org.nqcx.doox.commons.web.cookie.NqcxCookie;
import org.nqcx.generator.service.project.IProjectService;
import org.nqcx.generator.web.controller.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author naqichuan Feb 24, 2014 10:11:06 PM
 */
@Controller
@RequestMapping("/generator-api/project")
public class ProjectController extends AbstractController {

    @Autowired
    private IProjectService projectService;

    @Autowired
    @Qualifier("authorCookie")
    private NqcxCookie authorCookie;
    @Autowired
    @Qualifier("projectCookie")
    private NqcxCookie projectCookie;

    @RequestMapping(value = "/info", method = RequestMethod.GET,
            produces = "application/json")
    @ResponseBody
    public Map<?, ?> info(HttpServletRequest request) {
        // 确定 project path
        String pPath = null;
        String projectCookieValue = CookieUtils.getCookieValue(request, projectCookie.getName());
        if (projectCookieValue != null && projectCookieValue.length() > 0)
            pPath = projectCookieValue;

        String author = null;
        String authorCookieValue = CookieUtils.getCookieValue(request, authorCookie.getName());
        if (authorCookieValue != null && authorCookieValue.length() > 0)
            author = authorCookieValue;

        return buildResult(projectService.info(pPath, author));
    }

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

    @RequestMapping(value = "/groupId", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json")
    @ResponseBody
    public Map<?, ?> groupId(
            @RequestParam(value = "wsPath") String wsPath,
            @RequestParam(value = "pPath", required = false, defaultValue = "") String pPath) {
        return buildResult(projectService.groupId(wsPath, pPath));
    }
}
