/*
 * Copyright 2018 nqcx.org All right reserved. This software is the
 * confidential and proprietary information of nqcx.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.cg.service.generate;

import org.nqcx.cg.provide.o.Generate;
import org.nqcx.commons3.lang.o.DTO;

/**
 * @author naqichuan Feb 9, 2014 2:21:48 AM
 */
public interface GenerateService {

    /**
     * @param generate
     * @return
     */
    DTO generate(Generate generate);
}
