/*
 * Copyright 2018 nqcx.org All right reserved. This software is the
 * confidential and proprietary information of nqcx.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.generator.service.ws.impl;

import org.nqcx.doox.commons.lang.o.DTO;
import org.nqcx.generator.common.util.CgFileUtils;
import org.nqcx.generator.service.ws.ProjectService;
import org.springframework.stereotype.Service;

/**
 * @author naqichuan Feb 9, 2014 10:15:26 PM
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Override
    public DTO openFile(String wsPath, String projectPath, String path, String name) {
        DTO dto = new DTO();

        dto.setObject(CgFileUtils.getCgFile(wsPath + projectPath,
                path, name, false));

        return dto.setSuccess(true);
    }
}
