/*
 * Copyright 2018 nqcx.org All right reserved. This software is the
 * confidential and proprietary information of nqcx.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.generator.service.table.impl;

import org.nqcx.doox.commons.lang.o.DTO;
import org.nqcx.generator.provide.o.table.Column;
import org.nqcx.generator.provide.o.table.Table;
import org.nqcx.generator.service.conn.CgResult;
import org.nqcx.generator.service.conn.ConnService;
import org.nqcx.generator.service.table.TableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author naqichuan Feb 8, 2014 10:48:07 AM
 */
@Service
public class TableServiceImpl implements TableService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConnService connService;

    @Override
    public DTO listTables() {
        DTO dto = new DTO();

        String sql = "SHOW TABLES";
        CgResult result = connService.query(sql);
        if (!result.isSuccess())
            return dto.putResult("10", result.getMsg());

        List<Table> list = new ArrayList<Table>();
        try {
            Table t = null;
            ResultSet rs = result.getResultSet();
            while (rs != null && rs.next()) {
                t = new Table();
                list.add(t);

                t.setName(rs.getString(1));
            }
            return dto.setSuccess(true).setList(list);
        } catch (SQLException e) {
            dto.setSuccess(false).putResult("10", e.toString());
            logger.error("", e);
        } finally {
            result.close();
        }

        return dto;
    }

    @Override
    public DTO getTable(String tableName) {
        DTO dto = new DTO();
        Table t = new Table();
        t.setName(tableName);
        t.setColumns(new ArrayList<>());

        String cSql = "SHOW FULL COLUMNS FROM " + tableName;
        CgResult result = connService.query(cSql);

        try {
            Column c = null;
            ResultSet rs = result.getResultSet();
            while (rs != null && rs.next()) {
                c = new Column();
                t.getColumns().add(c);

                c.setField(rs.getString("Field"));
                c.setType(rs.getString("Type"));
                c.setCollation(rs.getString("Collation"));
                c.setIsNull(rs.getString("Null"));
                c.setKey(rs.getString("Key"));
                c.setDefaultValue(rs.getString("Default"));
                c.setExtra(rs.getString("Extra"));
                c.setPrivileges(rs.getString("Privileges"));
                c.setComment(rs.getString("Comment"));
            }
            dto.setSuccess(true).setObject(t);
        } catch (SQLException e) {
            dto.setSuccess(false).putResult("10", e.toString());
            logger.error("", e);
        } finally {
            result.close();
        }

        return dto;
    }
}
