/* 
 * Copyright 2014 nqcx.org All right reserved. This software is the 
 * confidential and proprietary information of nqcx.org ("Confidential 
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.cg.service.ws.impl;

import org.nqcx.cg.common.util.CgFileUtils;
import org.nqcx.cg.service.ws.ProjectService;
import org.nqcx.commons.lang.o.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author naqichuan Feb 9, 2014 10:15:26 PM
 * 
 */
@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private String workspacePath;

	// @Autowired
	// private WsService wsService;

	@Override
	public DTO openFile(String projectPath, String path, String name) {
		DTO dto = new DTO();

		dto.setObject(CgFileUtils.getCgFile(this.workspacePath + projectPath,
				path, name, false));

		return dto.setSuccess(true);
	}
}
