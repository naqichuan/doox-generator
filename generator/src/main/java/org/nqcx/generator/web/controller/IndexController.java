/*
 * Copyright 2019 nqcx.org All right reserved. This software is the confidential and proprietary information
 * of nqcx.org ("Confidential Information"). You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you entered into with nqcx.org.
 */

package org.nqcx.generator.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

/**
 * @author naqichuan 2019-08-01 16:26
 */
@Controller
public class IndexController extends AbstractController {

    @RequestMapping(value = {"", "/", "/index"}, method = RequestMethod.GET)
    public void index(HttpServletResponse response) {
        sendRedirectNormalPage(response, "/generator-ui.html");
    }
}
