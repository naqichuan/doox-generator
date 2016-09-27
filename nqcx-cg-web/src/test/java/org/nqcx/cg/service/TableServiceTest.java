/* 
 * Copyright 2014 nqcx.org All right reserved. This software is the 
 * confidential and proprietary information of nqcx.org ("Confidential 
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.cg.service;

import java.util.List;

import org.junit.Test;
import org.nqcx.cg.AutoInject;
import org.nqcx.cg.entity.table.Table;
import org.nqcx.cg.service.conn.ConnService;
import org.nqcx.cg.service.table.TableService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author naqichuan Feb 8, 2014 11:02:54 AM
 * 
 */
public class TableServiceTest extends AutoInject {

	@Autowired
	private ConnService connService;
	@Autowired
	private TableService tableService;

	@Test
	public void listTables() {
		String hostname = "localhost";
		int port = 3306;
		String username = "nqcx";
		String password = "nqcx";
		String schema = "passport";

		String connNum = connService.createConn(hostname, port, username,
				password, schema);

		List<Table> list = tableService.listTables(connNum).getList();

		System.out.println("#####################################");
		for (Table t : list) {
			System.out.println(t.getName());
		}
		System.out.println("#####################################");

		connService.destroyConn(connNum);
	}
}
