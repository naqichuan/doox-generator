/*
 * Copyright 2018 nqcx.org All right reserved. This software is the
 * confidential and proprietary information of nqcx.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.generator.web.controller;

import org.nqcx.doox.commons.web.cookie.CookieUtils;
import org.nqcx.doox.commons.web.cookie.NqcxCookie;
import org.nqcx.generator.service.ws.WsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author naqichuan Feb 7, 2014 3:14:16 PM
 */
@Controller
public class IndexController extends AbstractController {

    @Autowired
    private WsService wsService;
    @Autowired
    @Qualifier("jdbcCookie")
    private NqcxCookie jdbcCookie;
    @Autowired
    @Qualifier("wsCookie")
    private NqcxCookie wsCookie;
    @Autowired
    @Qualifier("authorCookie")
    private NqcxCookie authorCookie;

    @RequestMapping(value = "/", method = {RequestMethod.GET,
            RequestMethod.POST})
    public ModelAndView slash(HttpServletRequest request) {
        return index(request);
    }

    @RequestMapping(value = "/index", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("main");

        String wsCookieValue = CookieUtils.getCookieValue(request, wsCookie.getName());
        if (wsCookieValue != null && wsCookieValue.length() > 0)
            mav.addObject("ws", wsService.getWs(wsCookieValue, false));

        String author = "NaqiChuan";
        String authorCookieValue = CookieUtils.getCookieValue(request, authorCookie.getName());
        if (authorCookieValue != null && authorCookieValue.length() > 0)
            author = authorCookieValue;
        mav.addObject("author", author);

        String jdbcUrl = "localhost:3306/test";
        String user = "test";
        String password = "test";

        String jdbcCookieValue = CookieUtils.getCookieValue(request, jdbcCookie.getName());
        String[] vals = null;
        if (jdbcCookieValue != null && (vals = jdbcCookieValue.split(",")).length == 3) {
            jdbcUrl = vals[0] == null ? jdbcUrl : vals[0];
            user = vals[1] == null ? user : vals[1];
            password = vals[2] == null ? password : vals[2];
        }

        mav.addObject("jdbcUrl", jdbcUrl);
        mav.addObject("jdbcUser", user);
        mav.addObject("jdbcPassword", password);

        return mav;
    }

    @RequestMapping(value = "/center", method = {RequestMethod.GET,
            RequestMethod.POST})
    public String config(Model model) {
        return "center";
    }
}
