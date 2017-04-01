/* 
 * Copyright 2014 nqcx.org All right reserved. This software is the 
 * confidential and proprietary information of nqcx.org ("Confidential 
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.cg.web.controller;

import static org.nqcx.cg.common.consts.CgConst.CONNECTION_KEY;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.nqcx.cg.service.conn.ConnService;
import org.nqcx.commons.lang.o.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author naqichuan Feb 7, 2014 4:04:01 PM
 * 
 */
@Controller
public class LoginController extends CgController {

	@Autowired
	private ConnService connService;

	//
	// @RequestMapping(value = "/login", method = { RequestMethod.GET,
	// RequestMethod.POST })
	// public String login() {
	// return "login";
	// }
	//
	@RequestMapping(value = "/session", method = { RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public Map<?, ?> session(HttpServletRequest request,
			@RequestParam("hostname") String hostname,
			@RequestParam("port") int port,
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("schema") String schema) {

		String connNum = connService.createConn(hostname, port, username, password, schema);

		DTO dto = new DTO();
		if (StringUtils.isNotBlank(connNum)) {
			HttpSession session = request.getSession();
			session.setAttribute(CONNECTION_KEY, connNum);
			dto.setSuccess(true);
		} else {
			dto.putResult("10", "12");
			dto.setSuccess(false);
		}

		return buildResult(dto);
	}

	@RequestMapping(value = "/destroySession", method = { RequestMethod.GET }, produces = "application/json")
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
