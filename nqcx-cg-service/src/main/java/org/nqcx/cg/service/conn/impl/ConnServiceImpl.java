/*
 * Copyright 2014 nqcx.org All right reserved. This software is the
 * confidential and proprietary information of nqcx.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.cg.service.conn.impl;

import org.nqcx.cg.service.conn.CgConn;
import org.nqcx.cg.service.conn.CgResult;
import org.nqcx.cg.service.conn.ConnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author naqichuan Feb 7, 2014 10:58:42 PM
 */
@Service("connService")
public class ConnServiceImpl implements ConnService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Map<String, CgConn> connMap = new HashMap<String, CgConn>();

    @Override
    public String createConn(String jdbcUrl, String user, String password) {

        CgConn cc = CgConn.newInstance("jdbc:mysql://" + jdbcUrl, user, password);

        if (cc.isSuccess()) {
            String connNum = UUID.randomUUID().toString();
            this.connMap.put(connNum, cc);
            return connNum;
        }

        return null;
    }

    @Override
    public void destroyConn(String connNum) {

        CgConn cc = connMap.get(connNum);

        try {
            if (cc != null)
                cc.close();
        } catch (SQLException e) {
            logger.error("", e);
        }

        connMap.remove(connNum);
        cc = null;
    }

    @Override
    public CgResult query(String connNum, String sql) {

        CgConn cgConn = this.connMap.get(connNum);
        Statement st = null;
        CgResult result = CgResult.newResult(null);
        if (cgConn == null || (st = cgConn.getStatement()) == null) {
            return result.setStatement(st);
        }
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
            return result.getResultSet() == null ? false : result
                    .getResultSet().next();
        } catch (SQLException e) {
            logger.error("", e);
        } finally {
            result.close();
        }
        return false;
    }
}
