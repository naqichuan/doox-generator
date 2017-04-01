/* 
 * Copyright 2014 nqcx.org All right reserved. This software is the 
 * confidential and proprietary information of nqcx.org ("Confidential 
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.cg.web.controller.generate;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nqcx.cg.entity.ws.enums.PType;
import org.nqcx.cg.service.generate.GenerateService;
import org.nqcx.cg.service.table.TableService;
import org.nqcx.cg.service.ws.WsService;
import org.nqcx.cg.web.controller.CgController;
import org.nqcx.commons.lang.o.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author naqichuan Feb 8, 2014 9:33:03 PM
 * 
 */
@Controller
@RequestMapping("/generate")
public class GenerateController extends CgController {

	@Autowired
	private TableService tableService;
	@Autowired
	private WsService wsService;
	@Autowired
	private GenerateService generateService;

	@RequestMapping(value = "/doit", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public Map<?, ?> doit(
			HttpServletRequest request,
			@RequestParam(value = "tableName", required = true) String tableName,
			// @RequestParam(value = "wsPath", required = true) String wsPath,
			@RequestParam(value = "pName", required = true) String pName,
			@RequestParam(value = "pType", required = true) String pType,
			@RequestParam(value = "pPath", required = true) String pPath,
			@RequestParam(value = "pPackage", required = true) String pPackage,
			@RequestParam(value = "entityName", required = false) String entityName,
			@RequestParam(value = "entityProjectName", required = false, defaultValue = "") String entityProjectName,
			@RequestParam(value = "entityProjectPackage", required = false, defaultValue = "") String entityProjectPackage,
			@RequestParam(value = "mapperProjectName", required = false, defaultValue = "") String mapperProjectName,
			@RequestParam(value = "mapperProjectPackage", required = false, defaultValue = "") String mapperProjectPackage,
			// @RequestParam(value = "managerProjectName", required = false, defaultValue = "") String
			// managerProjectName,
			// @RequestParam(value = "managerProjectPackage", required = false, defaultValue = "") String
			// managerProjectPackage,
			@RequestParam(value = "serviceProjectName", required = false, defaultValue = "") String serviceProjectName,
			@RequestParam(value = "serviceProjectPackage", required = false, defaultValue = "") String serviceProjectPackage,
			String[] tableColumns, String[] entityField, String[] entityType,
			@RequestParam(value = "mapperInterface", required = false, defaultValue = "") String mapperInterface,
			@RequestParam(value = "managerInterface", required = false, defaultValue = "") String managerInterface,
			@RequestParam(value = "managerImplement", required = false, defaultValue = "") String managerImplement,
			@RequestParam(value = "serviceInterface", required = false, defaultValue = "") String serviceInterface,
			@RequestParam(value = "serviceImplement", required = false, defaultValue = "") String serviceImplement) {

		PType newPType = PType.get(Integer.parseInt(pType));

		DTO dto = generateService.generate(tableName, pName, newPType, pPath, pPackage, entityName, entityProjectName,
				entityProjectPackage, mapperProjectName,
				mapperProjectPackage,
				// managerProjectName,
				// managerProjectPackage,
				serviceProjectName, serviceProjectPackage, tableColumns, entityField, entityType, mapperInterface,
				managerInterface, managerImplement, serviceInterface, serviceImplement);

		return buildResult(dto);
	}

}
