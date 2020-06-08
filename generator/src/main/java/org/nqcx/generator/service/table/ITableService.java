/*
 * Copyright 2018 nqcx.org All right reserved. This software is the
 * confidential and proprietary information of nqcx.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.generator.service.table;

import org.nqcx.doox.commons.lang.o.DTO;

/**
 * @author naqichuan Feb 8, 2014 10:47:21 AM
 */
public interface ITableService {

    /***
     *
     * @return
     */
    DTO listTables();

    /**
     * @param tableName
     * @return
     */
    DTO getTable(String tableName);
}
