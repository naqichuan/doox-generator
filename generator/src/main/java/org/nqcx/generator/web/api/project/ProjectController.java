/*
 * Copyright 2018 nqcx.org All right reserved. This software is the
 * confidential and proprietary information of nqcx.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.generator.web.api.project;

import org.apache.commons.lang3.StringUtils;
import org.nqcx.doox.commons.lang.o.DTO;
import org.nqcx.doox.commons.web.cookie.CookieUtils;
import org.nqcx.doox.commons.web.cookie.NqcxCookie;
import org.nqcx.generator.service.project.IProjectService;
import org.nqcx.generator.web.controller.AbstractController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @author naqichuan Feb 24, 2014 10:11:06 PM
 */
@Controller
@RequestMapping("/generator-api/project")
public class ProjectController extends AbstractController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private IProjectService projectService;

    @Autowired
    @Qualifier("authorCookie")
    private NqcxCookie authorCookie;
    @Autowired
    @Qualifier("basedirCookie")
    private NqcxCookie basedirCookie;

    /**
     * @param request
     * @param name
     * @return
     */
    private String getCookieValue(HttpServletRequest request, String name) {
        return CookieUtils.getCookieValue(request, name);
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "/basedir", method = RequestMethod.GET,
            produces = "application/json")
    @ResponseBody
    public Map<?, ?> basedir(HttpServletRequest request,
                             @RequestParam(value = "basedir", required = false) String basedir) {

        if (StringUtils.isBlank(basedir))
            basedir = getCookieValue(request, basedirCookie.getName());

        return buildResult(projectService.basedir(basedir));
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET,
            produces = "application/json")
    @ResponseBody
    public Map<?, ?> info(HttpServletRequest request) {
        // 确定 project path
        String pPath = null;
        String basedirCookieValue = CookieUtils.getCookieValue(request, basedirCookie.getName());
        if (basedirCookieValue != null && basedirCookieValue.length() > 0)
            pPath = basedirCookieValue;

        String author = null;
        String authorCookieValue = CookieUtils.getCookieValue(request, authorCookie.getName());
        if (authorCookieValue != null && authorCookieValue.length() > 0)
            author = authorCookieValue;

        return buildResult(projectService.info(pPath, author));
    }

    /**
     * @param wsPath
     * @param pPath
     * @param path
     * @param name
     * @return
     */
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

    @RequestMapping(value = "/author/save", method = {RequestMethod.POST},
            produces = "application/json")
    @ResponseBody
    public Map<?, ?> author(HttpServletResponse response,
                            @RequestParam("author") String author) {

        if (author != null && author.trim().length() > 0) {
            String cookieValue = (author = author.trim());
            // 记录写入 cookie
            CookieUtils.setCookie(response, authorCookie.getName(), cookieValue);
        }

        return buildResult(new DTO(true).setObject(author));
    }

    @RequestMapping(value = "/path/save", method = {RequestMethod.POST},
            produces = "application/json")
    @ResponseBody
    public Map<?, ?> savePath(HttpServletResponse response,
                              @RequestParam("path") String path) {

        File p = new File(path);
        if (p.exists() && p.isDirectory()) {
            try {
                String cookieValue = p.getCanonicalPath();
                // 记录写入 cookie
                CookieUtils.setCookie(response, basedirCookie.getName(), cookieValue);

                return buildResult(new DTO(true).setObject(path));
            } catch (IOException e) {
                LOGGER.error("", e);
            }
        }

        return buildResult(new DTO(false));
    }
}
