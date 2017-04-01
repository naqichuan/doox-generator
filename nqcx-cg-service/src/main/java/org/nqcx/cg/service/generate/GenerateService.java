/* 
 * Copyright 2014 nqcx.org All right reserved. This software is the 
 * confidential and proprietary information of nqcx.org ("Confidential 
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.cg.service.generate;

import org.nqcx.cg.entity.ws.enums.PType;
import org.nqcx.commons.lang.o.DTO;

/**
 * 
 * @author naqichuan Feb 9, 2014 2:21:48 AM
 * 
 */
public interface GenerateService {

	/**
	 * @param tableName
	 * @param pName
	 * @param pType
	 * @param pPath
	 * @param pPackage
	 * @param entityName
	 * @param entityProjectName
	 * @param entityProjectPackage
	 * @param mapperProjectName
	 * @param mapperProjectPackage
	 *            // * @param manageProjectName // * @param manageProjectPackage
	 * @param serviceProjectName
	 * @param serviceProjectPackage
	 * @param tableColumns
	 * @param entityField
	 * @param entityType
	 * @param mapperInterface
//	 * @param managerInterface
//	 * @param managerImplement
	 * @param serviceInterface
	 * @param serviceImplement
	 * @return
	 */
	public DTO generate(String tableName, String pName, PType pType, String pPath, String pPackage, String entityName,
			String entityProjectName, String entityProjectPackage,
			String mapperProjectName,
			String mapperProjectPackage,
			// String manageProjectName, String manageProjectPackage,
			String serviceProjectName, String serviceProjectPackage, String[] tableColumns, String[] entityField,
			String[] entityType, String mapperInterface, String managerInterface, String managerImplement,
			String serviceInterface, String serviceImplement);
}
