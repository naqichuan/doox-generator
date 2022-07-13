/*
 * Copyright 2018 nqcx.org All right reserved. This software is the
 * confidential and proprietary information of nqcx.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.generator.web.controller.connection;

import org.nqcx.doox.commons.lang.o.DTO;
import org.nqcx.doox.commons.web.cookie.CookieUtils;
import org.nqcx.doox.commons.web.cookie.NqcxCookie;
import org.nqcx.generator.domain.o.GenerateErrorCode;
import org.nqcx.generator.service.conn.IConnService;
import org.nqcx.generator.web.controller.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
@RequestMapping("/generator-api/connection")
public class ConnectionController extends AbstractController {

    @Autowired
    private IConnService connService;
    @Autowired
    @Qualifier("jdbcCookie")
    private NqcxCookie jdbcCookie;


    /**
     * @return map
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET,
            produces = "application/json")
    @ResponseBody
    public Map<?, ?> info(HttpServletRequest request) {
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

        return dto2map(new DTO(true)
                .putResult("jdbcUrl", jdbcUrl)
                .putResult("jdbcUser", user)
                .putResult("jdbcPassword", password)
        );
    }

    /**
     * @param response
     * @param jdbcUrl
     * @param user
     * @param password
     * @return
     */
    @RequestMapping(value = "/connect", method = {RequestMethod.POST},
            produces = "application/json")
    @ResponseBody
    public Map<?, ?> connect(HttpServletResponse response,
                             @RequestParam("jdbcUrl") String jdbcUrl,
                             @RequestParam("jdbcUser") String user,
                             @RequestParam("jdbcPassword") String password) {

        boolean success = connService.connect(jdbcUrl, user, password);

        if (!success)
            return dto2map(new DTO().putError(GenerateErrorCode.E12.buildError()));

        String cookieValue = jdbcUrl + "," + user + "," + password;
        // 记录写入 cookie
        CookieUtils.setCookie(response, jdbcCookie.getName(), cookieValue);

        return dto2map(new DTO(true));
    }

    /**
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/destroy", method = {RequestMethod.GET},
            produces = "application/json")
    @ResponseBody
    public Map<?, ?> destroy(HttpServletRequest request, HttpServletResponse response) {
        connService.destroy();

        CookieUtils.removeCookie(request, response, jdbcCookie.getName());

        return dto2map(new DTO(true));
    }
}
