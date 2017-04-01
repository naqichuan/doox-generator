/* 
 * Copyright 2014 nqcx.org All right reserved. This software is the 
 * confidential and proprietary information of nqcx.org ("Confidential 
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.cg.service.table;

import org.nqcx.commons.lang.o.DTO;

/**
 * 
 * @author naqichuan Feb 8, 2014 10:47:21 AM
 * 
 */
public interface TableService {

	/***
	 * 
	 * @param connNum
	 * @return
	 */
	public DTO listTables(String connNum);

	/**
	 * 
	 * @param connNum
	 * @param tableName
	 * @return
	 */
	public DTO getTable(String connNum, String tableName);
}
