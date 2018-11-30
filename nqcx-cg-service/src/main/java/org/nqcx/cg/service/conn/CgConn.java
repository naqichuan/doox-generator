/*
 * Copyright 2014 nqcx.org All right reserved. This software is the
 * confidential and proprietary information of nqcx.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.cg.service.conn;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author naqichuan Feb 7, 2014 10:57:53 PM
 */
public class CgConn {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final static String driver = "com.mysql.jdbc.Driver";

    private DruidDataSource dataSource;

    private boolean success;

    private Connection connection = null;

    private CgConn(DruidDataSource dataSource) {
        this.dataSource = dataSource;
        this.setSuccess(true);
    }

    private CgConn connection() {
        return this;
    }

    public synchronized static CgConn newInstance(DruidDataSource dataSource) {
        return new CgConn(dataSource).connection();
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            logger.error("", e);
        }

        return null;
    }

    public void close() throws SQLException {
        connection.close();
    }

    public Statement getStatement() {
        if (connection == null)
            return null;

        try {
            return connection.createStatement();
        } catch (SQLException e) {
            logger.error("", e);
        }

        return null;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
