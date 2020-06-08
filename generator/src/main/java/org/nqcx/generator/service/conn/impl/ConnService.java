/*
 * Copyright 2018 nqcx.org All right reserved. This software is the
 * confidential and proprietary information of nqcx.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.generator.service.conn.impl;

import com.alibaba.druid.pool.DruidDataSource;
import org.nqcx.generator.service.conn.CgResult;
import org.nqcx.generator.service.conn.IConnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author naqichuan Feb 7, 2014 10:58:42 PM
 */
@Service
public class ConnService implements IConnService {

    private final static Logger logger = LoggerFactory.getLogger(ConnService.class);

    private volatile String initString = "";
    private DruidDataSource dataSource = null;

    @Autowired
    @Qualifier("abstractDataSource")
    private DruidDataSource abstractDataSource;

    @Override
    public boolean connect(String jdbcUrl, String user, String password) {

        String url = "jdbc:mysql://" + jdbcUrl;
        String newInitString = url + "_" + user + "_" + password;

        if (initString.equals(newInitString) && dataSource.isInited() && dataSource.isEnable())
            return true;

        if (dataSource != null) {
            initString = "";
            dataSource.close();
        }

        dataSource = abstractDataSource.cloneDruidDataSource();

        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);

        try {
            initString = newInitString;
            dataSource.init();
        } catch (SQLException e) {
            logger.error("", e);

            if (dataSource != null) {
                initString = "";
                dataSource.close();
            }

            return false;
        }

        return true;
    }

    @Override
    public Connection getConn() {
        try {
            return dataSource != null && !dataSource.isClosed() ? dataSource.getConnection() : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void destroy() {
        if (!dataSource.isClosed())
            dataSource.close();
    }


    @Override
    public CgResult query(String sql) {

        CgResult result = CgResult.newResult(null);
        Connection connection = null;
        Statement st = null;
        try {
            connection = getConn();
            if (connection == null)
                return result;
            st = connection.createStatement();
        } catch (SQLException e) {
            result.setMsg(e.toString());
            logger.error("", e);
        }

        if (st == null)
            return result;

        try {
            return result.setResultSet(st.executeQuery(sql)).setSuccess(true);
        } catch (SQLException e) {
            result.setMsg(e.toString());
            logger.error("", e);
        }

        return result;
    }

    @Override
    public boolean check() {
        String sql = "select 1";

        CgResult result = this.query(sql);
        try {
            return result.getResultSet() != null && result
                    .getResultSet().next();
        } catch (SQLException e) {
            logger.error("", e);
        } finally {
            result.close();
        }
        return false;
    }
}
