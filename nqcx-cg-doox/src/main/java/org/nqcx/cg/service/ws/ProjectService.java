/*
 * Copyright 2018 nqcx.org All right reserved. This software is the
 * confidential and proprietary information of nqcx.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.cg.service.ws;

import org.nqcx.commons3.lang.o.DTO;

/**
 * @author naqichuan Feb 9, 2014 10:14:39 PM
 */
public interface ProjectService {

    /**
     * @param projectPath
     * @param path
     * @param name
     * @return
     * @author naqichuan Mar 2, 2014 7:33:13 PM
     */
    DTO openFile(String wsPath, String projectPath, String path, String name);
}
