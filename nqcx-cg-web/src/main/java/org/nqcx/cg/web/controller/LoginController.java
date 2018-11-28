/*
 * Copyright 2014 nqcx.org All right reserved. This software is the
 * confidential and proprietary information of nqcx.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.cg.web.controller;

import org.apache.commons.lang.StringUtils;
import org.nqcx.cg.service.conn.ConnService;
import org.nqcx.commons3.lang.o.DTO;
import org.nqcx.commons3.web.cookie.CookieUtils;
import org.nqcx.commons3.web.cookie.NqcxCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

import static org.nqcx.cg.common.consts.CgConst.CONNECTION_KEY;

/**
 * @author naqichuan Feb 7, 2014 4:04:01 PM
 */
@Controller
public class LoginController extends CgController {

    @Autowired
    private ConnService connService;
    @Autowired
    @Qualifier("jdbcCookie")
    private NqcxCookie jdbcCookie;

    @RequestMapping(value = "/session", method = {RequestMethod.POST}, produces = "application/json")
    @ResponseBody
    public Map<?, ?> session(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam("jdbcUrl") String jdbcUrl,
                             @RequestParam("user") String user,
                             @RequestParam("password") String password) {

        String connNum = connService.createConn(jdbcUrl, user, password);

        DTO dto = new DTO();
        if (StringUtils.isNotBlank(connNum)) {
            HttpSession session = request.getSession();
            session.setAttribute(CONNECTION_KEY, connNum);
            dto.setSuccess(true);

            String cookieValue = jdbcUrl + "," + user + "," + password;

            // 记录写入 cookie
            CookieUtils.setCookie(response, jdbcCookie.getName(), cookieValue);
        } else {
            dto.putResult("10", "12");
            dto.setSuccess(false);
        }

        return buildResult(dto);
    }

    @RequestMapping(value = "/destroySession", method = {RequestMethod.GET}, produces = "application/json")
    @ResponseBody
    public Map<?, ?> destroySession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String connNum = (String) session.getAttribute(CONNECTION_KEY);
        if (StringUtils.isNotBlank(connNum)) {
            connService.destroyConn(connNum);
            session.removeAttribute(CONNECTION_KEY);
        }

        return buildResult(new DTO(true));
    }
}
