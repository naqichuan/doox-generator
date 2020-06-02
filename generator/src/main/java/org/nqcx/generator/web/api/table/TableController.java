/*
 * Copyright 2018 nqcx.org All right reserved. This software is the
 * confidential and proprietary information of nqcx.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.generator.web.api.table;

import org.nqcx.generator.service.table.TableService;
import org.nqcx.generator.web.controller.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author naqichuan Feb 8, 2014 11:32:39 AM
 */
@Controller
@RequestMapping("/generator-api/table")
public class TableController extends AbstractController {

    @Autowired
    private TableService tableService;

    @RequestMapping(value = "/list", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json")
    @ResponseBody
    public Map<?, ?> listTable() {
        return buildResult(tableService.listTables());
    }

    @RequestMapping(value = "/get/{table}", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json")
    @ResponseBody
    public Map<?, ?> config(@PathVariable("table") String table) {
        return buildResult(tableService.getTable(table));
    }
}
