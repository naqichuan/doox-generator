/*
 * Copyright 2018 nqcx.org All right reserved. This software is the
 * confidential and proprietary information of nqcx.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.cg.web.controller.connection;

import org.nqcx.cg.service.conn.ConnService;
import org.nqcx.cg.web.controller.AbstractController;
import org.nqcx.commons3.lang.o.DTO;
import org.nqcx.commons3.web.cookie.CookieUtils;
import org.nqcx.commons3.web.cookie.NqcxCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author naqichuan Feb 7, 2014 4:04:01 PM
 */
@Controller
@RequestMapping("/connection")
public class ConnectionController extends AbstractController {

    @Autowired
    private ConnService connService;
    @Autowired
    @Qualifier("jdbcCookie")
    private NqcxCookie jdbcCookie;

    /**
     * @param response
     * @param jdbcUrl
     * @param user
     * @param password
     * @return
     */
    @RequestMapping(value = "/connect", method = {RequestMethod.POST},
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Map<?, ?> connect(HttpServletResponse response,
                             @RequestParam("jdbcUrl") String jdbcUrl,
                             @RequestParam("jdbcUser") String user,
                             @RequestParam("jdbcPassword") String password) {

        boolean success = connService.connect(jdbcUrl, user, password);

        if (!success)
            return buildResult(new DTO().putResult("12", "Connect fail"));

        String cookieValue = jdbcUrl + "," + user + "," + password;
        // 记录写入 cookie
        CookieUtils.setCookie(response, jdbcCookie.getName(), cookieValue);

        return buildResult(new DTO(true));
    }

    /**
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/destroy", method = {RequestMethod.GET},
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Map<?, ?> destroy(HttpServletRequest request, HttpServletResponse response) {
        connService.destroy();

        CookieUtils.removeCookie(request, response, jdbcCookie.getName());

        return buildResult(new DTO(true));
    }
}
