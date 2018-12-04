/*
 * Copyright 2018 nqcx.org All right reserved. This software is the
 * confidential and proprietary information of nqcx.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.cg.web.controller;

import org.nqcx.cg.service.ws.WsService;
import org.nqcx.commons3.web.cookie.CookieUtils;
import org.nqcx.commons3.web.cookie.NqcxCookie;
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

    @RequestMapping(value = "/", method = {RequestMethod.GET,
            RequestMethod.POST})
    public ModelAndView slash(HttpServletRequest request) {
        return index(request);
    }

    @RequestMapping(value = "/index", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("main");

        mav.addObject("ws", wsService.getWs(true));

        String jdbcUrl = "localhost:3306/nqcx";
        String user = "nqcx";
        String password = "nqcx";

        String cookieValue = CookieUtils.getCookieValue(request, jdbcCookie.getName());
        String[] vals = null;
        if (cookieValue != null && (vals = cookieValue.split(",")) != null && vals.length == 3) {
            jdbcUrl = vals[0] == null ? jdbcUrl : vals[0];
            user = vals[1] == null ? user : vals[1];
            password = vals[2] == null ? password : vals[2];
        }

        mav.addObject("jdbcUrl", jdbcUrl);
        mav.addObject("user", user);
        mav.addObject("password", password);

        return mav;
    }

    @RequestMapping(value = "/center", method = {RequestMethod.GET,
            RequestMethod.POST})
    public String config(Model model) {
        return "center";
    }
}
