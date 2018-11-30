/*
 * Copyright 2014 nqcx.org All right reserved. This software is the
 * confidential and proprietary information of nqcx.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.cg.service.conn;


import java.sql.Connection;

/**
 * @author naqichuan Feb 7, 2014 10:53:49 PM
 */
public interface ConnService {

    /**
     * @param jdbcUrl
     * @param user
     * @param password
     * @return
     */
    boolean createConn(String jdbcUrl, String user, String password);

    /**
     * @return
     */
    Connection getConn();

    /**
     * @param connNum
     */
    void destroyConn(String connNum);

    /**
     * @param connNum
     * @param sql
     * @return
     */
    CgResult query(String connNum, String sql);

    /**
     * @param connNum
     * @return
     */
    boolean checkConn(String connNum);
}
