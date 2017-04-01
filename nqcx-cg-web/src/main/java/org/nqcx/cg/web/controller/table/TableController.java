/* 
 * Copyright 2014 nqcx.org All right reserved. This software is the 
 * confidential and proprietary information of nqcx.org ("Confidential 
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.cg.web.controller.table;

import java.util.Map;

import org.nqcx.cg.service.table.TableService;
import org.nqcx.cg.web.controller.CgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author naqichuan Feb 8, 2014 11:32:39 AM
 * 
 */
@Controller
@RequestMapping("/table")
public class TableController extends CgController {

	@Autowired
	private TableService tableService;

	@RequestMapping(value = "/list", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public Map<?, ?> listTable() {
		return buildResult(tableService.listTables(this.getConnNum()));
	}

	@RequestMapping(value = "/get/{table}", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public Map<?, ?> config(@PathVariable("table") String table, Model model) {
		return buildResult(tableService.getTable(this.getConnNum(), table));
	}
}
