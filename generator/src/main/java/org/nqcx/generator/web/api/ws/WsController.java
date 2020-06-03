/*
 * Copyright 2018 nqcx.org All right reserved. This software is the confidential and proprietary information
 * of nqcx.org ("Confidential Information"). You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you entered into with nqcx.org.
 */

package org.nqcx.generator.web.api.ws;

import org.nqcx.doox.commons.lang.o.DTO;
import org.nqcx.doox.commons.web.cookie.CookieUtils;
import org.nqcx.doox.commons.web.cookie.NqcxCookie;
import org.nqcx.generator.provide.o.ws.Ws;
import org.nqcx.generator.service.ws.WsService;
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
import java.util.Map;

/**
 * @author naqichuan 2018-12-17 17:07
 */
@Controller
@RequestMapping("/generator-api/ws")
public class WsController extends AbstractController {

    private final static Logger LOGGER = LoggerFactory.getLogger(WsController.class);

    @Autowired
    private WsService wsService;

    @Autowired
    @Qualifier("wsCookie")
    private NqcxCookie wsCookie;
    @Autowired
    @Qualifier("authorCookie")
    private NqcxCookie authorCookie;


    @RequestMapping(value = "/info", method = {RequestMethod.GET},
            produces = "application/json")
    @ResponseBody
    public Map<?, ?> loadWsPath(HttpServletRequest request) {
        Ws ws = null;
        String wsCookieValue = CookieUtils.getCookieValue(request, wsCookie.getName());
        if (wsCookieValue != null && wsCookieValue.length() > 0)
            ws = wsService.getWs(wsCookieValue, false);

        return buildResult(new DTO(true)
                .putResult("ws", ws));
    }

}
