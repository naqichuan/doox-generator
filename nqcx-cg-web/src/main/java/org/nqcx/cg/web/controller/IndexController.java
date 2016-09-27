/* 
 * Copyright 2014 nqcx.org All right reserved. This software is the 
 * confidential and proprietary information of nqcx.org ("Confidential 
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.cg.web.controller;

import org.nqcx.cg.service.ws.WsService;
import org.nqcx.commons.web.WebSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author naqichuan Feb 7, 2014 3:14:16 PM
 * 
 */
@Controller
public class IndexController extends WebSupport {

	@Autowired
	private WsService wsService;

	@RequestMapping(value = "/", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String slash(Model model) {
		return index(model);
	}

	@RequestMapping(value = "/index", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String index(Model model) {
		model.addAttribute("ws", wsService.getWs(true));
		return "main";
	}

	@RequestMapping(value = "/center", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String config(Model model) {
		return "center";
	}
}
