/*
 * Copyright 2018 nqcx.org All right reserved. This software is the
 * confidential and proprietary information of nqcx.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.generator.service.generate;

import org.nqcx.doox.commons.lang.o.DTO;
import org.nqcx.generator.domain.dto.Generate;

/**
 * @author naqichuan Feb 9, 2014 2:21:48 AM
 */
public interface IGenerateService {

    /**
     * @param generate
     * @return
     */
    DTO generate(Generate generate);
}
