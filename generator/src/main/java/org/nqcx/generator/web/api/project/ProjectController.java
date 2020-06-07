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

    @RequestMapping(value = "/basedir/change", method = {RequestMethod.POST},
            produces = "application/json")
    @ResponseBody
    public Map<?, ?> changePath(HttpServletResponse response,
                                @RequestParam("basedir") String basedir) {

        File p = new File(basedir);
        if (p.exists() && p.isDirectory()) {
            try {
                String cookieValue = p.getCanonicalPath();
                // 记录写入 cookie
                CookieUtils.setCookie(response, basedirCookie.getName(), cookieValue);

                return buildResult(new DTO(true).setObject(basedir));
            } catch (IOException e) {
                LOGGER.error("", e);
            }
        }

        return buildResult(new DTO(false));
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
        String basedir = null;
        String basedirCookieValue = CookieUtils.getCookieValue(request, basedirCookie.getName());
        if (basedirCookieValue != null && basedirCookieValue.length() > 0)
            basedir = basedirCookieValue;

        return buildResult(projectService.info(basedir));
    }

    /**
     * @param request  request
     * @param response response
     * @return map
     */
    @RequestMapping(value = "/author", method = {RequestMethod.POST},
            produces = "application/json")
    @ResponseBody
    public Map<?, ?> author(HttpServletRequest request, HttpServletResponse response) {

        String author = StringUtils.trimToEmpty(this.getCookieValue(request, authorCookie.getName()));
        if (author.length() == 0) {
            author = projectService.author();

            // 记录写入 cookie
            CookieUtils.setCookie(response, authorCookie.getName(), author);
        }

        return buildResult(new DTO(true).setObject(author));
    }

    /**
     * @param request
     * @param response
     * @param author
     * @return
     */
    @RequestMapping(value = "/author/change", method = {RequestMethod.POST},
            produces = "application/json")
    @ResponseBody
    public Map<?, ?> changeAuthor(HttpServletRequest request,
                                  HttpServletResponse response,
                                  @RequestParam("author") String author) {

        author = StringUtils.trimToEmpty(author);

        if (author.length() == 0) {
            CookieUtils.removeCookie(request, response, authorCookie.getName());
            author = projectService.author();
        }

        // 记录写入 cookie
        CookieUtils.setCookie(response, authorCookie.getName(), author);

        return buildResult(new DTO(true).setObject(author));
    }

    /**
     * @param basedir
     * @param path
     * @param name
     * @return
     */
    @RequestMapping(value = "/openFile", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json")
    @ResponseBody
    public Map<?, ?> openFile(
            @RequestParam(value = "basedir") String basedir,
            @RequestParam(value = "path", required = false, defaultValue = "") String path,
            @RequestParam(value = "name", required = false, defaultValue = "") String name) {
        return buildResult(projectService.openFile(basedir, path, name));
    }
}
