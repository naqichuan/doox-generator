/*
 * Copyright 2014 nqcx.org All right reserved. This software is the
 * confidential and proprietary information of nqcx.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.cg.service.conn.impl;

import com.alibaba.druid.pool.DruidDataSource;
import org.nqcx.cg.service.conn.CgResult;
import org.nqcx.cg.service.conn.ConnService;
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
@Service("connService")
public class ConnServiceImpl implements ConnService {

    private final static Logger logger = LoggerFactory.getLogger(ConnServiceImpl.class);
//    private final Map<String, CgConn> connMap = new HashMap<String, CgConn>();

    private volatile String initString = "";
    private DruidDataSource dataSource = null;


    @Autowired
    @Qualifier("abstractDataSource")
    private DruidDataSource abstractDataSource;

    @Override
    public boolean createConn(String jdbcUrl, String user, String password) {

        String url = "jdbc:mysql://" + jdbcUrl;

        if (initString.equals(url + "_" + user) && dataSource.isInited() && dataSource.isEnable())
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
            initString = url + "_" + user;
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
    public void destroyConn(String connNum) {
        if (!dataSource.isClosed())
            dataSource.close();

//        CgConn cc = connMap.get(connNum);
//
//        try {
//            if (cc != null)
//                cc.close();
//        } catch (SQLException e) {
//            logger.error("", e);
//        }
//
//        connMap.remove(connNum);
//        cc = null;
    }


    @Override
    public CgResult query(String connNum, String sql) {

//        CgConn cgConn = this.connMap.get(connNum);
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
    public boolean checkConn(String connNum) {
        String sql = "select 1";

        CgResult result = this.query(connNum, sql);
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
